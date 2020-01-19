package co.com.gcode.bookstoreweb.co.com.gcode.bookstoreweb.controller;

import co.com.gcode.bookstoreweb.co.com.gcode.bookstoreweb.model.Book;
import co.com.gcode.bookstoreweb.co.com.gcode.bookstoreweb.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/books")
@AllArgsConstructor
public class    BookController {

    private BookRepository repository;

    /**
     * Controlador que devuelve todos los libros
     * @return Flux<Book>
     */
    @GetMapping
    public Flux<Book> getAllBooks(){
        return repository.findAll();
    }

    /**
     * Controlador que devuelve un libro identificado con el id
     * @return Mono<ResponseEntity<Book>>
     */
    @GetMapping("{id}")
    public Mono<ResponseEntity<Book>> getBook(@PathVariable String id){
        return repository.findById(id).map(book -> ResponseEntity.ok(book))
                         .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    /**
     * Controlador que almacena un libro enviado en el body
     * @param book
     * @return Mono<Book>
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Book> saveBook(@RequestBody Book book){
        return repository.save(book);

    }

    /**
     * Controlador que actualiza un libro existente en base a su id y a la entidad enviada en el body
     * @param id
     * @param book
     * @return Mono<ResponseEntity<Book>>
     */
    @PutMapping("{id}")
    public Mono<ResponseEntity<Book>> updateBook(@PathVariable(value="id") String id, @RequestBody Book book){
        return  repository.findById(id)
                .flatMap(existingBook -> {
                    existingBook.setAuthor(book.getAuthor());
                    existingBook.setPages(book.getPages());
                    existingBook.setPrice(book.getPrice());
                    existingBook.setTitle(book.getTitle());
                    return repository.save(existingBook);
                }).map(updateBook -> ResponseEntity.ok(updateBook))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }


    /**
     * Controlador que elimina un libro en base a su id
     * @param id
     * @return <ResponseEntity<Void>>
     */
    @DeleteMapping("{id}")
    public Mono<ResponseEntity<Void>> deleteBook(@PathVariable(value = "id") String id){
        return repository.findById(id)
                .flatMap(existingBook -> repository.delete(existingBook)
                .then(Mono.just(ResponseEntity.ok().<Void>build())))
        .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    /**
     * Controlador que elimina todos los libros
     * @return Mono<Void>
     */
    @DeleteMapping
    public Mono<Void> deleteAllBooks(){
        return repository.deleteAll();
    }

}
