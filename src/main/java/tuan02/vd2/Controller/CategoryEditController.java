package tuan02.vd2.Controller;

import tuan02.vd2.service.CategoryService;
import tuan02.vd2.service.impl.CategoryServiceImpl;
import tuan02.vd2.model.Category;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@WebServlet("/admin/category/edit")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024,   // 1MB
    maxFileSize = 1024 * 1024 * 10,    // 10MB
    maxRequestSize = 1024 * 1024 * 50  // 50MB
)
public class CategoryEditController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String idParam = req.getParameter("id");

        try {
            if (idParam == null || idParam.trim().isEmpty()) {
                req.setAttribute("error", "ID danh mục không được để trống!");
                req.getRequestDispatcher("/views/edit-category.jsp").forward(req, resp);
                return;
            }

            int categoryId = Integer.parseInt(idParam.trim());
            if (categoryId <= 0) {
                req.setAttribute("error", "ID danh mục phải lớn hơn 0!");
                req.getRequestDispatcher("/views/edit-category.jsp").forward(req, resp);
                return;
            }

            Category category = categoryService.findById(categoryId);
            if (category == null) {
                req.setAttribute("error", "Không tìm thấy danh mục với ID: " + categoryId);
                req.getRequestDispatcher("/views/edit-category.jsp").forward(req, resp);
                return;
            }

            req.setAttribute("category", category);
            req.getRequestDispatcher("/views/edit-category.jsp").forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Có lỗi xảy ra khi tải thông tin danh mục: " + e.getMessage());
            req.getRequestDispatcher("/views/edit-category.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String idParam = req.getParameter("id");
        String name = req.getParameter("name");

        try {
            if (idParam == null || idParam.trim().isEmpty()) {
                req.setAttribute("error", "ID danh mục không được để trống!");
                req.getRequestDispatcher("/views/edit-category.jsp").forward(req, resp);
                return;
            }

            int categoryId = Integer.parseInt(idParam.trim());
            if (categoryId <= 0) {
                req.setAttribute("error", "ID danh mục phải lớn hơn 0!");
                req.getRequestDispatcher("/views/edit-category.jsp").forward(req, resp);
                return;
            }

            if (name == null || name.trim().isEmpty()) {
                Category category = categoryService.findById(categoryId);
                req.setAttribute("category", category);
                req.setAttribute("error", "Tên danh mục không được để trống!");
                req.getRequestDispatcher("/views/edit-category.jsp").forward(req, resp);
                return;
            }

            name = name.trim();
            if (name.length() > 100) {
                Category category = categoryService.findById(categoryId);
                req.setAttribute("category", category);
                req.setAttribute("error", "Tên danh mục không được quá 100 ký tự!");
                req.getRequestDispatcher("/views/edit-category.jsp").forward(req, resp);
                return;
            }

            Category existingCategory = categoryService.findById(categoryId);
            if (existingCategory == null) {
                req.setAttribute("error", "Không tìm thấy danh mục với ID: " + categoryId);
                req.getRequestDispatcher("/views/edit-category.jsp").forward(req, resp);
                return;
            }

            // Upload file ảnh (nếu có)
            Part filePart = req.getPart("icon");
            String imagePath = existingCategory.getIcon(); // giữ ảnh cũ nếu không chọn ảnh mới

            if (filePart != null && filePart.getSize() > 0) {
                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                String uploadDir = getServletContext().getRealPath("/uploads");

                File uploadDirFile = new File(uploadDir);
                if (!uploadDirFile.exists()) uploadDirFile.mkdirs();

                String newFileName = System.currentTimeMillis() + "_" + fileName;
                String fullPath = uploadDir + File.separator + newFileName;

                filePart.write(fullPath);
                imagePath = "uploads/" + newFileName; // lưu path vào DB
            }

            Category category = new Category();
            category.setCateid(categoryId);
            category.setCatename(name);
            category.setIcon(imagePath);

            boolean updateResult = categoryService.update(category);

            if (updateResult) {
                req.getSession().setAttribute("success", "Cập nhật thành công danh mục: " + name);
                resp.sendRedirect(req.getContextPath() + "/admin/category/list");
            } else {
                req.setAttribute("category", category);
                req.setAttribute("error", "Không thể cập nhật danh mục. Vui lòng thử lại!");
                req.getRequestDispatcher("/views/edit-category.jsp").forward(req, resp);
            }

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Có lỗi xảy ra khi cập nhật danh mục: " + e.getMessage());
            req.getRequestDispatcher("/views/edit-category.jsp").forward(req, resp);
        }
    }
}