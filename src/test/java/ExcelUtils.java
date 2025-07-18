import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;


public class ExcelUtils {


    public static Object[][] getTestData(String excelPath, String sheetName) {
            try (FileInputStream fis = new FileInputStream(new File(excelPath));
                 Workbook workbook = new XSSFWorkbook(fis)) {

                Sheet sheet = workbook.getSheet(sheetName);
                int rowCount = sheet.getPhysicalNumberOfRows();
                int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

                Object[][] data = new Object[rowCount - 1][colCount];

                for (int i = 1; i < rowCount; i++) {
                    Row row = sheet.getRow(i);
                    for (int j = 0; j < colCount; j++) {
                        data[i - 1][j] = row.getCell(j).toString();
                    }
                }
                return data;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }




