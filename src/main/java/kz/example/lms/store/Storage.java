package kz.example.lms.store;

import kz.example.lms.model.Author;
import kz.example.lms.model.Book;
import kz.example.lms.model.BookText;
import kz.example.lms.model.Library;
import kz.example.lms.model.Member;
import kz.example.lms.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public final class Storage {
    private static final List<UserRecord> USERS = new ArrayList<>();
    private static final List<Author> AUTHORS = new ArrayList<>();
    private static final List<Library> LIBRARIES = new ArrayList<>();
    private static final List<Book> BOOKS = new ArrayList<>();
    private static final List<BookText> BOOK_TEXTS = new ArrayList<>();
    private static final List<Member> MEMBERS = new ArrayList<>();

    private static final AtomicInteger USER_ID = new AtomicInteger(1);
    private static final AtomicInteger AUTHOR_ID = new AtomicInteger(1);
    private static final AtomicInteger LIBRARY_ID = new AtomicInteger(1);
    private static final AtomicInteger BOOK_ID = new AtomicInteger(1);
    private static final AtomicInteger MEMBER_ID = new AtomicInteger(1);

    static {
        createLibrary("Орталық кітапхана", "Астана");
        createLibrary("Білім ордасы", "Алматы");

        createAuthor("Абай Құнанбайұлы", "Қазақстан");
        createAuthor("Ыбырай Алтынсарин", "Қазақстан");
        createAuthor("Иван Крылов", "Россия");
        createAuthor("Александр Пушкин", "Россия");
        createAuthor("Жазира Жолдас", "Қазақстан");
        createAuthor("Мұхтар Мағауин", "Қазақстан");
        createAuthor("Қабдеш Жұмаділов", "Қазақстан");
        createAuthor("Дулат Исабеков", "Қазақстан");

        createBook("Ғылым таппай мақтанба", "PD-KZ-0001", 1, 1, 1886);
        createBook("Кел, балалар, оқылық!", "PD-KZ-0002", 2, 1, 1879);
        createBook("Ворона и Лисица", "PD-RU-0003", 3, 2, 1808);
        createBook("Стрекоза и Муравей", "PD-RU-0004", 3, 2, 1808);
        createBook("Руслан и Людмила (пролог)", "PD-RU-0005", 4, 2, 1820);
        createBook("Білімнің жолы", "KZ-ESSAY-0006", 5, 1, 2026);
        createBook("Еңбек пен ой", "KZ-ESSAY-0007", 5, 1, 2026);
        createBook("Уақыт және мақсат", "KZ-ESSAY-0008", 5, 1, 2026);
        createBook("Аласапыран (аннотация)", "KZ-SUM-0009", 6, 1, 1981);
        createBook("Дарабоз (аннотация)", "KZ-SUM-0010", 7, 1, 1994);
        createBook("Гауһартас (аннотация)", "KZ-SUM-0011", 8, 1, 1972);

        registerBookText(1, "kk", "Абайдың ғылым мен мінез туралы өлеңі.", "books/abai_gilim.txt");
        registerBookText(2, "kk", "Оқу-білімге шақыратын өлең.", "books/altynsarin_kel.txt");
        registerBookText(3, "ru", "Басқаның сөзіне еріп, мақтанға алдану туралы мысал.", "books/krylov_vorona.txt");
        registerBookText(4, "ru", "Еңбек пен жалқаулық туралы мысал.", "books/krylov_strekoza.txt");
        registerBookText(5, "ru", "Пролог к поэме о сказочном мире.", "books/pushkin_lukomorye.txt");
        registerBookText(6, "kk", "Қысқа эссе: оқу мен дағдыны тұрақты қалыптастыру.", "books/bilim_joly.txt");
        registerBookText(7, "kk", "Қысқа эссе: еңбек пен ойдың байланысы.", "books/enbek_oi.txt");
        registerBookText(8, "kk", "Қысқа эссе: уақытты басқару және мақсат қою.", "books/uakyt_maksat.txt");
        registerBookText(9, "kk", "Аннотация: тарихи роман туралы қысқаша мазмұн.", "books/magauin_alasapyran.txt");
        registerBookText(10, "kk", "Аннотация: батырлық эпос пен ел тағдыры туралы.", "books/zhumadilov_daraboz.txt");
        registerBookText(11, "kk", "Аннотация: адам мінезі мен махаббат жайлы повесть.", "books/isabekov_gauhartas.txt");
    }

    private Storage() {
    }

    public static synchronized User createUser(String fullName, String email, String passwordHash, String role) {
        int id = USER_ID.getAndIncrement();
        User user = new User(id, fullName, email, role);
        USERS.add(new UserRecord(user, passwordHash));
        return user;
    }

    public static synchronized User findUserByEmail(String email) {
        for (UserRecord record : USERS) {
            if (record.user.getEmail().equalsIgnoreCase(email)) {
                return record.user;
            }
        }
        return null;
    }

    public static synchronized String findPasswordHash(String email) {
        for (UserRecord record : USERS) {
            if (record.user.getEmail().equalsIgnoreCase(email)) {
                return record.passwordHash;
            }
        }
        return null;
    }

    public static synchronized List<Author> getAuthors() {
        return Collections.unmodifiableList(new ArrayList<>(AUTHORS));
    }

    public static synchronized Author findAuthorById(int id) {
        for (Author author : AUTHORS) {
            if (author.getId() == id) {
                return author;
            }
        }
        return null;
    }

    public static synchronized void createAuthor(String fullName, String country) {
        AUTHORS.add(new Author(AUTHOR_ID.getAndIncrement(), fullName, country));
    }

    public static synchronized void updateAuthor(int id, String fullName, String country) {
        for (int i = 0; i < AUTHORS.size(); i++) {
            if (AUTHORS.get(i).getId() == id) {
                AUTHORS.set(i, new Author(id, fullName, country));
                return;
            }
        }
    }

    public static synchronized void deleteAuthor(int id) {
        AUTHORS.removeIf(author -> author.getId() == id);
    }

    public static synchronized List<Library> getLibraries() {
        return Collections.unmodifiableList(new ArrayList<>(LIBRARIES));
    }

    public static synchronized Library findLibraryById(int id) {
        for (Library library : LIBRARIES) {
            if (library.getId() == id) {
                return library;
            }
        }
        return null;
    }

    public static synchronized void createLibrary(String name, String address) {
        LIBRARIES.add(new Library(LIBRARY_ID.getAndIncrement(), name, address));
    }

    public static synchronized void updateLibrary(int id, String name, String address) {
        for (int i = 0; i < LIBRARIES.size(); i++) {
            if (LIBRARIES.get(i).getId() == id) {
                LIBRARIES.set(i, new Library(id, name, address));
                return;
            }
        }
    }

    public static synchronized void deleteLibrary(int id) {
        LIBRARIES.removeIf(library -> library.getId() == id);
    }

    public static synchronized List<Book> getBooks() {
        return Collections.unmodifiableList(new ArrayList<>(BOOKS));
    }

    public static synchronized Book findBookById(int id) {
        for (Book book : BOOKS) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    public static synchronized BookText findBookTextByBookId(int bookId) {
        for (BookText bookText : BOOK_TEXTS) {
            if (bookText.getBookId() == bookId) {
                return bookText;
            }
        }
        return null;
    }

    public static synchronized void createBook(String title, String isbn, int authorId, int libraryId, Integer year) {
        Author author = findAuthorById(authorId);
        Library library = findLibraryById(libraryId);
        String authorName = author == null ? "Unknown" : author.getFullName();
        String libraryName = library == null ? "Unknown" : library.getName();
        BOOKS.add(new Book(BOOK_ID.getAndIncrement(), title, isbn, authorId, libraryId, year, authorName, libraryName));
    }

    public static synchronized void updateBook(int id, String title, String isbn, int authorId, int libraryId,
                                               Integer year) {
        Author author = findAuthorById(authorId);
        Library library = findLibraryById(libraryId);
        String authorName = author == null ? "Unknown" : author.getFullName();
        String libraryName = library == null ? "Unknown" : library.getName();
        for (int i = 0; i < BOOKS.size(); i++) {
            if (BOOKS.get(i).getId() == id) {
                BOOKS.set(i, new Book(id, title, isbn, authorId, libraryId, year, authorName, libraryName));
                return;
            }
        }
    }

    public static synchronized void deleteBook(int id) {
        BOOKS.removeIf(book -> book.getId() == id);
    }

    public static synchronized void registerBookText(int bookId, String language, String description,
                                                     String resourcePath) {
        BOOK_TEXTS.add(new BookText(bookId, language, description, resourcePath));
    }

    public static synchronized List<Member> getMembers() {
        return Collections.unmodifiableList(new ArrayList<>(MEMBERS));
    }

    public static synchronized Member findMemberById(int id) {
        for (Member member : MEMBERS) {
            if (member.getId() == id) {
                return member;
            }
        }
        return null;
    }

    public static synchronized void createMember(String fullName, String email, String phone, int libraryId) {
        Library library = findLibraryById(libraryId);
        String libraryName = library == null ? "Unknown" : library.getName();
        MEMBERS.add(new Member(MEMBER_ID.getAndIncrement(), fullName, email, phone, libraryId, libraryName));
    }

    public static synchronized void updateMember(int id, String fullName, String email, String phone, int libraryId) {
        Library library = findLibraryById(libraryId);
        String libraryName = library == null ? "Unknown" : library.getName();
        for (int i = 0; i < MEMBERS.size(); i++) {
            if (MEMBERS.get(i).getId() == id) {
                MEMBERS.set(i, new Member(id, fullName, email, phone, libraryId, libraryName));
                return;
            }
        }
    }

    public static synchronized void deleteMember(int id) {
        MEMBERS.removeIf(member -> member.getId() == id);
    }

    public static synchronized int getAuthorCount() {
        return AUTHORS.size();
    }

    public static synchronized int getBookCount() {
        return BOOKS.size();
    }

    public static synchronized int getLibraryCount() {
        return LIBRARIES.size();
    }

    public static synchronized int getMemberCount() {
        return MEMBERS.size();
    }

    private static final class UserRecord {
        private final User user;
        private final String passwordHash;

        private UserRecord(User user, String passwordHash) {
            this.user = user;
            this.passwordHash = passwordHash;
        }
    }
}
