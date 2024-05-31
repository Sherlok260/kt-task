package uz.freelance.kt_task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.freelance.kt_task.payload.ApiResponse;
import uz.freelance.kt_task.payload.EmployeeDto;
import uz.freelance.kt_task.payload.PositionDto;
import uz.freelance.kt_task.service.PositionService;

import java.util.List;

@RestController
@RequestMapping("/api/positions")
public class PositionController {

    @Autowired
    PositionService positionService;

    @PostMapping
    public HttpEntity<?> createPosition(@RequestBody PositionDto position) {
        ApiResponse apiResponse = positionService.createPosition(position);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

    @GetMapping
    public HttpEntity<?> getAllPositions(Pageable pageable) {
        ApiResponse apiResponse = positionService.getAllPositions(pageable);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

    @PutMapping
    public HttpEntity<?> updatePosition(@RequestBody PositionDto positionDto, @RequestParam Long positionId) {
        ApiResponse apiResponse = positionService.updatePosition(positionDto, positionId);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getPositionById(@PathVariable Long id) {
        ApiResponse apiResponse = positionService.getPositionById(id);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deletePosition(@PathVariable Long id) {
        ApiResponse apiResponse = positionService.deletePosition(id);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

    @PostMapping("/multiDelete")
    public ResponseEntity<Void> deleteEmployees(@RequestBody List<Long> ids) {
        ApiResponse apiResponse = positionService.deletePositions(ids);
        return ResponseEntity.status(apiResponse.getStatus()).build();
    }


}
