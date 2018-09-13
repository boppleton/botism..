package data;

import java.util.ArrayList;

public class Orders {

    private static ArrayList<SingleOrder> ordersList = new ArrayList<>();

    private static Orders orders;



    public Orders() {
        orders = this;
    }

    public static Orders getInstance() {
        return orders;
    }

    public ArrayList<SingleOrder> getOrdersList() {
        return ordersList;
    }

    public void add(SingleOrder order) {
        ordersList.add(order);
    }
}
