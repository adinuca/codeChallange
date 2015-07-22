package co.wds.deserializer;


import co.wds.model.Attribute;
import co.wds.model.Device;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DevicesDeserializer extends JsonDeserializer<Device> {
    private static final Logger logger = LoggerFactory.getLogger(DevicesDeserializer.class);

    @Override
    public Device deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException {
        try {
            JsonNode node = jp.readValueAsTree();
            String brand = node.findValue("brand").asText();
            String model = node.findValue("model").asText();
            String formFactor = String.valueOf(node.findValue("formFactor").asText());
            List<Attribute> attributeList = getListOfAttributes(node.findValue("attributes"));
            if (isDeviceFieldValid(brand) && isDeviceFieldValid(model) && attributeList != null) {
                return new Device(brand, model, formFactor, attributeList);
            }
            logger.error("Device with brand {} and model {} is not valid.", brand, model);
            throw new IOException("File did not contain valid devices");
        } catch (NullPointerException | IllegalArgumentException e) {
            throw new IOException("File did not contain valid devices");
        }
    }

    private boolean isDeviceFieldValid(String field) {
        return !(field == null || field.isEmpty() || field.length() > 50);
    }

    private List<Attribute> getListOfAttributes(JsonNode node) {
        List<Attribute> attributes = new ArrayList<>();
        Iterator<JsonNode> iterator = node.elements();
        while (iterator.hasNext()) {
            JsonNode attribute = iterator.next();
            String name = attribute.findValue("name").asText();
            String value = attribute.findValue("value").asText();
            if (name != null && !name.isEmpty() && value != null && !value.isEmpty()) {
                if (isAttributeNameValid(name) && isAttributeValueValid(value)) {
                    attributes.add(new Attribute(name, value));
                } else {
                    return null;
                }
            } else if ((name == null || name.isEmpty()) && !(value == null || value.isEmpty())) {
                return null;
            }
        }
        return attributes;
    }

    private boolean isAttributeValueValid(String value) {
        return value.length() <= 100;
    }

    private boolean isAttributeNameValid(String name) {
        return name.length() <= 20;
    }
}