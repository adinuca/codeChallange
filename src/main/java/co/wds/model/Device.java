package co.wds.model;

import co.wds.deserializer.DevicesDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

@JsonDeserialize(using = DevicesDeserializer.class)
public class Device {
    private final String brand;
    private final String model;
    private final String formFactor;
    private final List<Attribute> attributes;

    public Device(String brand, String model, String formFactor, List<Attribute> attributes) {
        this.brand = brand;
        this.model = model;
        this.formFactor = formFactor;
        this.attributes = attributes;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getFormFactor() {
        return formFactor;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public String getName(){
        return String.format("%s %s", brand, model);
    }

    @Override
    public String toString() {
        return "Device{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", formFactor=" + formFactor +
                ", attributes=" + attributes +
                '}';
    }
}
