package example.bookprogressapp.series;

import example.bookprogressapp.book.BookDTO;

import java.util.List;

public class SeriesDTO {

    private Long seriesId;
    private String seriesTitle;
    private List<BookDTO> booksList;

    public Long getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(Long seriesId) {
        this.seriesId = seriesId;
    }

    public String getSeriesTitle() {
        return seriesTitle;
    }

    public void setSeriesTitle(String seriesTitle) {
        this.seriesTitle = seriesTitle;
    }

    public List<BookDTO> getBooksList() {
        return booksList;
    }

    public void setBooksList(List<BookDTO> booksList) {
        this.booksList = booksList;
    }
}
