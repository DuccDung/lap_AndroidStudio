package com.example.qlsv.api_service;

import com.example.qlsv.Leanners;

import java.util.List;

public interface DataCallback {
    void onSuccess(List<Leanners> leannersList);
    void onFailure(Throwable t);
}

