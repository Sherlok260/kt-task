package uz.freelance.kt_task.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.freelance.kt_task.entity.Position;
import uz.freelance.kt_task.payload.ApiResponse;
import uz.freelance.kt_task.payload.PositionDto;
import uz.freelance.kt_task.service.PositionService;
import uz.freelance.kt_task.utills.PositionExcelHelper;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/positions")
public class PositionController {

    @Autowired
    PositionService positionService;

    @PostMapping
    public HttpEntity<?> createPosition(@Valid @RequestBody PositionDto position) {
        ApiResponse apiResponse = positionService.createPosition(position);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

    @GetMapping
    public HttpEntity<?> getAllPositions(Pageable pageable) {
        ApiResponse apiResponse = positionService.getAllPositions(pageable);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

    @PutMapping
    public HttpEntity<?> updatePosition(@Valid @RequestBody PositionDto positionDto, @Validated @RequestParam Long positionId) {
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
    public ResponseEntity<?> deleteEmployees(@Valid @RequestBody List<Long> ids) {
        ApiResponse apiResponse = positionService.deletePositions(ids);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

    @GetMapping("/export")
    public void exportPositionsToExcel(HttpServletResponse response, @RequestHeader("Accept-Language") String language) throws IOException {
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=positions.xlsx");
        List<Position> positions = ((Page<Position>) positionService.getAllPositions(Pageable.unpaged()).getData()).getContent();
        ByteArrayInputStream stream = PositionExcelHelper.positionsToExcel(positions, language);
        IOUtils.copy(stream, response.getOutputStream());
    }


}
