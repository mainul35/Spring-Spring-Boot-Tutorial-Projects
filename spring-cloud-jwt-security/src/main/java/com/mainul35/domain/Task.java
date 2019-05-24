package com.mainul35.domain;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Task {
    private String id;
    private String description;

    protected Task() { }

    public Task(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}