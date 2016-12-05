package com.kennuware.sales.services;

import static com.kennuware.sales.services.EmployeeService.login;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import com.kennuware.sales.Utilities.HttpUtils;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.gson.Gson;
import com.kennuware.sales.domain.Employees.Employee;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;
import org.mockito.Mockito;
import com.kennuware.sales.domain.Item;
import com.kennuware.sales.domain.ItemOrders;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

	@Test
	public void getEmployeeRevenueTest() throws Exception {
		
		Session mockedSession = mock(Session.class);
		
		Query mockedOrderIdsQuery = mock(Query.class);
		Query mockedItemsQuery = mock(Query.class);
		Query mockedItemOrdersQuery = mock(Query.class);
		
		List orderIds = new ArrayList<Integer>();
		orderIds.add(1);
		orderIds.add(3);
		
		ArrayList<ItemOrders> itemOrders = new ArrayList<>();
		itemOrders.add(new ItemOrders(1, 1, 1));
		itemOrders.add(new ItemOrders(2, 1, 1));
		itemOrders.add(new ItemOrders(3, 2, 1));
		
		ArrayList<Item> items = new ArrayList<>();
		items.add(new Item(1, "F", 10));
		items.add(new Item(2, "H", 20));
		items.add(new Item(3, "W", 40));
		
		
		when(mockedSession.getNamedQuery("findAllItemOrders")).thenReturn(mockedItemOrdersQuery);
		when(mockedSession.getNamedQuery("findOrderIdsByEmployeeID")).thenReturn(mockedOrderIdsQuery);
		when(mockedOrderIdsQuery.setString(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedOrderIdsQuery);
		when(mockedOrderIdsQuery.list()).thenReturn(orderIds);
		when(mockedSession.getNamedQuery("findAllItems")).thenReturn(mockedItemsQuery);
		when(mockedItemsQuery.list()).thenReturn(items);
		when(mockedItemOrdersQuery.iterate()).thenReturn(itemOrders.iterator());
		
		
		
		assertEquals(30.0, EmployeeService.getEmployeeRevenue("1", mockedSession), 0);
	}

	/**
	 * This test should be moved onto SSO and converted
	 */
//    @Test
//    public void loginTest(){
//        Gson gson = new Gson();
//        Session mockedSession = mock(Session.class);
//        List<Employee> employeeResultList = new ArrayList<Employee>();
//
//        Employee e = new Employee();
//        e.setName("SalesRep1");
//        e.setRegionId(0);
//        e.setEid(1);
//        employeeResultList.add(e);
//
//        Query mockedQuery = mock(Query.class);
//        when(mockedQuery.list()).thenReturn(employeeResultList);
//
//        when(mockedSession.getNamedQuery(Mockito.anyString())).thenReturn(mockedQuery);
//        when(mockedQuery.setString(Mockito.anyString(), Mockito.anyString())).thenReturn(mockedQuery);
//
//        assertEquals(gson.toJson(e), gson.toJson(login("SalesRep1", mockedSession)));
//        assertEquals(gson.toJson(null), gson.toJson(login("NONEXISTANT USER",mockedSession)));
//    }

	@Test
	public void verifyEmployeeTest() {
		EmployeeService service = new EmployeeService();
		String expectedResultJson = "{\"exists\": true}";
		Boolean expectedResult = true;
		HttpUtils util = mock(HttpUtils.class);
		when(util.get(Mockito.anyString())).thenReturn(expectedResultJson);

		Boolean result = service.verifyEmployee(util, 1);
		assertEquals(expectedResult, result);
	}
}
