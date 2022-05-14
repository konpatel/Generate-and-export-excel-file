package kon.patel.export.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

@Component
public class FormatStyles {

  /**
   *
   * @param workbook: provide your already created workbook.
   * @return a predefined HEADER Font for your columns.
   */
  public Font getHeaderFont(XSSFWorkbook workbook) {
    Font headerFont = workbook.createFont();
    headerFont.setFontHeightInPoints(Short.parseShort("12"));
    headerFont.setFontName("Calibri");
    headerFont.setBold(true);
    headerFont.setItalic(false);
    return headerFont;
  }

  /**
   *
   * @param workbook: provide your already created workbook.
   * @return a predefined HEADER Style for your columns.
   */
  public CellStyle getHeaderStyle(XSSFWorkbook workbook) {
    CellStyle styleHeader = workbook.createCellStyle();
    styleHeader.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    styleHeader.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
    styleHeader.setFont(getHeaderFont(workbook));
    return styleHeader;
  }

  /**
   *
   * @param workbook: provide your already created workbook.
   * @return a predefined GENERAL Cell Style for your data rows.
   */
  public XSSFCellStyle getStyleGeneral(XSSFWorkbook workbook) {
    XSSFCellStyle styleGeneral = workbook.createCellStyle();
    styleGeneral.setVerticalAlignment(VerticalAlignment.CENTER);
    styleGeneral.setAlignment(HorizontalAlignment.LEFT);
    styleGeneral.setWrapText(true);
    return styleGeneral;
  }

  /**
   *
   * @param workbook: provide your already created workbook.
   * @return a predefined NUMBER (integer) Cell Style for your data rows.
   */
  public XSSFCellStyle getStyleNumber(XSSFWorkbook workbook) {
    XSSFCellStyle styleNumber = workbook.createCellStyle();
    styleNumber.setDataFormat(0);
    styleNumber.setVerticalAlignment(VerticalAlignment.CENTER);
    styleNumber.setAlignment(HorizontalAlignment.RIGHT);
    styleNumber.setWrapText(true);
    return styleNumber;
  }

  /**
   *
   * @param workbook: provide your already created workbook.
   * @return a predefined CURRENCY Cell Style for your data rows.
   */
  public XSSFCellStyle getStyleCurrency(XSSFWorkbook workbook) {
    XSSFCellStyle styleCurrency = workbook.createCellStyle();
    styleCurrency.setDataFormat((short) 0x7); // builtin currency format
    styleCurrency.setVerticalAlignment(VerticalAlignment.CENTER);
    styleCurrency.setAlignment(HorizontalAlignment.CENTER);
    styleCurrency.setWrapText(true);
    return styleCurrency;
  }

  /**
   *
   * @param workbook: provide your already created workbook.
   * @return a predefined DECIMAL Cell Style for your data rows.
   */
  public XSSFCellStyle getStyleDouble(XSSFWorkbook workbook) {
    XSSFCellStyle styleDouble = workbook.createCellStyle();
    styleDouble.setDataFormat(4);
    styleDouble.setVerticalAlignment(VerticalAlignment.CENTER);
    styleDouble.setAlignment(HorizontalAlignment.CENTER);
    styleDouble.setWrapText(true);
    return styleDouble;
  }

  /**
   *
   * @param workbook: provide your already created workbook.
   * @return a predefined DATE Cell Style for your data rows.
   */
  public XSSFCellStyle getStyleDate(XSSFWorkbook workbook) {
    XSSFCellStyle styleDate = workbook.createCellStyle();
    styleDate.setDataFormat((short) 14);
    styleDate.setVerticalAlignment(VerticalAlignment.CENTER);
    return styleDate;
  }

  /**
   *
   * @return a predefined text for BOOLEAN fields to fill in your data rows.
   */
  public String yesOrNot(boolean b) {
    return b ? "Ναι" : "Όχι";
  }

  /**
   * Use it to create your own font Styles
   * @param workbook: provide your already created workbook.
   * @param fontSize: the font size, integer
   * @param fontName: the font name, string
   * @param isBold: true or false
   * @param isItalic: true or false
   * @param isStrikeOut: true or false
   * @return a font variable of Type Font, that you can you in a cellStyle.
   * For example, myStyle.setFont(getDataFont(all your variables))
   */
  public Font getDataFont(
    XSSFWorkbook workbook,
    int fontSize,
    String fontName,
    Boolean isBold,
    Boolean isItalic,
    Boolean isStrikeOut
  ) {
    Font dataFont = workbook.createFont();
    dataFont.setFontHeightInPoints((short)(fontSize));
    dataFont.setFontName(fontName);
    dataFont.setBold(isBold);
    dataFont.setItalic(isItalic);
    dataFont.setStrikeout(isStrikeOut);
    return dataFont;
  }

}
