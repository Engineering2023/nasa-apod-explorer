package com.example.apod.controller;
import com.example.apod.model.ApodResponse;
import com.example.apod.service.NasaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/apod")
@CrossOrigin(origins = "*")
public class ApodController {

@Autowired
private NasaService nasaService;

@GetMapping("/today")
public ResponseEntity<ApodResponse> today() {
ApodResponse r = nasaService.fetchApod(null);
if (r == null) return ResponseEntity.status(502).build();
return ResponseEntity.ok(r);
}

@GetMapping("/date/{date}")
public ResponseEntity<ApodResponse> byDate(@PathVariable String date) {
ApodResponse r = nasaService.fetchApod(date);
if (r == null) return ResponseEntity.status(404).build();
return ResponseEntity.ok(r);
}

@GetMapping("/recent")
public ResponseEntity<List<ApodResponse>> recent(@RequestParam(defaultValue = "12") int limit) {
List<ApodResponse> list = nasaService.fetchRecent(limit);
return ResponseEntity.ok(list);
}
}
