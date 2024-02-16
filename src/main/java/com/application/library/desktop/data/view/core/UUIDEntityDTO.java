package com.application.library.desktop.data.view.core;

import java.util.UUID;

public class UUIDEntityDTO extends TimestampEntityDTO {
    private UUID id;

    public UUIDEntityDTO() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
