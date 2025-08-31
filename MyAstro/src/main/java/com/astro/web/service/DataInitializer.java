package com.astro.web.service;

import com.astro.web.model.Chart;
import com.astro.web.repository.ChartRepository;
import com.astrology.MultipDivCharts.ChartData;
import com.astrology.MultipDivCharts.ExcelChartParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    private final ChartRepository chartRepository;

    public DataInitializer(ChartRepository chartRepository) {
        this.chartRepository = chartRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (chartRepository.count() > 0) {
            logger.info("Database already populated. Skipping data initialization.");
            return;
        }

        logger.info("Database is empty. Initializing data from Charts.csv...");

        try {
            List<ChartData> chartDataList = ExcelChartParser.parse("Charts.csv");
            for (ChartData chartData : chartDataList) {
                Chart chart = new Chart();
                chart.setName(chartData.getName());
                chart.setDob(chartData.getDob());
                chart.setBirthTime(chartData.getBirthTime());
                chart.setBirthPlace(chartData.getBirthPlace());

                if (chartData.getHousePositions() != null) {
                    chart.setHousePositions(String.join(";", chartData.getHousePositions()));
                }
                if (chartData.getD9HousePositions() != null) {
                    chart.setD9HousePositions(String.join(";", chartData.getD9HousePositions()));
                }

                chartRepository.save(chart);
            }
            logger.info("Successfully loaded {} charts into the database.", chartDataList.size());
        } catch (Exception e) {
            logger.error("Failed to initialize data from Charts.csv", e);
        }
    }
}
