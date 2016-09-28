package nds_api;

/**
 *
 * The translated header of the NDS ROM.
 *
 * @author Whis
 *
 */
public final class RomHeader {
    /**
     * The {@link RomTitle}.
     */
    private final RomTitle title;

    /**
     * The addresses and sizes of the file allocation tables and file name tables.
     */
    private final int fntAddress, fntSize;
    private final int fatAddress, fatSize;

    /**
     * Creates a new {@link RomHeader}.
     */
    public RomHeader(RomTitle title, int fntAddress, int fntSize, int fatAddress, int fatSize) {
        this.title = title;
        this.fntAddress = fntAddress;
        this.fntSize = fntSize;
        this.fatAddress = fatAddress;
        this.fatSize = fatSize;
    }

    public RomTitle getTitle() {
        return title;
    }

    public int getFntAddress() {
        return fntAddress;
    }

    public int getFntSize() {
        return fntSize;
    }

    public int getFatAddress() {
        return fatAddress;
    }

    public int getFatSize() {
        return fatSize;
    }
}
