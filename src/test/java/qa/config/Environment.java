package qa.config;

import java.util.Objects;

public class Environment {
    public AddressHolder CDN;
    public AddressHolder KEK;

    public AddressHolder getCDN() {
        return CDN;
    }

    public void setCDN(AddressHolder CDN) {
        this.CDN = CDN;
    }

    public AddressHolder getKEK() {
        return KEK;
    }

    public void setKEK(AddressHolder KEK) {
        this.KEK = KEK;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Environment that = (Environment) o;
        return Objects.equals(CDN, that.CDN) &&
                Objects.equals(KEK, that.KEK);
    }

    @Override
    public int hashCode() {
        return Objects.hash(CDN, KEK);
    }

    @Override
    public String toString() {
        return "Environment{" +
                "CDN=" + CDN +
                ", KEK=" + KEK +
                '}';
    }
}
