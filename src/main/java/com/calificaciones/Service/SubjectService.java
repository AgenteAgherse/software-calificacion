package com.calificaciones.Service;

import com.calificaciones.Model.Materia;
import com.calificaciones.Repository.GradeRepository;
import com.calificaciones.Repository.HomeworkRepository;
import com.calificaciones.Repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    @Autowired private SubjectRepository subjectRepository;
    @Autowired private HomeworkRepository homeworkRepository;
    @Autowired private GradeRepository gradeRepository;


    public HashMap<Integer, String> getSubjectsByProfessor(Integer id) {
        Optional<List<Materia>> materias = subjectRepository.obtenerMaterias(id);
        if (materias.isEmpty()) return new HashMap<>();

        HashMap<Integer, String> nombresMaterias = new HashMap<>();
        for (Materia materia: materias.get()) {
            nombresMaterias.put(materia.getId(), materia.getName());
        }

        return nombresMaterias;
    }

    public void addSubject(Materia materia) {
        subjectRepository.save(materia);
    }

    public Materia getSubject(Integer subject) {
        return subjectRepository.findById(subject).orElse(null);
    }

    public void deleteSubject(Integer id) {
        gradeRepository.deleteBySubject(id);
        homeworkRepository.deleteBySubject(id);
        subjectRepository.deleteById(id);
    }
}
