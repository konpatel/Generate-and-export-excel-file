package kon.patel.export.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import kon.patel.export.dto.Column;
import kon.patel.export.dto.Excel;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class ExportService {

  private final FormatStyles styles;
  private XSSFWorkbook workbook = new XSSFWorkbook();
  private XSSFSheet sheet;

  public byte[] exportToExcel(Excel excel) throws IOException {
    List<JSONObject> jsonObjectList = new ArrayList<JSONObject>();

    excel.getObjList().forEach(obj -> {
      LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) obj;
      JSONObject jsonObject = new JSONObject();
      for (String key : map.keySet()) {
        try {
          jsonObject.put(key, map.get(key));
        } catch (JSONException e) {
          e.printStackTrace();
        }
      }
      jsonObjectList.add(jsonObject);
    });

    sheet = workbook.createSheet();
    Row row = sheet.createRow(0);
    writeHeaderCell(workbook, sheet, row, excel.getColumnList());
    writeDataLines(workbook, sheet, excel.getColumnList(), jsonObjectList);

    return createExcelBytes(workbook);
  }

  private void writeHeaderCell(XSSFWorkbook workbook, XSSFSheet sheet, Row headerRow, List<Column> columnList) {
    AtomicInteger i = new AtomicInteger();
    columnList.forEach(col -> {
      Cell cell = headerRow.createCell(i.get());
      cell.setCellValue(col.getName());
      cell.setCellStyle(styles.getHeaderStyle(workbook));
      sheet.createFreezePane(0, 1);
      sheet.autoSizeColumn(i.get());
      i.getAndIncrement();
    });
  }

  private void writeDataLines(XSSFWorkbook workbook, XSSFSheet sheet, List<Column> columnList, List<JSONObject> jsonObjectList) {
    AtomicInteger rowCount = new AtomicInteger(1);

    jsonObjectList.forEach(json -> {
      Row row = sheet.createRow(rowCount.get());
      AtomicInteger columnCount = new AtomicInteger();
      columnList.forEach(column -> {
        Object value = null;
        try {
          value = json.get(column.getName());
        } catch (JSONException e) {
          e.printStackTrace();
        }
        createCell(row, columnCount.get(), column.getType(), value);
        columnCount.getAndIncrement();
      });
      rowCount.getAndIncrement();
    });

  }

  private void createCell(Row row, int columnCount, String type, Object value) {
    sheet.autoSizeColumn(columnCount);
    Cell cell = row.createCell(columnCount);
    switch (type) {
      case "number":
        cell.setCellValue(Integer.parseInt((String) value));
      case "double":
        if (value instanceof Integer) {
          double dec = ((Integer) value).doubleValue();
          cell.setCellValue(dec);
        } else if (value instanceof Double) {
          cell.setCellValue((Double) value);
        }
      case "currency":
        cell.setCellValue(Double.parseDouble((String) value));
      default:
        cell.setCellValue((String) value);
    }
    cell.setCellStyle(getCellStyle(type, workbook));
  }

  private XSSFCellStyle getCellStyle(String type, XSSFWorkbook workbook) {
    switch (type) {
      case "number":
        return styles.getStyleNumber(workbook);
      case "double":
        return styles.getStyleDouble(workbook);
      case "currency":
        return styles.getStyleCurrency(workbook);
      case "date":
        return styles.getStyleDate(workbook);
      default:
        return styles.getStyleGeneral(workbook);
    }
  }

  private byte[] createExcelBytes(XSSFWorkbook workbook) throws IOException {
    try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
      workbook.write(out);
      return out.toByteArray();
    }
  }

}
