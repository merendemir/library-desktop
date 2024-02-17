package com.application.library.desktop.enumerations;

public enum SortDirection {
    ASC("Ascending"),
    DESC("Descending");

    private final String description;

    SortDirection(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static SortDirection getByDescription(String name) {
        for (SortDirection sortDirection : SortDirection.values()) {
            if (sortDirection.getDescription().equals(name)) {
                return sortDirection;
            }
        }
        return null;
    }
}
