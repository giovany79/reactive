package co.com.gcode.bookstorewebfunctionalendpoints.repository;


import co.com.gcode.bookstorewebfunctionalendpoints.model.Book;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;


public interface BookRepository extends ReactiveMongoRepository<Book, String>{

}


