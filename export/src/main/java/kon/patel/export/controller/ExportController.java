package kon.patel.export.controller;

import kon.patel.export.dto.Excel;
import kon.patel.export.service.ExportService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin
@RestController
@AllArgsConstructor
public class ExportController {

  private final ExportService exportService;

  @PostMapping
  @RequestMapping("/export")
  public byte[] exportToExcel(@RequestBody Excel excel) throws IOException {
    return exportService.exportToExcel(excel);
  }

}
