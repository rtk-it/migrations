package ru.rgordeev.migrations.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rgordeev.migrations.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
