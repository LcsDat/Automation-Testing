package cores;

import org.testng.Assert;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CustomAssert extends Assert {
    private String formattedTime = new SimpleDateFormat("hh:mm:ss a", Locale.US).format(new Date());
    private String keyword;

    private char checkIcon = (char) 10004;
    private char clockIcon = (char) 9200;

    private String ansiReset = "\u001B[0m";
    private String ansiCyan = "\u001B[36m";
    private String ansiGreen = "\u001B[32m";
    private String ansiRed = "\u001B[31m";
    private String wordOrange = "\033[38:5:208m";
    private String wordReset = "\033[m";

    private String defaultTrue = "[" + (char) 10004 + " True ]";
    private String defaultFalse = "[" + (char) 10004 + " False]";
    private String defaultEqual = "[" + (char) 10004 + " Equal]";
    private String pass = "PASS";
    private String fail = "FAIL";
    private String failFormat = "%c%12s%8s%S%5s" + "%s%s%7s" + "%s " + "%s " + "%s%s%s at .(%s:%d) %n";
    private String passFormat = "%c%12s%8s%S%5s" + "%s%S%7s";

    public CustomAssert(String keyword) {
        this.keyword = keyword;
    }

    /**
     * @param e To get StackTraceElements
     * @return The target StacktraceElement
     */
    private StackTraceElement getTargetStackElement(Throwable e) {
        StackTraceElement targetStack = null;
        for (StackTraceElement element : e.getStackTrace()) {
            if (element.toString().contains(keyword)) {
                targetStack = element;
                break;
            }
        }
        return targetStack;
    }

    private String getClassName(Throwable e) {
        return getTargetStackElement(e).getClassName();
    }

    private String getMethodName(Throwable e) {
        return getTargetStackElement(e).getMethodName();
    }

    private int getLineNumber(Throwable e) {
        return getTargetStackElement(e).getLineNumber();
    }

    public boolean verifyTrue(boolean condition) {
        boolean result = true;

        try {
            assertTrue(condition);
            System.out.printf(passFormat + "%n", clockIcon ,formattedTime, ansiCyan, defaultTrue, ansiReset, ansiGreen, pass, ansiReset);
        } catch (Throwable e) {
            result = false;

            System.out.printf(failFormat
                   ,clockIcon , formattedTime, ansiCyan, defaultTrue, ansiReset, ansiRed, fail, ansiReset, getClassName(e), getMethodName(e), wordOrange, e.getMessage(), wordReset, getTargetStackElement(e).getFileName(), getLineNumber(e));
        }
        return result;
    }

    public boolean verifyTrue(boolean condition, String message) {
        boolean result = true;
        try {
            assertTrue(condition);
            System.out.printf(passFormat + message + "%n", clockIcon ,formattedTime, ansiCyan, defaultTrue, ansiReset, ansiGreen, pass, ansiReset);
        } catch (Throwable e) {
            result = false;
            System.out.printf(failFormat
                    , clockIcon ,formattedTime, ansiCyan, defaultTrue, ansiReset, ansiRed, fail, ansiReset, getClassName(e), getMethodName(e), wordOrange, e.getMessage(), wordReset, getTargetStackElement(e).getFileName(), getLineNumber(e));
        }
        return result;
    }

    public boolean verifyFalse(boolean condition) {
        boolean result = false;
        try {
            assertFalse(condition);
            System.out.printf(passFormat + "%n", clockIcon ,formattedTime, ansiCyan, defaultFalse, ansiReset, ansiGreen, pass, ansiReset);
        } catch (Throwable e) {
            result = true;
            System.out.printf(failFormat
                    , clockIcon ,formattedTime, ansiCyan, defaultFalse, ansiReset, ansiRed, fail, ansiReset, getClassName(e), getMethodName(e), wordOrange, e.getMessage(), wordReset, getTargetStackElement(e).getFileName(), getLineNumber(e));
        }
        return result;
    }

    public boolean verifyFalse(boolean condition, String message) {
        boolean result = false;
        try {
            assertFalse(condition);
            System.out.printf(passFormat + message + "%n", clockIcon ,formattedTime, ansiCyan, defaultFalse, ansiReset, ansiGreen, pass, ansiReset);
        } catch (Throwable e) {
            result = true;
            System.out.printf(failFormat
                    , clockIcon ,formattedTime, ansiCyan, defaultFalse, ansiReset, ansiRed, fail, ansiReset, getClassName(e), getMethodName(e), wordOrange, e.getMessage(), wordReset, getTargetStackElement(e).getFileName(), getLineNumber(e));
        }
        return result;
    }

    public void verifyEquals(Object actual, Object expected) {

        try {
            assertEquals(actual, expected);
            System.out.printf(passFormat + "%n", clockIcon ,formattedTime, ansiCyan, defaultEqual, ansiReset, ansiGreen, pass, ansiReset);
        } catch (AssertionError e) {
            System.out.printf(failFormat,
                    clockIcon ,formattedTime, ansiCyan, defaultEqual, ansiReset, ansiRed, fail, ansiReset, getClassName(e), getMethodName(e), wordOrange, e.getMessage(), wordReset, getTargetStackElement(e).getFileName(), getLineNumber(e));
        }
    }

    public void verifyEquals(Object actual, Object expected, String message) {

        try {
            assertEquals(actual, expected);
            System.out.printf(passFormat + message + "%n", clockIcon ,formattedTime, ansiCyan, defaultEqual, ansiReset, ansiGreen, pass, ansiReset);
        } catch (AssertionError e) {
            System.out.printf(failFormat,
                    clockIcon ,formattedTime, ansiCyan, defaultEqual, ansiReset, ansiRed, fail, ansiReset, getClassName(e), getMethodName(e), wordOrange, e.getMessage(), wordReset, getTargetStackElement(e).getFileName(), getLineNumber(e));
        }
    }

}
