package ro.fasttrackit.curs10.homework.exceptions;

public class TrainNotInTheStartLocationException extends RuntimeException {
    public TrainNotInTheStartLocationException(String message) {
        super(message);
    }
}
