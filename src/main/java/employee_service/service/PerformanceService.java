package employee_service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import employee_service.dto.PerformanceDTO;
import employee_service.entity.Performance;
import employee_service.repository.PerformanceRepository;

@Service
public class PerformanceService {

    @Autowired
    private PerformanceRepository performanceRepository;

    public PerformanceDTO createPerformance(PerformanceDTO dto) {

        Performance performance = new Performance();

        performance.setEmployeeId(dto.getEmployeeId());
        performance.setEmployeeName(dto.getEmployeeName());
        performance.setReviewPeriod(dto.getReviewPeriod());
        performance.setRating(dto.getRating());
        performance.setGoalsAchieved(dto.getGoalsAchieved());
        performance.setManagerComments(dto.getManagerComments());
        performance.setPerformanceStatus(dto.getPerformanceStatus());

        Performance savedPerformance =
                performanceRepository.save(performance);

        return toDTO(savedPerformance);
    }

    public List<PerformanceDTO> getAllPerformances() {

        return performanceRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public PerformanceDTO getPerformanceById(Long id) {

        Performance performance = performanceRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Performance record not found")
                );

        return toDTO(performance);
    }

    public PerformanceDTO updatePerformance(
            Long id,
            PerformanceDTO dto) {

        Performance performance = performanceRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Performance record not found")
                );

        performance.setEmployeeId(dto.getEmployeeId());
        performance.setEmployeeName(dto.getEmployeeName());
        performance.setReviewPeriod(dto.getReviewPeriod());
        performance.setRating(dto.getRating());
        performance.setGoalsAchieved(dto.getGoalsAchieved());
        performance.setManagerComments(dto.getManagerComments());
        performance.setPerformanceStatus(dto.getPerformanceStatus());

        Performance updatedPerformance =
                performanceRepository.save(performance);

        return toDTO(updatedPerformance);
    }

    public void deletePerformance(Long id) {

        Performance performance = performanceRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Performance record not found")
                );

        performanceRepository.delete(performance);
    }

    private PerformanceDTO toDTO(Performance performance) {

        PerformanceDTO dto = new PerformanceDTO();

        dto.setId(performance.getId());
        dto.setEmployeeId(performance.getEmployeeId());
        dto.setEmployeeName(performance.getEmployeeName());
        dto.setReviewPeriod(performance.getReviewPeriod());
        dto.setRating(performance.getRating());
        dto.setGoalsAchieved(performance.getGoalsAchieved());
        dto.setManagerComments(performance.getManagerComments());
        dto.setPerformanceStatus(performance.getPerformanceStatus());

        return dto;
    }
}