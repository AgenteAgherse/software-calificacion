package com.calificaciones.Repository;

import com.calificaciones.Model.Nota;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GradeRepository extends JpaRepository<Nota, Integer> {
    @Query("DELETE FROM Nota n WHERE n.homeworkId IN (SELECT t.subject FROM Tarea t WHERE t.subject = :nota)")
    @Transactional
    @Modifying
    void deleteBySubject(@Param("nota") Integer materia);

    @Query("DELETE FROM Nota n WHERE n.student = :id")
    @Transactional
    @Modifying
    void deleteByStudent(@Param("id") Integer id);

}
