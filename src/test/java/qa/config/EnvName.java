package qa.config;

public enum EnvName {

    DEV,
    TEST;

    @Override
    public String toString() {
        return "EnvName{" + this.name() + "}";
    }
}
