
package br.com.digitalhouse.marvelscomics.model.pojo;

import com.google.gson.annotations.Expose;

public class Price {

    @Expose
    private Double price;
    @Expose
    private String type;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
