package nds_api;

import java.io.IOException;

/**
 *
 * Reads the entire header of the NDS ROM. If you only require identification, prefer to make use of
 * {@link RomIdentifier} to receive a {@link RomTitle} you can continue working with.
 *
 * @author Whis
 *
 */
public final class RomHeaderReader {
    /**
     * The {@link RomMapping} to read from.
     */
    private final RomMapping mapping;

    /**
     * Creates a new {@link RomHeaderReader}.
     */
    public RomHeaderReader(RomMapping mapping) {
        this.mapping = mapping;
    }

    public RomHeader read() throws IOException {
        String franchise = mapping.readString(0, RomIdentifier.TITLE_LENGTH);

        String gameCode = franchise.substring(12, 16);
        String makerCode = franchise.substring(16, 18);
        if (!makerCode.equals("01")) {
            throw new IOException("Not a Nintendo product");
        }

        RomTitle title = new RomTitle(gameCode);

        int unitCode = mapping.readUByte();
        int encryptionSeedSelect = mapping.readUByte();
        int deviceCapacity = mapping.readUByte();

        mapping.readAndDiscard(8); // padding

        int ndsRegion = mapping.readUByte();
        int romVersion = mapping.readUByte();
        int autoStart = mapping.readUByte();

        int arm9RomOffset = mapping.readUIntLE();
        int arm9EntryAddress = mapping.readUIntLE();
        int arm9RamAddress = mapping.readUIntLE();
        int arm9Size = mapping.readUIntLE();

        int arm7RomOffset = mapping.readUIntLE();
        int arm7EntryAddress = mapping.readUIntLE();
        int arm7RamAddress = mapping.readUIntLE();
        int arm7Size = mapping.readUIntLE();

        int fntAddress = mapping.readUIntLE();
        int fntSize = mapping.readUIntLE();

        int fatAddress = mapping.readUIntLE();
        int fatSize = mapping.readUIntLE() >> 3;

        // TODO finish header

        return new RomHeader(title, fntAddress, fntSize, fatAddress, fatSize);
    }
}
