package employee_service.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import employee_service.service.ExcelExportService;

@RestController
@RequestMapping("/employees/export")
public class ExcelExportController {

    private final ExcelExportService excelExportService;

    public ExcelExportController(ExcelExportService excelExportService) {
        this.excelExportService = excelExportService;
    }

    @GetMapping("/excel")
    public ResponseEntity<InputStreamResource> exportEmployeesToExcel()
            throws IOException {

        ByteArrayInputStream excelFile =
                excelExportService.exportEmployeesToExcel();

        HttpHeaders headers = new HttpHeaders();

        headers.add(
                HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=employees.xlsx"
        );

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(
                        MediaType.parseMediaType(
                                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
                        )
                )
                .body(new InputStreamResource(excelFile));
    }
}