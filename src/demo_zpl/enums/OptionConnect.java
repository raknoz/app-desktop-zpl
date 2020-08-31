package demo_zpl.enums;

public enum OptionConnect {
    WIRELESS_CONNECT("WIFI"), USB("USB");

    private final String type;

    private OptionConnect(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
