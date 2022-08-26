package razi.constructor.recyclerviewpractice.Models;

public class CurrencyModel {

    private String code, country;

    public CurrencyModel() {
    }

    public CurrencyModel(String code, String country) {
        this.code = code;
        this.country = country;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
