package uz.pdp.librarymanagementsystem.books;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import uz.pdp.librarymanagementsystem.authors.AuthorDao;
import uz.pdp.librarymanagementsystem.category.CategoryDao;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static uz.pdp.librarymanagementsystem.utils.Util.UPLOAD_DIRECTORY;


@WebServlet("/add-book")
@MultipartConfig(maxFileSize = 10_000_000)
public class AddBookServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("authorList", AuthorDao.getAllAuthors());
        req.setAttribute("categoryList", CategoryDao.getAllCategories());

        req.getRequestDispatcher("/add-book-form.jsp").forward(req, resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        String[] authorsIdsStr = req.getParameterValues("authorsIds");

        Set<Long> authorsIds = getAuthorIdsFromStrArr(authorsIdsStr);
        Long categoryId = Long.valueOf(req.getParameter("categoryId"));
        String isbn = req.getParameter("isbn");
        String year =req.getParameter("year");
        Integer quantity = Integer.valueOf(req.getParameter("quantity"));
        Part imagePart = req.getPart("image");

        Book book = Book.builder()
              //  .id()
                .title(title)
                .description(description)
                .year(year)
                .isbn(isbn)
                .authorsIds(authorsIds)
                .categoryId(categoryId)
                .quantity(quantity)
                .imgUrl(uploadAndGetImageUrl(imagePart))
                .build();

        boolean result = BookDao.addNewBook(book);

        if (result) {
            resp.sendRedirect("/books?added=true");
        }


    }

    private Set<Long> getAuthorIdsFromStrArr(String[] authorsIdsStr) {
        Set<Long> authorIds = new HashSet<>();
        for (String authorId : authorsIdsStr) {
            authorIds.add(Long.parseLong(authorId));
        }
        return authorIds;
    }


    private String uploadAndGetImageUrl(Part imagePart) {
        try {

            File uploadDir = new File(UPLOAD_DIRECTORY);
            if (!uploadDir.exists())
                uploadDir.mkdir();
            int index = imagePart.getSubmittedFileName().lastIndexOf('.');
            String extension = imagePart.getSubmittedFileName().substring(index + 1);
            System.out.println("File extension is " + extension);

            String imgName = System.currentTimeMillis() + "." + extension;
            String imgPath = uploadDir.getPath() + "/" + imgName;
            imagePart.write(imgPath);
            return imgName;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
