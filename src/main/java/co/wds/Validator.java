package co.wds;

import co.wds.model.Attribute;
import co.wds.model.Device;

import java.util.List;

public class Validator {
    public enum FormFactor {
        CANDYBAR, SMARTPHONE, PHABLET, CLAMSHELL
    }

    public boolean validateDevice(Device device) {
        return validateBrandOrModel(device.getBrand()) && validateBrandOrModel(device.getModel()) &&
                validateFormFactor(device.getFormFactor()) && validateAttributes(device.getAttributes());
    }

    private boolean validateAttributes(List<Attribute> attributes) {
        if (attributes == null) {
            return true;
        }
        for (Attribute attribute : attributes) {
            if (validateAttribute(attribute) == false) {
                return false;
            }
        }
        return true;
    }

    private boolean validateAttribute(Attribute attribute) {
        String value = attribute.getValue();
        String name = attribute.getName();
        return !(name == null || value == null || name.length() > 20 || value.length() > 100 || value.isEmpty() || name.isEmpty());
    }

    private boolean validateFormFactor(String formFactor) {
        for (FormFactor value : FormFactor.values()) {
            if (value.name().equals(formFactor)) {
                return true;
            }
        }
        return false;
    }

    private boolean validateBrandOrModel(String value) {
        if (value == null || value.isEmpty() || value.length() > 50)
            return false;
        return true;
    }

}
