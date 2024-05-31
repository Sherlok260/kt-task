package uz.freelance.kt_task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.freelance.kt_task.entity.Employee;
import uz.freelance.kt_task.entity.Position;
import uz.freelance.kt_task.payload.ApiResponse;
import uz.freelance.kt_task.payload.EmployeeDto;
import uz.freelance.kt_task.payload.PositionDto;
import uz.freelance.kt_task.repository.EmployeeRepository;
import uz.freelance.kt_task.repository.PositionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PositionService {

    @Autowired
    PositionRepository positionRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    public ApiResponse createPosition(PositionDto dto) {
        try {
            Position position = new Position();
            position.convertDtoToEntity(dto);
            Position save = positionRepository.save(position);
            return new ApiResponse("position success create", 201, save);
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResponse("some error", 500, e.getMessage());
        }
    }

    public ApiResponse updatePosition(Position position) {
        try {
            Optional<Position> optionalPosition = positionRepository.findById(position.getId());
            if (optionalPosition.isEmpty()) {
                return new ApiResponse("position not found", 404);
            }
            Position save = positionRepository.save(position);
            return new ApiResponse("position success update", 201, save);
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResponse("some error", 500, e.getMessage());
        }
    }

    public ApiResponse updatePosition(PositionDto dto, Long positionId) {
        try {
            Optional<Position> optionalPosition = positionRepository.findById(positionId);
            if (optionalPosition.isEmpty()) {
                return new ApiResponse("position not found", 404);
            }
            Position position = optionalPosition.get();
            position.convertDtoToEntity(dto);
            Position save = positionRepository.save(position);
            return new ApiResponse("position success update", 201, save);
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResponse("some error", 500, e.getMessage());
        }
    }

    public ApiResponse getPositionById(Long id) {
        try {
            Optional<Position> optionalPosition = positionRepository.findById(id);
            if (optionalPosition.isEmpty()) {
                return new ApiResponse("position not found", 404);
            }
            return new ApiResponse("position success get", 201, optionalPosition.get());
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResponse("some error", 500, e.getMessage());
        }
    }

    public ApiResponse deletePosition(Long id) {
        try {
            if (positionRepository.existsById(id)) {
                positionRepository.deleteById(id);
                return new ApiResponse("position success delete", 201);
            }
            return new ApiResponse("position not found", 404);
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResponse("some error", 500, e.getMessage());
        }
    }

    public ApiResponse getAllPositions(Pageable pageable) {
        try {
            Iterable<Position> positions = positionRepository.findAll(pageable);
            return new ApiResponse("positions", 200, positions);
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResponse("some error", 500, e.getMessage());
        }
    }

    public ApiResponse deletePositions(List<Long> ids) {
        try {
            positionRepository.deleteAllById(ids);
            return new ApiResponse("success delete positions", 201);
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResponse("some error", 500, e.getMessage());
        }
    }

}
