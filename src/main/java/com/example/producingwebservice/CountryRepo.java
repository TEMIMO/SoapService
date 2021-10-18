package com.example.producingwebservice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepo extends JpaRepository<CountryDB, Long> {
    CountryDB findByName(String name);

}
