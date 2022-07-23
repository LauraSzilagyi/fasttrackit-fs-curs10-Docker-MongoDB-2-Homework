package ro.fasttrackit.curs10.homework.entity;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "routes")
@Builder
public record Route(
        @Id
        String id,
        Location start,
        Location destination,
        Integer length,
        String trainId) {
}
