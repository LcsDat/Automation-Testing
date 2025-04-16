package core;

import org.testng.Assert;

public class CustomAssert extends Assert {

    private String keyword;

    private String ansiReset = "\u001B[0m";
    private String ansiCyan = "\u001B[36m";
    private String ansiGreen = "\u001B[32m";
    private String ansiRed = "\u001B[31m";
    private String wordOrange = "\033[38:5:208m";
    private String wordReset = "\033[m";

    private String defaultTrue = "[Verification True ]";
    private String defaultFalse = "[Verification False]";
    private String defaultEqual = "[Verification Equal]";
    private String pass = "PASS";
    private String fail = "FAIL";
    private String failFormat = "%s%S%5s" + "%s%s%7s" + "%s " + "%s " + "%s%S%s at .(%s:%d) %n";
    private String passFormat = "%s%S%5s" + "%s%S%s%n";

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
            System.out.printf(passFormat, ansiCyan, defaultTrue, ansiReset, ansiGreen, pass, ansiReset);
        } catch (Throwable e) {
            result = false;

            System.out.printf(failFormat, ansiCyan, defaultTrue, ansiReset, ansiRed, fail, ansiReset
                    , getClassName(e), getMethodName(e), e.getMessage(), getTargetStackElement(e).getFileName(), getLineNumber(e));
        }
        return result;
    }

    public boolean verifyTrue(boolean condition, String message) {
        boolean result = true;
        try {
            assertTrue(condition);
            System.out.printf(passFormat, ansiCyan, defaultTrue, ansiReset, ansiGreen, pass, ansiReset);
        } catch (Throwable e) {
            result = false;
            System.out.printf(failFormat
                    , ansiCyan, defaultTrue, ansiReset, ansiRed, fail, ansiReset, getClassName(e), getMethodName(e), wordOrange, e.getMessage(), wordReset, getTargetStackElement(e).getFileName(), getLineNumber(e));
        }
        return result;
    }

    public boolean verifyFalse(boolean condition) {
        boolean result = false;
        try {
            assertFalse(condition);
            System.out.printf(passFormat, ansiCyan, defaultFalse, ansiReset, ansiGreen, pass, ansiReset);
        } catch (Throwable e) {
            result = true;
            System.out.printf(failFormat
                    , ansiCyan, defaultFalse, ansiReset, ansiRed, fail, ansiReset, getClassName(e), getMethodName(e), wordOrange, e.getMessage(), wordReset, getTargetStackElement(e).getFileName(), getLineNumber(e));
        }
        return result;
    }

    public boolean verifyFalse(boolean condition, String message) {
        boolean result = false;
        try {
            assertFalse(condition);
            System.out.printf(passFormat, ansiCyan, defaultFalse, ansiReset, ansiGreen, pass, ansiReset);
        } catch (Throwable e) {
            result = true;
            System.out.printf(failFormat
                    , ansiCyan, defaultFalse, ansiReset, ansiRed, fail, ansiReset, getClassName(e), getMethodName(e), wordOrange, e.getMessage(), wordReset, getTargetStackElement(e).getFileName(), getLineNumber(e));
        }
        return result;
    }

    public void verifyEquals(Object actual, Object expected) {

        try {
            assertEquals(actual, expected);
            System.out.printf(passFormat, ansiCyan, defaultEqual, ansiReset, ansiGreen, pass, ansiReset);
        } catch (AssertionError e) {
            System.out.printf(failFormat,
                    ansiCyan, defaultEqual, ansiReset, ansiRed, fail, ansiReset, getClassName(e), getMethodName(e), wordOrange, e.getMessage(), wordReset, getTargetStackElement(e).getFileName(), getLineNumber(e));
        }
    }

    public void verifyEquals(Object actual, Object expected, String message) {

        try {
            assertEquals(actual, expected);
            System.out.printf(passFormat, ansiCyan, defaultEqual, ansiReset, ansiGreen, pass, ansiReset);
        } catch (AssertionError e) {
            System.out.printf(failFormat,
                    ansiCyan, defaultEqual, ansiReset, ansiRed, fail, ansiReset, getClassName(e), getMethodName(e), wordOrange, e.getMessage(), wordReset, getTargetStackElement(e).getFileName(), getLineNumber(e));
        }
    }

}
