package qa.config;

import java.util.Objects;

public class AddressHolder {

    public String protocol;
    public String host;
    public int port;

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressHolder hostPort = (AddressHolder) o;
        return port == hostPort.port &&
                Objects.equals(protocol, hostPort.protocol) &&
                Objects.equals(host, hostPort.host);
    }

    @Override
    public int hashCode() {
        return Objects.hash(protocol, host, port);
    }

    @Override
    public String toString() {
        return "AddressHolder{" +
                "protocol='" + protocol + '\'' +
                ", host='" + host + '\'' +
                ", port=" + port +
                '}';
    }
}
