/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ${groupId}.${artifactId};

import com.ambimmort.rmr.server.Server;

/**
 *
 * @author ¶¨Î¡
 */
public class App {
    public static void main(String[] args){
        Server server = new Server();
        server.bind();
    }
}
