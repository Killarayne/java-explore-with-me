package ru.practicum.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.practicum.main.models.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Boolean existsByName(String name);

    @Query(value = "SELECT * from CATEGORIES " +
            "limit :size offset :from", nativeQuery = true)
    List<Category> findAllCategoriesWithLimitAndOffset(@Param("from") Integer from, @Param("size") Integer size);
}
