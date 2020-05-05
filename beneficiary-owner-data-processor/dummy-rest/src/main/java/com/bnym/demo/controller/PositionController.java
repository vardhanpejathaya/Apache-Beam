package com.bnym.demo.controller;

import com.bnym.demo.model.Position;
import com.bnym.demo.model.Result1Map;
import com.google.gson.Gson;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

@RestController
@RequestMapping("/position")
public class PositionController {

    static Gson gson = new Gson();

    @RequestMapping("/all")
    @GetMapping(produces = "application/json")
    public ResponseEntity<Position[]> show() throws IOException {
        Position[] positions = null;
        try (Reader reader = new FileReader("json/positions.json")) {
            positions = gson.fromJson(reader, Position[].class);
        } catch (IOException e) {
            throw new IOException();
        }

        return ResponseEntity.ok(positions);
    }

    @RequestMapping("/print")
    @PostMapping
    public void print(@RequestBody Result1Map result1Map) {
        System.out.println(result1Map);
    }

}
