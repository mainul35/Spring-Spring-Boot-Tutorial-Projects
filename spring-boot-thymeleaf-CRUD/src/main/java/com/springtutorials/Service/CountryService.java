package com.springtutorials.Service;

import com.springtutorials.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CountryService {

    public Country getCountryById(Integer id) {
        return this.findAll().stream()
                .filter(p -> Objects.equals(p.getCountryId(), id))
                .findFirst()
                .orElse(null);
    }

    public boolean exists(Integer id) {
        return this.getCountryById(id) instanceof Country ? true : false;
    }

    public List<Country> findAll() {
        List<Country> countries = new ArrayList();
        countries.add(new Country(1, "Bangladesh", false));
        countries.add(new Country(2, "India", false));
        countries.add(new Country(3, "Japan", false));
        countries.add(new Country(4, "Canada", false));
        countries.add(new Country(5, "France", false));
        return countries;
    }
}
