package co.wds;

import co.wds.model.Attribute;
import co.wds.model.Device;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ValidatorTest {

    private Validator validator = new Validator();

    @Test
    public void shouldReturnFalseWhenBrandIsEmpty() {
        Device device = new Device("", "name", "CANDYBAR", null);

        boolean isValid = validator.validateDevice(device);

        assertFalse(isValid);
    }

    @Test
    public void shouldReturnFalseWhenBrandHasMoreThan50Chars() {
        Device device = new Device("brandbrandbrandbrandbrandbrandbrandbrandbrandbrand ", "name", "CANDYBAR", null);

        boolean isValid = validator.validateDevice(device);

        assertFalse(isValid);

    }

    @Test
    public void shouldReturnFalseWhenBrandIsNull() {
        Device device = new Device(null, "name", "CANDYBAR", null);

        boolean isValid = validator.validateDevice(device);

        assertFalse(isValid);
    }

    @Test
    public void shouldReturnFalseWhenModelIsEmpty() {
        Device device = new Device("brand", "", "CANDYBAR", null);

        boolean isValid = validator.validateDevice(device);

        assertFalse(isValid);
    }

    @Test
    public void shouldReturnFalseWhenModelHasMoreThan50Chars() {
        Device device = new Device("brand", "model model model model model model model model model mod", "CANDYBAR", null);

        boolean isValid = validator.validateDevice(device);

        assertFalse(isValid);
    }

    @Test
    public void shouldReturnFalseWhenModelIsNull() {
        Device device = new Device("brand", null, "CANDYBAR", null);

        boolean isValid = validator.validateDevice(device);

        assertFalse(isValid);
    }

    @Test
    public void shouldReturnFalseWhenFormFactorIsInvalid() {
        Device device = new Device("brand", "model", "CANDYBAR123", null);

        boolean isValid = validator.validateDevice(device);

        assertFalse(isValid);
    }

    @Test
    public void shouldReturnFalseWhenFormFactorIsNull() {
        Device device = new Device("brand", "model", null, null);

        boolean isValid = validator.validateDevice(device);

        assertFalse(isValid);
    }

    @Test
    public void shouldReturnFalseWhenFormFactorIsEmpty() {
        Device device = new Device("brand", "model", "", null);

        boolean isValid = validator.validateDevice(device);

        assertFalse(isValid);
    }


    @Test
    public void shouldReturnFalseWhenFormFactorIsCandybarCaseSensitive() {
        Device device = new Device("brand", "model", "CandYBAR", null);

        boolean isValid = validator.validateDevice(device);

        assertFalse(isValid);
    }


    @Test
    public void shouldReturnFalseWhenFormFactorIsSmartphoneCaseSensitive() {
        Device device = new Device("brand", "Candybar", "SMARTPhoNE", null);

        boolean isValid = validator.validateDevice(device);

        assertFalse(isValid);

    }

    @Test
    public void shouldReturnFalseWhenFormFactorIsPhabletCaseSensitive() {
        Device device = new Device("brand", "pHabLet", "PHabLET", null);

        boolean isValid = validator.validateDevice(device);

        assertFalse(isValid);

    }

    @Test
    public void shouldReturnFalseWhenFormFactorIsClamshellCaseSensitive() {
        Device device = new Device("brand", "ClamShell", "claMSHELL", null);

        boolean isValid = validator.validateDevice(device);

        assertFalse(isValid);
    }

    @Test
    public void shouldReturnTrueWhenFormFactorIsCandybar() {
        Device device = new Device("brand", "model", "CANDYBAR", null);

        boolean isValid = validator.validateDevice(device);

        assertTrue(isValid);
    }


    @Test
    public void shouldReturnTrueWhenFormFactorIsSmartphone() {
        Device device = new Device("brand", "Candybar", "SMARTPHONE", null);

        boolean isValid = validator.validateDevice(device);

        assertTrue(isValid);

    }

    @Test
    public void shouldReturnTrueWhenFormFactorIsPhablet() {
        Device device = new Device("brand", "pHabLet", "PHABLET", null);

        boolean isValid = validator.validateDevice(device);

        assertTrue(isValid);

    }

    @Test
    public void shouldReturnTrueWhenFormFactorIsClamshell() {
        Device device = new Device("brand", "ClamShell", "CLAMSHELL", null);

        boolean isValid = validator.validateDevice(device);

        assertTrue(isValid);
    }

    @Test
    public void shouldReturnFalseIfAttributesAreMissingName(){
        List<Attribute> attributes = new ArrayList<>();
        attributes.add(new Attribute(null, "value"));
        Device device = new Device("brand", "ClamShell", "CLAMSHELL", attributes);

        boolean isValid = validator.validateDevice(device);

        assertFalse(isValid);
    }

    @Test
    public void shouldReturnFalseIfAttributesAreMissingValue(){
        List<Attribute> attributes = new ArrayList<>();
        attributes.add(new Attribute("name", null));
        Device device = new Device("brand", "ClamShell", "CLAMSHELL", attributes);

        boolean isValid = validator.validateDevice(device);

        assertFalse(isValid);
    }

    @Test
    public void shouldReturnFalseIfAttributeNameHasMoreThan20Characters(){
        List<Attribute> attributes = new ArrayList<>();
        attributes.add(new Attribute("name name name name name  ", "value"));
        Device device = new Device("brand", "ClamShell", "CLAMSHELL", attributes);

        boolean isValid = validator.validateDevice(device);

        assertFalse(isValid);
    }

    @Test
    public void shouldReturnFalseIfAttributeNameIsEmpty(){
        List<Attribute> attributes = new ArrayList<>();
        attributes.add(new Attribute("", "a"));
        Device device = new Device("brand", "ClamShell", "CLAMSHELL", attributes);

        boolean isValid = validator.validateDevice(device);

        assertFalse(isValid);
    }

    @Test
    public void shouldReturnFalseIfAttributeValueIsEmpty(){
        List<Attribute> attributes = new ArrayList<>();
        attributes.add(new Attribute("a", ""));
        Device device = new Device("brand", "ClamShell", "CLAMSHELL", attributes);

        boolean isValid = validator.validateDevice(device);

        assertFalse(isValid);
    }

    @Test
    public void shouldReturnTrueWhenDeviceIsValid() {
        List<Attribute> attributes = new ArrayList<>();
        attributes.add(new Attribute("a", "bc"));
        attributes.add(new Attribute("b", "sbc"));
        attributes.add(new Attribute("c", "abc"));
        Device device = new Device("brand", "ClamShell", "CLAMSHELL", attributes);

        boolean isValid = validator.validateDevice(device);

        assertTrue(isValid);
    }
}