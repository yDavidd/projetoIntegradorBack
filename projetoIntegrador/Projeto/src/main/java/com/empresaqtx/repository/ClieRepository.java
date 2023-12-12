package com.empresaqtx.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresaqtx.entities.Cliente;

public interface ClieRepository extends JpaRepository <Cliente, Long>{

}
