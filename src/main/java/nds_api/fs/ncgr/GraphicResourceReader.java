package nds_api.fs.ncgr;

import nds_api.RomMapping;

/**
 *
 * Reads a NCGR file and transforms the data into a {@link GraphicResource}.
 *
 * @author Whis
 *
 */
public final class GraphicResourceReader {
    /**
     * The {@link RomMapping} to read from.
     */
    private final RomMapping mapping;

    /**
     * Creates a new {@link GraphicResourceReader}.
     */
    public GraphicResourceReader(RomMapping mapping) {
        this.mapping = mapping;
    }

    public GraphicResource read() {
        return new GraphicResource();
    }
}