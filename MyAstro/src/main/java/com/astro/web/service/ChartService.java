package com.astro.web.service;

import com.astro.web.model.Chart;
import com.astro.web.repository.ChartRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChartService {

    private final ChartRepository chartRepository;

    public ChartService(ChartRepository chartRepository) {
        this.chartRepository = chartRepository;
    }

    public List<Chart> getAllCharts() {
        return chartRepository.findAll();
    }

    public Optional<Chart> getChartById(Long id) {
        return chartRepository.findById(id);
    }

    public Chart saveChart(Chart chart) {
        return chartRepository.save(chart);
    }

    public void deleteChart(Long id) {
        chartRepository.deleteById(id);
    }
}
