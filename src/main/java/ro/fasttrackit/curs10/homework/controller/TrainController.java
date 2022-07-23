package ro.fasttrackit.curs10.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.curs10.homework.entity.Train;
import ro.fasttrackit.curs10.homework.filter.TrainFilter;
import ro.fasttrackit.curs10.homework.model.TrainModel;
import ro.fasttrackit.curs10.homework.service.TrainService;

@RestController
@RequestMapping("trains")
@RequiredArgsConstructor
public class TrainController {
    private final TrainService service;

    @GetMapping
    Page<Train> getAll(TrainFilter filters, Pageable pageable) {
        return service.getAll(filters, pageable);
    }

    @PostMapping
    Train addNewTrain(@RequestBody TrainModel model) {
        return service.addNewTrain(model);
    }

    @DeleteMapping("{id}")
    Train deleteTrain(@PathVariable String id) {
        return service.deleteTrain(id).orElse(null);
    }

    @PatchMapping("{id}")
    Train updateTrain(@PathVariable String id, @RequestBody TrainModel model){
        return service.updateTrain(id, model);
    }
}
