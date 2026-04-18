package kz.example.lms.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

public final class I18n {

    public static final String LANG_SESSION_KEY = "lang";
    public static final String RU = "ru";
    public static final String KK = "kk";
    public static final String EN = "en";

    private static final Map<String, String> RU_MAP = new HashMap<>();
    private static final Map<String, String> KK_MAP = new HashMap<>();
    private static final Map<String, String> EN_MAP = new HashMap<>();

    static {
        RU_MAP.put("nav.brand", "Библиотечная система");
        RU_MAP.put("nav.home", "Главная");
        RU_MAP.put("nav.books", "Книги");
        RU_MAP.put("nav.authors", "Авторы");
        RU_MAP.put("nav.libraries", "Библиотеки");
        RU_MAP.put("nav.readers", "Читатели");
        RU_MAP.put("nav.settings", "Настройки");
        RU_MAP.put("nav.logout", "Выход");
        RU_MAP.put("nav.login", "Вход");
        RU_MAP.put("nav.register", "Регистрация");
        RU_MAP.put("lang.ru", "Русский");
        RU_MAP.put("lang.kk", "Қазақша");

        RU_MAP.put("home.mainPanel", "Главная панель");
        RU_MAP.put("home.title", "Обзор библиотеки");
        RU_MAP.put("home.lead", "Добро пожаловать в цифровую библиотеку: статистика, каталоги и быстрый доступ ко всем разделам.");
        RU_MAP.put("home.quickActions", "Быстрые действия");
        RU_MAP.put("home.authorsDesc", "Управление карточками авторов.");
        RU_MAP.put("home.booksDesc", "Работа с полным каталогом книг.");
        RU_MAP.put("home.librariesDesc", "Учет филиалов и адресов.");
        RU_MAP.put("home.readersDesc", "Управление читателями.");
        RU_MAP.put("home.classicBooks", "Классика и современные книги");
        RU_MAP.put("home.readingMood", "Уютная атмосфера для чтения");
        RU_MAP.put("home.story", "Каждая книга рассказывает новую историю");

        RU_MAP.put("authors.title", "Авторы");
        RU_MAP.put("authors.heroTitle", "Управление авторами");
        RU_MAP.put("authors.lead", "Создавайте и редактируйте авторов для аккуратного каталога.");
        RU_MAP.put("authors.total", "Всего авторов");
        RU_MAP.put("authors.module", "Раздел каталога");
        RU_MAP.put("authors.fullName", "ФИО");
        RU_MAP.put("authors.country", "Страна");

        RU_MAP.put("libraries.title", "Библиотеки");
        RU_MAP.put("libraries.heroTitle", "Управление филиалами");
        RU_MAP.put("libraries.lead", "Поддерживайте актуальные названия и адреса библиотек.");
        RU_MAP.put("libraries.total", "Всего библиотек");
        RU_MAP.put("libraries.module", "Раздел филиалов");
        RU_MAP.put("libraries.name", "Название");
        RU_MAP.put("libraries.address", "Адрес");

        RU_MAP.put("members.title", "Читатели");
        RU_MAP.put("members.heroTitle", "Реестр читателей");
        RU_MAP.put("members.lead", "Управляйте профилями читателей и поддерживайте порядок в системе.");
        RU_MAP.put("members.total", "Всего читателей");
        RU_MAP.put("members.module", "Пользователи");
        RU_MAP.put("members.fullName", "ФИО");
        RU_MAP.put("members.phone", "Телефон");

        RU_MAP.put("books.title", "Книги");
        RU_MAP.put("books.heroTitle", "Каталог книг");
        RU_MAP.put("books.lead", "Создавайте и редактируйте книги с авторами и библиотеками.");
        RU_MAP.put("books.isbn", "ISBN");
        RU_MAP.put("books.year", "Год издания");
        RU_MAP.put("books.cover", "Обложка");
        RU_MAP.put("books.open", "Открыть");

        RU_MAP.put("login.title", "Вход");
        RU_MAP.put("login.welcome", "С возвращением");
        RU_MAP.put("login.lead", "Войдите, чтобы управлять библиотечной системой.");
        RU_MAP.put("login.signIn", "Войти");
        RU_MAP.put("login.createAccount", "Создать аккаунт");

        RU_MAP.put("register.title", "Регистрация");
        RU_MAP.put("register.newAccount", "Новый аккаунт");
        RU_MAP.put("register.createProfile", "Создание профиля");
        RU_MAP.put("register.lead", "Зарегистрируйтесь для доступа к системе.");
        RU_MAP.put("register.createAccount", "Создать аккаунт");
        RU_MAP.put("register.toLogin", "Перейти ко входу");
        RU_MAP.put("register.role", "Роль");
        RU_MAP.put("register.admin", "Администратор");
        RU_MAP.put("register.reader", "Читатель");

        RU_MAP.put("settings.title", "Настройки");
        RU_MAP.put("settings.profile", "Профиль пользователя");
        RU_MAP.put("settings.lead", "Основная информация учетной записи.");

        RU_MAP.put("book.details", "Карточка книги");
        RU_MAP.put("book.detailLead", "Подробная информация о выбранной книге.");
        RU_MAP.put("book.backToList", "Назад к списку");
        RU_MAP.put("book.read", "Читать");
        RU_MAP.put("book.language", "Язык");
        RU_MAP.put("book.description", "Описание");
        RU_MAP.put("book.untitled", "Без названия");

        RU_MAP.put("read.room", "Чтение");
        RU_MAP.put("read.readBook", "Чтение книги");

        RU_MAP.put("common.email", "Email");
        RU_MAP.put("common.password", "Пароль");
        RU_MAP.put("common.actions", "Действия");
        RU_MAP.put("common.id", "ID");
        RU_MAP.put("common.add", "Добавить");
        RU_MAP.put("common.save", "Сохранить");
        RU_MAP.put("common.edit", "Изменить");
        RU_MAP.put("common.delete", "Удалить");
        RU_MAP.put("common.cancel", "Отмена");
        RU_MAP.put("common.back", "Назад");
        RU_MAP.put("common.total", "всего");
        RU_MAP.put("common.role", "Роль");
        RU_MAP.put("common.language", "Язык");
        RU_MAP.put("common.kazakh", "Казахский");
        RU_MAP.put("common.russian", "Русский");
        RU_MAP.put("common.profile", "Профиль");

        RU_MAP.put("footer.brand", "Библиотечная система.");

        KK_MAP.putAll(RU_MAP);
        KK_MAP.put("nav.brand", "Кітапхана жүйесі");
        KK_MAP.put("nav.home", "Басты бет");
        KK_MAP.put("nav.books", "Кітаптар");
        KK_MAP.put("nav.authors", "Авторлар");
        KK_MAP.put("nav.libraries", "Кітапханалар");
        KK_MAP.put("nav.readers", "Оқырмандар");
        KK_MAP.put("nav.settings", "Баптаулар");
        KK_MAP.put("nav.logout", "Шығу");
        KK_MAP.put("nav.login", "Кіру");
        KK_MAP.put("nav.register", "Тіркелу");

        KK_MAP.put("home.mainPanel", "Негізгі панель");
        KK_MAP.put("home.title", "Кітапханаға шолу");
        KK_MAP.put("home.lead", "Сандық кітапханаға қош келдіңіз: статистика, каталогтар және жылдам қолжетімділік.");
        KK_MAP.put("home.quickActions", "Жылдам әрекеттер");
        KK_MAP.put("home.authorsDesc", "Авторлар карталарын басқару.");
        KK_MAP.put("home.booksDesc", "Кітаптар каталогымен жұмыс.");
        KK_MAP.put("home.librariesDesc", "Филиалдар мен мекенжайлар есебі.");
        KK_MAP.put("home.readersDesc", "Оқырмандарды басқару.");
        KK_MAP.put("home.classicBooks", "Классика және заманауи кітаптар");
        KK_MAP.put("home.readingMood", "Оқуға жайлы атмосфера");
        KK_MAP.put("home.story", "Әр кітап жаңа тарих айтады");

        KK_MAP.put("authors.title", "Авторлар");
        KK_MAP.put("authors.heroTitle", "Авторларды басқару");
        KK_MAP.put("authors.lead", "Каталог құрылымын реттеу үшін авторларды қосып, өңдеңіз.");
        KK_MAP.put("authors.total", "Авторлар саны");
        KK_MAP.put("authors.module", "Каталог бөлімі");
        KK_MAP.put("authors.fullName", "Аты-жөні");
        KK_MAP.put("authors.country", "Елі");

        KK_MAP.put("libraries.title", "Кітапханалар");
        KK_MAP.put("libraries.heroTitle", "Филиалдарды басқару");
        KK_MAP.put("libraries.lead", "Кітапханалардың атауы мен мекенжайын өзекті ұстаңыз.");
        KK_MAP.put("libraries.total", "Кітапхана саны");
        KK_MAP.put("libraries.module", "Филиал бөлімі");
        KK_MAP.put("libraries.name", "Атауы");
        KK_MAP.put("libraries.address", "Мекенжайы");

        KK_MAP.put("members.title", "Оқырмандар");
        KK_MAP.put("members.heroTitle", "Оқырмандар тізілімі");
        KK_MAP.put("members.lead", "Оқырмандар профилін басқарып, жүйеде тәртіп сақтаңыз.");
        KK_MAP.put("members.total", "Оқырман саны");
        KK_MAP.put("members.module", "Пайдаланушылар");
        KK_MAP.put("members.fullName", "Аты-жөні");
        KK_MAP.put("members.phone", "Телефон");

        KK_MAP.put("books.title", "Кітаптар");
        KK_MAP.put("books.heroTitle", "Кітаптар каталогы");
        KK_MAP.put("books.lead", "Авторлар мен кітапханаларға байланыстырып кітаптарды басқару.");
        KK_MAP.put("books.year", "Жылы");
        KK_MAP.put("books.cover", "Мұқаба");
        KK_MAP.put("books.open", "Ашу");

        KK_MAP.put("login.title", "Кіру");
        KK_MAP.put("login.welcome", "Қош келдіңіз");
        KK_MAP.put("login.lead", "Кітапхана жүйесін басқару үшін кіріңіз.");
        KK_MAP.put("login.signIn", "Кіру");
        KK_MAP.put("login.createAccount", "Аккаунт ашу");

        KK_MAP.put("register.title", "Тіркелу");
        KK_MAP.put("register.newAccount", "Жаңа аккаунт");
        KK_MAP.put("register.createProfile", "Профиль жасау");
        KK_MAP.put("register.lead", "Жүйеге кіру үшін тіркеліңіз.");
        KK_MAP.put("register.createAccount", "Аккаунт құру");
        KK_MAP.put("register.toLogin", "Кіруге өту");
        KK_MAP.put("register.role", "Рөлі");
        KK_MAP.put("register.admin", "Әкімші");
        KK_MAP.put("register.reader", "Оқырман");

        KK_MAP.put("settings.title", "Баптаулар");
        KK_MAP.put("settings.profile", "Пайдаланушы профилі");
        KK_MAP.put("settings.lead", "Аккаунттың негізгі ақпараты.");

        KK_MAP.put("book.details", "Кітап карточкасы");
        KK_MAP.put("book.detailLead", "Таңдалған кітап туралы толық ақпарат.");
        KK_MAP.put("book.backToList", "Тізімге қайту");
        KK_MAP.put("book.read", "Оқу");
        KK_MAP.put("book.language", "Тілі");
        KK_MAP.put("book.description", "Сипаттама");
        KK_MAP.put("book.untitled", "Атаусыз");

        KK_MAP.put("read.room", "Оқу");
        KK_MAP.put("read.readBook", "Кітап оқу");

        KK_MAP.put("common.password", "Құпия сөз");
        KK_MAP.put("common.actions", "Әрекеттер");
        KK_MAP.put("common.add", "Қосу");
        KK_MAP.put("common.save", "Сақтау");
        KK_MAP.put("common.edit", "Өңдеу");
        KK_MAP.put("common.delete", "Жою");
        KK_MAP.put("common.cancel", "Болдырмау");
        KK_MAP.put("common.back", "Артқа");
        KK_MAP.put("common.total", "барлығы");
        KK_MAP.put("common.role", "Рөл");
        KK_MAP.put("common.language", "Тіл");
        KK_MAP.put("common.kazakh", "Қазақша");
        KK_MAP.put("common.russian", "Орысша");
        KK_MAP.put("common.profile", "Профиль");

        KK_MAP.put("footer.brand", "Кітапхана жүйесі.");

        EN_MAP.putAll(RU_MAP);
        EN_MAP.put("nav.brand", "Library System");
        EN_MAP.put("nav.home", "Home");
        EN_MAP.put("nav.books", "Books");
        EN_MAP.put("nav.authors", "Authors");
        EN_MAP.put("nav.libraries", "Libraries");
        EN_MAP.put("nav.readers", "Readers");
        EN_MAP.put("nav.settings", "Settings");
        EN_MAP.put("nav.logout", "Logout");
        EN_MAP.put("nav.login", "Login");
        EN_MAP.put("nav.register", "Register");
        EN_MAP.put("lang.ru", "Russian");
        EN_MAP.put("lang.kk", "Kazakh");
        EN_MAP.put("lang.en", "English");
        EN_MAP.put("home.mainPanel", "Dashboard");
        EN_MAP.put("home.title", "Library Overview");
        EN_MAP.put("home.lead", "Welcome to the digital library with quick access to all modules.");
        EN_MAP.put("home.quickActions", "Quick Actions");
        EN_MAP.put("home.authorsDesc", "Manage author records.");
        EN_MAP.put("home.booksDesc", "Work with the full book catalog.");
        EN_MAP.put("home.librariesDesc", "Manage branches and addresses.");
        EN_MAP.put("home.readersDesc", "Manage readers.");
        EN_MAP.put("authors.title", "Authors");
        EN_MAP.put("authors.heroTitle", "Author Management");
        EN_MAP.put("authors.lead", "Create and update authors in your catalog.");
        EN_MAP.put("authors.total", "Total authors");
        EN_MAP.put("authors.fullName", "Full name");
        EN_MAP.put("authors.country", "Country");
        EN_MAP.put("books.title", "Books");
        EN_MAP.put("books.heroTitle", "Book Catalog");
        EN_MAP.put("books.lead", "Manage books linked to authors and libraries.");
        EN_MAP.put("books.isbn", "ISBN");
        EN_MAP.put("books.year", "Published year");
        EN_MAP.put("books.cover", "Cover");
        EN_MAP.put("books.open", "Open");
        EN_MAP.put("libraries.title", "Libraries");
        EN_MAP.put("libraries.heroTitle", "Library Management");
        EN_MAP.put("libraries.lead", "Keep library names and addresses up to date.");
        EN_MAP.put("libraries.total", "Total libraries");
        EN_MAP.put("libraries.name", "Name");
        EN_MAP.put("libraries.address", "Address");
        EN_MAP.put("members.title", "Readers");
        EN_MAP.put("members.heroTitle", "Reader Registry");
        EN_MAP.put("members.lead", "Manage reader profiles.");
        EN_MAP.put("members.total", "Total readers");
        EN_MAP.put("members.fullName", "Full name");
        EN_MAP.put("members.phone", "Phone");
        EN_MAP.put("book.details", "Book Details");
        EN_MAP.put("book.detailLead", "Detailed information about the selected book.");
        EN_MAP.put("book.backToList", "Back to list");
        EN_MAP.put("book.read", "Read");
        EN_MAP.put("book.language", "Language");
        EN_MAP.put("book.description", "Description");
        EN_MAP.put("book.untitled", "Untitled");
        EN_MAP.put("read.room", "Reading");
        EN_MAP.put("read.readBook", "Read Book");
        EN_MAP.put("common.email", "Email");
        EN_MAP.put("common.password", "Password");
        EN_MAP.put("common.actions", "Actions");
        EN_MAP.put("common.id", "ID");
        EN_MAP.put("common.add", "Add");
        EN_MAP.put("common.save", "Save");
        EN_MAP.put("common.edit", "Edit");
        EN_MAP.put("common.delete", "Delete");
        EN_MAP.put("common.cancel", "Cancel");
        EN_MAP.put("common.back", "Back");
        EN_MAP.put("common.total", "total");
        EN_MAP.put("common.role", "Role");
        EN_MAP.put("common.language", "Language");
        EN_MAP.put("common.kazakh", "Kazakh");
        EN_MAP.put("common.russian", "Russian");
        EN_MAP.put("common.profile", "Profile");
        EN_MAP.put("footer.brand", "Library System.");
    }

    private I18n() {
    }

    public static String getLang(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return RU;
        }
        Object value = session.getAttribute(LANG_SESSION_KEY);
        if (value == null) {
            return RU;
        }
        String lang = String.valueOf(value);
        return isValid(lang) ? lang : RU;
    }

    public static void setLang(HttpServletRequest request, String lang) {
        String resolved = isValid(lang) ? lang : RU;
        request.getSession(true).setAttribute(LANG_SESSION_KEY, resolved);
    }

    public static String t(HttpServletRequest request, String key) {
        String lang = getLang(request);
        Map<String, String> map = KK.equals(lang) ? KK_MAP : (EN.equals(lang) ? EN_MAP : RU_MAP);
        String value = map.get(key);
        if (value != null) {
            return value;
        }
        return RU_MAP.getOrDefault(key, key);
    }

    private static boolean isValid(String lang) {
        return RU.equals(lang) || KK.equals(lang) || EN.equals(lang);
    }
}



