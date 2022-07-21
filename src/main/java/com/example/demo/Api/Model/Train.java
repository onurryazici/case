package com.example.demo.Api.Model;

import com.example.demo.Api.Service.ITrainService;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Train {
    private String name;
    private List<Wagon> wagons;

}
