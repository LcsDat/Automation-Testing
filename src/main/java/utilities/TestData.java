package utilities;

import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;

public class TestData {

    @DataProvider(name = "loginTestData")
    public static Object[][] successData(Method method) {
        String filePath = "D:\\Work\\Automation\\IntelliJ\\Automation-Testing\\src\\main\\resources";
        String fileName = "TestCaseReference.xlsx";
        String sheetName = "Login Success Data";
        var excelManager = new ExcelManager(filePath, fileName, sheetName);

        int rows = excelManager.getSheet().getLastRowNum();
        int columns = excelManager.getSheet().getRow(0).getLastCellNum();

        System.out.println("test case: " + method.getName());
        Object[][] data = new Object[rows][columns];

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                data[row][column] = excelManager.getCell(row + 1,column).toString();
            }
        }

        return data;
    }

    @DataProvider(name = "loginTestData1")
    public static Object[][] failData(Method method) {
        String filePath = "D:\\Work\\Automation\\IntelliJ\\Automation-Testing\\src\\main\\resources";
        String fileName = "TestCaseReference.xlsx";
        String sheetName = "Login Unsuccess Data";
        var excelManager = new ExcelManager(filePath, fileName, sheetName);

        int rows = excelManager.getSheet().getLastRowNum();
        int columns = excelManager.getSheet().getRow(0).getLastCellNum();

        Object[][] data = new Object[rows][columns];

        System.out.println("test case: " + method.getName());

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                data[row][column] = excelManager.getCell(row + 1,column).toString();
            }
        }

        return data;
    }
}
