
package com.srtapps.chemicalelements.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Element {

    @SerializedName("atomicMass")
    @Expose
    private String atomicMass;
    @SerializedName("atomicNumber")
    @Expose
    private Integer atomicNumber;
    @SerializedName("cpkHexColor")
    @Expose
    private String cpkHexColor;
    @SerializedName("groupBlock")
    @Expose
    private String groupBlock;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("symbol")
    @Expose
    private String symbol;

    public String getAtomicMass() {
        return atomicMass;
    }

    public void setAtomicMass(String atomicMass) {
        this.atomicMass = atomicMass;
    }

    public Integer getAtomicNumber() {
        return atomicNumber;
    }

    public void setAtomicNumber(Integer atomicNumber) {
        this.atomicNumber = atomicNumber;
    }

    public String getCpkHexColor() {
        return cpkHexColor;
    }

    public void setCpkHexColor(String cpkHexColor) {
        this.cpkHexColor = cpkHexColor;
    }

    public String getGroupBlock() {
        return groupBlock;
    }

    public void setGroupBlock(String groupBlock) {
        this.groupBlock = groupBlock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

}
