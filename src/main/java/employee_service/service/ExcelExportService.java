package employee_service.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import employee_service.entity.Employee;
import employee_service.repository.EmployeeRepository;

@Service
public class ExcelExportService {

    private final EmployeeRepository employeeRepository;

    public ExcelExportService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public ByteArrayInputStream exportEmployeesToExcel() throws IOException {

        List<Employee> employees = employeeRepository.findAll();

        try (
                Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream()
        ) {

            Sheet sheet = workbook.createSheet("Employees");

            Row headerRow = sheet.createRow(0);

            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("First Name");
            headerRow.createCell(2).setCellValue("Last Name");
            headerRow.createCell(3).setCellValue("Email");
            headerRow.createCell(4).setCellValue("Department");
            headerRow.createCell(5).setCellValue("Salary");

            int rowNumber = 1;

            for (Employee employee : employees) {

                Row row = sheet.createRow(rowNumber++);

                row.createCell(0).setCellValue(employee.getId());
                row.createCell(1).setCellValue(employee.getFirstName());
                row.createCell(2).setCellValue(employee.getLastName());
                row.createCell(3).setCellValue(employee.getEmail());
                row.createCell(4).setCellValue(employee.getDepartment());
                row.createCell(5).setCellValue(employee.getSalary());
            }

            for (int column = 0; column < 6; column++) {
                sheet.autoSizeColumn(column);
            }

            workbook.write(outputStream);

            return new ByteArrayInputStream(outputStream.toByteArray());
        }
    }
}