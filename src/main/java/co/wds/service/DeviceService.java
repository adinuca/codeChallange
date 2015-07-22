package co.wds.service;

import co.wds.Validator;
import co.wds.model.Device;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class DeviceService {
    private static final Logger logger = LoggerFactory.getLogger(DeviceService.class);;
    private final Validator validator;
    private final ObjectMapper mapper;
    private Set<Device> devices;

    public DeviceService() {
        this.devices = new HashSet<>();
        this.mapper = new ObjectMapper();
        this.validator = new Validator();
    }

    public void saveDevices(InputStream inputStream) throws IOException {
        devices = mapper.readValue(inputStream, mapper.getTypeFactory().constructCollectionType(Set.class, Device
                .class));
        removeInvalidDevices();
    }

    public Set<Device> getDevices() {

        return devices;
    }
    public Set<Device> getDevices(String name) {
        Set<Device> filteredDevices = new HashSet<>();
        for(Device device : devices){
            if(device.getName().equalsIgnoreCase(name)){
                filteredDevices.add(device);
            }
        }
        return filteredDevices;

    }
    private void removeInvalidDevices(){
        Iterator<Device> itterator = devices.iterator();
        while (itterator.hasNext()){
            Device device = itterator.next();
            if (validator.validateDevice(device) == false){
                logger.info("Device was not valid "+ device.toString());
                itterator.remove();
            }
        }
    }

    public Set<Device> getDevices(String brand, String model) {
        Set<Device> deviceSet = new HashSet<>();
        for (Device device : devices){
            if (device.getBrand().equalsIgnoreCase(brand) || device.getModel().equalsIgnoreCase(model)){
                deviceSet.add(device);
            }
        }
        return deviceSet;
    }
}
