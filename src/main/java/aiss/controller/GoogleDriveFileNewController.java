package aiss.controller;

import aiss.model.google.drive.FileItem;
import aiss.model.resource.GoogleDriveResource;
import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GoogleDriveFileNewController extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(GoogleDriveFileNewController.class.getName());

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String accessToken = (String) req.getSession().getAttribute("GoogleDrive-token");
        String title = req.getParameter("title");
        String content = req.getParameter("recipe");
        if (accessToken != null && !"".equals(accessToken)) {
            if (title != null && !"".equals(title)) {
                GoogleDriveResource gdResource = new GoogleDriveResource(accessToken);
                FileItem file = new FileItem();
                file.setTitle(title);
                file.setMimeType("text/plain");
                gdResource.insertFile(file, content);
                req.setAttribute("message", "File '" + title + "' added to your Drive!");
                req.getRequestDispatcher("/successUpload.html").forward(req, resp);
            } else {
                req.setAttribute("message", "You must provide a valid title for file");
                req.setAttribute("recipe", content);
                req.getRequestDispatcher("/successUpload.html").forward(req, resp);
            }
        } else {
            log.info("Trying to access Google Drive without an access token, redirecting to OAuth servlet");
            req.getRequestDispatcher("/AuthController/GoogleDrive").forward(req, resp);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        doGet(req, resp);
    }
}
