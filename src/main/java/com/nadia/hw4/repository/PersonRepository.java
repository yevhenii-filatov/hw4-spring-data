package com.nadia.hw4.repository;

import com.nadia.hw4.model.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Yevhenii Filatov
 * @since 6/5/23
 */

@Repository
@Transactional
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {
    Optional<PersonEntity> findOneByName(String name);

    List<PersonEntity> findAllByName(String name);

    List<PersonEntity> findAllByAgeBetween(Integer from, Integer to);

    @Modifying
    @Query("update PersonEntity person set person.name = :name where person.id = :id")
    void updateNameById(@Param("id") Long id, @Param("name") String name);

    @Modifying
    @Query(value = "DELETE FROM person WHERE name = :name", nativeQuery = true)
    void deleteAllByName(@Param("name") String name);
}
