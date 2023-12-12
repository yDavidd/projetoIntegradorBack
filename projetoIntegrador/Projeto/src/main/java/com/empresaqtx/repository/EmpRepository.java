package com.empresaqtx.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresaqtx.entities.Empresa;

public interface EmpRepository extends JpaRepository <Empresa, Long>{

}