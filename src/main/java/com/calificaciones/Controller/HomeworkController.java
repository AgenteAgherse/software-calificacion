package com.calificaciones.Controller;

import com.calificaciones.Model.Tarea;
import com.calificaciones.Service.HomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeworkController {

    @Autowired private HomeworkService homeworkService;
    @GetMapping("/newHomework/{materia}")
    public String addHomework(@PathVariable("materia") Integer materia, Model model){
        Tarea tarea = new Tarea();
        tarea.setSubject(materia);
        model.addAttribute("materia", materia);
        model.addAttribute("taller", tarea);

        return "addHomework";
    }

    @PostMapping("/newHomework/Confirm/{materia}")
    public String saveHomework(@PathVariable("materia") Integer materia, @ModelAttribute Tarea tarea) {
        if (tarea == null) return "redirect:/error";

        tarea.setSubject(materia);
        System.out.println(tarea.toString());
        homeworkService.addHomework(tarea);
        return "redirect:/";
    }
}
