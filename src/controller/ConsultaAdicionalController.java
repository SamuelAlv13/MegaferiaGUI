/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import controller.utils.Response;
import controller.utils.Status;
import java.util.ArrayList;
import model.Storage.Storage;
import model.book.AudioBook;
import model.book.Book;
import model.book.DigitalBook;
import model.book.PrintedBook;
import model.person.Author;

/**
 *
 * @author mayma
 */
public class ConsultaAdicionalController {
    public static ArrayList<Object[]> getBooksByFormatTableRows(String format) {

        ArrayList<Object[]> rows = new ArrayList<>();
        ArrayList<Book> books = Storage.getInstance().getAllBooks();

        for (Book book : books) {
            if (book.getFormat().equals(format)) {

                String authors = book.getAuthors().get(0).getFullname();
                for (int i = 1; i < book.getAuthors().size(); i++) {
                    authors += ", " + book.getAuthors().get(i).getFullname();
                }

                if (book instanceof PrintedBook printedBook) {
                    rows.add(new Object[]{
                        printedBook.getTitle(),
                        authors,
                        printedBook.getIsbn(),
                        printedBook.getGenre(),
                        printedBook.getFormat(),
                        printedBook.getValue(),
                        printedBook.getPublisher().getName(),
                        printedBook.getCopies(),
                        printedBook.getPages(),
                        "-", "-", "-"
                    });
                }
                else if (book instanceof DigitalBook digitalBook) {
                    rows.add(new Object[]{
                        digitalBook.getTitle(),
                        authors,
                        digitalBook.getIsbn(),
                        digitalBook.getGenre(),
                        digitalBook.getFormat(),
                        digitalBook.getValue(),
                        digitalBook.getPublisher().getName(),
                        "-", "-",
                        digitalBook.hasHyperlink() ? digitalBook.getHyperlink() : "No",
                        "-", "-"
                    });
                }
                else if (book instanceof AudioBook audiobook) {
                    rows.add(new Object[]{
                        audiobook.getTitle(),
                        authors,
                        audiobook.getIsbn(),
                        audiobook.getGenre(),
                        audiobook.getFormat(),
                        audiobook.getValue(),
                        audiobook.getPublisher().getName(),
                        "-", "-", "-",
                        audiobook.getNarrador().getFullname(),
                        audiobook.getDuration()
                    });
                }
            }
        }

        return rows;
    }
    public static Response getAuthorsWithMaxPublishers(ArrayList<Author> authors) {
        try {
            if (authors == null || authors.isEmpty()) {
                return new Response("No hay autores registrados.", Status.NO_CONTENT);
            }

            int maxPublishers = -1;
            ArrayList<Author> authorsMax = new ArrayList<>();

            for (Author author : authors) {
                if (author.getPublisherQuantity() > maxPublishers) {
                    maxPublishers = author.getPublisherQuantity();
                    authorsMax.clear();
                    authorsMax.add(author);
                } 
                else if (author.getPublisherQuantity() == maxPublishers) {
                    authorsMax.add(author);
                }
            }

            ArrayList<Object[]> tableRows = new ArrayList<>();
            for (Author author : authorsMax) {
                tableRows.add(new Object[]{
                    author.getId(),
                    author.getFullname(),
                    maxPublishers
                });
            }

            return new Response("Consulta realizada exitosamente.", Status.OK, tableRows);

        } catch (Exception ex) {
            return new Response("Error interno: " + ex.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }
    
}
