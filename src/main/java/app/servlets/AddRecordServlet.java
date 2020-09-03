package app.servlets;

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
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

public class AddRecordServlet extends HttpServlet {
    private Book book;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
        book = new Book();
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

        String title = "";
        String publisher = "";
        String author = "";
        String genre = "";
        String date = "";
        String description = "";
        long isbn = 0;
        int pages = 0;
        int total_amount = 0;

        if ("submit".equals(action)) {
            int count1=0,count2=0,count3=0,count4=0,count5=0, count6=0, count7=0,count8=0,count9=0;
            boolean isMultipart = ServletFileUpload.isMultipartContent(req);
            PrintWriter out = resp.getWriter();
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
                            String itemName = item.getName();
                        System.out.println(itemName);

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
            System.out.println(book.getTitle());
            System.out.println(book.getDate());
        }

        req.setAttribute("book", book);
        req.getRequestDispatcher("views/full_book_page.jsp").forward(req, resp);
    }

    private void insertBook() {
        DBService service = new DBService();
    }

    //${pageContext.request.contextPath}
}
