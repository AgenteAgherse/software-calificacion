package com.calificaciones.External_Forms;

import lombok.Data;

@Data
public class DetallesNota {

    private Integer id_estudiante, id_tarea, id_nota;
    private String nombre_tarea;
    private String nombre_estudiante;
    private Float nota;
    private String comentario;

}
