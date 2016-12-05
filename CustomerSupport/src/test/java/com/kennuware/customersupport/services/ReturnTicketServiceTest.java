package com.kennuware.customersupport.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javassist.expr.NewArray;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;
import org.mockito.Mockito;

import com.kennuware.customersupport.Utilities.HttpUtils;

/**
 * Created by Ryan on 10/31/2016.
 */
public class ReturnTicketServiceTest {
	
	
	
	@Test
    public void MostReturnedwDeniedTest() {
        
		String expectedResult = "{\"model\":\"Active\", \"count\": 1000}"; 
        List queryResult = new ArrayList<Object>();
        ArrayList<String> row = new ArrayList<>();
        
        row.add("Active");
        row.add("1000");
    	queryResult.add(row.toArray());
    	row.clear();
    	row.add("Health");
        row.add("900");
    	queryResult.add(row.toArray());
    	row.clear();
    	row.add("Fitness");
        row.add("100");
    	queryResult.add(row.toArray());
    	row.clear();
    	
        Session session= mock(Session.class);
        Query query = mock(Query.class);
        when(session.getNamedQuery(Mockito.anyString())).thenReturn(query);
        when(query.list()).thenReturn(queryResult);

        String result = ReturnTicketService.getMostReturnedModel(session, true);
        
        assertEquals(expectedResult, result);
    }
	
	@Test
	public void MostReturnedTest() {
        
		String expectedResult = "{\"model\":\"Active\", \"count\": 1000}"; 
        List queryResult = new ArrayList<Object>();
        ArrayList<String> row = new ArrayList<>();
        
    	row.add("Active");
        row.add("1000");
    	queryResult.add(row.toArray());
    	row.clear();
    	row.add("Health");
        row.add("900");
    	queryResult.add(row.toArray());
    	row.clear();
    	row.add("Fitness");
        row.add("100");
    	queryResult.add(row.toArray());
    	row.clear();
    	
        Session session= mock(Session.class);
        Query query = mock(Query.class);
        when(session.getNamedQuery(Mockito.anyString())).thenReturn(query);
        when(query.list()).thenReturn(queryResult);

        String result = ReturnTicketService.getMostReturnedModel(session, false);
        
        assertEquals(expectedResult, result);
    }

	@Test
    public void LeastReturnedwDeniedTest() {
        
		String expectedResult = "{\"model\":\"Fitness\", \"count\": 100}"; 
        List queryResult = new ArrayList<Object>();
        ArrayList<String> row = new ArrayList<>();
        
        row.add("Active");
        row.add("1000");
    	queryResult.add(row.toArray());
    	row.clear();
    	row.add("Health");
        row.add("900");
    	queryResult.add(row.toArray());
    	row.clear();
    	row.add("Fitness");
        row.add("100");
    	queryResult.add(row.toArray());
    	row.clear();
    	
        Session session= mock(Session.class);
        Query query = mock(Query.class);
        when(session.getNamedQuery(Mockito.anyString())).thenReturn(query);
        when(query.list()).thenReturn(queryResult);

        String result = ReturnTicketService.getLeastReturnedModel(session, true);
        
        assertEquals(expectedResult, result);
    }

	@Test
	public void LeastReturnedTest() {
        
		String expectedResult = "{\"model\":\"Fitness\", \"count\": 100}"; 
        List queryResult = new ArrayList<Object>();
        ArrayList<String> row = new ArrayList<>();
        
    	row.add("Active");
        row.add("1000");
    	queryResult.add(row.toArray());
    	row.clear();
    	row.add("Health");
        row.add("900");
    	queryResult.add(row.toArray());
    	row.clear();
    	row.add("Fitness");
        row.add("100");
    	queryResult.add(row.toArray());
    	row.clear();
    	
        Session session= mock(Session.class);
        Query query = mock(Query.class);
        when(session.getNamedQuery(Mockito.anyString())).thenReturn(query);
        when(query.list()).thenReturn(queryResult);

        String result = ReturnTicketService.getLeastReturnedModel(session, false);
        
        assertEquals(expectedResult, result);
    }
	
	@Test
	public void HighestReturnsReasonTest() {
        
		String expectedResult = "{\"reason\":\"Broken\", \"count\": 1000}"; 
        List queryResult = new ArrayList<Object>();
        ArrayList<String> row = new ArrayList<>();
        
    	row.add("Broken");
        row.add("1000");
    	queryResult.add(row.toArray());
    	row.clear();
    	row.add("Got a new version");
        row.add("900");
    	queryResult.add(row.toArray());
    	row.clear();
    	row.add("Didn't like it");
        row.add("100");
    	queryResult.add(row.toArray());
    	row.clear();
    	
        Session session= mock(Session.class);
        Query query = mock(Query.class);
        when(session.getNamedQuery(Mockito.anyString())).thenReturn(query);
        when(query.list()).thenReturn(queryResult);

        String result = ReturnTicketService.getHighestReturnsReason(session);
        
        assertEquals(expectedResult, result);
    }

	@Test
	public void ReturnsWithType0Test() {
        
		String expectedResult = "{\"count\": 10}"; 
        List queryResult = new ArrayList<Object>();


        for(int i = 0; i < 10; i++){
        	queryResult.add(new String(""));
        }
        
    	
    	
        Session session= mock(Session.class);
        Query query = mock(Query.class);
        when(session.getNamedQuery(Mockito.anyString())).thenReturn(query);
        when(session.getNamedQuery(Mockito.anyString()).setInteger(Mockito.anyString(), 
        		Mockito.anyInt())).thenReturn(query);
        when(query.list()).thenReturn(queryResult);

        String result = ReturnTicketService.getReturnsWith(session, 0);
        
        assertEquals(expectedResult, result);
    }
	
	@Test
	public void ReturnsWithType1Test() {
        
		String expectedResult = "{\"count\": 10}"; 
        List queryResult = new ArrayList<Object>();


        for(int i = 0; i < 10; i++){
        	queryResult.add(new String(""));
        }
        
    	
    	
        Session session= mock(Session.class);
        Query query = mock(Query.class);
        when(session.getNamedQuery(Mockito.anyString())).thenReturn(query);
        when(session.getNamedQuery(Mockito.anyString()).setInteger(Mockito.anyString(), 
        		Mockito.anyInt())).thenReturn(query);
        when(query.list()).thenReturn(queryResult);

        String result = ReturnTicketService.getReturnsWith(session, 1);
        
        assertEquals(expectedResult, result);
    }
	
	@Test
	public void ReturnsWithType2Test() {
        
		String expectedResult = "{\"count\": 10}"; 
        List queryResult = new ArrayList<Object>();


        for(int i = 0; i < 10; i++){
        	queryResult.add(new String(""));
        }
        
    	
    	
        Session session= mock(Session.class);
        Query query = mock(Query.class);
        when(session.getNamedQuery(Mockito.anyString())).thenReturn(query);
        when(session.getNamedQuery(Mockito.anyString()).setInteger(Mockito.anyString(), 
        		Mockito.anyInt())).thenReturn(query);
        when(query.list()).thenReturn(queryResult);

        String result = ReturnTicketService.getReturnsWith(session, 2);
        
        assertEquals(expectedResult, result);
    }
}
