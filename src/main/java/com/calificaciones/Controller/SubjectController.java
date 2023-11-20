package com.calificaciones.Controller;

import com.calificaciones.External_Forms.FormsIndex;
import com.calificaciones.Model.Materia;
import com.calificaciones.Model.Persona;
import com.calificaciones.Model.Profesor;
import com.calificaciones.Model.Tarea;
import com.calificaciones.Repository.GradeRepository;
import com.calificaciones.Repository.RegisterRepository;
import com.calificaciones.Service.GradeService;
import com.calificaciones.Service.HomeworkService;
import com.calificaciones.Service.PersonService;
import com.calificaciones.Service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class SubjectController {
    @Autowired private SubjectService subjectService;
    @Autowired private PersonService personService;
    @Autowired private GradeService gradeService;
    @Autowired private RegisterRepository registerRepository;

    @Autowired private HomeworkService homeworkService;

    @GetMapping("/addSubject")
    public String addSubject(Model model) {
        model.addAttribute("materia", new Materia());
        return "addSubject";
    }

    @PostMapping("/addSubject/Confirm")
    public String registerSubject(Authentication authentication,  @ModelAttribute Materia materia) {
        //Se toman datos de usuario logueado.
        UserDetails usuario = (UserDetails) authentication.getPrincipal();
        Profesor profesor = personService.getProfessor(usuario.getUsername());
        materia.setTeacher(profesor.getId());


        subjectService.addSubject(materia);
        return "redirect:/";
    }

    // los alumnos registrados y las notas (se crea un botón)
    //También se debe agregar una sección para agregar alumnos y otra de ver notas.
    @GetMapping("/lookSubject")
    public String getMateria(Authentication auth, @ModelAttribute FormsIndex materia, Model model) {
        Materia curso = null;
        if (materia != null) {
            System.out.println(materia.getCurso());
            curso = subjectService.getSubject(Integer.parseInt(materia.getCurso()));
        }

        if (curso == null) return "index";

        ArrayList<Persona> personas = personService.getGroupOfStudentsInCourse(Integer.parseInt(materia.getCurso()));
        UserDetails details = (UserDetails) auth.getPrincipal();
        model.addAttribute("nombre_profesor", personService.getProfessorName(details.getUsername()));
        model.addAttribute("curso", curso);
        model.addAttribute("registrados", personas);
        model.addAttribute("busqueda", new FormsIndex());


        return "infoMateria";
    }

    @GetMapping("/lookSubject/{materia}/homeworkDetails")
    public String verDetallesMateria(@PathVariable("materia") Integer materia, Model model){
        ArrayList<Tarea> tareas = homeworkService.getListOfHomeworks(materia);
        model.addAttribute("tareas", tareas);

        return "detallesTrabajos";
    }







    @GetMapping("/deleteStudentSubject/{id}")
    public String deleteStudentSubject(@PathVariable("id") String id) {
        personService.deleteStudentSubject(id);
        gradeService.deleteGradesByStudent(id);
        return "redirect:/";
    }

    @GetMapping("/deleteSubject/{id}")
    public String deleteSubject(@PathVariable("id") Integer id) {
        subjectService.deleteSubject(id);
        return "redirect:/";
    }



}
