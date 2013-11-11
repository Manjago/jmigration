package jmigration.common;

/**
 * @author Manjago (kirill@temnenkov.com)
 */
public final class LameLib {
    private LameLib() {
    }

    public static <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new IllegalArgumentException();
        }
        return reference;
    }

    public static <T> T checkNotNull(T reference, Object errorMessage) {
        if (reference == null) {
            throw new IllegalArgumentException(String.valueOf(errorMessage));
        }
        return reference;
    }
}
