package com.kennuware.sales.services;

import com.kennuware.sales.Utilities.HttpUtils;
import com.kennuware.sales.domain.Item;
import com.kennuware.sales.domain.external.WearableItem;
import com.kennuware.sales.domain.external.WearableList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;
import org.mockito.Mockito;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Ryan on 10/31/2016.
 */
public class ItemServiceTest {

    @Test
    public void testGetItems() {
        ItemService service = new ItemService();
        Session mockedSession = mock(Session.class);
        HttpUtils util = mock(HttpUtils.class);

        String jsonObject = "{\"wearables\":[" +
                                "{\"id\":1,\"name\":\"343\",\"type\":\"Act\",\"quantity\":45,\"active\":\"yes\"}," +
                                "{\"id\":2,\"name\":\"344\",\"type\":\"Simple\",\"quantity\":30,\"active\":\"yes\"}" + "]}";

        when(util.get(Mockito.anyString())).thenReturn(jsonObject);
        Query mockedItemsIdQuery1 = mock(Query.class);
        Query mockedItemsIdQuery2 = mock(Query.class);
        when(mockedSession.getNamedQuery("ItemById")).thenReturn(mockedItemsIdQuery1, mockedItemsIdQuery2);
        ArrayList<Item> item = new ArrayList<Item>();

        when(mockedItemsIdQuery1.setString(Mockito.eq("id"),Mockito.eq("1"))).thenReturn(mockedItemsIdQuery1);
        when(mockedItemsIdQuery1.list()).thenReturn(item);

        when(mockedItemsIdQuery2.setString(Mockito.eq("id"),Mockito.eq("2"))).thenReturn(mockedItemsIdQuery2);
        when(mockedItemsIdQuery2.list()).thenReturn(item);


        WearableList items = service.getItems(util, mockedSession);
        System.out.println(items);



        WearableItem item3 = items.getList().get(1);
        assertEquals(2, item3.getId());
        assertEquals("344", item3.getName());
        assertEquals("Simple", item3.getType());
        assertEquals(30, item3.getQuantity());
    }


}
