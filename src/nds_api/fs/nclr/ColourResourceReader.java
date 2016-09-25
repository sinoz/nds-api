package nds_api.fs.nclr;

import nds_api.RomMapping;

/**
 *
 * Reads a NCLR file and transforms the data into a {@link ColourResource}.
 *
 * @author Whis
 *
 */
public final class ColourResourceReader {
    /**
     * The {@link RomMapping} to read from.
     */
    private final RomMapping mapping;

    /**
     * Creates a new {@link ColourResourceReader}.
     */
    public ColourResourceReader(RomMapping mapping) {
        this.mapping = mapping;
    }

    public ColourResource read() {
        return new ColourResource();
    }
}
