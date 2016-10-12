/**
 * Created by John King on 11-Oct-16.
 */
package test;

import com.kennuware.customersupport.data.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestUnit.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        System.out.println(result.wasSuccessful());
    }
}
