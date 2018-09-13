package data;

import exchange.WebsocketClient;
import gui.GUI;

import java.util.ArrayList;

public class Websockets {

    private static Websockets websockets;


    private static ArrayList<WebsocketClient> websocketList = new ArrayList<>();


    public ArrayList<WebsocketClient> getWebsocketList() {
        return websocketList;
    }


    public void add(WebsocketClient websocket) {
        GUI.getInstance().log("adding websocket " + websocket.toString());
        websocketList.add(websocket);
    }

    public Websockets() {
        websockets = this;
    }

    public static Websockets getInstance() {
        return websockets;
    }

}
