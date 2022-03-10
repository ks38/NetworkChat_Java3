import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StartTest {

    int before = 0;
    int after = 0;

    public static void start(Class obj) throws Exception {
        StartTest startTest = new StartTest();

        Method[] methods = obj.getDeclaredMethods();
        List<Method> list = new ArrayList<>();
        for (Method method : methods) {
            if (method.isAnnotationPresent(BeforeSuite.class)) {
                startTest.before++;
            }
            if (method.isAnnotationPresent(AfterSuite.class)) {
                startTest.after++;
            }
        }
        if ((startTest.after | startTest.before) > 1) {
            throw new RuntimeException();
        }
        for (Method method : methods) {
            if (method.isAnnotationPresent(BeforeSuite.class)) {
                method.invoke(null);
            }
            if (method.isAnnotationPresent(Test.class)) {
                list.add(method);
            }
        }
        list.sort((m1, m2) -> m2.getAnnotation(Test.class).priority() - m1.getAnnotation(Test.class).priority());


        for (int i = list.size() - 1; i >= 0; i--) {
            System.out.println("Priory: " + list.get(i).getAnnotation(Test.class).priority() +
                    " Test: " + list.get(i).invoke(null));
        }
        for (Method method : methods) {
            if (method.isAnnotationPresent(AfterSuite.class)) {
                method.invoke(null);
            }
        }
    }
}