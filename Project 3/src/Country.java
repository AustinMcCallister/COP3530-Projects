/**
 * Describes a Country object containing various data fields to include name, capitol, population, GDP in USD,
 * Covid cases, Covid deaths, and land area in km²
 *
 * @author Austin McCallister (N01487083)
 * @version 10/17/22
 */
public class Country {
  private String name;
  private String capitol;
  private double population;
  private double GDP;
  private double covidCases;
  private double covidDeaths;
  private double area;

  /**
   * Constructor for the Country object containing its name, capitol, population, GDP in USD,
   * Covid cases, Covid deaths, and land area in km²
   *
   * @param  name  The name of the country
   * @param  capitol  The capitol of the country
   * @param  population  The population of the country
   * @param  GDP  The GDP of the country in USD
   * @param  covidCases  The number of Covid cases in the country
   * @param  covidDeaths  The number of Covid deaths in the country
   * @param  area  The land area of the country in km²
   */
  public Country(String name, String capitol, double population, double GDP, double covidCases, double covidDeaths, double area) {
    this.name = name;
    this.capitol = capitol;
    this.population = population;
    this.GDP = GDP;
    this.covidCases = covidCases;
    this.covidDeaths = covidDeaths;
    this.area = area;
  }

  /**
   * Returns country name
   *
   * @return The name of the country
   */
  public String getName() {
    return name;
  }

  /**
   * Sets country name
   *
   * @param  name  The name of the country
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets country capitol
   *
   * @return The capitol of the country
   */
  public String getCapitol() {
    return capitol;
  }

  /**
   * Sets country capitol
   *
   * @param  capitol  The capitol of the country
   */
  public void setCapitol(String capitol) {
    this.capitol = capitol;
  }

  /**
   * Gets country population
   *
   * @return The population of the country
   */
  public double getPopulation() {
    return population;
  }

  /**
   * Sets country population
   *
   * @param  population  The population of the country
   */
  public void setPopulation(double population) {
    this.population = population;
  }

  /**
   * Gets country GDP in USD
   *
   * @return The GDP of the country in USD
   */
  public double getGDP() {
    return GDP;
  }

  /**
   * Sets country GDP in USD
   *
   * @param  GDP  The GDP of the country in USD
   */
  public void setGDP(double GDP) {
    this.GDP = GDP;
  }

  /**
   * Gets country Covid cases
   *
   * @return The number of Covid cases in the country
   */
  public double getCovidCases() {
    return covidCases;
  }

  /**
   * Sets country Covid cases
   *
   * @param  covidCases  The number of Covid cases in the country
   */
  public void setCovidCases(double covidCases) {
    this.covidCases = covidCases;
  }

  /**
   * Gets country Covid deaths
   *
   * @return The number of Covid deaths in the country
   */
  public double getCovidDeaths() {
    return covidDeaths;
  }

  /**
   * Sets country Covid deaths
   *
   * @param  covidDeaths  The number of Covid deaths in the country
   */
  public void setCovidDeaths(double covidDeaths) {
    this.covidDeaths = covidDeaths;
  }

  /**
   * Gets country land area in km²
   *
   * @return The land area of the country in km²
   */
  public double getArea() {
    return area;
  }

  /**
   * Sets country land area in km²
   *
   * @param  area  The land area of the country in km²
   */
  public void setArea(double area) {
    this.area = area;
  }

  /**
   * Calculates and returns GDP per capita
   *
   * @return The country's GDP per capita
   */
  public double getGDPPC() {
    return (GDP / population);
  }

  /**
   * Calculates and returns the Covid fatality rate
   *
   * @return The country's Covid fatality rate
   */
  public double getCFR() {
    return (covidDeaths / covidCases);
  }

  /**
   * Calculates and returns the Covid case rate per 100,000 people
   *
   * @return The country's Covid case rate per 100,000 people
   */
  public double getCaseRate() {
    return ((covidCases / population) * 100000);
  }

  /**
   * Calculates and returns the Covid death rate per 100,000 people
   *
   * @return The country's Covid death rate per 100,000 people
   */
  public double getDeathRate() {
    return ((covidDeaths / population) * 100000);
  }

  /**
   * Calculates and returns the population density
   *
   * @return The country's population density
   */
  public double getPopDensity() {
    return (population / area);
  }

  /**
   * Prints country data to console
   */
  public void print() {
    System.out.printf("Name:       %s\n", name);
    System.out.printf("Capitol:    %s\n", capitol);
    System.out.printf("GDPPC:      %.3f\n", getGDPPC());
    System.out.printf("CFR:        %.6f\n", getCFR());
    System.out.printf("CaseRate:   %.3f\n", getCaseRate());
    System.out.printf("DeathRate:  %.3f\n", getDeathRate());
    System.out.printf("PopDensity: %.3f\n\n", getPopDensity());
  }
}
