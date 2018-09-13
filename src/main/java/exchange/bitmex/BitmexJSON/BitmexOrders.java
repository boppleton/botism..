package exchange.bitmex.BitmexJSON;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "table",
        "action",
        "data"
})
public class BitmexOrders {

/**/

        @JsonProperty("table")
        private String table;
        @JsonProperty("action")
        private String action;
        @JsonProperty("data")
        private List<BitmexOrder> data = null;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        @JsonProperty("table")
        public String getTable() {
            return table;
        }

        @JsonProperty("table")
        public void setTable(String table) {
            this.table = table;
        }

        @JsonProperty("action")
        public String getAction() {
            return action;
        }

        @JsonProperty("action")
        public void setAction(String action) {
            this.action = action;
        }

        @JsonProperty("data")
        public List<BitmexOrder> getData() {
            return data;
        }

        @JsonProperty("data")
        public void setData(List<BitmexOrder> data) {
            this.data = data;
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
