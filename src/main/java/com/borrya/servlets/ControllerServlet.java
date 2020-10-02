package com.borrya.servlets;

import com.borrya.dbService.DBException;
import com.borrya.dbService.PostgresBookService;
import com.borrya.dbService.entity.Book;
import org.apache.commons.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class ControllerServlet extends HttpServlet {
    private final static Logger log = LoggerFactory.getLogger(PostgresBookService.class);
    private static List<Book> listBook = new ArrayList<>();
    private final PostgresBookService service = new PostgresBookService();
    private String UPLOAD_DIRECTORY;

    private static byte[] readFileToByteArray(File file) {
        byte[] bArray = new byte[(int) file.length()];
        try (FileInputStream fis = new FileInputStream(file)) {
            fis.read(bArray);
        } catch (IOException ioExp) {
            log.error("Couldn't read file to byte array.");
        }
        return bArray;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
        //book = new Book();
        UPLOAD_DIRECTORY = "D:\\Book Library. ItechArt\\src\\main\\webapp\\images";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        Book book = new Book();
        req.setAttribute("book", book);
        switch (action) {
            case "/edit":
                req.getRequestDispatcher("views/full_book_page.jsp").forward(req, resp);
                break;
            case "/add":
                req.getRequestDispatcher("views/book_page.jsp").forward(req, resp);
                break;
            case "/remove":
            default:
                req.getRequestDispatcher("index.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getServletPath();
        String filename;
        String st_1 = "Available";
        String st_2 = "Unavailable";
        int id = 0;
        int id1 = 1;

        try {
            switch (action) {
                case "/add":
                    Book book = new Book();
                    boolean isMultipart = ServletFileUpload.isMultipartContent(req);
                    if (isMultipart) {
                        FileItemFactory factory = new DiskFileItemFactory();
                        ServletFileUpload upload = new ServletFileUpload(factory);
                        List items = null;
                        try {
                            items = upload.parseRequest(new ServletRequestContext(req));
                        } catch (FileUploadException e) {
                            log.error("Couldn't parse request.", e);
                        }
                        Iterator itr = items.iterator();
                        while (itr.hasNext()) {
                            FileItem item = (FileItem) itr.next();
                            if (item.isFormField()) {
                                String name = item.getFieldName();
                                String value = item.getString();
                                switch (name) {
                                    case "id":
                                        book.setId(Integer.parseInt(value));
                                        break;
                                    case "title":
                                        book.setTitle(value);
                                        break;
                                    case "author":
                                        book.setAuthor(value);
                                        break;
                                    case "genre":
                                        book.setGenre(value);
                                        break;
                                    case "publisher":
                                        book.setPublisher(value);
                                        break;
                                    case "date":
                                        book.setDate(value);
                                        break;
                                    case "description":
                                        book.setDescription(value);
                                        break;
                                    case "pages":
                                        book.setPages(Integer.parseInt(value));
                                        break;
                                    case "isbn":
                                        book.setIsbn(Long.parseLong(value));
                                        break;
                                    case "total_amount":
                                        book.setAmount(Integer.parseInt(value));
                                        break;
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
                                    log.error("Couldn't upload file.", e);
                                }
                            }
                        }
                    }
                    book.setStatus(st_1);

                    insertBook(req, resp, book);
                    //listBook(req, resp);
                    break;
                case "/remove":
                    deleteBook(req, resp);
                    break;
                case "/edit":
                    showEditForm(req, resp);
                    break;
                case "/update":
                    updateBook(req, resp);
                    break;
                case "/list":
                default:
                    listBook(req, resp);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        } catch (DBException e) {
            log.error("Something went wrong.", e);
        }
    }

    private void insertBook(HttpServletRequest req, HttpServletResponse resp, Book book) throws SQLException, IOException, ServletException {
        try {
            service.addBook(book);
            //req.setAttribute("book", book);
            listBook.add(book);
            req.setAttribute("listBook", listBook);
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        } catch (DBException e) {
            req.setAttribute("exception", e);
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }

    private void listBook(HttpServletRequest request, HttpServletResponse response)
            throws DBException, IOException, ServletException {
        if (!service.listBooks().isEmpty()) {
            request.setAttribute("listBook", listBook);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            request.setAttribute("exception", new DBException(new NullPointerException()));
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    private void deleteBook(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));

        Book book = new Book(id);
        try {
            service.deleteBook(book);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (DBException e) {
            request.setAttribute("exception", new DBException(new NullPointerException()));
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Optional<Book> existingBook = null;

        try {
            if (existingBook.isPresent()) {
                existingBook = service.getBook(id);
                RequestDispatcher dispatcher = request.getRequestDispatcher("views/full_book_page.jsp");
                request.setAttribute("book", existingBook.get());
                dispatcher.forward(request, response);
            } else {
                request.setAttribute("exception", new DBException(new NullPointerException()));
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } catch (DBException e) {
            log.error("Something went wrong in book editing.", e);
        }
    }

    private void updateBook(HttpServletRequest request, HttpServletResponse response)
            throws IOException, DBException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String genre = request.getParameter("genre");
        String publisher = request.getParameter("publisher");
        String date = request.getParameter("date");
        long isbn = Long.parseLong(request.getParameter("isbn"));
        int pages = Integer.parseInt(request.getParameter("pages"));
        String description = request.getParameter("description");
        int amount = Integer.parseInt(request.getParameter("total_amount"));

        Book book = new Book(id, title, author, genre, publisher, date, isbn, pages, description, amount);
        service.updateBook(book);
        response.sendRedirect("index.jsp");
    }
}
