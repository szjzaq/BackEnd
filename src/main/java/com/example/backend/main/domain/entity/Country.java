package com.example.backend.main.domain.entity;

public class Country implements Comparable<Country>{
    private Integer id;

    private String countryName;

    private Integer year;

    private Integer maleCount;

    private Integer femaleCount;

    private Integer total;

    public Country() {
    }

    public Country(String countryName, Integer year, Integer maleCount, Integer femaleCount, Integer total) {
        this.countryName = countryName;
        this.year = year;
        this.maleCount = maleCount;
        this.femaleCount = femaleCount;
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMaleCount() {
        return maleCount;
    }

    public void setMaleCount(Integer maleCount) {
        this.maleCount = maleCount;
    }

    public Integer getFemaleCount() {
        return femaleCount;
    }

    public void setFemaleCount(Integer femaleCount) {
        this.femaleCount = femaleCount;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }


    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", countryName='" + countryName + '\'' +
                ", year=" + year +
                ", maleCount=" + maleCount +
                ", femaleCount=" + femaleCount +
                ", total=" + total +
                '}';
    }

    @Override
    public int compareTo(Country o) {
        return this.total.compareTo(o.getTotal());
    }
}
