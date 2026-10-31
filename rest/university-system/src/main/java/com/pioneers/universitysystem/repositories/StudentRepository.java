package com.pioneers.universitysystem.repositories;

import com.pioneers.universitysystem.models.entities.Student;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for managing {@link Student} entity.
 *
 * <p>Extends {@link JpaRepository} to provide standard CRUD operations
 * and defines additional queries for retrieving, updating, and
 * soft-deleting student records.</p>
 *
 * <p>Soft deletion is performed by setting the {@code isArchived}
 * property to {@code true}, rather than physically removing records
 * from the database.</p>
 */
public interface StudentRepository extends JpaRepository<Student, UUID> {

    /**
     * Retrieves an active student by their unique identifier.
     *
     * @param id the student's unique identifier
     * @return an {@link Optional} containing the student if found and if not archived;
     *         otherwise, an empty Optional
     */
    Optional<Student> findByIdAndIsArchivedFalse(UUID id);

    /**
     * Retrieves an active student by their email address.
     *
     * @param email the email address associated with the student
     * @return an {@link Optional} containing the student if found and if not archived;
     *         otherwise, an empty Optional
     */
    Optional<Student> findByEmailAndIsArchivedFalse(String email);

    /**
     * Retrieves all active students, ordered by age in ascending order.
     *
     * @return a list of active students sorted by age;
     *         an empty list if no active students are found
     */
    List<Student> findAllByIsArchivedIsFalseOrderByAge();

    /**
     * Retrieves all active students whose scores exceed 50% of their final scores.
     *
     * @return a list of succeeded students;
     *         an empty list if no succeeded students are found
     */
    @Query("""
        SELECT s
        FROM Student s
        WHERE s.score > s.finalScore * 0.5
          AND s.isArchived = FALSE
        """)
    List<Student> findAllSucceeded();

    /**
     * Retrieves a student by their email address disregarding its archive status.
     *
     * @param email the email address associated with the student
     * @return an {@link Optional} containing the student if found;
     *         otherwise, an empty Optional
     */
    Optional<Student> findByEmail(String email);

    /**
     * Soft-deletes a student by their unique ID.
     *
     * <p>Marks the student as archived and sets their login status to false.
     * The student record remains in the database.</p>
     *
     * @param id the unique identifier of the student to soft-delete
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE Student s SET s.isArchived = TRUE, s.isLoggedIn = FALSE WHERE s.id = :id")
    void deleteById(@Param("id") @NonNull UUID id);


    /**
     * Soft-deletes all students.
     *
     * <p>Marks every student in the database as archived and sets their login status to false.
     * No student records are physically removed from the database.</p>
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE Student s SET s.isArchived = TRUE, s.isLoggedIn = FALSE")
    void deleteAll();
}
