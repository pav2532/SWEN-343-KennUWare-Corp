/**
 * Created by John King on 11-Oct-16.
 */

import com.google.gson.Gson;
import com.kennuware.customersupport.data.HibernateUtil;
import com.kennuware.customersupport.domain.Returns;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

import static com.kennuware.customersupport.services.ReturnTicketServices.*;
import static junit.framework.TestCase.*;

public class TestUnit {
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    Session session = sessionFactory.openSession();
    Gson gson = new Gson();

    @Test
    public void testReturnRequest(){
        assertEquals(gson.toJson(returnRequest("Bobby", "Temp Road", "Did a stupid thing", 1, "Fashion", session)),
                "{\"id\":4,\"reason\":\"Did a stupid thing\",\"storeID\":1,\"type\":\"PENDING\",\"itemID\":\"Fashion\"}");
    }

    @Test
    public void testMakeCustomer(){
        assertEquals(gson.toJson(makeCustomer("Bobby", "Temp Road", session)),
                "{\"id\":4,\"address\":\"Temp Road\",\"name\":\"Bobby\"}");
    }
}
