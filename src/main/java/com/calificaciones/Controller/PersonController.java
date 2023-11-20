package com.calificaciones.Controller;

import com.calificaciones.External_Forms.FormsIndex;
import com.calificaciones.External_Forms.InformacionPersona;
import com.calificaciones.Model.Estudiante;
import com.calificaciones.Model.Persona;
import com.calificaciones.Model.Profesor;
import com.calificaciones.Service.SubjectService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import com.calificaciones.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;

@Controller
public class PersonController {

    UserDetails usuario;
    Profesor profesor = new Profesor();
    @Autowired private PersonService personService;
    @Autowired private SubjectService subjectService;


    @GetMapping(path = "/")
    public String getResults(Authentication authentication, Model model) {
        usuario = (UserDetails) authentication.getPrincipal();
        this.profesor = personService.getProfessor(usuario.getUsername());
        if (this.profesor.getUser().equals(usuario.getUsername())) {
            //Poner las materias en caso que haya un usuario.
            HashMap<Integer, String> nombresMaterias = subjectService.getSubjectsByProfessor(this.profesor.getId());
            model.addAttribute("materia", new FormsIndex());
            model.addAttribute("materias", nombresMaterias);
            model.addAttribute("moreThan1Subject", !nombresMaterias.isEmpty());
        }

        model.addAttribute("username", personService.getProfessorName(usuario.getUsername()));

        return "index";
    }


    @GetMapping("/newStudent")
    public String crearEstudiante(Model model) {
        model.addAttribute("estudiante", new InformacionPersona());
        return "addStudent";
    }

    @PostMapping("/addStudent")
    public String agregarEstudiante(@ModelAttribute InformacionPersona persona) {
        Persona personaNueva = new Persona();
        personaNueva.setId(persona.getId());
        personaNueva.setId_type(persona.getId_type());
        personaNueva.setAddress(persona.getAddress());
        personaNueva.setName(persona.getName());
        personaNueva.setEmail(persona.getEmail());
        personaNueva.setRole("Estudiante");
        personaNueva.setSurname(persona.getSurname());
        personaNueva.setPhoneNumber(persona.getPhone());
        personService.addPerson(personaNueva);

        Estudiante nuevo = new Estudiante();
        nuevo.setIdentification(persona.getId());
        nuevo.setAttendant(persona.getAttendant());
        nuevo.setPhoneAttendant(persona.getPhoneAttendant());
        personService.addStudent(nuevo);

        return "redirect:/";
    }
}
