package com.kennuware.sales.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.kennuware.sales.domain.Item;
import com.kennuware.sales.domain.ItemOrders;
import com.kennuware.sales.domain.Employees.Employee;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServicesTest {

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
		
		
		
		assertEquals(30.0, EmployeeServices.getEmployeeRevenue("1", mockedSession), 0);
	}
}
