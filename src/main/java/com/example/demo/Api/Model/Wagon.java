package com.example.demo.Api.Model;

import lombok.Data;

@Data // Auto-generate for getters and setters ..............
public class Wagon {
    private String name;
    private int capacity;
    private int filledSeat;
    private int newPersons;
    public Wagon(String name, int capacity, int filledSeat){
        this.name = name;
        this.capacity = capacity;
        if(filledSeat > capacity)
            throw new UnsupportedOperationException();
        this.filledSeat = filledSeat;
    }
}
