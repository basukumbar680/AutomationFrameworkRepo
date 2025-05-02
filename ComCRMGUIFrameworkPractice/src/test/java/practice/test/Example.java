package practice.test;

public class Example {
	/*TestClass extends BaseClass

	*TestClass has two test methods

	*@Listeners is applied on TestClass

	*All lifecycle annotations (@BeforeSuite, @BeforeTest, @BeforeClass, etc.) are in the BaseClass

	*MyListener implements both ITestListener and ISuiteListener.
	

	public class MyListener implements ITestListener, ISuiteListener {

	    // ISuiteListener methods
	    public void onStart(ISuite suite) {
	        System.out.println("ISuiteListener: onStart");
	    }

	    public void onFinish(ISuite suite) {
	        System.out.println("ISuiteListener: onFinish");
	    }

	    // ITestListener methods
	    public void onStart(ITestContext context) {
	        System.out.println("ITestListener: onStart");
	    }

	    public void onFinish(ITestContext context) {
	        System.out.println("ITestListener: onFinish");
	    }

	    public void onTestStart(ITestResult result) {
	        System.out.println("ITestListener: onTestStart -> " + result.getName());
	    }

	    public void onTestSuccess(ITestResult result) {
	        System.out.println("ITestListener: onTestSuccess -> " + result.getName());
	    }

	    public void onTestFailure(ITestResult result) {
	        System.out.println("ITestListener: onTestFailure -> " + result.getName());
	    }

	    public void onTestSkipped(ITestResult result) {
	        System.out.println("ITestListener: onTestSkipped -> " + result.getName());
	    }
	}
	
	
	public class BaseClass {

	    @BeforeSuite
	    public void beforeSuite() {
	        System.out.println("BaseClass: @BeforeSuite");
	    }

	    @BeforeTest
	    public void beforeTest() {
	        System.out.println("BaseClass: @BeforeTest");
	    }

	    @BeforeClass
	    public void beforeClass() {
	        System.out.println("BaseClass: @BeforeClass");
	    }

	    @BeforeMethod
	    public void beforeMethod() {
	        System.out.println("BaseClass: @BeforeMethod");
	    }

	    @AfterMethod
	    public void afterMethod() {
	        System.out.println("BaseClass: @AfterMethod");
	    }

	    @AfterClass
	    public void afterClass() {
	        System.out.println("BaseClass: @AfterClass");
	    }

	    @AfterTest
	    public void afterTest() {
	        System.out.println("BaseClass: @AfterTest");
	    }

	    @AfterSuite
	    public void afterSuite() {
	        System.out.println("BaseClass: @AfterSuite");
	    }
	}


	@Listeners(MyListener.class)
	public class TestClass extends BaseClass {

	    @Test
	    public void testMethod1() {
	        System.out.println("TestClass: testMethod1 executed");
	    }

	    @Test
	    public void testMethod2() {
	        System.out.println("TestClass: testMethod2 executed");
	    }
	}
	
	
	ISuiteListener: onStart
	BaseClass: @BeforeSuite
	BaseClass: @BeforeTest
	ITestListener: onStart
	BaseClass: @BeforeClass

	// First Test
	BaseClass: @BeforeMethod
	ITestListener: onTestStart -> testMethod1
	TestClass: testMethod1 executed
	ITestListener: onTestSuccess -> testMethod1
	BaseClass: @AfterMethod

	// Second Test
	BaseClass: @BeforeMethod
	ITestListener: onTestStart -> testMethod2
	TestClass: testMethod2 executed
	ITestListener: onTestSuccess -> testMethod2
	BaseClass: @AfterMethod

	BaseClass: @AfterClass
	ITestListener: onFinish
	BaseClass: @AfterTest
	BaseClass: @AfterSuite
	ISuiteListener: onFinish

	
	✅ Summary of Execution Flow:
		1.Suite level starts → ISuiteListener.onStart

		2.BaseClass annotations kick in: @BeforeSuite, @BeforeTest, @BeforeClass

		3.TestListener lifecycle starts: ITestListener.onStart

		4.Each test:

		@BeforeMethod (BaseClass)

		onTestStart

		@Test method

		onTestSuccess / onTestFailure

		@AfterMethod

		5.After all tests:

		@AfterClass, @AfterTest, @AfterSuite

		ITestListener.onFinish

		ISuiteListener.onFinish*/

}
