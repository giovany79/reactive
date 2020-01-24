package co.com.gcode.bookstorewebfunctionalendpoints.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class Route {

    private static final  String ROUTE_BOOKS = "/books";
    private static final  String ROUTE_BOOK_BY_ID = "/books/{id}";


    @Bean
    RouterFunction<ServerResponse> routes(Handler handler){
        return route(GET(ROUTE_BOOKS).and(accept(MediaType.APPLICATION_JSON)), handler::getAllBooks)
                .andRoute(GET(ROUTE_BOOK_BY_ID).and(accept(MediaType.APPLICATION_JSON)), handler::getBook)
                .andRoute(POST(ROUTE_BOOKS).and(accept(MediaType.APPLICATION_JSON)), handler::saveBook)
                .andRoute(PUT(ROUTE_BOOK_BY_ID).and(accept(MediaType.APPLICATION_JSON)), handler::updateBook)
                .andRoute(DELETE(ROUTE_BOOK_BY_ID).and(accept(MediaType.APPLICATION_JSON)), handler::deleteBook)
                .andRoute(DELETE(ROUTE_BOOKS).and(accept(MediaType.APPLICATION_JSON)), handler::deleteAllBooks);
    }
}
