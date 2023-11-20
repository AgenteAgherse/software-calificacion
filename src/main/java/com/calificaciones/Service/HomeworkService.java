package com.calificaciones.Service;

import com.calificaciones.Model.Tarea;
import com.calificaciones.Repository.HomeworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class HomeworkService {

    @Autowired private HomeworkRepository homeworkRepository;

    public void addHomework(Tarea tarea) {
        homeworkRepository.save(tarea);
    }


    public ArrayList<Tarea> getListOfHomeworks(Integer id_materia) {
        return homeworkRepository.hwBySubject(id_materia).orElseGet(ArrayList<Tarea>::new);
    }
}
