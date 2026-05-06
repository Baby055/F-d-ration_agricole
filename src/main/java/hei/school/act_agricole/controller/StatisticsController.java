package hei.school.act_agricole.controller;

import hei.school.act_agricole.dto.response.CollectivityLocalStatisticsResponse;
import hei.school.act_agricole.dto.response.CollectivityOverallStatisticsResponse;
import hei.school.act_agricole.service.StatisticsService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
public class StatisticsController {

    private final StatisticsService service;

    public StatisticsController(StatisticsService service) {
        this.service = service;
    }

    @GetMapping("/collectivites/{id}/statistics")
    public ResponseEntity<List<CollectivityLocalStatisticsResponse>> getLocalStatistics(
            @PathVariable String id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        return ResponseEntity.ok(service.getLocalStatistics(id, from, to));
    }

    @GetMapping("/collectivites/statistics")
    public ResponseEntity<List<CollectivityOverallStatisticsResponse>> getOverallStatistics(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        return ResponseEntity.ok(service.getOverallStatistics(from, to));
    }
}