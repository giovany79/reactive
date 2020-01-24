package co.com.gcode.bookstorewebfunctionalendpoints.controller;

import co.com.gcode.bookstorewebfunctionalendpoints.model.Book;
import co.com.gcode.bookstorewebfunctionalendpoints.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
     * @param ServerRequst que contiene la peticion http
     * @return ServerResponse que contiene la respuesta http
     */
    public Mono<ServerResponse> getAllBooks(ServerRequest request){
        Flux<Book> books = repository.findAll();
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(books, Book.class);
    }

    /**
     * Retorna un libro especifico enviado en el path con el id
     * @param ServerRequst que contiene la peticion http
     * @return ServerResponse que contiene la respuesta http
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
     * @param ServerRequst que contiene la peticion http
     * @return ServerResponse que contiene la respuesta http
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
     * @param ServerRequst que contiene la peticion http
     * @return ServerResponse que contiene la respuesta http
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

    /**
     * Controlador que elimina un libro en base a su id
     * @param ServerRequst que contiene la peticion http
     * @return ServerResponse que contiene la respuesta http
     */
    public Mono<ServerResponse> deleteBook(ServerRequest request){
        String id = request.pathVariable("id");
        repository.findById(id)
                .flatMap(existingBook -> repository.delete(existingBook)
                        .then(Mono.just(ResponseEntity.ok().<Void>build())))
                .defaultIfEmpty(ResponseEntity.notFound().build());
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just("Eliminacion exitosa"), String.class);
    }

    /**
     * Controlador que elimina todos los libros
     * @param ServerRequst que contiene la peticion http
     * @return ServerResponse que contiene la respuesta http
     */
    public Mono<ServerResponse> deleteAllBooks(ServerRequest request){
        repository.deleteAll();
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just("Eliminacion exitosa"), String.class);
    }


}
