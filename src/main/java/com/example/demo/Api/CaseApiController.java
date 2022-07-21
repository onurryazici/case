package com.example.demo.Api;

import com.example.demo.Api.Model.Train;
import com.example.demo.Api.Response.ApiResponse;
import com.example.demo.Api.Service.TrainController;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CaseApiController {

    @GetMapping
    public String Api(){
        return "Hello. This is api";
    }

    @PostMapping(value = "/req")
    public ApiResponse request(@RequestBody String payload) throws JSONException {
        ///////////////////////
        // Parsing JSON objects
        JSONObject jsonObject            = new JSONObject(payload);
        JSONObject train                 = jsonObject.getJSONObject("Tren");
        int reserveCount                 = jsonObject.getInt("RezervasyonYapilacakKisiSayisi");
        boolean canReserveDifferentWagon = jsonObject.getBoolean("KisilerFarkliVagonlaraYerlestirilebilir");
        JSONArray wagons                 = train.getJSONArray("Vagonlar");
        String trainName                 = train.getString("Ad");

        ///////////////////////
        // Creating our train
        Train ourTrain                  = new Train();
        TrainController trainController = new TrainController(ourTrain);
        trainController.setTrainName(trainName);
        trainController.setCanReserveDifferentWagon(canReserveDifferentWagon);
        trainController.setReserveCount(reserveCount);

        ///////////////////////
        // Making reservation
        trainController.addWagons(wagons);
        trainController.makeReservation();

        ApiResponse response = new ApiResponse();
        response.setRezervasyonYapilabilir(trainController.getReservableWagonCount() > 0);
        response.setYerlesimAyrinti(trainController.getReservableWagons());
        return response;
    }
}
