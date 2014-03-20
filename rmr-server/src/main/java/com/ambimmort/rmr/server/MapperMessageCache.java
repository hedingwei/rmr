/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ambimmort.rmr.server;

import com.ambimmort.rmr.collector.AbstractCollector;
import com.ambimmort.rmr.configuration.Collector;
import com.ambimmort.rmr.configuration.Configuration;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author 定巍
 */
public class MapperMessageCache extends TimerTask {

    private static MapperMessageCache instance = null;

    private List<Object> msgs = new ArrayList<Object>();

    private Runnable runnable = null;

    private int limit = 1000;

    private long overtime = 10000;

    private ExecutorService service = null;

    private long lastUpdateTime = System.currentTimeMillis();

    private Timer timer = new Timer("UdMessageCacheTimer");

    private AbstractMapper mapper = null;

    public void setMapper(AbstractMapper mapper) {
        this.mapper = mapper;
    }

    public AbstractMapper getMapper() {
        return mapper;
    }

    private MapperMessageCache() {
        Configuration config = Configuration.getConfig();
        this.overtime = config.getCache().getPeriod() * 1000;
        this.limit = config.getCache().getLimit();
        service = Executors.newFixedThreadPool(config.getCache().getThreads_max());
        timer.scheduleAtFixedRate(this, 0, config.getCache().getCheck_interval() * 1000);
    }

    public static MapperMessageCache getInstance() {
        if (instance == null) {
            instance = new MapperMessageCache();
        }
        return instance;
    }

    public void add(Object msg) {
        msgs.add(msg);
        lastUpdateTime = System.currentTimeMillis();
        if (msgs.size() >= limit) {
            final List<Object> allmsgs = msgs;
            service.execute(new Runnable() {
                public void run() {
                    AbstractCollector collector = mapper.getCollector();
                    for (Object m : allmsgs) {
                        Object[] kv = mapper.makeKV(m);
                        mapper.preMap(m, kv[0], kv[1], collector);
                        mapper.map(kv[0], kv[1], collector);
                        mapper.postMap(m, kv[0], kv[1], collector);
                    }
                }
            });
            msgs = null;
            msgs = new ArrayList<Object>();
        }
    }

    @Override
    public void run() {
        if ((System.currentTimeMillis() - lastUpdateTime) >= overtime) {
            if (msgs.isEmpty()) {
                return;
            }
            final List<Object> allmsgs = msgs;
            service.execute(new Runnable() {
                public void run() {
                    AbstractCollector collector = mapper.getCollector();
                    for (Object m : allmsgs) {
                        Object[] kv = mapper.makeKV(m);
                        mapper.preMap(m, kv[0], kv[1], collector);
                        mapper.map(kv[0], kv[1], collector);
                        mapper.postMap(m, kv[0], kv[1], collector);
                    }
                }
            });
            msgs = null;
            msgs = new ArrayList<Object>();
        }
    }

}
