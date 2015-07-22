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
        JsonNode node = jp.readValueAsTree();
        String brand = getBrand(node);
        String model = getModel(node);
        String formFactor =getFormFactor(node);
        List<Attribute> attributeList = getListOfAttributes(node.findValue("attributes"));
        return new Device(brand, model, formFactor, attributeList);
    }

    private String getFormFactor(JsonNode node) {
        return getString(node, "formFactor");
    }

    public String getModel(JsonNode node) {
        return getString(node, "model");
    }

    public String getBrand(JsonNode node) {
        return getString(node, "brand");
    }

    public String getString(JsonNode node, String name) {
        try {
            return node.findValue(name).asText();
        } catch (Exception e) {
            logger.error(name + " could not pe deserialized");
        }
        return null;
    }


    private List<Attribute> getListOfAttributes(JsonNode node) {
        List<Attribute> attributes = new ArrayList<>();
        if(node != null) {
            Iterator<JsonNode> iterator = node.elements();
            while (iterator.hasNext()) {
                JsonNode attribute = iterator.next();
                String name = getString(attribute, "name");
                String value = getString(attribute, "value");

                attributes.add(new Attribute(name, value));
            }
        }
        return attributes;
    }

}