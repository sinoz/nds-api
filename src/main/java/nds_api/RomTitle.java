package nds_api;

/**
 *
 * The unique title of a loaded in ROM.
 *
 * @author Whis
 *
 */
public final class RomTitle {
    /**
     * The game code.
     */
    private final String gameCode;

    /**
     * Creates a new {@link RomTitle}.
     */
    public RomTitle(String gameCode) {
        this.gameCode = gameCode;
    }

    @Override
    public String toString() {
        return gameCode;
    }
}
