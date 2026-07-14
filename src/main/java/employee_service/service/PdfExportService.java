package employee_service.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import employee_service.entity.Employee;
import employee_service.repository.EmployeeRepository;

@Service
public class PdfExportService {

    private final EmployeeRepository employeeRepository;

    public PdfExportService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public ByteArrayInputStream exportEmployeesToPdf() {

        List<Employee> employees = employeeRepository.findAll();

        Document document = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, outputStream);

            document.open();

            Font titleFont = FontFactory.getFont(
                    FontFactory.HELVETICA_BOLD,
                    18
            );

            Paragraph title = new Paragraph(
                    "Employee Report",
                    titleFont
            );

            title.setAlignment(Paragraph.ALIGN_CENTER);
            title.setSpacingAfter(20);

            document.add(title);

            PdfPTable table = new PdfPTable(6);
            table.setWidthPercentage(100);

            addHeaderCell(table, "ID");
            addHeaderCell(table, "First Name");
            addHeaderCell(table, "Last Name");
            addHeaderCell(table, "Email");
            addHeaderCell(table, "Department");
            addHeaderCell(table, "Salary");

            for (Employee employee : employees) {
                table.addCell(String.valueOf(employee.getId()));
                table.addCell(employee.getFirstName());
                table.addCell(employee.getLastName());
                table.addCell(employee.getEmail());
                table.addCell(employee.getDepartment());
                table.addCell(String.valueOf(employee.getSalary()));
            }

            document.add(table);

        } catch (DocumentException exception) {
            throw new RuntimeException(
                    "Failed to generate employee PDF",
                    exception
            );
        } finally {
            document.close();
        }

        return new ByteArrayInputStream(outputStream.toByteArray());
    }

    private void addHeaderCell(
            PdfPTable table,
            String value) {

        Font font = FontFactory.getFont(
                FontFactory.HELVETICA_BOLD
        );

        PdfPCell cell = new PdfPCell(
                new Phrase(value, font)
        );

        cell.setPadding(6);

        table.addCell(cell);
    }
}