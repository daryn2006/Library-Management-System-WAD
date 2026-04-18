package kz.enu.vtrestapi.pattern;

import kz.example.lms.model.BookText;

public final class BookAccessFactory {

    private BookAccessFactory() {
    }

    public static BookAccess create(BookText bookText) {
        if (bookText == null) {
            return new BookAccess("Unavailable", "#", false);
        }
        if (bookText.getSourceUrl() != null && !bookText.getSourceUrl().isBlank()) {
            return new BookAccess("Open link", bookText.getSourceUrl(), false);
        }
        return new BookAccess("Download file", "/download?id=" + bookText.getBookId(), true);
    }

    public record BookAccess(String label, String href, boolean download) {
    }
}
