package com.ambimmort.rmr.server;

import com.ambimmort.rmr.configuration.Configuration;
import com.ambimmort.rmr.plugins.fstserialization.FstSerializationCodecFactory;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoEventType;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.filter.statistic.ProfilerTimerFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

/**
 * Hello world!
 */
public class Server {

    private IoAcceptor acceptor = null;

    private Configuration config = null;

    public Server() {
        config = Configuration.getConfig();
        acceptor = new NioSocketAcceptor();

        final ProfilerTimerFilter profiler = new ProfilerTimerFilter(
                TimeUnit.MILLISECONDS, IoEventType.MESSAGE_RECEIVED);
        
        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                try{
                System.out.println("mr_ave:"+profiler.getAverageTime(IoEventType.MESSAGE_RECEIVED));
                System.out.println("mr_max:"+profiler.getMaximumTime(IoEventType.MESSAGE_RECEIVED));
                System.out.println("mr_min:"+profiler.getMinimumTime(IoEventType.MESSAGE_RECEIVED));
                }catch(Exception e){
                    
                }
            }
        }, 0, 1000);
        
        acceptor.getFilterChain().addFirst("Profiler", profiler);

        if (config.getLog().isLogcodec()) {
            acceptor.getFilterChain().addLast("logger", new LoggingFilter());
        }

        acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new FstSerializationCodecFactory()));
        String type = config.getServer().getType();
        if (type.equals("mapper")) {
            acceptor.setHandler(new MapperHandler());
        } else if (type.equals("reducer")) {
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
