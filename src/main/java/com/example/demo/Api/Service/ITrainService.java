package com.example.demo.Api.Service;

import com.example.demo.Api.Model.Wagon;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;

import java.util.List;

public interface ITrainService {
    void addWagons(JSONArray wagons) throws JSONException;
    List<Wagon> getReservableWagons();
    int getReservableWagonCount();
    void addSingleWagon(Wagon wagon);

    void makeReservation();
}
