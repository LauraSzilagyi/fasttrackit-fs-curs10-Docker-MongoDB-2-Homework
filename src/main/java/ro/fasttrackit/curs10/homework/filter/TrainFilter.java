package ro.fasttrackit.curs10.homework.filter;

public record TrainFilter(
        String locationId,
        String modelName,
        Integer minVagoane,
        Integer maxVagoane
) {
}
