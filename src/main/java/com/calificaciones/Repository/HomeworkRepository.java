package com.calificaciones.Repository;

import com.calificaciones.Model.Tarea;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HomeworkRepository extends JpaRepository<Tarea, Integer> {

    @Query("DELETE FROM Tarea t WHERE t.subject = :nota")
    @Transactional
    @Modifying
    void deleteBySubject(@Param("nota") Integer materia);
}