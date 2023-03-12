package ru.practicum.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.practicum.main.models.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByName(String name);

    @Query(value = "SELECT * from USERS " +
            "limit :size offset :from", nativeQuery = true)
    List<User> findAllUserWithLimitAndOffset(@Param("from") Integer from, @Param("size") Integer size);
}
