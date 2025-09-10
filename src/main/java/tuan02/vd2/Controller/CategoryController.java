package tuan02.vd2.Controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import tuan02.vd2.model.Category;
import tuan02.vd2.service.CategoryService;
import tuan02.vd2.service.impl.CategoryServiceImpl;

@WebServlet(urlPatterns = {"/admin/category/list"})
public class CategoryController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CategoryService cateService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        // Set encoding
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        
        try {
            List<Category> categories = cateService.findAll();
            
            // Debug log
            System.out.println("Found " + (categories != null ? categories.size() : 0) + " categories");
            
            req.setAttribute("categories", categories);
            req.getRequestDispatcher("/views/list-category.jsp").forward(req, resp);
            
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Có lỗi xảy ra khi tải danh sách danh mục: " + e.getMessage());
            req.getRequestDispatcher("/views/list-category.jsp").forward(req, resp);
        }
    }
}