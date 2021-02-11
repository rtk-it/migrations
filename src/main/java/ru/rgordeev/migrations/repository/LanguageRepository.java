package ru.rgordeev.migrations.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rgordeev.migrations.model.Country;

public interface LanguageRepository extends JpaRepository<Country, String> {

}
