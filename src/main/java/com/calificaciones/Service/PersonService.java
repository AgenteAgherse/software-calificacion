package com.calificaciones.Service;

import com.calificaciones.Model.Estudiante;
import com.calificaciones.Model.Persona;
import com.calificaciones.Model.Profesor;
import com.calificaciones.Repository.PersonRepository;
import com.calificaciones.Repository.StudentRepository;
import com.calificaciones.Repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    @Autowired  private PersonRepository personRepository;
    @Autowired  private TeacherRepository teacherRepository;

    @Autowired private StudentRepository studentRepository;

    public void addPerson(Persona persona) {
        personRepository.save(persona);
    }

    public void addStudent(Estudiante estudiante) {
        studentRepository.save(estudiante);
    }
    public List<Persona> obtenerPersonas() {
        return personRepository.findAll();
    }

    public String getProfessorName(String user) {
        Optional<String> isNameAvailable = teacherRepository.getIdentification(user);

        if (isNameAvailable.isEmpty()) return "";

        Optional<Persona> persona = personRepository.findById(isNameAvailable.get());

        return persona.get().getName();
    }

    public Profesor getProfessor(String user) {
        Optional<Profesor> profesor = teacherRepository.getTeacherByUsername(user);
        return profesor.orElseGet(Profesor::new);
    }


    /**
     * Búsqueda de información personal de estudiantes por la materia.
     * @return
     */
    public ArrayList<Persona> getGroupOfStudentsInCourse(Integer id_materia) {
        Optional<ArrayList<Persona>> personas = personRepository.estudiantesRegistrados(id_materia);
        return personas.orElseGet(ArrayList::new);
    }
}
