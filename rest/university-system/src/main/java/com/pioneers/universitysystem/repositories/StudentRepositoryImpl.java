package com.pioneers.universitysystem.repositories;

import com.pioneers.universitysystem.errors.exceptions.RegistrationException;
import com.pioneers.universitysystem.models.entities.Student;
import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Repository
@RequiredArgsConstructor
public class StudentRepositoryImpl implements StudentRepository {

    private final EntityManager entityManager;

    @Override
    @Transactional
    public void save(final Student student) throws RegistrationException{
        final String methodName = "save";

        if (isEmailExists(student.getEmail())) {
            final String logMessage = String.format("%s, Registration failed: [%s] is already registered",
                    methodName, student.getEmail());

            throw new RegistrationException(
                    String.format("Student with email [%s] is already registered", student.getEmail()), logMessage);
        }

        entityManager.persist(student);

        log.debug("{}, Saved student with email = [{}] and id = [{}] to the database",
                methodName, student.getEmail(), student.getId());
    }

    @Override
    @Transactional
    public void update(final Student student) {
        final String methodName = "update";

        final String sql = "UPDATE students SET full_name = :fullName, age = :age, email = :email," +
                " password = :password, is_login = :isLogin, score = :score, final_score = :finalScore," +
                " created_at = :createdAt WHERE id = :id";

        final Query query = entityManager.createNativeQuery(sql);

        query.setParameter("id", student.getId());
        query.setParameter("fullName", student.getFullName());
        query.setParameter("age", student.getAge());
        query.setParameter("email", student.getEmail());
        query.setParameter("password", student.getPassword());
        query.setParameter("isLogin", student.isLoggedIn());
        query.setParameter("score", student.getScore());
        query.setParameter("finalScore", student.getFinalScore());
        query.setParameter("createdAt", student.getCreatedAt());

        query.executeUpdate();

        log.debug("{}, student with id [{}] has been updated into database", methodName, student.getId());
    }

    @Override
    @Transactional
    public void updateAll(final List<Student> students) {
        final String methodName = "updateAll";

        students.forEach(this::update);

        log.info("{}, Successfully updated all students", methodName);
    }

    @Override
    @Transactional
    public void delete(final UUID id) {
        final String methodName = "delete";
        final String jpql = "DELETE FROM Student s WHERE s.id = :id";

        final Query query = entityManager.createQuery(jpql);
        query.setParameter("id", id).executeUpdate();

        log.debug("{}, student with id [{}] has been deleted from database", methodName, id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        final String methodName = "deleteAll";
        final String jpql = "DELETE FROM Student";

        entityManager.createQuery(jpql).executeUpdate();

        log.debug("{}, All students have been deleted from database", methodName);
    }

    @Override
    public Optional<Student> findByEmail(final String email) {
        final String methodName = "findByEmail";
        final String jpql = "SELECT s FROM  Student s WHERE s.email = :email AND s.isArchived = FALSE";

        final TypedQuery<Student> typedQuery = entityManager.createQuery(jpql, Student.class);
        typedQuery.setParameter("email", email);

        try {
            final Student student = typedQuery.getSingleResult();
            log.debug("{}, Student {} with email [{}] successfully found", methodName, student.getFullName(), email);

            return Optional.of(student);
        } catch (final NoResultException e) {
            log.debug("{}, No student found with email [{}]", methodName, email);

            return Optional.empty();
        }
    }

    @Override
    public Optional<Student> findById(final UUID id) {
        final String methodName = "findById";
        final String jpql = "SELECT s FROM Student s WHERE s.id = :id AND s.isArchived = FALSE";

        final TypedQuery<Student> typedQuery = entityManager.createQuery(jpql, Student.class);
        typedQuery.setParameter("id", id);

        try {
            final Student student = typedQuery.getSingleResult();
            log.debug("{}, Student {} with ID [{}] successfully found in database", methodName, student.getFullName(), id);

            return Optional.of(student);
        } catch (final NoResultException e) {
            log.debug("{}, No student found with ID [{}]", methodName, id);

            return Optional.empty();
        }
    }

    @Override
    public List<Student> findAllSortedByAge() {
        final String methodName = "findAllSortedByAge";

        final List<Student> students = entityManager
                .createQuery("SELECT s FROM Student s WHERE s.isArchived = FALSE ORDER BY s.age ASC", Student.class)
                .getResultList();

        log.debug("{}, Successfully retrieved {} students sorted by age ascending from database", methodName, students.size());

        return students;
    }

    @Override
    public List<Student> findAllSucceeded() {
        final String methodName = "findAllSucceeded";

        final List<Student> students = entityManager.createQuery(
                        "SELECT s FROM Student s WHERE s.score > s.finalScore * 0.5 AND s.isArchived = FALSE",
                        Student.class)
                .getResultList();

        log.debug("{}, Successfully retrieved {} succeeded students from database", methodName, students.size());

        return students;
    }

    @Override
    public boolean isEmailExists(final String email) {
        final String jpql = "SELECT COUNT(s) FROM Student s WHERE s.email = :email";

        final Long count = entityManager
                .createQuery(jpql, Long.class)
                .setParameter("email", email)
                .getSingleResult();

        return count > 0;
    }
}
