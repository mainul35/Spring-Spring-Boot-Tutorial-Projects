package com.springtutorials.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sex")
public class Sex {
    @Id
    @Column(name = "sex_type_id")
    Integer typeId;
    @Column(name = "sex_type")
    String type;
    Boolean selected;

    public Sex() {
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Sex(String type) {
        this.type = type;
    }

    public Sex(String type, Boolean selected) {
        this.type = type;
        this.selected = selected;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
}
