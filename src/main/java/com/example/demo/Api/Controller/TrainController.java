package com.example.demo.Api.Controller;

import com.example.demo.Api.Model.Train;
import com.example.demo.Api.Model.Wagon;
import com.example.demo.Api.Service.ITrainService;
import lombok.Data;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.util.List;
@Data
public class TrainController implements ITrainService {
    Train model;
    List<Wagon> reservableWagons;
    int reserveCount;
    boolean canReserveDifferentWagon;
    public TrainController(Train train){
        this.model = train;
    }
    public void setTrainName(String name){
        this.model.setName(name);
    }


    @Override
    public void addWagons(JSONArray wagons) throws JSONException {
        for (int i=0; i<wagons.length(); i++) {
            JSONObject wagonArray   = wagons.getJSONObject(i);
            String name             = wagonArray.getString("Ad");
            int capacity            = wagonArray.getInt("Kapasite");
            int filledSeat          = wagonArray.getInt("DoluKoltukAdet");

            Wagon wagon = new Wagon(name,capacity,filledSeat);
            this.addSingleWagon(wagon);
            if((int)(capacity*0.7)>filledSeat)
                reservableWagons.add(wagon);
        }
    }

    @Override
    public List<Wagon> getReservableWagons() {
        return this.reservableWagons;
    }

    @Override
    public int getReservableWagonCount() {
        return this.reservableWagons.size();
    }

    @Override
    public void addSingleWagon(Wagon wagon) {
        model.getWagons().add(wagon);
    }

    @Override
    public void makeReservation() {
        if(this.reservableWagons.size() > 0){
            int count = reserveCount;
            int reservedWagonCount = this.reservableWagons.size();
            for(Wagon w: this.reservableWagons){
                int divided = count / reservedWagonCount;
                if(canReserveDifferentWagon){
                    int filledSeats = w.getFilledSeat();
                    w.setFilledSeat(divided + filledSeats);
                    w.setNewPersons(divided);
                    count-=divided;
                    reservedWagonCount-=divided;
                } else {
                    if(reserveCount <= w.getFilledSeat()){
                        this.reservableWagons.clear();
                        this.reservableWagons.add(w);
                    }
                }
            }
        }
    }
}
