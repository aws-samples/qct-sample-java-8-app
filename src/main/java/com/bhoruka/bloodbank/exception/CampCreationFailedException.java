package com.bhoruka.bloodbank.exception;

public class CampCreationFailedException extends RuntimeException {

    /**
     * Thrown when there is a problem while creating a camp in the DB.
     * @param message The cause of the failure
     */
    public CampCreationFailedException(final String message) {
        super(message);
    }
}
