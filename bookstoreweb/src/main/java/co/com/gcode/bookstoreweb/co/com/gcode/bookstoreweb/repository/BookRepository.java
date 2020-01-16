package co.com.gcode.bookstoreweb.co.com.gcode.bookstoreweb.repository;

import co.com.gcode.bookstoreweb.co.com.gcode.bookstoreweb.model.Book;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;


public interface BookRepository extends ReactiveMongoRepository<Book, String>{

}
