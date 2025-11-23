/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import Service.BookRegistration;
import controller.utils.Response;
import controller.utils.Status;
import java.util.ArrayList;
import model.Publisher;
import model.Storage.Storage;
import model.book.AudioBook;
import model.book.Book;
import model.book.DigitalBook;
import model.book.PrintedBook;
import model.person.Author;
import model.person.Narrator;

/**
 *
 * @author mayma
 */
public class BookController {

    private static final Storage storage = Storage.getInstance();

    
    public static String removeAuthorFromTextArea(String author, String currentText) {
        if (author == null || currentText == null) {
            return currentText;
        }
        return currentText.replace(author + "\n", "");
    }

    public static Response createPrintedBook(String title, ArrayList<Long> authorIds, String isbn,
            String genre, String format, double value, String publisherNIT,
            int pages, int copies) {
        
        if (title == null || title.isEmpty()) {
            return new Response("El título no puede estar vacío", Status.BAD_REQUEST);
        }
        if (isbn == null || isbn.isEmpty()) {
            return new Response("El ISBN no puede estar vacío", Status.BAD_REQUEST);
        }
        if (value <= 0) {
            return new Response("El valor debe ser mayor a 0", Status.BAD_REQUEST);
        }
        if (authorIds == null || authorIds.isEmpty()) {
            return new Response("Debe tener al menos un autor", Status.BAD_REQUEST);
        }
        if (publisherNIT == null || publisherNIT.isEmpty()) {
            return new Response("Editorial inválida", Status.BAD_REQUEST);
        }

        
        ArrayList<Author> authors = new ArrayList<>();
        for (Long id : authorIds) {
            Author author = storage.getAuthor(id);
            if (author == null) {
                return new Response("No se encontró el autor con ID " + id, Status.NOT_FOUND);
            }
            authors.add(author);
        }

        
        Publisher publisher = storage.getPublisher(publisherNIT);
        if (publisher == null) {
            return new Response("No se encontró la editorial con NIT " + publisherNIT, Status.NOT_FOUND);
        }

        PrintedBook book = new PrintedBook(title, authors, isbn, genre, format, value, publisher, pages, copies);

        if (!storage.addBook(book)) {
            return new Response("Ya existe un libro con el ISBN: " + isbn, Status.BAD_REQUEST);
        }
        
        new BookRegistration().registerBook(book);
        
        PrintedBook cloned;
        try {
            cloned = book.clone();
        } catch (CloneNotSupportedException e) {
            return new Response("Error al clonar el libro", Status.INTERNAL_SERVER_ERROR);
        }

        return new Response("Libro impreso creado correctamente", Status.CREATED, cloned);
    }

    public static Response createDigitalBook(String title, ArrayList<Long> authorIds, String isbn,
            String genre, String format, double value, String publisherNIT,
            String hyperlink) {
        
        if (title == null || title.isEmpty()) {
            return new Response("El título no puede estar vacío", Status.BAD_REQUEST);
        }
        if (isbn == null || isbn.isEmpty()) {
            return new Response("El ISBN no puede estar vacío", Status.BAD_REQUEST);
        }
        if (value <= 0) {
            return new Response("El valor debe ser mayor a 0", Status.BAD_REQUEST);
        }
        if (authorIds == null || authorIds.isEmpty()) {
            return new Response("Debe tener al menos un autor", Status.BAD_REQUEST);
        }
        if (publisherNIT == null || publisherNIT.isEmpty()) {
            return new Response("Editorial inválida", Status.BAD_REQUEST);
        }

        
        ArrayList<Author> authors = new ArrayList<>();
        for (Long id : authorIds) {
            Author author = storage.getAuthor(id);
            if (author == null) {
                return new Response("No se encontró el autor con ID " + id, Status.NOT_FOUND);
            }
            authors.add(author);
        }

        
        Publisher publisher = storage.getPublisher(publisherNIT);
        if (publisher == null) {
            return new Response("No se encontró la editorial con NIT " + publisherNIT, Status.NOT_FOUND);
        }

        DigitalBook book;
        if (hyperlink == null || hyperlink.isEmpty()) {
            book = new DigitalBook(title, authors, isbn, genre, format, value, publisher);
        } else {
            book = new DigitalBook(title, authors, isbn, genre, format, value, publisher, hyperlink);
        }

        if (!storage.addBook(book)) {
            return new Response("Ya existe un libro con el ISBN: " + isbn, Status.BAD_REQUEST);
        }
        
        new BookRegistration().registerBook(book);

        DigitalBook cloned;
        try {
            cloned = book.clone();
        } catch (CloneNotSupportedException e) {
            return new Response("Error al clonar el libro", Status.INTERNAL_SERVER_ERROR);
        }

        return new Response("Libro digital creado correctamente", Status.CREATED, cloned);
    }

    public static Response createAudioBook(String title, ArrayList<Long> authorIds, String isbn,
            String genre, String format, double value, String publisherNIT,
            int duration, long narratorId) {
       
        if (title == null || title.isEmpty()) {
            return new Response("El título no puede estar vacío", Status.BAD_REQUEST);
        }
        if (isbn == null || isbn.isEmpty()) {
            return new Response("El ISBN no puede estar vacío", Status.BAD_REQUEST);
        }
        if (value <= 0) {
            return new Response("El valor debe ser mayor a 0", Status.BAD_REQUEST);
        }
        if (authorIds == null || authorIds.isEmpty()) {
            return new Response("Debe tener al menos un autor", Status.BAD_REQUEST);
        }
        if (publisherNIT == null || publisherNIT.isEmpty()) {
            return new Response("Editorial inválida", Status.BAD_REQUEST);
        }

        
        ArrayList<Author> authors = new ArrayList<>();
        for (Long id : authorIds) {
            Author author = storage.getAuthor(id);
            if (author == null) {
                return new Response("No se encontró el autor con ID " + id, Status.NOT_FOUND);
            }
            authors.add(author);
        }

        
        Publisher publisher = storage.getPublisher(publisherNIT);
        if (publisher == null) {
            return new Response("No se encontró la editorial con NIT " + publisherNIT, Status.NOT_FOUND);
        }

        
        Narrator narrator = storage.getNarrator(narratorId);
        if (narrator == null) {
            return new Response("No se encontró el narrador con ID " + narratorId, Status.NOT_FOUND);
        }

        AudioBook book = new AudioBook(title, authors, isbn, genre, format, value, publisher, duration, narrator);

        if (!storage.addBook(book)) {
            return new Response("Ya existe un libro con el ISBN: " + isbn, Status.BAD_REQUEST);
        }
        
        new BookRegistration().registerBook(book);

        AudioBook cloned;
        try {
            cloned = book.clone();
        } catch (CloneNotSupportedException e) {
            return new Response("Error al clonar el libro", Status.INTERNAL_SERVER_ERROR);
        }

        return new Response("Audiolibro creado correctamente", Status.CREATED, cloned);
    }

    public static ArrayList<Object[]> getBooksTableRows(String bookTypeFilter, ArrayList<Book> allBooks) {
        ArrayList<Object[]> tableRows = new ArrayList<>();

        for (Book currentBook : allBooks) {
            boolean includeCurrentBook = false;

            if (bookTypeFilter.equals("Libros Impresos") && currentBook instanceof PrintedBook) {
                includeCurrentBook = true;
            } else if (bookTypeFilter.equals("Libros Digitales") && currentBook instanceof DigitalBook) {
                includeCurrentBook = true;
            } else if (bookTypeFilter.equals("Audiolibros") && currentBook instanceof AudioBook) {
                includeCurrentBook = true;
            } else if (bookTypeFilter.equals("Todos los Libros")) {
                includeCurrentBook = true;
            }

            if (!includeCurrentBook) {
                continue;
            }

            
            ArrayList<Author> bookAuthorsList = currentBook.getAuthors();
            String authorsFullNames = "";
            if (!bookAuthorsList.isEmpty()) {
                authorsFullNames += bookAuthorsList.get(0).getFullname();
            }
            for (int authorIndex = 1; authorIndex < bookAuthorsList.size(); authorIndex++) {
                authorsFullNames += ", " + bookAuthorsList.get(authorIndex).getFullname();
            }

            if (currentBook instanceof PrintedBook printedBook) {
                tableRows.add(new Object[]{
                    printedBook.getTitle(),
                    authorsFullNames,
                    printedBook.getIsbn(),
                    printedBook.getGenre(),
                    printedBook.getFormat(),
                    printedBook.getValue(),
                    printedBook.getPublisher().getName(),
                    printedBook.getCopies(),
                    printedBook.getPages(),
                    "-", "-", "-"
                });
            } else if (currentBook instanceof DigitalBook digitalBook) {
                tableRows.add(new Object[]{
                    digitalBook.getTitle(),
                    authorsFullNames,
                    digitalBook.getIsbn(),
                    digitalBook.getGenre(),
                    digitalBook.getFormat(),
                    digitalBook.getValue(),
                    digitalBook.getPublisher().getName(),
                    "-", "-",
                    digitalBook.hasHyperlink() ? digitalBook.getHyperlink() : "No",
                    "-", "-"
                });
            } else if (currentBook instanceof AudioBook audioBook) {
                tableRows.add(new Object[]{
                    audioBook.getTitle(),
                    authorsFullNames,
                    audioBook.getIsbn(),
                    audioBook.getGenre(),
                    audioBook.getFormat(),
                    audioBook.getValue(),
                    audioBook.getPublisher().getName(),
                    "-", "-", "-",
                    audioBook.getNarrador().getFullname(),
                    audioBook.getDuration()
                });
            }
        }

        return tableRows;
    }

    public static ArrayList<Object[]> getBooksByAuthorTableRows(Author author) {
        ArrayList<Object[]> booksTableRows = new ArrayList<>();

        for (Book book : author.getBooks()) {
           
            String authorsFullNames = book.getAuthors().get(0).getFullname();
            for (int authorIndex = 1; authorIndex < book.getAuthors().size(); authorIndex++) {
                authorsFullNames += ", " + book.getAuthors().get(authorIndex).getFullname();
            }

            if (book instanceof PrintedBook printedBook) {
                Object[] rowData = new Object[]{
                    printedBook.getTitle(),
                    authorsFullNames,
                    printedBook.getIsbn(),
                    printedBook.getGenre(),
                    printedBook.getFormat(),
                    printedBook.getValue(),
                    printedBook.getPublisher().getName(),
                    printedBook.getCopies(),
                    printedBook.getPages(),
                    "-", "-", "-"
                };
                booksTableRows.add(rowData);
            }

            if (book instanceof DigitalBook digitalBook) {
                Object[] rowData = new Object[]{
                    digitalBook.getTitle(),
                    authorsFullNames,
                    digitalBook.getIsbn(),
                    digitalBook.getGenre(),
                    digitalBook.getFormat(),
                    digitalBook.getValue(),
                    digitalBook.getPublisher().getName(),
                    "-", "-",
                    digitalBook.hasHyperlink() ? digitalBook.getHyperlink() : "No",
                    "-", "-"
                };
                booksTableRows.add(rowData);
            }

            if (book instanceof AudioBook audioBook) {
                Object[] rowData = new Object[]{
                    audioBook.getTitle(),
                    authorsFullNames,
                    audioBook.getIsbn(),
                    audioBook.getGenre(),
                    audioBook.getFormat(),
                    audioBook.getValue(),
                    audioBook.getPublisher().getName(),
                    "-", "-", "-",
                    audioBook.getNarrador().getFullname(),
                    audioBook.getDuration()
                };
                booksTableRows.add(rowData);
            }
        }

        return booksTableRows;
    }
    
    
    
    
    
    public static ArrayList<Object[]> getBooksByFormatTableRows(String format) {
    ArrayList<Object[]> rows = new ArrayList<>();

    for (Book book : Storage.getInstance().getAllBooks()) {

        if (!book.getFormat().equals(format))
            continue;

        String authors = book.getAuthors().get(0).getFullname();
        for (int i = 1; i < book.getAuthors().size(); i++) {
            authors += ", " + book.getAuthors().get(i).getFullname();
        }

        if (book instanceof PrintedBook printedbook) {
            rows.add(new Object[]{
                printedbook.getTitle(), authors, printedbook.getIsbn(), printedbook.getGenre(),
                printedbook.getFormat(), printedbook.getValue(), printedbook.getPublisher().getName(),
                printedbook.getCopies(), printedbook.getPages(),
                "-", "-", "-"
            });
        }
        else if (book instanceof DigitalBook digitalbook) {
            rows.add(new Object[]{
                digitalbook.getTitle(), authors, digitalbook.getIsbn(), digitalbook.getGenre(),
                digitalbook.getFormat(), digitalbook.getValue(), digitalbook.getPublisher().getName(),
                "-", "-",
                digitalbook.hasHyperlink() ? digitalbook.getHyperlink() : "No", "-", "-"});
        }
        else if (book instanceof AudioBook audiobook) {
            rows.add(new Object[]{
                audiobook.getTitle(), authors, audiobook.getIsbn(), audiobook.getGenre(),
                audiobook.getFormat(), audiobook.getValue(), audiobook.getPublisher().getName(),
                "-", "-", "-",
                audiobook.getNarrador().getFullname(),
                audiobook.getDuration()
            });
        }
    }

    return rows;
}    
}
