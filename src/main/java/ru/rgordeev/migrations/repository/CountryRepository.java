package ru.rgordeev.migrations.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rgordeev.migrations.model.Country;

public interface CountryRepository extends JpaRepository<Country, String> {

}
