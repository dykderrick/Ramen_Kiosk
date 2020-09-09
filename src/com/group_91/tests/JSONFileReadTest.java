package com.group_91.tests;

import com.alibaba.fastjson.JSONArray;
import com.group_91.utils.Jsons;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * This class defines a TDD test for reading JSON files of the project.
 * Passes only if readable and not null.
 *
 * @author Yingke Ding
 */
public class JSONFileReadTest {

    /**
     * test <code>Jsons.getJSONArray()</code> method,
     * and ensure they are not null.
     * @see Test
     * @see Jsons#getJSONArray(String)
     */
    @Test
    void testReadData() {
        JSONArray add_ons = Jsons.getJSONArray("add_ons");
        JSONArray customers = Jsons.getJSONArray("customers");
        JSONArray manager = Jsons.getJSONArray("manager");
        JSONArray orders = Jsons.getJSONArray("orders");
        JSONArray restaurants = Jsons.getJSONArray("restaurants");
        JSONArray suits = Jsons.getJSONArray("suits");

        assertNotNull(add_ons);
        assertNotNull(customers);
        assertNotNull(manager);
        assertNotNull(orders);
        assertNotNull(restaurants);
        assertNotNull(suits);

    }

}
