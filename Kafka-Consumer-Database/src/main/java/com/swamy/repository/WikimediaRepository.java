package com.swamy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swamy.entity.WikimediaData;

public interface WikimediaRepository extends JpaRepository<WikimediaData, Long> {

}

