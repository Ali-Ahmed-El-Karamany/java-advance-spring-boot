package com.pioneers.universitysystem.repositories;

import com.pioneers.universitysystem.models.entities.Student;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemoryDb implements Db {
    public final Map<UUID, Student> studentsDb = new ConcurrentHashMap<>();

    public void save(final Student student) {
        studentsDb.put(student.getId(), student);
    }

    @Override
    public void update(final UUID id, final Student student) {
        studentsDb.put(student.getId(), student);
    }

    @Override
    public void delete(final UUID id) {
        studentsDb.remove(id);
    }

    @Override
    public void clear() {
        studentsDb.clear();
    }

    @Override
    public Optional<Student> findByEmail(final String email) {
        return studentsDb.values()
                .stream()
                .filter(student -> student.getEmail().equals(email))
                .findFirst();
    }

    @Override
    public Optional<Student> findById(final UUID id) {
        return Optional.ofNullable(studentsDb.get(id));
    }

    @Override
    public Collection<Student> findAll() {
        return studentsDb.values();
    }

}
