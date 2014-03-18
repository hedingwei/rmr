/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ambimmort.rmr.server;

import com.ambimmort.rmr.collector.AbstractCollector;

/**
 *
 * @author 定巍
 */
public class SimpleMapper extends AbstractMapper{

    @Override
    public void map(Object key, Object value, AbstractCollector collector) {
        System.out.println("key="+key+",value="+value);
    }

    @Override
    public Object[] makeKV(Object msg) {
        Object[] data = new Object[2];
        data[0] = 1;
        data[1] = msg;
        return data;
    }

    @Override
    public void preMap(Object msg, Object key, Object value, AbstractCollector collector) {
        
    }

    @Override
    public void postMap(Object msg, Object key, Object value, AbstractCollector collector) {
        
    }
    
}
