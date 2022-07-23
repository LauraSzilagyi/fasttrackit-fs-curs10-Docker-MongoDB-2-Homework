package ro.fasttrackit.curs10.homework.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ro.fasttrackit.curs10.homework.entity.Train;

import java.util.Optional;

public interface TrainRepository extends MongoRepository<Train, String> {
    Optional<Train> findByModel(String modelname);
}
