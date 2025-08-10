package utilities;

import cores.GlobalVariables;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;

public class TestData {

    private static String resoucePath = GlobalVariables.PROJECTPATH + "\\src\\main\\resources";
    @DataProvider(name = "Login-tc01")
    public static Object[][] logicTC01(Method method) {
//        String filePath = "D:\\Work\\Automation\\IntelliJ\\Automation-Testing\\src\\main\\resources";
        String fileName = "TestCaseReference.xlsx";
        String sheetName = "Login-tc01";
        var excelManager = new ExcelManager(resoucePath, fileName, sheetName);

        System.out.println("sheet name " + excelManager.getSheet().toString());

        int rows = excelManager.getSheet().getLastRowNum();
        int columns = excelManager.getSheet().getRow(0).getLastCellNum();

        Object[][] data = new Object[rows][columns];

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                data[row][column] = excelManager.getCell(row + 1,column).toString();
            }
        }

        return data;
    }

    @DataProvider(name = "Login-tc02")
    public static Object[][] loginTC02(Method method) {
//        String filePath = "D:\\Work\\Automation\\IntelliJ\\Automation-Testing\\src\\main\\resources";
        String fileName = "TestCaseReference.xlsx";
        String sheetName = "Login-tc02";
        var excelManager = new ExcelManager(resoucePath, fileName, sheetName);

        int rows = excelManager.getSheet().getLastRowNum();
        int columns = excelManager.getSheet().getRow(0).getLastCellNum();

        Object[][] data = new Object[rows][columns];

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                data[row][column] = excelManager.getCell(row + 1,column).toString();
            }
        }

        return data;
    }

    @DataProvider(name = "OrderProductInChrome-tc01")
    public static Object[][] orderProductInChromeTC01(Method method) {
//        String filePath = "D:\\Work\\Automation\\IntelliJ\\Automation-Testing\\src\\main\\resources";
        String fileName = "TestCaseReference.xlsx";
        String sheetName = "OrderProductInChrome-tc01";
        var excelManager = new ExcelManager(resoucePath, fileName, sheetName);

        int rows = excelManager.getSheet().getLastRowNum();
        int columns = excelManager.getSheet().getRow(0).getLastCellNum();

        Object[][] data = new Object[rows][columns];

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                data[row][column] = excelManager.getCell(row + 1,column).toString();
            }
        }

        return data;
    }
}
