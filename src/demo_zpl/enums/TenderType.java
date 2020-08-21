package demo_zpl.enums;

public enum TenderType {
    CREDIT_CARD("CC"), DEBIT_CARD("DC"), CASH("CS");

    private String type;

    private TenderType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
