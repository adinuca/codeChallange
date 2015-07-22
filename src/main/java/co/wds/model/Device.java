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

    @Override
    public boolean equals(Object o) {
        if (brand == null || model == null) {
            return false;
        }
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Device device = (Device) o;

        if (!brand.equalsIgnoreCase(device.brand)) return false;
        return model.equalsIgnoreCase(device.model);
    }

    @Override
    public int hashCode() {
        if(brand !=null && model!=null) {
            int result = brand.hashCode();
            result = 31 * result + model.hashCode();
            return result;
        }
        return 31;
    }
}
