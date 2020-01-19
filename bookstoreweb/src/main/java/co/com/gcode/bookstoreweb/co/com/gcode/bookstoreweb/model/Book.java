package co.com.gcode.bookstoreweb.co.com.gcode.bookstoreweb.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@Data
@Builder
public class Book {

    @Id
    private String id;

    private String title;
    private String author;
    private int yearPublisher;
    private int pages;
    private Double price;
}
