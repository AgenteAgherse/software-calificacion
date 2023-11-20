package com.calificaciones.Repository;

import com.calificaciones.Model.Register;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RegisterRepository extends JpaRepository<Register, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Register r WHERE r.subject = :subject")
    void deleteBySubject(@Param("subject") Integer materia);

    @Transactional
    @Modifying
    @Query("DELETE FROM Register r WHERE r.subject = :subject")
    void deleteByStudent(@Param("subject") Integer materia);

}
