package com.empresaqtx.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresaqtx.entities.Funcionario;

public interface FunRepository extends JpaRepository <Funcionario, Long> {

}
