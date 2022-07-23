package ro.fasttrackit.curs10.homework.model;

public record RouteModel(
        String start,
        String destination,
        Integer length,
        String trainModel) {
}
