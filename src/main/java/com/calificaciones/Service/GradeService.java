package com.calificaciones.Service;

import com.calificaciones.Model.Estudiante;
import com.calificaciones.Repository.GradeRepository;
import com.calificaciones.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GradeService {

    @Autowired private GradeRepository gradeRepository;
    @Autowired private StudentRepository studentRepository;

    public void deleteGradesByStudent(String id_student) {
        Optional<Estudiante> estudiante = studentRepository.findByIdentification(id_student);
        estudiante.ifPresent(value -> gradeRepository.deleteByStudent(value.getId()));

    }
}
