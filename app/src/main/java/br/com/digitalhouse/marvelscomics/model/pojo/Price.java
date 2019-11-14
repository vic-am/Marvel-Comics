
package br.com.digitalhouse.marvelscomics.model.pojo;

import com.google.gson.annotations.Expose;

public class Price {

    @Expose
    private Long price;
    @Expose
    private String type;

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
