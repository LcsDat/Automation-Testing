package cores;

import org.testng.Assert;

import java.util.Arrays;

public class CustomAssert extends Assert {

    public CustomAssert() {
    }

    private static String getClassName(Throwable e){
        return e.getStackTrace()[6].getClassName();
    }

    private static String getMethodName(Throwable e){
        return e.getStackTrace()[6].getMethodName();
    }

    private static int getLineNumber(Throwable e){
        return e.getStackTrace()[6].getLineNumber();
    }

    public boolean verifyTrue(boolean condition){
        boolean result = true;
        try {
            assertTrue(condition);
            System.out.println("[Verification] PASS");
        } catch (Throwable e){
            result = false;
            System.out.println("FAIL: " + getClassName(e) + " " + getMethodName(e) + " " + e.getMessage() + " at line " + getLineNumber(e));
        }
        return result;
    }

    public boolean verifyFalse(boolean condition){
        boolean result = false;
        try {
            assertFalse(condition);
            System.out.println("[Verification] PASS");
        } catch (Throwable e){
            result = true;
            System.out.println("[Verification] FAIL: " + getClassName(e) + " " + getMethodName(e) + " " + e.getMessage()+ " at line " + getLineNumber(e));
        }
        return result;
    }

    public void verifyEquals(Object actual, Object expected){
        try {
            assertEquals(actual, expected);
            System.out.println("[Verification] PASS");
        } catch (Throwable e){
//            System.out.println(Arrays.toString(e.getStackTrace()));
            System.out.println("FAIL: " + e.getStackTrace()[7].getClassName() + " " + e.getStackTrace()[7].getMethodName() + " " + e.getMessage() + " at .("  + e.getStackTrace()[7].getFileName() + ":" +e.getStackTrace()[7].getLineNumber() + ")");
        }
    }

}
