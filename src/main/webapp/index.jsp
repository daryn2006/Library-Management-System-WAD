<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>О системе</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/style.css">
</head>
<body>
<div class="page">
    <nav class="nav">
        <div class="brand">
            <span class="brand-icon" aria-hidden="true">&#128214;</span>
            <span>Библиотечная система</span>
        </div>
        <div class="nav-links nav-main">
            <a href="<%= request.getContextPath() %>/login">Вход</a>
            <a href="<%= request.getContextPath() %>/register">Регистрация</a>
        </div>
    </nav>

    <main class="container">
        <section class="hero hero--home">
            <span class="eyebrow">О системе</span>
            <h1>Цифровая библиотека для читателей, авторов и администраторов.</h1>
            <p class="lead">
                Стартовая страница показывает основные возможности: каталог книг, поиск авторов,
                доступ к файлам или ссылкам на книги и профиль пользователя.
            </p>
            <div class="actions" style="margin-top: 8px;">
                <a class="button" href="<%= request.getContextPath() %>/login">Войти</a>
                <a class="button secondary" href="<%= request.getContextPath() %>/register">Создать аккаунт</a>
            </div>
        </section>

        <section class="cards">
            <div class="card">
                <span class="tag">Книги</span>
                <h3>Просмотр и загрузка</h3>
                <p class="lead">Карточка книги открывает файл для скачивания или внешнюю ссылку.</p>
                <p class="card-note">Внутри: поиск, открытие книги, чтение и скачивание.</p>
                <p class="card-motivation">Чем больше читаешь, тем сильнее становишься.</p>
            </div>
            <div class="card">
                <span class="tag">Авторы</span>
                <h3>Поиск по имени</h3>
                <p class="lead">Найти автора можно по имени или стране без лишних дубликатов.</p>
                <p class="card-note">Внутри: список авторов, фильтр и переход к книгам.</p>
                <p class="card-motivation">Начинай с автора, потом открывай его мир.</p>
            </div>
            <div class="card">
                <span class="tag">Админ</span>
                <h3>Читатели только для админа</h3>
                <p class="lead">Реестр читателей скрыт от обычных пользователей и защищён на сервере.</p>
                <p class="card-note">Внутри: учёт читателей, доступы и управление профилями.</p>
                <p class="card-motivation">Порядок в данных начинается с понятной структуры.</p>
            </div>
        </section>

        <section class="section">
            <div class="panel">
                <div class="row">
                    <div>
                        <span class="eyebrow">Галерея</span>
                        <h2 style="margin: 6px 0 0;">Подборка изображений библиотеки</h2>
                    </div>
                    <p class="muted" style="margin: 0;">Нажмите на карточку, чтобы открыть коллекцию.</p>
                </div>
                <div class="book-gallery">
                    <a class="gallery-card gallery-link" href="<%= request.getContextPath() %>/books" data-gallery-index="0">
                        <img src="https://images.unsplash.com/photo-1495446815901-a7297e633e8d?auto=format&fit=crop&w=900&q=80" alt="Bookshelf">
                        <div class="overlay">Каталог книг</div>
                    </a>
                    <a class="gallery-card gallery-link" href="<%= request.getContextPath() %>/authors" data-gallery-index="1">
                        <img src="https://images.unsplash.com/photo-1521587760476-6c12a4b040da?auto=format&fit=crop&w=900&q=80" alt="Library hall">
                        <div class="overlay">Авторы</div>
                    </a>
                    <a class="gallery-card gallery-link" href="<%= request.getContextPath() %>/libraries" data-gallery-index="2">
                        <img src="https://images.unsplash.com/photo-1512820790803-83ca734da794?auto=format&fit=crop&w=900&q=80" alt="Reading desk">
                        <div class="overlay">Библиотеки</div>
                    </a>
                </div>
            </div>
        </section>
    </main>

    <footer class="footer">
        Библиотечная система. Управление каталогом, читателями и профилем.
    </footer>
</div>

<div class="gallery-modal" id="galleryModal" aria-hidden="true">
    <div class="gallery-modal__backdrop" data-close-gallery></div>
    <div class="gallery-modal__dialog" role="dialog" aria-modal="true" aria-label="Галерея">
        <button class="gallery-modal__close" type="button" data-close-gallery>&times;</button>
        <img id="galleryModalImage" src="" alt="">
        <div class="gallery-modal__caption">
            <div>
                <strong id="galleryModalTitle"></strong>
                <p id="galleryModalText"></p>
                <p class="gallery-modal__hint" id="galleryModalHint"></p>
            </div>
            <div class="gallery-modal__controls">
                <a class="button secondary" id="galleryEnter" href="#">Открыть раздел</a>
                <button type="button" class="button secondary" id="galleryPrev">Назад</button>
                <button type="button" class="button" id="galleryNext">Вперёд</button>
            </div>
        </div>
    </div>
</div>

<script>
    (function () {
        const items = [
            {
                title: "Каталог книг",
                text: "Список литературы с поиском, карточкой книги и кнопкой загрузки.",
                hint: "Внутри раздел помогает быстро найти книгу и открыть её сразу.",
                src: "https://images.unsplash.com/photo-1495446815901-a7297e633e8d?auto=format&fit=crop&w=1400&q=80",
                href: "<%= request.getContextPath() %>/books"
            },
            {
                title: "Зал библиотеки",
                text: "Пространство для просмотра коллекций и работы с каталогом.",
                hint: "Здесь можно перейти к авторам и выбрать нужное направление чтения.",
                src: "https://images.unsplash.com/photo-1521587760476-6c12a4b040da?auto=format&fit=crop&w=1400&q=80",
                href: "<%= request.getContextPath() %>/authors"
            },
            {
                title: "Читальный стол",
                text: "Открытые книги можно читать онлайн или скачивать как файл.",
                hint: "Внутри есть доступ к книгам, скачиванию и личному профилю.",
                src: "https://images.unsplash.com/photo-1512820790803-83ca734da794?auto=format&fit=crop&w=1400&q=80",
                href: "<%= request.getContextPath() %>/libraries"
            }
        ];

        const modal = document.getElementById("galleryModal");
        const modalImage = document.getElementById("galleryModalImage");
        const modalTitle = document.getElementById("galleryModalTitle");
        const modalText = document.getElementById("galleryModalText");
        const modalHint = document.getElementById("galleryModalHint");
        const enterButton = document.getElementById("galleryEnter");
        const prevButton = document.getElementById("galleryPrev");
        const nextButton = document.getElementById("galleryNext");
        let currentIndex = 0;

        function render(index) {
            const item = items[index];
            modalImage.src = item.src;
            modalImage.alt = item.title;
            modalTitle.textContent = item.title;
            modalText.textContent = item.text;
            modalHint.textContent = item.hint;
            enterButton.href = item.href;
        }

        function open(index) {
            currentIndex = index;
            render(currentIndex);
            modal.classList.add("open");
            modal.setAttribute("aria-hidden", "false");
        }

        function close() {
            modal.classList.remove("open");
            modal.setAttribute("aria-hidden", "true");
        }

        prevButton.addEventListener("click", () => {
            currentIndex = (currentIndex - 1 + items.length) % items.length;
            render(currentIndex);
        });

        nextButton.addEventListener("click", () => {
            currentIndex = (currentIndex + 1) % items.length;
            render(currentIndex);
        });

        modal.addEventListener("click", (event) => {
            if (event.target.hasAttribute("data-close-gallery")) {
                close();
            }
        });

        document.addEventListener("keydown", (event) => {
            if (!modal.classList.contains("open")) {
                return;
            }
            if (event.key === "Escape") {
                close();
            }
            if (event.key === "ArrowLeft") {
                currentIndex = (currentIndex - 1 + items.length) % items.length;
                render(currentIndex);
            }
            if (event.key === "ArrowRight") {
                currentIndex = (currentIndex + 1) % items.length;
                render(currentIndex);
            }
        });
    }());
</script>
</body>
</html>
