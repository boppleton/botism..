package exchange.bitmex;

import exchange.WebsocketClient;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import utils.Broadcaster;
import utils.HMAC;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;


public class BitmexWebsocketClient extends WebsocketClient {



    public BitmexWebsocketClient(String accountName) throws URISyntaxException {
        super(new URI("wss://www.bitmex.com/realtime/"));

        setAccountname(accountName);
        setExchange("bitmex");
    }


    public void subscribe(boolean connect, String topic, String pair) {
        send("{\"op\": \""   +(connect ? "" : "un")+   "subscribe\", \"args\": [\"" +topic+ ":" +pair+ "\"]}");
    }

    public void authSubscribe(String apikey, String sig) {
        send("{\"op\": \"authKeyExpires\", \"args\": [\"" + apikey + "\"," + 1630475936 + ", \"" + sig + "\"]}");
    }


    @Override
    public void onMessage(String message) {

        if (!message.contains("keys")) {
            Broadcaster.broadcast(message);
        }
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("bitmex onOpen");
        super.onOpen(handshakedata);
    }

    public static WebsocketClient coonectBitmexWebsocket(String accountName, String key, String sec) throws URISyntaxException, InterruptedException {

        BitmexWebsocketClient bitmexClient;



        bitmexClient = new BitmexWebsocketClient(accountName);
        bitmexClient.connectBlocking();

        bitmexClient.authSubscribe(key, HMAC.hmacDigest("GET/realtime" + 1630475936, sec, "HmacSHA256"));

        bitmexClient.subscribe(true, "order", "XBTUSD");

        return bitmexClient;
    }

    public static void addPairBitmexWebsocket(String accountName, String pair) {
//        bitmexClient.subscribe(true, "trade", pair);
//        bitmexClient.subscribe(true, "order", pair);
//        bitmexClient.subscribe(true, "position", pair);
    }





}
