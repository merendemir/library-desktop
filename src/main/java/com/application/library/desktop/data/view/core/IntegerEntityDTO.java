package com.application.library.desktop.data.view.core;

public class IntegerEntityDTO extends TimestampEntityDTO {
    private Long id;

    public IntegerEntityDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "IntegerEntityDTO{" +
                "id=" + id +
                '}';
    }
}
