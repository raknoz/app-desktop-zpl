package demo_zpl.enums;

/**
 * Valid transaction types that may be part of a Retail Transaction Commit.
 */
public enum TransactionType {
    SALE,
    LAYAWAY;

    public static boolean isLayaway(final String transactionType) {
        return LAYAWAY.name().equalsIgnoreCase(transactionType);
    }

    public static boolean isSale(final String transactionType) {
        return SALE.name().equalsIgnoreCase(transactionType);
    }

    public static TransactionType from(final String transactionType) {
        return TransactionType.valueOf(transactionType);
    }
}
