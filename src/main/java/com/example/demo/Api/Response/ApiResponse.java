package com.example.demo.Api.Response;

import com.example.demo.Api.Model.Wagon;
import lombok.Data;

import java.util.List;

@Data // Auto-generate for getters and setters .................
public class ApiResponse {
    private boolean RezervasyonYapilabilir;
    private List<Wagon> YerlesimAyrinti;
}
