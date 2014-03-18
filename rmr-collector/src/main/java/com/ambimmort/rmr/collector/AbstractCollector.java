/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ambimmort.rmr.collector;


import com.ambimmort.rmr.configuration.Configuration;
import java.util.HashMap;

/**
 *
 * @author 定巍
 */
public abstract class AbstractCollector {

    private HashMap paras = Configuration.getConfig().getServer().getCollector().getParas();

    public HashMap getParas() {
        return paras;
    }

    public AbstractCollector() {

    }

    public abstract void collect(Object key, Object value);
}
