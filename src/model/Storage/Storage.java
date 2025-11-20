/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
    package model.Storage;

    import model.person.Author;
    import model.book.Book;
    import model.person.Manager;
    import model.person.Narrator;
    import model.Publisher;
    import model.Stand;
    import java.util.ArrayList;

    /**
     *
     * @author mayma
     */
    public class Storage {

        private static Storage instance;

        // Colecciones de datos
        private ArrayList<Stand> stands;
        private ArrayList<Author> authors;
        private ArrayList<Manager> managers;
        private ArrayList<Narrator> narrators;
        private ArrayList<Publisher> publishers;
        private ArrayList<Book> books;

        private Storage() {
            this.stands = new ArrayList<>();
            this.authors = new ArrayList<>();
            this.managers = new ArrayList<>();
            this.narrators = new ArrayList<>();
            this.publishers = new ArrayList<>();
            this.books = new ArrayList<>();
        }

        public static Storage getInstance() {
            if (instance == null) {
                instance = new Storage();
            }
            return instance;
        }


        public boolean addStand(Stand stand) {
            for (Stand s : this.stands) {
                if (s.getId() == stand.getId()) {
                    return false;
                }
            }
            this.stands.add(stand);
            return true;
        }

        public Stand getStand(long id) {
            for (Stand stand : this.stands) {
                if (stand.getId() == id) {
                    return stand;
                }
            }
            return null;
        }

        public ArrayList<Stand> getAllStands() {
            return new ArrayList<>(this.stands); // Retorna una copia de la lista
        }


        public boolean addAuthor(Author author) {
            for (Author a : this.authors) {
                if (a.getId() == author.getId()) {
                    return false;
                }
            }
            this.authors.add(author);
            return true;
        }

        public Author getAuthor(long id) {
            for (Author author : this.authors) {
                if (author.getId() == id) {
                    return author;
                }
            }
            return null;
        }

        public ArrayList<Author> getAllAuthors() {
            return new ArrayList<>(this.authors);
        }


        public boolean addManager(Manager manager) {
            for (Manager m : this.managers) {
                if (m.getId() == manager.getId()) {
                    return false;
                }
            }
            this.managers.add(manager);
            return true;
        }

        public Manager getManager(long id) {
            for (Manager manager : this.managers) {
                if (manager.getId() == id) {
                    return manager;
                }
            }
            return null;
        }

        public ArrayList<Manager> getAllManagers() {
            return new ArrayList<>(this.managers);
        }


        public boolean addNarrator(Narrator narrator) {
            for (Narrator n : this.narrators) {
                if (n.getId() == narrator.getId()) {
                    return false;
                }
            }
            this.narrators.add(narrator);
            return true;
        }

        public Narrator getNarrator(long id) {
            for (Narrator narrator : this.narrators) {
                if (narrator.getId() == id) {
                    return narrator;
                }
            }
            return null;
        }

        public ArrayList<Narrator> getAllNarrators() {
            return new ArrayList<>(this.narrators);
        }


        public boolean addPublisher(Publisher publisher) {
            for (Publisher p : this.publishers) {
                if (p.getNit().equals(publisher.getNit())) {
                    return false;
                }
            }
            this.publishers.add(publisher);
            return true;
        }

        public Publisher getPublisher(String nit) {
            for (Publisher publisher : this.publishers) {
                if (publisher.getNit().equals(nit)) {
                    return publisher;
                }
            }
            return null;
        }

        public ArrayList<Publisher> getAllPublishers() {
            return new ArrayList<>(this.publishers);
        }


        public boolean addBook(Book book) {
            for (Book b : this.books) {
                if (b.getIsbn().equals(book.getIsbn())) {
                    return false;
                }
            }
            this.books.add(book);
            return true;
        }

        public Book getBook(String isbn) {
            for (Book book : this.books) {
                if (book.getIsbn().equals(isbn)) {
                    return book;
                }
            }
            return null;
        }

        public ArrayList<Book> getAllBooks() {
            return new ArrayList<>(this.books);
        }
    }