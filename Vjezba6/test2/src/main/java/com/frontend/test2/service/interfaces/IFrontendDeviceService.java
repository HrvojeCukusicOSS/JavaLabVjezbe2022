package com.frontend.test2.service.interfaces;

import com.frontend.test2.entities.Device;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface IFrontendDeviceService {

    List<Device> getDevices(int page, int size, String field, String direction) throws IOException, InterruptedException;
    void AddData(String id, String date, String value) throws IOException, InterruptedException;
}
