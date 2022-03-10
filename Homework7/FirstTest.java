public class FirstTest {

    @BeforeSuite
    public static void before() {
        System.out.println("THE FIRST MESSAGE");
    }

    public static void notTest() {
        System.out.println("default priority 5");
    }

    @Test(priority = 6)
    public static void addTest1() {
        System.out.println("addTest1 - priority 6");
    }

    @Test(priority = 8)
    public static void addTest2() {
        System.out.println("addTest2 - priority 8");
    }

    @Test(priority = 10)
    public static void addTest3() {
        System.out.println("addTest3 - priority 10");
    }

    @AfterSuite
    public static void after() {
        System.out.println("THE LAST MESSAGE");
    }
}
