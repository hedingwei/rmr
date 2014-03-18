/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ambimmort.rmr.server;

import com.ambimmort.rmr.messages.commons.KeyValueMessage;
import com.ambimmort.rmr.collector.AbstractCollector;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author 定巍
 */
public abstract class AbstractReducer {

    private AbstractCollector collector = null;

    private HashMap<Object, List> store = new HashMap<Object, List>();

    public abstract void reduce(Object key, List<Object> values, AbstractCollector collector);

    public void collectKV(Object msg) {
        KeyValueMessage kvm = (KeyValueMessage) msg;

        if (getStore().containsKey(kvm.getKey())) {
            getStore().get(kvm.getKey()).add(kvm.getValue());
        } else {
            List list = new ArrayList();
            list.add(kvm.getValue());
            getStore().put(kvm.getKey(), list);
        }
    }

    public void preReduce(Object key, List<Object> values, AbstractCollector collector) {
    }

    public void postReduce(Object key, List<Object> values, AbstractCollector collector) {
    }

    public HashMap<Object, List> getStore() {
        return store;
    }

    public AbstractCollector getCollector() {
        return collector;
    }

    public void setCollector(AbstractCollector collector) {
        this.collector = collector;
    }

    public void doReduce() {
        final HashMap<Object, List> map = store;
        store = new HashMap<Object, List>();
        new Thread(new Runnable() {

            public void run() {
                for (Object o : map.keySet()) {
                    preReduce(o, map.get(o), collector);
                    reduce(o, map.get(o), collector);
                    postReduce(o, map.get(o), collector);
                }
            }
        }).start();

    }

}
