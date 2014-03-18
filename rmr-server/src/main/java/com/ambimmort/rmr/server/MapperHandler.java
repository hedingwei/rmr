/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ambimmort.rmr.server;

import com.ambimmort.rmr.collector.AbstractCollector;
import com.ambimmort.rmr.server.AbstractMapper;
import com.ambimmort.rmr.configuration.Configuration;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

/**
 *
 * @author 定巍
 */
public class MapperHandler extends IoHandlerAdapter {

    private static AbstractMapper mapper = null;

    public MapperHandler() {
        String v = Configuration.getConfig().getServer().getMrhandler();
        try {
            mapper = (AbstractMapper) Class.forName(v).newInstance();
            System.out.println("mapper:"+mapper.getClass().getName()+" initialized.");
            AbstractCollector collector = (AbstractCollector) Class.forName(Configuration.getConfig().getServer().getCollector().getClassName()).newInstance();
            mapper.setCollector(collector);
            System.out.println("collector:"+this.getClass().getName()+" initialized.");
        } catch (Exception ex) {
            Logger.getLogger(MapperHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        cause.printStackTrace();
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        mapper.doMap(message);
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        session.close(true);
    }

}
