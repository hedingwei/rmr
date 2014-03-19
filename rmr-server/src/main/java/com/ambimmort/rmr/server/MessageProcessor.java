/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ambimmort.rmr.server;

import java.util.ArrayList;

/**
 *
 * @author 定巍
 */
public class MessageProcessor implements Runnable{
    private ArrayList<Object> msgs = null;
    


    public MessageProcessor(ArrayList<Object> msgs) {
        this.msgs = msgs;
    }
    
    public void run() {
        
    }
    
}
