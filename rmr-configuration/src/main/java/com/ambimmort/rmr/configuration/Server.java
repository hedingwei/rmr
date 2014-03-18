/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ambimmort.rmr.configuration;

/**
 *
 * @author 定巍
 */
public class Server {

    private int port;
    private String uuid_path;
    private int timeout;
    private String type;
    private String mrhandler;
    private Collector collector;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMrhandler() {
        return mrhandler;
    }

    public void setMrhandler(String mrhandler) {
        this.mrhandler = mrhandler;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUuid_path() {
        return uuid_path;
    }

    public void setUuid_path(String uuid_path) {
        this.uuid_path = uuid_path;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public Collector getCollector() {
        return collector;
    }

    public void setCollector(Collector collector) {
        this.collector = collector;
    }

}
