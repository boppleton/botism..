package exchange.bitmex;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.bitmex.BitmexExchange;
import utils.Lines;

public class BitmexSetup {

    public Exchange createExchange(String key, String sec) {

        // Use the factory to get Bitmex exchange API using default settings
        Exchange bitmex = ExchangeFactory.INSTANCE.createExchange(BitmexExchange.class);

        ExchangeSpecification bitmexSpec = bitmex.getDefaultExchangeSpecification();

         bitmexSpec.setApiKey(key);
         bitmexSpec.setSecretKey(sec);

        bitmex.applySpecification(bitmexSpec);

        return bitmex;
    }


}
