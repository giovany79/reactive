package co.com.gcode.bookstoreweb;

import co.com.gcode.bookstoreweb.co.com.gcode.bookstoreweb.model.Book;
import co.com.gcode.bookstoreweb.co.com.gcode.bookstoreweb.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;


@SpringBootApplication
public  class ApplicationBookStoreWeb {
    public  static void main(String[] args) {
        SpringApplication.run(ApplicationBookStoreWeb.class, args);
    }

    /**
     * CommandLineRunner se usa para ejecutar codigo una vez finalice el inicio.
     * Para este ejercicio inicializa valores en la base de datos
     * @param repository
     * @return lambda expresion
     */
    @Bean
    CommandLineRunner init(BookRepository repository){
        return args -> {

            Flux<Book> books = Flux.just(
                    Book.builder().title("Einstein").author("Walter Isaacson").yearPublisher(2007).pages(675).price(90000.00).build(),
                    Book.builder().title("Elon Musk").author("Ashlee Vance").yearPublisher(2015).pages(400).price(60000.00).build(),
                    Book.builder().title("Por otro camino").author("Carlos Raul Yepes").yearPublisher(2016).pages(244).price(49000.00).build()
            ).flatMap(p-> repository.save(p));

            books.thenMany(repository.findAll())
                    .subscribe(System.out::println);

        };
    }
}
