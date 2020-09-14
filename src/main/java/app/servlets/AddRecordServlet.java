package app.servlets;

import app.dbService.DBException;
import app.dbService.DBService;
import app.dbService.dataSets.Book;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import org.apache.commons.codec.binary.Base64;
import java.util.Iterator;
import java.util.List;

public class AddRecordServlet extends HttpServlet {
    private DBService service = new DBService();
    private Book book;
    private String UPLOAD_DIRECTORY;
    private File file;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
        book = new Book();
        UPLOAD_DIRECTORY ="D:\\Book Library. ItechArt\\src\\main\\webapp\\images";
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
        String action = req.getParameter("action");

        String filename = "";
        String title = "";
        String publisher = "";
        String author = "";
        String genre = "";
        String date = "";
        String description = "";
        long isbn = 0;
        int pages = 0;
        int total_amount = 0;
        String st_1 = "Available";
        String st_2 = "Unavailable";

        if ("submit".equals(action)) {
            boolean isMultipart = ServletFileUpload.isMultipartContent(req);
            if (!isMultipart) {
            }
            else {
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                List items = null;
                try {
                    items = upload.parseRequest(new ServletRequestContext(req));
                }
                catch (FileUploadException e) {
                    e.printStackTrace();
                }
                Iterator itr = items.iterator();
                while (itr.hasNext())
                {
                    FileItem item = (FileItem) itr.next();
                    if (item.isFormField())
                    {
                        String name = item.getFieldName();
                        String value = item.getString();
                        if(name.equals("title"))
                        {
                            title=value;
                        }
                        if(name.equals("author"))
                        {
                            author=value;
                        }
                        if(name.equals("genre"))
                        {
                            genre=value;
                        }
                        if(name.equals("publisher"))
                        {
                            publisher=value;
                        }
                        if(name.equals("date"))
                        {
                            date=value;
                        }
                        if(name.equals("description"))
                        {
                            description=value;
                        }
                        if(name.equals("pages"))
                        {
                            pages=Integer.parseInt(value);
                        }
                        if(name.equals("isbn"))
                        {
                            isbn=Long.parseLong(value);
                        }
                        if(name.equals("total_amount"))
                        {
                            total_amount=Integer.parseInt(value);
                        }
                    }
                    else
                    {
                        int id = 1;
                        filename = new File(item.getName()).getName();
                        int i = filename.lastIndexOf('.');
                        if (i != -1) {
                            String filename2 = filename.substring(0, i) + id;
                            id++;
                            String ext = filename.substring(i, filename.length());
                            filename = filename2 + ext;
                        }
                        try {
                            file = new File(UPLOAD_DIRECTORY + File.separator + filename);
                            item.write(file);
                            byte[] bArray = readFileToByteArray(file);
                            String base64Image = Base64.encodeBase64String(bArray);
                            book.setCover64(base64Image);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            }

            book.setTitle(title);
            book.setAuthor(author);
            book.setGenre(genre);
            book.setPublisher(publisher);
            book.setDate(date);
            book.setPages(pages);
            book.setIsbn(isbn);
            book.setDescription(description);
            book.setAmount(total_amount);
            book.setStatus(st_1);
        }

        insertBook(book);

        req.setAttribute("book", book);
        req.getRequestDispatcher("views/full_book_page.jsp").forward(req, resp);
    }

    private void insertBook(Book book) {
        try {
            service.addBook(book);
        }catch (DBException e){
            System.out.println("Cannot add book in the database");
        }
    }

    private static byte[] readFileToByteArray(File file){
        FileInputStream fis = null;
        byte[] bArray = new byte[(int) file.length()];
        try{
            fis = new FileInputStream(file);
            fis.read(bArray);
            fis.close();
        }catch(IOException ioExp){
            ioExp.printStackTrace();
        }
        return bArray;
    }
}