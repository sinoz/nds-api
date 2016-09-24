package nds_api;

import java.io.IOException;

/**
 *
 * Reads, identifies and translates the title chunk of the header to a {@link RomTitle}.
 *
 * @author Whis
 *
 */
public final class RomIdentifier {
    /**
     * The length of the rom game title in bytes.
     */
    private static final int TITLE_LENGTH = 18;

    /**
     * The {@link RomMapping} to read from.
     */
    private final RomMapping mapping;

    /**
     * The start address of the rom game title.
     */
    private final int titleAddress;

    /**
     * Creates a new {@link RomIdentifier}.
     */
    public RomIdentifier(RomMapping mapping, int titleAddress) {
        this.mapping = mapping;
        this.titleAddress = titleAddress;
    }

    public RomTitle identify() throws IOException {
        String franchise = mapping.readString(titleAddress, TITLE_LENGTH);

        String gameCode = franchise.substring(12, 16);
        String makerCode = franchise.substring(16, 18);
        if (!makerCode.equals("01")) {
            throw new IOException("Not a Nintendo product");
        }

        return new RomTitle(gameCode);
    }
}
