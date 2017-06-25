package unittesting;

import java.lang.reflect.Method;
import java.util.Iterator;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import com.hp.lft.report.Status;
import com.hp.lft.unittesting.TestNgUnitTestBase;

public class UnitTestClassBase extends TestNgUnitTestBase {
    
    @BeforeSuite(alwaysRun=true)
    public void baseBeforeSuite() throws Exception{
        suiteSetup();
    }

    @BeforeClass(alwaysRun=true)
    public void baseBeforeClass(ITestContext ctx) throws Exception {
        String[] context = { ctx.getSuite().getName(), ctx.getName(), this.getClass().getName() };
        className.set(this.getClass().getName());
        classSetup(context);
    }

    @BeforeMethod(alwaysRun=true)
    public void baseBeforeMethod(Object[] arguments, ITestContext ctx, Method method) throws Exception {
        String methodName = super.getMethodName(method, arguments);
        String[] context = { ctx.getSuite().getName(), ctx.getName(), this.getClass().getName(), methodName };
        testName.set(methodName);
        testSetup(context);
    }

    @AfterMethod(alwaysRun=true)
    public void baseAfterMethod(ITestResult result) throws Exception {
        testTearDown();
        setStatus(result.getStatus() == ITestResult.FAILURE ? Status.Failed : Status.Passed);
    }

    @AfterClass(alwaysRun=true)
    public void baseAfterClass(ITestContext ctx) throws Exception {
        classTearDown();
    }

    @AfterSuite(alwaysRun=true)
    public void baseAfterSuite() throws Exception {
        suiteTearDown();
        getReporter().generateReport();
    }

    @Override
    protected String getTestName() {
        return testName.get();
    }

    @Override
    protected String getClassName() {
        return className.get();
    }

    @DataProvider(name="ALM")
    public Iterator<String[]> data() throws Exception {
        return getAlmData().iterator(); 
    }

    protected ThreadLocal<String> className = new ThreadLocal<String>();
    protected ThreadLocal<String> testName = new ThreadLocal<String>();
}