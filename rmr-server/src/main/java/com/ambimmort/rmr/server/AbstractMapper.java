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
public abstract class AbstractMapper {

    private AbstractCollector collector = null;

    public abstract void map(Object key, Object value, AbstractCollector collector);

    public abstract Object[] makeKV(Object msg);

    public abstract void preMap(Object msg, Object key, Object value, AbstractCollector collector);

    public abstract void postMap(Object msg, Object key, Object value, AbstractCollector collector);

    public AbstractCollector getCollector() {
        return collector;
    }

    public void setCollector(AbstractCollector collector) {
        this.collector = collector;
    }

    public void doMap(Object msg) {
        Object[] kv = makeKV(msg);
        preMap(msg, kv[0], kv[1], collector);
        map(kv[0], kv[1], collector);
        postMap(msg, kv[0], kv[1], collector);
    }
}
