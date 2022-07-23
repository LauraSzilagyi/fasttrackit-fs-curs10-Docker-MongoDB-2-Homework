package ro.fasttrackit.curs10.homework.entity;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Document(collection = "trains")
public record Train(
        @Id
        String id,
        String model,
        Integer carts,
        String locationId) {
}
