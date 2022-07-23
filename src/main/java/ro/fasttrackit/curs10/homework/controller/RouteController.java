package ro.fasttrackit.curs10.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.curs10.homework.entity.Route;
import ro.fasttrackit.curs10.homework.model.RouteModel;
import ro.fasttrackit.curs10.homework.service.RouteService;

import java.util.List;

@RestController
@RequestMapping("routes")
@RequiredArgsConstructor
public class RouteController {

    private final RouteService service;

    @GetMapping
    List<Route> getAll() {
        return service.getAll();
    }

    @PostMapping
    Route addNewRoute(@RequestBody RouteModel model) {
        return service.addNewRoute(model);
    }
}
