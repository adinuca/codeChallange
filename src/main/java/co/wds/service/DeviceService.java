package co.wds.service;

import co.wds.model.Device;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

public class DeviceService {
    private Set<Device> devices;
    private ObjectMapper mapper;

    public DeviceService() {
        this.devices = new HashSet<>();
        this.mapper = new ObjectMapper();
    }

    public void saveDevices(InputStream inputStream) throws IOException {
        devices = mapper.readValue(inputStream, mapper.getTypeFactory().constructCollectionType(Set.class, Device
                .class));
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
}
