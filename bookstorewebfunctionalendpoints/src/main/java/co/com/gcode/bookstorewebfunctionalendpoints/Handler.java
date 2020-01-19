package co.com.gcode.bookstorewebfunctionalendpoints;

import co.com.gcode.bookstorewebfunctionalendpoints.model.Book;
import co.com.gcode.bookstorewebfunctionalendpoints.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Component
public class Handler {
    BookRepository repository;


    /**
     * Retorna todos los libros de la libreria
     * @return ServerResponse response
     */
    public Mono<ServerResponse> getAllBooks(){
        Flux<Book> books = repository.findAll();
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(books, Book.class);
    }

    /**
     * Retorna el libro asociado al id
     * @return ServerResponse response
     */
    public Mono<ServerResponse> getBook(ServerRequest request){
        String id = request.pathVariable("id");
        Mono<Book> book = repository.findById(id);
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(book, Book.class);
    }

    /**
     * Almacena un libro enviado en el body
     * @param ServerRequest request
     * @return ServerResponse response
     */
    public Mono<ServerResponse> saveBook(ServerRequest request){
        Mono<Book> bookMono = request.bodyToMono(Book.class);
        return bookMono.flatMap(book->
                ServerResponse.status(HttpStatus.CREATED)
                              .contentType(MediaType.APPLICATION_JSON)
                              .body(repository.save(book),Book.class));

    }

    /**
     * Actualiza un libro existente en base a su id y a la entidad enviada en el body
     * @param id
     * @param ServerRequest request
     * @return ServerResponse response
     */
    public Mono<ServerResponse> updateBook(ServerRequest request){
        String id = request.pathVariable("id");
        Mono<Book> existingBookMono = repository.findById(id);
        Mono<Book> bookMono = request.bodyToMono(Book.class);
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        return  bookMono.zipWith(existingBookMono,
                (book, existingBook)-> Book.builder()
                        .id(existingBook.getId())
                        .title(book.getTitle())
                        .pages(book.getPages())
                        .price(book.getPrice())
                        .yearPublisher(book.getYearPublisher())
                        .author(book.getAuthor())
                        .build()).flatMap(product -> ServerResponse.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(repository.save(product), Book.class)).switchIfEmpty(notFound);
    }


}
