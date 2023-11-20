package com.calificaciones.Repository;

import com.calificaciones.Model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Persona, String> {

    @Query("SELECT p FROM Persona p WHERE p.id IN (SELECT e.identification FROM Estudiante e WHERE e.id IN (SELECT r.student FROM Register r WHERE r.subject = :id_materia))")
    Optional<ArrayList<Persona>> estudiantesRegistrados(@Param("id_materia") Integer id);
}
