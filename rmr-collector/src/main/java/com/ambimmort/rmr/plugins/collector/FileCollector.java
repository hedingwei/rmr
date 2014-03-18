/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ambimmort.rmr.plugins.collector;


import com.ambimmort.rmr.collector.AbstractCollector;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 定巍
 */
public class FileCollector extends AbstractCollector {

    private PrintWriter writer = null;

    public FileCollector() {
     
        try {
            writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream((String) getParas().get("file")), 1024), true);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileCollector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void collect(Object key, Object value) {
        writer.println("key=" + key + ", value=" + value);
    }

}
