package me.zhengjie;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * Hello world!
 */
@Slf4j
public class App {


    public static void main(String[] args) {


        try {
            Server server = new Server();
            server.bind(54555, 54777);

            server.addListener(new Listener() {
                @Override
                public void received(Connection connection, Object object) {
                    if (object instanceof SomeRequest) {
                        SomeRequest request = (SomeRequest) object;
                        System.out.println(request.text);

                        SomeResponse response = new SomeResponse();
                        response.text = "Thanks";
                        connection.sendTCP(response);
                    }
                }
            });

            //
            server.start();
            log.info("Server started");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class SomeRequest {
    public String text;
}

class SomeResponse {
    public String text;
}
