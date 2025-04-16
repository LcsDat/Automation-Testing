package core;

import org.testng.Assert;

public class CustomAssert extends Assert {

    private String keyword;

    private String ansiReset = "\u001B[0m";
    private String ansiCyan = "\u001B[36m";
    private String defaultTrue =  ansiCyan+"[Verification True]" +ansiReset;
    private String defaultFalse = ansiCyan+"[Verification False]"+ansiReset;
    private String defaultEqual = ansiCyan+"[Verification Equal]"+ansiReset;
    private String pass = " PASS ";
    private String fail = " FAIL ";

    private StringBuilder tPass = new StringBuilder(defaultTrue).append(pass);
    private StringBuilder fPass = new StringBuilder(defaultFalse).append(pass);
    private StringBuilder ePass = new StringBuilder(defaultEqual).append(pass);
    private StringBuilder tFail = new StringBuilder(defaultTrue).append(fail);
    private StringBuilder fFail = new StringBuilder(defaultFalse).append(fail);
    private StringBuilder eFail = new StringBuilder(defaultEqual).append(fail);

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
            System.out.println(tPass);
        } catch (Throwable e) {
            result = false;

            System.out.println(tFail
                    .append(getClassName(e))
                    .append(" ")
                    .append(getMethodName(e))
                    .append(" ")
                    .append(e.getMessage())
                    .append(" at .(")
                    .append(getTargetStackElement(e).getFileName())
                    .append(":")
                    .append(getLineNumber(e))
                    .append(")"));
        }
        return result;
    }

    public boolean verifyTrue(boolean condition, String message) {
        boolean result = true;
        try {
            assertTrue(condition);
            System.out.println(tPass + message);
        } catch (Throwable e) {
            result = false;

            System.out.println(tFail
                    .append(getClassName(e))
                    .append(" ")
                    .append(getMethodName(e))
                    .append(" ")
                    .append(e.getMessage())
                    .append(" at .(")
                    .append(getTargetStackElement(e).getFileName())
                    .append(":")
                    .append(getLineNumber(e))
                    .append(")"));
        }
        return result;
    }

    public boolean verifyFalse(boolean condition) {
        boolean result = false;
        try {
            assertFalse(condition);
            System.out.println(fPass);
        } catch (Throwable e) {
            result = true;
            System.out.println(fFail
                    .append(getClassName(e))
                    .append(" ")
                    .append(getMethodName(e))
                    .append(" ")
                    .append(e.getMessage())
                    .append(" at .(")
                    .append(getTargetStackElement(e).getFileName())
                    .append(":")
                    .append(getLineNumber(e))
                    .append(")"));
        }
        return result;
    }

    public boolean verifyFalse(boolean condition, String message) {
        boolean result = false;
        try {
            assertFalse(condition);
            System.out.println(fPass + message);
        } catch (Throwable e) {
            result = true;
            System.out.println(fFail
                    .append(getClassName(e))
                    .append(" ")
                    .append(getMethodName(e))
                    .append(" ")
                    .append(e.getMessage())
                    .append(" at .(")
                    .append(getTargetStackElement(e).getFileName())
                    .append(":")
                    .append(getLineNumber(e))
                    .append(")"));
        }
        return result;
    }

    public void verifyEquals(Object actual, Object expected) {

        try {
            assertEquals(actual, expected);
            System.out.println(ePass);
        } catch (AssertionError e) {
            System.out.println(eFail
                    .append(getClassName(e))
                    .append(" ")
                    .append(getMethodName(e))
                    .append(" ")
                    .append(e.getMessage())
                    .append(" at .(")
                    .append(getTargetStackElement(e).getFileName())
                    .append(":")
                    .append(getLineNumber(e))
                    .append(")"));
        }
    }

    public void verifyEquals(Object actual, Object expected, String message) {

        try {
            assertEquals(actual, expected);
            System.out.println(ePass + message);
        } catch (AssertionError e) {
            System.out.println(eFail
                    .append(getClassName(e))
                    .append(" ")
                    .append(getMethodName(e))
                    .append(" ")
                    .append(e.getMessage())
                    .append(" at .(")
                    .append(getTargetStackElement(e).getFileName())
                    .append(":")
                    .append(getLineNumber(e))
                    .append(")"));
        }
    }

}
