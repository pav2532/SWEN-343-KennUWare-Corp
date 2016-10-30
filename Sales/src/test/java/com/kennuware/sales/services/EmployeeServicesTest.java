package com.kennuware.sales.services;

import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.gson.Gson;
import com.kennuware.sales.domain.Employees.Employee;
import com.kennuware.sales.domain.SalesOrder;
import com.kennuware.sales.domain.Employees.Employee;
import junit.framework.Assert;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static com.kennuware.sales.services.EmployeeServices.login;
import static com.kennuware.sales.services.OrderServices.completeSaleOrder;
import static com.kennuware.sales.services.OrderServices.sendOrder;
import static com.kennuware.sales.services.EmployeeServices.login;
import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServicesTest {
    @Test
    public void loginTest(){
        Gson gson = new Gson();
        Session mockedSession = mock(Session.class);
        List<Employee> employeeResultList = new ArrayList<Employee>();

        Employee e = new Employee();
        e.setName("SalesRep1");
        e.setRegionId(0);
        e.setPassword("test");
        e.setEid(1);
        employeeResultList.add(e);

        Query mockedQuery = mock(Query.class);
        when(mockedQuery.list()).thenReturn(employeeResultList);

        when(mockedSession.getNamedQuery(Mockito.anyString())).thenReturn(mockedQuery);
        when(mockedQuery.setString(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery);

        assertEquals(gson.toJson(e), gson.toJson(login("SalesRep1", "test", mockedSession)));
        assertEquals(gson.toJson(null), gson.toJson(login("NONEXISTANT USER","NONEXISTANT PASSWORD", mockedSession)));
    }
}

