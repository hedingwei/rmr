/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ambimmort.rmr.configuration;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.ho.yaml.Yaml;

/**
 *
 * @author 定巍
 */
public class Configuration {

    private Server server;

    private Log log;
    
    private Cache cache;

    public Cache getCache() {
        return cache;
    }

    public void setCache(Cache cache) {
        this.cache = cache;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public Log getLog() {
        return log;
    }

    public void setLog(Log log) {
        this.log = log;
    }

    public static Configuration getConfig() {
        try {
            if (new File("etc/config.yml").exists()) {
                return Yaml.loadType(new File("etc/config.yml"), Configuration.class);
            } else if (new File("src/main/resources/config.yml").exists()) {
                return Yaml.loadType(new File("src/main/resources/config.yml"), Configuration.class);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Configuration();
    }
    
    public static void main(String[] args) throws FileNotFoundException{
         Object obj =  Yaml.load(new File("src/main/resources/config.yml"));
         
         System.out.println(obj);
    }
}
