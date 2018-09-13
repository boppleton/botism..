package exchange.bitmex.BitmexJSON;

public class BitmexMessage {

    String message;

    String getField(String item, boolean string) {

        if (message.contains("\"" + item + "\"")) {

            String field;

            int start  = message.indexOf(item) + item.length() + (string?3:2);
            int end = message.indexOf(string ? "\"" : ",", start);

            field = message.substring(start, end);

//            System.out.println(item + ": " + field);

            return field;
        } else {
            return null;
        }

    }

}
