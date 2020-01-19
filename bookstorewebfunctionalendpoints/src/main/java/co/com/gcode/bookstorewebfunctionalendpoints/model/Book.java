package co.com.gcode.bookstorewebfunctionalendpoints.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
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