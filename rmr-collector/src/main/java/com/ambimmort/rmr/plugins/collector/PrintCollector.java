/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ambimmort.rmr.plugins.collector;

import com.ambimmort.rmr.collector.AbstractCollector;

/**
 *
 * @author 定巍
 */
public class PrintCollector extends AbstractCollector {

    public PrintCollector() {
    }

    
    @Override
    public void collect(Object key, Object value) {
        System.out.println("key=" + key + ", value=" + value);
    }

}
