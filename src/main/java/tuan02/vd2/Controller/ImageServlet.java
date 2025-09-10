package tuan02.vd2.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tuan02.vd2.util.Constant;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/image")
public class ImageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String fname = req.getParameter("fname");
        if (fname == null || fname.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "File name is missing");
            return;
        }

        // Lấy file từ thư mục upload đã config
        String uploadPath = Constant.DIR;
        File file = new File(uploadPath, fname);

        if (!file.exists()) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found: " + fname);
            return;
        }

        // Xác định loại file (jpg, png, gif, webp…)
        String mimeType = getServletContext().getMimeType(file.getName());
        if (mimeType == null || !mimeType.startsWith("image")) {
            mimeType = "image/*"; // fallback cho mọi ảnh
        }
        resp.setContentType(mimeType);
        resp.setContentLengthLong(file.length());

        // Gửi dữ liệu ảnh về client
        try (FileInputStream fis = new FileInputStream(file);
             OutputStream os = resp.getOutputStream()) {
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
        }
    }
}
