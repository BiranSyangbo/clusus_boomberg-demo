package org.clusus.bloomberg.exception;

public class EntityAlreadyExist extends RuntimeException {
    private String message;

    public EntityAlreadyExist() {
        super();
    }

    public EntityAlreadyExist(String message) {
        super(message);
        this.message = message;
    }
}
