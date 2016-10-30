package com.kennuware.customersupport.services;

import com.kennuware.customersupport.domain.Employees.Employee;
import com.kennuware.customersupport.domain.Employees.EmployeeType;
import com.kennuware.customersupport.domain.Returns;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Ryan on 10/11/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class EmployeeServicesTest {


    @Mock
    private Employee employee;

    @Test
    public void testSmoke() {
        assertEquals(1,1);
    }

    @Test
    public void employeeLoginMockTest() {
        List<Employee> employeeResultList = new ArrayList<Employee>();
        Employee e = new Employee();
        e.setName("TestUser");
        e.setRegionId(1);
        e.setType(EmployeeType.AGENT);
        e.setPassword("test");
        e.setEid(1);
        employeeResultList.add(e);

        Query mockedQuery = mock(Query.class);
        when(mockedQuery.list()).thenReturn(employeeResultList);

        Session mockedSession = mock(Session.class);
        when(mockedSession.getNamedQuery(Mockito.anyString())).thenReturn(mockedQuery);
        when(mockedQuery.setString(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery);

        Employee returnedEmployee = EmployeeServices.login("TestUser", "test", mockedSession);

        assertEquals(e.getName(), returnedEmployee.getName());
        assertEquals(e.getEid(), returnedEmployee.getEid());
    }
}
