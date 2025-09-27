package tuan02.vd2.Controller;

import tuan02.vd2.dao.Userdao;
import tuan02.vd2.dao.impl.UserdaoImpl;
import tuan02.vd2.model.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.File;
import java.io.IOException;

@WebServlet(urlPatterns = {"/admin/account/profile"})
@MultipartConfig
public class AccountController extends HttpServlet {
    private static final String UPLOAD_DIR = "uploads";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/views/account-profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        user account = (user) session.getAttribute("account");

        String fullname = req.getParameter("fullname");
        String phone = req.getParameter("phone");

        // Upload ảnh
        Part filePart = req.getPart("avatar");
        String fileName = extractFileName(filePart);

        if (fileName != null && !fileName.isEmpty()) {
            String savePath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;
            File fileSaveDir = new File(savePath);
            if (!fileSaveDir.exists()) fileSaveDir.mkdirs();

            filePart.write(savePath + File.separator + fileName);
            account.setAvatar(UPLOAD_DIR + "/" + fileName);
        }

        // Cập nhật thông tin
        account.setFullName(fullname);
        account.setPhone(phone);

        Userdao userDao = new UserdaoImpl();
        userDao.update(account);

        // Cập nhật lại session
        session.setAttribute("account", account);

        resp.sendRedirect(req.getContextPath() + "/admin/category/list");
    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        for (String cd : contentDisp.split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf("=") + 2, cd.length() - 1);
            }
        }
        return null;
    }
}
