package exchange.bitmex.BitmexJSON;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "orderID",
        "clOrdID",
        "clOrdLinkID",
        "account",
        "symbol",
        "side",
        "simpleOrderQty",
        "orderQty",
        "price",
        "displayQty",
        "stopPx",
        "pegOffsetValue",
        "pegPriceType",
        "currency",
        "settlCurrency",
        "ordType",
        "timeInForce",
        "execInst",
        "contingencyType",
        "exDestination",
        "ordStatus",
        "triggered",
        "workingIndicator",
        "ordRejReason",
        "simpleLeavesQty",
        "leavesQty",
        "simpleCumQty",
        "cumQty",
        "avgPx",
        "multiLegReportingType",
        "text",
        "transactTime",
        "timestamp"
})
public class BitmexOrder {


        @JsonProperty("orderID")
        private String orderID;
        @JsonProperty("clOrdID")
        private String clOrdID;
        @JsonProperty("clOrdLinkID")
        private String clOrdLinkID;
        @JsonProperty("account")
        private Integer account;
        @JsonProperty("symbol")
        private String symbol;
        @JsonProperty("side")
        private String side;
        @JsonProperty("simpleOrderQty")
        private Object simpleOrderQty;
        @JsonProperty("orderQty")
        private Integer orderQty;
        @JsonProperty("price")
        private Integer price;
        @JsonProperty("displayQty")
        private Object displayQty;
        @JsonProperty("stopPx")
        private Object stopPx;
        @JsonProperty("pegOffsetValue")
        private Object pegOffsetValue;
        @JsonProperty("pegPriceType")
        private String pegPriceType;
        @JsonProperty("currency")
        private String currency;
        @JsonProperty("settlCurrency")
        private String settlCurrency;
        @JsonProperty("ordType")
        private String ordType;
        @JsonProperty("timeInForce")
        private String timeInForce;
        @JsonProperty("execInst")
        private String execInst;
        @JsonProperty("contingencyType")
        private String contingencyType;
        @JsonProperty("exDestination")
        private String exDestination;
        @JsonProperty("ordStatus")
        private String ordStatus;
        @JsonProperty("triggered")
        private String triggered;
        @JsonProperty("workingIndicator")
        private Boolean workingIndicator;
        @JsonProperty("ordRejReason")
        private String ordRejReason;
        @JsonProperty("simpleLeavesQty")
        private Integer simpleLeavesQty;
        @JsonProperty("leavesQty")
        private Integer leavesQty;
        @JsonProperty("simpleCumQty")
        private Integer simpleCumQty;
        @JsonProperty("cumQty")
        private Integer cumQty;
        @JsonProperty("avgPx")
        private Object avgPx;
        @JsonProperty("multiLegReportingType")
        private String multiLegReportingType;
        @JsonProperty("text")
        private String text;
        @JsonProperty("transactTime")
        private String transactTime;
        @JsonProperty("timestamp")
        private String timestamp;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        @JsonProperty("orderID")
        public String getOrderID() {
            return orderID;
        }

        @JsonProperty("orderID")
        public void setOrderID(String orderID) {
            this.orderID = orderID;
        }

        @JsonProperty("clOrdID")
        public String getClOrdID() {
            return clOrdID;
        }

        @JsonProperty("clOrdID")
        public void setClOrdID(String clOrdID) {
            this.clOrdID = clOrdID;
        }

        @JsonProperty("clOrdLinkID")
        public String getClOrdLinkID() {
            return clOrdLinkID;
        }

        @JsonProperty("clOrdLinkID")
        public void setClOrdLinkID(String clOrdLinkID) {
            this.clOrdLinkID = clOrdLinkID;
        }

        @JsonProperty("account")
        public Integer getAccount() {
            return account;
        }

        @JsonProperty("account")
        public void setAccount(Integer account) {
            this.account = account;
        }

        @JsonProperty("symbol")
        public String getSymbol() {
            return symbol;
        }

        @JsonProperty("symbol")
        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        @JsonProperty("side")
        public String getSide() {
            return side;
        }

        @JsonProperty("side")
        public void setSide(String side) {
            this.side = side;
        }

        @JsonProperty("simpleOrderQty")
        public Object getSimpleOrderQty() {
            return simpleOrderQty;
        }

        @JsonProperty("simpleOrderQty")
        public void setSimpleOrderQty(Object simpleOrderQty) {
            this.simpleOrderQty = simpleOrderQty;
        }

        @JsonProperty("orderQty")
        public Integer getOrderQty() {
            return orderQty;
        }

        @JsonProperty("orderQty")
        public void setOrderQty(Integer orderQty) {
            this.orderQty = orderQty;
        }

        @JsonProperty("price")
        public Integer getPrice() {
            return price;
        }

        @JsonProperty("price")
        public void setPrice(Integer price) {
            this.price = price;
        }

        @JsonProperty("displayQty")
        public Object getDisplayQty() {
            return displayQty;
        }

        @JsonProperty("displayQty")
        public void setDisplayQty(Object displayQty) {
            this.displayQty = displayQty;
        }

        @JsonProperty("stopPx")
        public Object getStopPx() {
            return stopPx;
        }

        @JsonProperty("stopPx")
        public void setStopPx(Object stopPx) {
            this.stopPx = stopPx;
        }

        @JsonProperty("pegOffsetValue")
        public Object getPegOffsetValue() {
            return pegOffsetValue;
        }

        @JsonProperty("pegOffsetValue")
        public void setPegOffsetValue(Object pegOffsetValue) {
            this.pegOffsetValue = pegOffsetValue;
        }

        @JsonProperty("pegPriceType")
        public String getPegPriceType() {
            return pegPriceType;
        }

        @JsonProperty("pegPriceType")
        public void setPegPriceType(String pegPriceType) {
            this.pegPriceType = pegPriceType;
        }

        @JsonProperty("currency")
        public String getCurrency() {
            return currency;
        }

        @JsonProperty("currency")
        public void setCurrency(String currency) {
            this.currency = currency;
        }

        @JsonProperty("settlCurrency")
        public String getSettlCurrency() {
            return settlCurrency;
        }

        @JsonProperty("settlCurrency")
        public void setSettlCurrency(String settlCurrency) {
            this.settlCurrency = settlCurrency;
        }

        @JsonProperty("ordType")
        public String getOrdType() {
            return ordType;
        }

        @JsonProperty("ordType")
        public void setOrdType(String ordType) {
            this.ordType = ordType;
        }

        @JsonProperty("timeInForce")
        public String getTimeInForce() {
            return timeInForce;
        }

        @JsonProperty("timeInForce")
        public void setTimeInForce(String timeInForce) {
            this.timeInForce = timeInForce;
        }

        @JsonProperty("execInst")
        public String getExecInst() {
            return execInst;
        }

        @JsonProperty("execInst")
        public void setExecInst(String execInst) {
            this.execInst = execInst;
        }

        @JsonProperty("contingencyType")
        public String getContingencyType() {
            return contingencyType;
        }

        @JsonProperty("contingencyType")
        public void setContingencyType(String contingencyType) {
            this.contingencyType = contingencyType;
        }

        @JsonProperty("exDestination")
        public String getExDestination() {
            return exDestination;
        }

        @JsonProperty("exDestination")
        public void setExDestination(String exDestination) {
            this.exDestination = exDestination;
        }

        @JsonProperty("ordStatus")
        public String getOrdStatus() {
            return ordStatus;
        }

        @JsonProperty("ordStatus")
        public void setOrdStatus(String ordStatus) {
            this.ordStatus = ordStatus;
        }

        @JsonProperty("triggered")
        public String getTriggered() {
            return triggered;
        }

        @JsonProperty("triggered")
        public void setTriggered(String triggered) {
            this.triggered = triggered;
        }

        @JsonProperty("workingIndicator")
        public Boolean getWorkingIndicator() {
            return workingIndicator;
        }

        @JsonProperty("workingIndicator")
        public void setWorkingIndicator(Boolean workingIndicator) {
            this.workingIndicator = workingIndicator;
        }

        @JsonProperty("ordRejReason")
        public String getOrdRejReason() {
            return ordRejReason;
        }

        @JsonProperty("ordRejReason")
        public void setOrdRejReason(String ordRejReason) {
            this.ordRejReason = ordRejReason;
        }

        @JsonProperty("simpleLeavesQty")
        public Integer getSimpleLeavesQty() {
            return simpleLeavesQty;
        }

        @JsonProperty("simpleLeavesQty")
        public void setSimpleLeavesQty(Integer simpleLeavesQty) {
            this.simpleLeavesQty = simpleLeavesQty;
        }

        @JsonProperty("leavesQty")
        public Integer getLeavesQty() {
            return leavesQty;
        }

        @JsonProperty("leavesQty")
        public void setLeavesQty(Integer leavesQty) {
            this.leavesQty = leavesQty;
        }

        @JsonProperty("simpleCumQty")
        public Integer getSimpleCumQty() {
            return simpleCumQty;
        }

        @JsonProperty("simpleCumQty")
        public void setSimpleCumQty(Integer simpleCumQty) {
            this.simpleCumQty = simpleCumQty;
        }

        @JsonProperty("cumQty")
        public Integer getCumQty() {
            return cumQty;
        }

        @JsonProperty("cumQty")
        public void setCumQty(Integer cumQty) {
            this.cumQty = cumQty;
        }

        @JsonProperty("avgPx")
        public Object getAvgPx() {
            return avgPx;
        }

        @JsonProperty("avgPx")
        public void setAvgPx(Object avgPx) {
            this.avgPx = avgPx;
        }

        @JsonProperty("multiLegReportingType")
        public String getMultiLegReportingType() {
            return multiLegReportingType;
        }

        @JsonProperty("multiLegReportingType")
        public void setMultiLegReportingType(String multiLegReportingType) {
            this.multiLegReportingType = multiLegReportingType;
        }

        @JsonProperty("text")
        public String getText() {
            return text;
        }

        @JsonProperty("text")
        public void setText(String text) {
            this.text = text;
        }

        @JsonProperty("transactTime")
        public String getTransactTime() {
            return transactTime;
        }

        @JsonProperty("transactTime")
        public void setTransactTime(String transactTime) {
            this.transactTime = transactTime;
        }

        @JsonProperty("timestamp")
        public String getTimestamp() {
            return timestamp;
        }

        @JsonProperty("timestamp")
        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        @JsonAnyGetter
        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        @JsonAnySetter
        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

}
