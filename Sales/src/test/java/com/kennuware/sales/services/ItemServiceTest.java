package com.kennuware.sales.services;

import com.kennuware.sales.Utilities.HttpUtils;
import com.kennuware.sales.domain.WearableItem;
import com.kennuware.sales.domain.WearableList;
import org.junit.Test;
import org.mockito.Mockito;

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
        HttpUtils util = mock(HttpUtils.class);

        String jsonObject = "{\"wearables\":[" +
                                "{\"id\":1,\"name\":\"343\",\"type\":\"Act\",\"quantity\":45,\"active\":\"yes\"}," +
                                "{\"id\":2,\"name\":\"232\",\"type\":\"All\",\"quantity\":5,\"active\":\"no\"}," +
                                "{\"id\":3,\"name\":\"344\",\"type\":\"Simple\",\"quantity\":30,\"active\":\"yes\"}" +
                            "]}";

        when(util.get(Mockito.anyString())).thenReturn(jsonObject);

        WearableList items = service.getItems(util);

        int expectedLength = 3;
        assertEquals(expectedLength, items.getList().size());
        WearableItem item1 = items.getList().get(0);
        assertEquals(1, item1.getId());
        assertEquals("343", item1.getName());
        assertEquals("Act", item1.getType());
        assertEquals(45, item1.getQuantity());

        WearableItem item3 = items.getList().get(2);
        assertEquals(3, item3.getId());
        assertEquals("344", item3.getName());
        assertEquals("Simple", item3.getType());
        assertEquals(30, item3.getQuantity());
    }


}
