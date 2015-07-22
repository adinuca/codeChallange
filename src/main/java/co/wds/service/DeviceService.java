package co.wds.service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class DeviceService {


    private final String fileName;

    public DeviceService(String devicesFileName) {
        this.fileName = devicesFileName;
    }

    public void saveDevices(InputStream inputStream) throws IOException {
        Path path = Paths.get(fileName);

        Files.copy(inputStream,path, StandardCopyOption.REPLACE_EXISTING);
    }

    public FileInputStream getDevices() throws FileNotFoundException {
        File file = new File(fileName);
        return new FileInputStream(file);
    }
}
