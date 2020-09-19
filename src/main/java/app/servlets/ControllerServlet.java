package app.servlets;

import app.dbService.DBException;
import app.dbService.PostgresBookService;
import app.dbService.dao.BookDao;
import app.dbService.entity.Book;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

import org.apache.commons.codec.binary.Base64;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class ControllerServlet extends HttpServlet {
    private BookDao bookDao;
    private final PostgresBookService service = new PostgresBookService();
    private Book book;
    private String UPLOAD_DIRECTORY;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
        book = new Book();
        UPLOAD_DIRECTORY = "D:\\Book Library. ItechArt\\src\\main\\webapp\\images";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        req.setAttribute("book", book);
        switch (action == null ? "info" : action) {
            case "update":
                req.getRequestDispatcher("views/full_book_page.jsp").forward(req, resp);
                break;
            case "info":
            default:
                req.getRequestDispatcher("views/book_page.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        //String action = req.getParameter("action");
        String action = req.getServletPath();
        String filename;
        String st_1 = "Available";
        String st_2 = "Unavailable";
        int id = 1;
        int id1 = 1;

        try {
            switch (action) {
                case "/add":
                    boolean isMultipart = ServletFileUpload.isMultipartContent(req);
                    if (isMultipart) {
                        FileItemFactory factory = new DiskFileItemFactory();
                        ServletFileUpload upload = new ServletFileUpload(factory);
                        List items = null;
                        try {
                            items = upload.parseRequest(new ServletRequestContext(req));
                        } catch (FileUploadException e) {
                            e.printStackTrace();
                        }
                        Iterator itr = items.iterator();
                        while (itr.hasNext()) {
                            FileItem item = (FileItem) itr.next();
                            if (item.isFormField()) {
                                String name = item.getFieldName();
                                String value = item.getString();
                                if (name.equals("title")) {
                                    book.setTitle(value);
                                }
                                if (name.equals("author")) {
                                    book.setAuthor(value);
                                }
                                if (name.equals("genre")) {
                                    book.setGenre(value);
                                }
                                if (name.equals("publisher")) {
                                    book.setPublisher(value);
                                }
                                if (name.equals("date")) {
                                    book.setDate(value);
                                }
                                if (name.equals("description")) {
                                    book.setDescription(value);
                                }
                                if (name.equals("pages")) {
                                    book.setPages(Integer.parseInt(value));
                                }
                                if (name.equals("isbn")) {
                                    book.setIsbn(Long.parseLong(value));
                                }
                                if (name.equals("total_amount")) {
                                    book.setAmount(Integer.parseInt(value));
                                }
                            } else {
                                filename = new File(item.getName()).getName();
                                int i = filename.lastIndexOf('.');
                                if (i != -1) {
                                    String filename2 = filename.substring(0, i) + id1;
                                    id1++;
                                    String ext = filename.substring(i);
                                    filename = filename2 + ext;
                                }
                                try {
                                    File file = new File(UPLOAD_DIRECTORY + File.separator + filename);
                                    item.write(file);
                                    byte[] bArray = readFileToByteArray(file);
                                    String base64Image = Base64.encodeBase64String(bArray);
                                    book.setCover64(base64Image);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                    book.setId(id);
                    book.setStatus(st_1);

                    insertBook(req, resp, book);
                    break;
                case "/remove":
                    deleteBook(req, resp);
                    break;
                case "/edit":
                    showEditForm(req, resp);
                    break;
                default:
                    listBook(req, resp);
                    break;
            }

        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void insertBook(HttpServletRequest req, HttpServletResponse resp, Book book) throws SQLException, IOException, ServletException {
        try {
            service.addBook(book);
            req.setAttribute("book", book);
            req.getRequestDispatcher("views/full_book_page.jsp").forward(req, resp);
        } catch (DBException e) {
            System.out.println("Cannot add book in the database");
        }
    }

    private static byte[] readFileToByteArray(File file) {
        byte[] bArray = new byte[(int) file.length()];
        try (FileInputStream fis = new FileInputStream(file)) {
            fis.read(bArray);
        } catch (IOException ioExp) {
            ioExp.printStackTrace();
        }
        return bArray;
    }

    private void listBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Book> listBook = bookDao.listAllBooks();
        request.setAttribute("listBook", listBook);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.html");
        dispatcher.forward(request, response);
    }

    private void deleteBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        Book book = new Book(id);
        bookDao.deleteBook(book);
        response.sendRedirect("index.html");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Book existingBook = bookDao.getBook(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("book_page.jsp");
        request.setAttribute("book", existingBook);
        dispatcher.forward(request, response);
    }
}