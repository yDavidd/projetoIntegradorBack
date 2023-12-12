package com.empresaqtx.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresaqtx.entities.Projeto;

public interface ProjRepository extends JpaRepository <Projeto, Long>{

}
