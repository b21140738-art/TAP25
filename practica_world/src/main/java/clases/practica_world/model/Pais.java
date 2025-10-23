package clases.practica_world.model;


public class Pais {
    private String code;
    private String name;
    private String continent;
    private String region;
    private Double surfaceArea;
    private Integer indepYear;
    private Integer population;
    private String governmentForm;
    private String headOfState;
    private Double lifeExpectancy;

    public Pais() {}

    public Pais(String code, String name, String continent, String region,
                Double surfaceArea, Integer indepYear, Integer population,
                String governmentForm, String headOfState, Double lifeExpectancy) {
        this.code = code;
        this.name = name;
        this.continent = continent;
        this.region = region;
        this.surfaceArea = surfaceArea;
        this.indepYear = indepYear;
        this.population = population;
        this.governmentForm = governmentForm;
        this.headOfState = headOfState;
        this.lifeExpectancy = lifeExpectancy;
    }

    // Getters y setters
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getContinent() { return continent; }
    public void setContinent(String continent) { this.continent = continent; }

    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }

    public Double getSurfaceArea() { return surfaceArea; }
    public void setSurfaceArea(Double surfaceArea) { this.surfaceArea = surfaceArea; }

    public Integer getIndepYear() { return indepYear; }
    public void setIndepYear(Integer indepYear) { this.indepYear = indepYear; }

    public Integer getPopulation() { return population; }
    public void setPopulation(Integer population) { this.population = population; }

    public String getGovernmentForm() { return governmentForm; }
    public void setGovernmentForm(String governmentForm) { this.governmentForm = governmentForm; }

    public String getHeadOfState() { return headOfState; }
    public void setHeadOfState(String headOfState) { this.headOfState = headOfState; }

    public Double getLifeExpectancy() { return lifeExpectancy; }
    public void setLifeExpectancy(Double lifeExpectancy) { this.lifeExpectancy = lifeExpectancy; }

    @Override
    public String toString() {
        return name + " (" + code + ")";
    }
}
