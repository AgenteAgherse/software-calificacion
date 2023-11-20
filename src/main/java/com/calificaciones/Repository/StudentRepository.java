package com.calificaciones.Repository;

import com.calificaciones.Model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Estudiante, Integer> {
}
