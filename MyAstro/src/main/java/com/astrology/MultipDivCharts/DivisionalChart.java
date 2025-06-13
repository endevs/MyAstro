package com.astrology.MultipDivCharts;

public enum DivisionalChart {
	D1("Rasi"), D2("Hora"), D3("Drekkana"), D4("Chaturthamsa"), 
    D7("Saptamsa"), D9("Navamsa"), D10("Dasamsa"), D12("Dwadasamsa"), 
    D16("Shodasamsa"), D20("Vimsamsa"), D24("Chaturvimsamsa"), 
    D27("Nakshatramsa"), D30("Trimsamsa"), D40("Khavedamsa"), 
    D45("Akshavedamsa"), D60("Shastiamsa");

    private final String name;
    
    DivisionalChart(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
}
