package com.astro.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.astro.web.model.Chart;

@Repository
public interface ChartRepository extends JpaRepository<Chart, Long> {
}
