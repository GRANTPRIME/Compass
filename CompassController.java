package ru.appline.controller;

import org.springframework.web.bind.annotation.*;
import ru.appline.CompassRequest;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CompassController {
    private Map<String, String> compassMap;

    public CompassController() {
        compassMap = new HashMap<>();
    }

    @PostMapping("/compass")
    public void setCompassRange(@RequestBody CompassRequest request) {
        compassMap.put("North", request.getNorth());
        compassMap.put("East", request.getEast());
        compassMap.put("South", request.getSouth());
        compassMap.put("West", request.getWest());
    }

    @GetMapping("/compass/{degree}")
    public String getCompassSide(@PathVariable int degree) {
        String side = null;

        for (Map.Entry<String, String> entry : compassMap.entrySet()) {
            String[] range = entry.getValue().split("-");
            int start = Integer.parseInt(range[0]);
            int end = Integer.parseInt(range[1]);

            if (degree >= start && degree <= end) {
                side = entry.getKey();
                break;
            }
        }

        return "{\"Side\": \"" + side + "\"}";
    }
}