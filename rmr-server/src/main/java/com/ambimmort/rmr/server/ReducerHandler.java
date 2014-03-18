/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ambimmort.rmr.server;

import com.ambimmort.rmr.collector.AbstractCollector;
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
public class ReducerHandler extends IoHandlerAdapter {

    private static AbstractReducer reducer = null;

    public ReducerHandler() {
        String v = Configuration.getConfig().getServer().getMrhandler();
        try {
            reducer = (AbstractReducer) Class.forName(v).newInstance();
            System.out.println("reducer:" + reducer.getClass().getName() + " initialized.");
            AbstractCollector collector = (AbstractCollector) Class.forName(Configuration.getConfig().getServer().getCollector().getClassName()).newInstance();
            reducer.setCollector(collector);
            System.out.println("collector:" + this.getClass().getName() + " initialized.");
        } catch (Exception ex) {
            Logger.getLogger(ReducerHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        cause.printStackTrace();
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        reducer.collectKV(message);
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        session.close(true);
    }

}
