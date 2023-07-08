package ru.hogwarts.school.service;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.repositories.FacultyRepository;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty findFacultyById(Long id) {
        return facultyRepository.findById(id).orElse(null);
    }

    public Faculty editFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }
    public void deleteFaculty(Long id) {
        facultyRepository.deleteById(id);
    }
    public Collection<Faculty> getAll() {
        return facultyRepository.findAll();
    }
    public Collection<Faculty> getAllByColor(String color) {
        return getAll().stream().filter(it -> it.getColor().equals(color)).collect(Collectors.toList());
    }
    public Faculty findByFacultyName(String name) {
        return facultyRepository.findByNameIgnoreCase(name);
    }
}

