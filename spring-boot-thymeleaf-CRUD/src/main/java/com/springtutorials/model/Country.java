package com.springtutorials.model;

import javax.persistence.*;

public class Country {

    Integer countryId;
    String countryName;
    Boolean selected;

    public Country() {
    }

    public Country(Integer countryId, String countryName) {
        this.countryId = countryId;
        this.countryName = countryName;
    }

    public Country(Integer countryId, String countryName, Boolean selected) {
        this.countryId = countryId;
        this.countryName = countryName;
        this.selected = selected;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public String toString() {
        return "Country{" +
                "countryId=" + countryId +
                ", countryName='" + countryName + '\'' +
                ", selected=" + selected +
                '}';
    }
}
