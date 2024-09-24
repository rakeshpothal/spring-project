package com.jsp.allocationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsp.allocationservice.entity.AllocationEntity;

@Repository
public interface AllocationRepository extends JpaRepository<AllocationEntity, Long> {

}
