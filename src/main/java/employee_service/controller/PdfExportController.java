package employee_service.controller;

import java.io.ByteArrayInputStream;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import employee_service.service.PdfExportService;

@RestController
@RequestMapping("/employees/export")
public class PdfExportController {

    private final PdfExportService pdfExportService;

    public PdfExportController(
            PdfExportService pdfExportService) {
        this.pdfExportService = pdfExportService;
    }

    @GetMapping("/pdf")
    public ResponseEntity<InputStreamResource> exportEmployeesToPdf() {

        ByteArrayInputStream pdfFile =
                pdfExportService.exportEmployeesToPdf();

        HttpHeaders headers = new HttpHeaders();

        headers.add(
                HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=employees.pdf"
        );

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(pdfFile));
    }
}