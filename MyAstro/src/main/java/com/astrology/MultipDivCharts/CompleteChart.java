package com.astrology.MultipDivCharts;

import java.util.EnumMap;
import java.util.Map;

public class CompleteChart {
private final Map<DivisionalChart, DivisionalChartData> charts;
    
    public CompleteChart() {
        charts = new EnumMap<>(DivisionalChart.class);
    }
    
    public void addDivisionalChart(DivisionalChart type, DivisionalChartData data) {
        charts.put(type, data);
    }
    
    public DivisionalChartData getChart(DivisionalChart type) {
        return charts.get(type);
    }
    
    public boolean hasChart(DivisionalChart type) {
        return charts.containsKey(type);
    }
}
