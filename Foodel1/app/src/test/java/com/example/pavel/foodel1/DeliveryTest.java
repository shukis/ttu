package com.example.pavel.foodel1;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Pavel on 16.12.2016.
 */

public class DeliveryTest {
    @Test
    public void testChedi(){
        Delivery d = new Delivery();
        String[] address = new String[3];
        String[] result = d.chedi(address);
        String[] chedi = {"Chedi","Tallinn","Sulevimägi 1"};
        assertEquals(chedi,result);
    }
    @Test
    public void testGetRestaurantAddressChedi(){
        Delivery d = new Delivery();
        d.restoranName = "chedi";
        String[] chedi = {"Chedi","Tallinn","Sulevimägi 1"};
        String[] result = d.getRestaurantAddress();
        assertEquals(chedi,result);
    }
    @Test
    public void testMoon(){
        Delivery d = new Delivery();
        String[] address = new String[3];
        String[] result = d.moon(address);
        String[] moon = {"Moon","Tallinn","Võrgu 3"};
        assertEquals(moon,result);
    }
    @Test
    public void testGetRestaurantAddressMoon(){
        Delivery d = new Delivery();
        d.restoranName = "moon";
        String[] moon = {"Moon","Tallinn","Võrgu 3"};
        String[] result = d.getRestaurantAddress();
        assertEquals(moon,result);
    }
    @Test
    public void testKaksKokka(){
        Delivery d = new Delivery();
        String[] address = new String[3];
        String[] result = d.kaksKokka(address);
        String[] kaksKokka = {"Kaks Kokka","Tallinn","Mere puiestee 6E"};
        assertEquals(kaksKokka,result);
    }
    @Test
    public void testGetRestaurantAddressKaksKokka(){
        Delivery d = new Delivery();
        d.restoranName = "Kaks Kokka";
        String[] kaksKokka = {"Kaks Kokka","Tallinn","Mere puiestee 6E"};
        String[] result = d.getRestaurantAddress();
        assertEquals(kaksKokka,result);
    }
    @Test
    public void testTruhvel(){
        Delivery d = new Delivery();
        String[] address = new String[3];
        String[] result = d.truhvel(address);
        String[] truhvel = {"Trühvel","Tallinn","Telliskivi 60"};
        assertEquals(truhvel,result);
    }
    @Test
    public void testGetRestaurantAddressTruhvel(){
        Delivery d = new Delivery();
        d.restoranName = "Trühvel";
        String[] truhvel = {"Trühvel","Tallinn","Telliskivi 60"};
        String[] result = d.getRestaurantAddress();
        assertEquals(truhvel,result);
    }
    @Test
    public void testKolmSibulat(){
        Delivery d = new Delivery();
        String[] address = new String[3];
        String[] result = d.kolmSibulat(address);
        String[] kolmSibulat = {"Kolm Sibulat","Tallinn","Telliskivi 2"};
        assertEquals(kolmSibulat,result);
    }
    @Test
    public void testGetRestaurantAddressKolmSibulat(){
        Delivery d = new Delivery();
        d.restoranName = "kolm sibulat";
        String[] kolmSibulat = {"Kolm Sibulat","Tallinn","Telliskivi 2"};
        String[] result = d.getRestaurantAddress();
        assertEquals(kolmSibulat,result);
    }

}
