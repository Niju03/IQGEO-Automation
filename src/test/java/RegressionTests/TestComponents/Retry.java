package RegressionTests.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

    private int retryCount = 0;
    private static final int maxRetryCount = 1; // Maximum number of times to retry

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            return true; // Retry the test if the retry count is within the limit
        }
        return false; // Do not retry if retry count exceeds the max retry limit
    }
}
