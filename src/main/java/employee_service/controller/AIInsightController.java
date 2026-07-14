package employee_service.controller;

import employee_service.dto.AIInsightDTO;
import employee_service.service.AIInsightService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ai")
public class AIInsightController {

    private final AIInsightService aiInsightService;

    public AIInsightController(AIInsightService aiInsightService) {
        this.aiInsightService = aiInsightService;
    }

    @GetMapping("/insights")
    public AIInsightDTO getInsights() {
        return aiInsightService.generateInsights();
    }
}