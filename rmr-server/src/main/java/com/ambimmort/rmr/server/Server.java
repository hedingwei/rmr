package com.ambimmort.rmr.server;

import com.ambimmort.rmr.configuration.Configuration;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

/**
 * Hello world!
 *
 */
public class Server {

    private IoAcceptor acceptor = null;

    private Configuration config = null;

    public Server() {
        config = Configuration.getConfig();
        acceptor = new NioSocketAcceptor();
        if (config.getLog().isLogcodec()) {
            acceptor.getFilterChain().addLast("logger", new LoggingFilter());
        }
        acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
        String type = config.getServer().getType();
        if (type.equals("mapper")) {
            acceptor.setHandler(new MapperHandler());
        }else if(type.equals("reducer")){
            acceptor.setHandler(new ReducerHandler());
        }
        acceptor.getSessionConfig().setIdleTime(IdleStatus.READER_IDLE, config.getServer().getTimeout());
    }

    public void bind() {
        try {
            acceptor.bind(new InetSocketAddress(config.getServer().getPort()));
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("%%%%%%%%%%");
        }
    }

    public void unbind() {
        acceptor.unbind();
    }

    public static void main(String[] args) throws IOException {

        Server server = new Server();
        server.bind();

    }
}
