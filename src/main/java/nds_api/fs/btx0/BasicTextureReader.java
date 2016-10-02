package nds_api.fs.btx0;

import nds_api.RomMapping;

import java.io.IOException;

/**
 *
 * Reads data to translate to a {@link BasicTexture}.
 *
 * @author Whis
 *
 */
public final class BasicTextureReader {
    /**
     * The expected BTX0 file extension.
     */
    private static final String BTX0_EXT = "BTX0";

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

    public BasicTexture read() throws IOException {
        String stamp = mapping.readString(BTX0_EXT.length());
        if (!stamp.equals(BTX0_EXT)) {
            throw new IOException();
        }

        return new BasicTexture(null, null);
    }
}
