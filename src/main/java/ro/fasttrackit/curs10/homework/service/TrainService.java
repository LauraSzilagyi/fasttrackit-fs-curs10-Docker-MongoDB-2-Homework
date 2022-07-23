package ro.fasttrackit.curs10.homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ro.fasttrackit.curs10.homework.entity.Location;
import ro.fasttrackit.curs10.homework.entity.Train;
import ro.fasttrackit.curs10.homework.exceptions.EntityNotFoundException;
import ro.fasttrackit.curs10.homework.exceptions.InvalidModelException;
import ro.fasttrackit.curs10.homework.filter.TrainFilter;
import ro.fasttrackit.curs10.homework.model.TrainModel;
import ro.fasttrackit.curs10.homework.repository.TrainDao;
import ro.fasttrackit.curs10.homework.repository.TrainRepository;

import java.util.Optional;

import static java.util.UUID.randomUUID;

@Service
@RequiredArgsConstructor
public class TrainService {
    private final TrainRepository repository;
    private final TrainDao dao;
    private final LocationService locationService;

    public Page<Train> getAll(TrainFilter filters, Pageable pageable) {
        return dao.findBy(filters, pageable);
    }

    public Train addNewTrain(TrainModel model) {
        validateModel(model);
        Location locationByCityName = locationService.findLocationByCityName(model.location());
        Train entity = Train.builder()
                .id(randomUUID().toString())
                .carts(model.carts())
                .model(model.modelName())
                .locationId(locationByCityName.id())
                .build();
        return repository.save(entity);

    }

    private void validateModel(TrainModel model) {
        verifyModel(model);
        locationService.verifyIfLocationExists(model.location());
    }

    private void verifyModel(TrainModel model) {
        if (model.modelName() == null
                || model.location() == null
                || model.carts() == null
                || model.carts() < 0) {
            throw new InvalidModelException("Must contains model, carts and location!");
        }
    }

    public Optional<Train> deleteTrain(String id) {
        Optional<Train> train = repository.findById(id);
        train.ifPresent(repository::delete);
        return train;
    }

    public Train updateTrain(String id, TrainModel model) {
        validateModel(model);
        Train train = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Train with id %s doesn't exist".formatted(id)));
        Location locationByCityName = locationService.findLocationByCityName(model.location());
        Train newEntity = Train.builder()
                .id(train.id())
                .locationId(locationByCityName.id())
                .model(train.model())
                .carts(model.carts())
                .build();
        return repository.save(newEntity);
    }


}
