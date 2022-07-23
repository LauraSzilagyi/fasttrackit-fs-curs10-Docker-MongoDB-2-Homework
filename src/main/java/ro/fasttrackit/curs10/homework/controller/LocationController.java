package ro.fasttrackit.curs10.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.curs10.homework.entity.Location;
import ro.fasttrackit.curs10.homework.model.LocationModel;
import ro.fasttrackit.curs10.homework.service.LocationService;

import java.util.List;

@RestController
@RequestMapping("locations")
@RequiredArgsConstructor
public class LocationController {
    private final LocationService service;

    @GetMapping
    List<Location> getAll() {
        return service.getAll();
    }

    @PostMapping
    Location addNewLocation(@RequestBody LocationModel model) {
        return service.addNewLocation(model);
    }

    @DeleteMapping("{id}")
    Location deleteLocation(@PathVariable String id) {
        return service.deleteLocation(id).orElse(null);
    }
}
