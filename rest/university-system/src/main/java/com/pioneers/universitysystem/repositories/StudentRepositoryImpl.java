package com.pioneers.universitysystem.repositories;

import com.pioneers.universitysystem.models.entities.Student;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Repository
@RequiredArgsConstructor
public class StudentRepositoryImpl implements StudentRepository {

    private final Db db;

    @Override
    public void save(final Student student) {
        final String methodName = "save";
        db.save(student);
        log.debug("{}, Saved student with email = [{}] and id = [{}] to the DB", methodName, student.getEmail(), student.getId());
    }

    @Override
    public void update(final UUID id, final Student student) {
        final String methodName = "update";
        db.update(id, student);
        log.debug("{}, student with id [{}] has been updated into DB", methodName, student.getId());
    }

    @Override
    public void delete(final UUID id) {
        final String methodName = "delete";
        db.delete(id);
        log.debug("{}, student with id [{}] has been deleted from DB", methodName, id);
    }

    @Override
    public void deleteAll() {
        final String methodName = "deleteAll";
        db.clear();
        log.debug("{}, All students have been deleted from DB", methodName);
    }

    @Override
    public Optional<Student> findByEmail(final String email) {
        return db.findByEmail(email);
    }

    @Override
    public Optional<Student> findById(final UUID id) {
        return db.findById(id);
    }

    @Override
    public List<Student> findAllSortedByAge() {
        return db.findAll()
                .stream()
                .sorted(Comparator.comparingInt(Student::getAge))
                .toList();
    }

    @Override
    public List<Student> findAllSucceeded() {
        return db.findAll()
                .stream()
                .filter(Student::isPassedExam)
                .toList();
    }
}
