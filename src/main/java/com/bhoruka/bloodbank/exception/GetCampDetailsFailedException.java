package com.bhoruka.bloodbank.exception;

public class GetCampDetailsFailedException extends RuntimeException {

    /**
     * Thrown when there is a problem while fetching details of the camp from the DB.
     * @param message The cause of the failure
     */
    public GetCampDetailsFailedException(final String message) {
        super(message);
    }
}
