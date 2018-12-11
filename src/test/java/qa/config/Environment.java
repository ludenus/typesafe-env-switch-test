package qa.config;

import lombok.Data;

@Data
public class Environment {
    public AddressHolder CDN;
    public AddressHolder KEK;
    public AddressHolder AMQP;
    public AddressHolder PVC;
    public AddressHolder SPM;

}
