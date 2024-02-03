package spring.webflux.book;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class BookDto {
    @Getter
    @Setter
    public static class Post {

        private String titleKorean;
        private String titleEnglish;
        private String description;
        private String author;
        private String isbn;
        private String publishDate;

    }

    @Getter
    @Setter
    public static class Patch {

        private long bookId;
        private String titleKorean;
        private String titleEnglish;
        private String description;
        private String author;
        private String isbn;
        private String publishDate;
    }

    @Builder
    @Getter
    public static class Response {
        private long bookId;
        private String titleKorean;
        private String titleEnglish;
        private String description;
        private String author;
        private String isbn;
        private String publishDate;
    }
}