package nds_api.fs.btx0;

import nds_api.RomMapping;

/**
 *
 * Reads data to translate to a {@link BasicTexture}.
 *
 * @author Whis
 *
 */
public final class BasicTextureReader {
    /**
     * The {@link RomMapping} to read from.
     */
    private final RomMapping mapping;

    /**
     * Creates a new {@link BasicTextureReader}.
     */
    public BasicTextureReader(RomMapping mapping) {
        this.mapping = mapping;
    }

    public BasicTexture read() {
        // TODO

        return new BasicTexture(null, null);
    }
}
