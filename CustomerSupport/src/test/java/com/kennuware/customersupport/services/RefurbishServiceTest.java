package com.kennuware.customersupport.services;

import com.kennuware.customersupport.Utilities.HttpUtils;
import com.kennuware.customersupport.domain.inventory.InventoryItem;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Ryan on 10/30/2016.
 */
public class RefurbishServiceTest {

    /**
     * This test basically just tests that RefurbishService.reportItemRefurbished(itemId, httpUtil)
     * creates the appropriate object for the /refurbish API endpoint.
     *
     * This is done by creating an ArgumentCaptor using Mockito and verifying the fields on
     * the captured object.
     */
    @Test
    public void testReportItemRefurbished() {
        RefurbishService service = new RefurbishService();
        int itemId = 1;
        HttpUtils util = mock(HttpUtils.class);

        ArgumentCaptor<InventoryItem> argument = ArgumentCaptor.forClass(InventoryItem.class);

        when(util.post(Mockito.anyObject(), Mockito.anyString())).thenReturn("");

        service.reportItemRefurbished(itemId, util);
        verify(util).post(argument.capture(), Mockito.anyString());

        assertEquals("refurbished", argument.getValue().getType());
        assertEquals(itemId, argument.getValue().getWearableID());
    }

}
