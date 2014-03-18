/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ambimmort.rmr.server;

import com.ambimmort.rmr.collector.AbstractCollector;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import com.ambimmort.rmr.messages.commons.KeyValueMessage;

/**
 *
 * @author 定巍
 */
public class SimpleReducer extends AbstractReducer {

    private Timer timer = new Timer();

    public SimpleReducer() {
        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                doReduce();
            }
        }, 0, 10000);
    }

    @Override
    public void reduce(Object key, List<Object> values, AbstractCollector collector) {
        System.out.println("reduce key:" + key + ", values:" + values);
    }

    @Override
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

    @Override
    public void preReduce(Object key, List<Object> values, AbstractCollector collector) {

    }

    @Override
    public void postReduce(Object key, List<Object> values, AbstractCollector collector) {

    }

}
