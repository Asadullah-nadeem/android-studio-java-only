package com.livescorescrickets.livescores.Pojo.NewsPackage;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Source implements Serializable {
    @SerializedName("id")
    @Expose
    private String f964id;
    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
    @Expose
    private String name;

    public String getId() {
        return this.f964id;
    }

    public void setId(String str) {
        this.f964id = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }
}
