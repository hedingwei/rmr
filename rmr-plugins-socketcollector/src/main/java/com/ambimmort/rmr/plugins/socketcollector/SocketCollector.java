/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ambimmort.rmr.plugins.socketcollector;

import java.io.Serializable;
import java.util.List;
import com.ambimmort.rmr.client.Client;
import com.ambimmort.rmr.client.ConnectionPoint;
import com.ambimmort.rmr.collector.AbstractCollector;
import com.ambimmort.rmr.messages.commons.KeyValueMessage;


/**
 *
 * @author 定巍
 */
public class SocketCollector extends AbstractCollector {

    Client client = null;

    public SocketCollector() {
        client = new Client();
        List cps = (List) getParas().get("cps");
        for (int i = 0; i < cps.size(); i++) {
            String cp = (String) cps.get(i);
            String host = cp.split(":")[0];
            int port = Integer.parseInt(cp.split(":")[1]);
            new ConnectionPoint(host, port, client);
            
        }
        client.connect();
    }

    @Override
    public void collect(Object key, Object value) {
        System.out.println(client.send(key, new KeyValueMessage((Serializable) key, (Serializable) value)));
    }

}
