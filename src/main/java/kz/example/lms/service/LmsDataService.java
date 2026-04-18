package kz.example.lms.service;

import jakarta.annotation.PostConstruct;
import kz.example.lms.model.Author;
import kz.example.lms.model.Book;
import kz.example.lms.model.BookText;
import kz.example.lms.model.Library;
import kz.example.lms.model.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LmsDataService {

    private final JdbcTemplate jdbcTemplate;

    public LmsDataService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void initSchemaAndSeed() {
        jdbcTemplate.execute("""
                create table if not exists authors (
                    id serial primary key,
                    full_name varchar(255) not null,
                    country varchar(255)
                )
                """);
        jdbcTemplate.execute("""
                create table if not exists libraries (
                    id serial primary key,
                    name varchar(255) not null,
                    address varchar(255) not null
                )
                """);
        jdbcTemplate.execute("""
                create table if not exists books (
                    id serial primary key,
                    title varchar(255) not null,
                    genre varchar(120) not null default 'General',
                    isbn varchar(120) not null,
                    author_id integer not null,
                    library_id integer not null,
                    published_year integer
                )
                """);
        jdbcTemplate.execute("alter table books add column if not exists genre varchar(120) not null default 'General'");
        jdbcTemplate.execute("""
                create table if not exists book_texts (
                    book_id integer primary key,
                    language varchar(10) not null,
                    description varchar(600),
                    resource_path varchar(255) not null
                )
                """);
        jdbcTemplate.execute("alter table book_texts add column if not exists source_url varchar(500)");
        jdbcTemplate.execute("""
                create table if not exists members (
                    id serial primary key,
                    full_name varchar(255) not null,
                    email varchar(255) not null,
                    phone varchar(120),
                    library_id integer not null
                )
                """);

        Integer authorCount = jdbcTemplate.queryForObject("select count(*) from authors", Integer.class);
        if (authorCount != null && authorCount == 0) {
            seedData();
        }
    }

    private void seedData() {
        jdbcTemplate.update("insert into libraries(id, name, address) values (1, ?, ?)", "Central Library", "Astana");
        jdbcTemplate.update("insert into libraries(id, name, address) values (2, ?, ?)", "Knowledge Hub", "Almaty");

        jdbcTemplate.update("insert into authors(id, full_name, country) values (1, ?, ?)", "Abai Kunanbayuly", "Kazakhstan");
        jdbcTemplate.update("insert into authors(id, full_name, country) values (2, ?, ?)", "Ibray Altynsarin", "Kazakhstan");
        jdbcTemplate.update("insert into authors(id, full_name, country) values (3, ?, ?)", "Ivan Krylov", "Russia");
        jdbcTemplate.update("insert into authors(id, full_name, country) values (4, ?, ?)", "Alexander Pushkin", "Russia");

        jdbcTemplate.update("insert into books(id, title, genre, isbn, author_id, library_id, published_year) values (1, ?, ?, ?, 1, 1, 1886)", "Gylym tappay maqtanba", "Poetry", "PD-KZ-0001");
        jdbcTemplate.update("insert into books(id, title, genre, isbn, author_id, library_id, published_year) values (2, ?, ?, ?, 2, 1, 1879)", "Kel, balalar, oqylyq!", "Poetry", "PD-KZ-0002");
        jdbcTemplate.update("insert into books(id, title, genre, isbn, author_id, library_id, published_year) values (3, ?, ?, ?, 3, 2, 1808)", "Vorona i Lisitsa", "Fable", "PD-RU-0003");
        jdbcTemplate.update("insert into books(id, title, genre, isbn, author_id, library_id, published_year) values (4, ?, ?, ?, 4, 2, 1820)", "Ruslan i Lyudmila", "Poem", "PD-RU-0004");

        jdbcTemplate.update("insert into book_texts(book_id, language, description, resource_path) values (1, 'kk', ?, ?)", "Poem about science and growth", "books/abai_gilim.txt");
        jdbcTemplate.update("insert into book_texts(book_id, language, description, resource_path) values (2, 'kk', ?, ?)", "Poem calling children to learn", "books/altynsarin_kel.txt");
        jdbcTemplate.update("insert into book_texts(book_id, language, description, resource_path) values (3, 'ru', ?, ?)", "A fable about flattery", "books/krylov_vorona.txt");
        jdbcTemplate.update("insert into book_texts(book_id, language, description, resource_path) values (4, 'ru', ?, ?)", "Classic poem fragment", "books/pushkin_lukomorye.txt");
        jdbcTemplate.update("update book_texts set source_url=? where book_id=?", "https://www.gutenberg.org/ebooks/1181", 4);

        jdbcTemplate.update("insert into members(id, full_name, email, phone, library_id) values (1, ?, ?, ?, 1)", "Aruzhan S.", "aruzhan@example.com", "+7 701 111 22 33");
        jdbcTemplate.update("insert into members(id, full_name, email, phone, library_id) values (2, ?, ?, ?, 2)", "Dias K.", "dias@example.com", "+7 702 444 55 66");

        jdbcTemplate.execute("select setval('authors_id_seq', (select max(id) from authors))");
        jdbcTemplate.execute("select setval('libraries_id_seq', (select max(id) from libraries))");
        jdbcTemplate.execute("select setval('books_id_seq', (select max(id) from books))");
        jdbcTemplate.execute("select setval('members_id_seq', (select max(id) from members))");
    }

    public List<Author> getAuthors() {
        return jdbcTemplate.query(
                "select id, full_name, country from authors order by id",
                (rs, i) -> new Author(rs.getInt("id"), rs.getString("full_name"), rs.getString("country"))
        );
    }

    public List<Author> searchAuthors(String query) {
        String normalized = query == null ? "" : query.trim().toLowerCase();
        if (normalized.isEmpty()) {
            return getAuthors();
        }
        String like = "%" + normalized + "%";
        return jdbcTemplate.query("""
                        select id, full_name, country
                        from authors
                        where lower(full_name) like ? or lower(coalesce(country, '')) like ?
                        order by id
                        """,
                (rs, i) -> new Author(rs.getInt("id"), rs.getString("full_name"), rs.getString("country")),
                like, like
        );
    }

    public Author findAuthorById(int id) {
        List<Author> list = jdbcTemplate.query(
                "select id, full_name, country from authors where id=?",
                (rs, i) -> new Author(rs.getInt("id"), rs.getString("full_name"), rs.getString("country")),
                id
        );
        return list.isEmpty() ? null : list.get(0);
    }

    public void createAuthor(String fullName, String country) {
        jdbcTemplate.update("insert into authors(full_name, country) values (?, ?)", fullName, country);
    }

    public void updateAuthor(int id, String fullName, String country) {
        jdbcTemplate.update("update authors set full_name=?, country=? where id=?", fullName, country, id);
    }

    public void deleteAuthor(int id) {
        jdbcTemplate.update("delete from authors where id=?", id);
    }

    public List<Library> getLibraries() {
        return jdbcTemplate.query(
                "select id, name, address from libraries order by id",
                (rs, i) -> new Library(rs.getInt("id"), rs.getString("name"), rs.getString("address"))
        );
    }

    public Library findLibraryById(int id) {
        List<Library> list = jdbcTemplate.query(
                "select id, name, address from libraries where id=?",
                (rs, i) -> new Library(rs.getInt("id"), rs.getString("name"), rs.getString("address")),
                id
        );
        return list.isEmpty() ? null : list.get(0);
    }

    public void createLibrary(String name, String address) {
        jdbcTemplate.update("insert into libraries(name, address) values (?, ?)", name, address);
    }

    public void updateLibrary(int id, String name, String address) {
        jdbcTemplate.update("update libraries set name=?, address=? where id=?", name, address, id);
    }

    public void deleteLibrary(int id) {
        jdbcTemplate.update("delete from libraries where id=?", id);
    }

    public List<Book> getBooks() {
        return deduplicateBooks(jdbcTemplate.query("""
                        select b.id, b.title, b.genre, b.isbn, b.author_id, b.library_id, b.published_year,
                               coalesce(a.full_name, 'Unknown') as author_name,
                               coalesce(l.name, 'Unknown') as library_name
                        from books b
                        left join authors a on a.id = b.author_id
                        left join libraries l on l.id = b.library_id
                        order by b.id
                        """,
                (rs, i) -> new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("genre"),
                        rs.getString("isbn"),
                        rs.getInt("author_id"),
                        rs.getInt("library_id"),
                        (Integer) rs.getObject("published_year"),
                        rs.getString("author_name"),
                        rs.getString("library_name")
                )
        ));
    }

    public Book findBookById(int id) {
        List<Book> list = jdbcTemplate.query("""
                        select b.id, b.title, b.genre, b.isbn, b.author_id, b.library_id, b.published_year,
                               coalesce(a.full_name, 'Unknown') as author_name,
                               coalesce(l.name, 'Unknown') as library_name
                        from books b
                        left join authors a on a.id = b.author_id
                        left join libraries l on l.id = b.library_id
                        where b.id=?
                        """,
                (rs, i) -> new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("genre"),
                        rs.getString("isbn"),
                        rs.getInt("author_id"),
                        rs.getInt("library_id"),
                        (Integer) rs.getObject("published_year"),
                        rs.getString("author_name"),
                        rs.getString("library_name")
                ),
                id
        );
        return list.isEmpty() ? null : list.get(0);
    }

    public List<Book> searchBooks(String query, String genre) {
        String normalizedQuery = query == null ? "" : query.trim().toLowerCase();
        String normalizedGenre = genre == null ? "" : genre.trim();
        if (normalizedQuery.isEmpty() && normalizedGenre.isEmpty()) {
            return getBooks();
        }

        StringBuilder sql = new StringBuilder("""
                        select b.id, b.title, b.genre, b.isbn, b.author_id, b.library_id, b.published_year,
                               coalesce(a.full_name, 'Unknown') as author_name,
                               coalesce(l.name, 'Unknown') as library_name
                        from books b
                        left join authors a on a.id = b.author_id
                        left join libraries l on l.id = b.library_id
                        where 1=1
                        """);
        List<Object> params = new ArrayList<>();
        if (!normalizedQuery.isEmpty()) {
            sql.append(" and (lower(b.title) like ? or lower(a.full_name) like ?) ");
            String like = "%" + normalizedQuery + "%";
            params.add(like);
            params.add(like);
        }
        if (!normalizedGenre.isEmpty()) {
            sql.append(" and b.genre = ? ");
            params.add(normalizedGenre);
        }
        sql.append(" order by b.id ");

        return deduplicateBooks(jdbcTemplate.query(sql.toString(),
                (rs, i) -> new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("genre"),
                        rs.getString("isbn"),
                        rs.getInt("author_id"),
                        rs.getInt("library_id"),
                        (Integer) rs.getObject("published_year"),
                        rs.getString("author_name"),
                        rs.getString("library_name")
                ),
                params.toArray()
        ));
    }

    public void createBook(String title, String genre, String isbn, int authorId, int libraryId, Integer publishedYear) {
        jdbcTemplate.update(
                "insert into books(title, genre, isbn, author_id, library_id, published_year) values (?, ?, ?, ?, ?, ?)",
                title, genre, isbn, authorId, libraryId, publishedYear
        );
    }

    public void updateBook(int id, String title, String genre, String isbn, int authorId, int libraryId, Integer publishedYear) {
        jdbcTemplate.update(
                "update books set title=?, genre=?, isbn=?, author_id=?, library_id=?, published_year=? where id=?",
                title, genre, isbn, authorId, libraryId, publishedYear, id
        );
    }

    public void deleteBook(int id) {
        jdbcTemplate.update("delete from book_texts where book_id=?", id);
        jdbcTemplate.update("delete from books where id=?", id);
    }

    public BookText findBookTextByBookId(int bookId) {
        List<BookText> list = jdbcTemplate.query(
                "select book_id, language, description, resource_path, source_url from book_texts where book_id=?",
                (rs, i) -> new BookText(
                        rs.getInt("book_id"),
                        rs.getString("language"),
                        rs.getString("description"),
                        rs.getString("resource_path"),
                        rs.getString("source_url")
                ),
                bookId
        );
        return list.isEmpty() ? null : list.get(0);
    }

    public List<Member> getMembers() {
        return jdbcTemplate.query("""
                        select m.id, m.full_name, m.email, m.phone, m.library_id,
                               coalesce(l.name, 'Unknown') as library_name
                        from members m
                        left join libraries l on l.id = m.library_id
                        order by m.id
                        """,
                (rs, i) -> new Member(
                        rs.getInt("id"),
                        rs.getString("full_name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getInt("library_id"),
                        rs.getString("library_name")
                )
        );
    }

    public Member findMemberById(int id) {
        List<Member> list = jdbcTemplate.query("""
                        select m.id, m.full_name, m.email, m.phone, m.library_id,
                               coalesce(l.name, 'Unknown') as library_name
                        from members m
                        left join libraries l on l.id = m.library_id
                        where m.id=?
                        """,
                (rs, i) -> new Member(
                        rs.getInt("id"),
                        rs.getString("full_name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getInt("library_id"),
                        rs.getString("library_name")
                ),
                id
        );
        return list.isEmpty() ? null : list.get(0);
    }

    public Member findMemberByEmail(String email) {
        String normalized = email == null ? "" : email.trim().toLowerCase();
        if (normalized.isEmpty()) {
            return null;
        }
        List<Member> list = jdbcTemplate.query("""
                        select m.id, m.full_name, m.email, m.phone, m.library_id,
                               coalesce(l.name, 'Unknown') as library_name
                        from members m
                        left join libraries l on l.id = m.library_id
                        where lower(m.email) = ?
                        """,
                (rs, i) -> new Member(
                        rs.getInt("id"),
                        rs.getString("full_name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getInt("library_id"),
                        rs.getString("library_name")
                ),
                normalized
        );
        return list.isEmpty() ? null : list.get(0);
    }

    public void createMember(String fullName, String email, String phone, int libraryId) {
        jdbcTemplate.update(
                "insert into members(full_name, email, phone, library_id) values (?, ?, ?, ?)",
                fullName, email, phone, libraryId
        );
    }

    public void updateMember(int id, String fullName, String email, String phone, int libraryId) {
        jdbcTemplate.update(
                "update members set full_name=?, email=?, phone=?, library_id=? where id=?",
                fullName, email, phone, libraryId, id
        );
    }

    public void deleteMember(int id) {
        jdbcTemplate.update("delete from members where id=?", id);
    }

    public int getAuthorCount() {
        Integer value = jdbcTemplate.queryForObject("select count(*) from authors", Integer.class);
        return value == null ? 0 : value;
    }

    public int getBookCount() {
        Integer value = jdbcTemplate.queryForObject("select count(*) from books", Integer.class);
        return value == null ? 0 : value;
    }

    public int getLibraryCount() {
        Integer value = jdbcTemplate.queryForObject("select count(*) from libraries", Integer.class);
        return value == null ? 0 : value;
    }

    public int getMemberCount() {
        Integer value = jdbcTemplate.queryForObject("select count(*) from members", Integer.class);
        return value == null ? 0 : value;
    }

    private List<Book> deduplicateBooks(List<Book> books) {
        return books.stream()
                .collect(java.util.stream.Collectors.toMap(
                        book -> normalizeKey(book.getIsbn(), book.getTitle(), book.getAuthorId()),
                        book -> book,
                        (left, right) -> left,
                        java.util.LinkedHashMap::new
                ))
                .values()
                .stream()
                .toList();
    }

    private String normalizeKey(String isbn, String title, int authorId) {
        String normalizedIsbn = isbn == null ? "" : isbn.trim().toLowerCase();
        if (!normalizedIsbn.isEmpty()) {
            return normalizedIsbn;
        }
        return (title == null ? "" : title.trim().toLowerCase()) + ":" + authorId;
    }
}
