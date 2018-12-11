package qa.config;

import lombok.Data;

@Data
public class AddressHolder {

    public String protocol;
    public String host;
    public int port;
    public String authHeaderValue;
}
