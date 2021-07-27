package com.dhb.springapp.service;

import com.dhb.springapp.models.Thuoc;

import java.util.List;

public interface IThuocService extends IGenericService<Thuoc> {
    List<Thuoc> getThuocTheoTen(String name);
}
