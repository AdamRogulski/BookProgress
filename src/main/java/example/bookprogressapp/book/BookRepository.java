package example.bookprogressapp.book;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {
    List<Book> findAllBySeriesNull();
    List<Book> findTop8ByOrderByAddedDateDesc();
    List<Book> findAllByFavouriteIsTrue();
}
