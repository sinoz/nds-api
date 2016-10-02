package nds_api.fs.btx0;

import com.google.common.base.Preconditions;

/**
 *
 * The context of a basic texture file that consists of a collection of {@link BasicTextureFrame}s
 * and {@link BasicTexturePalette}s.
 *
 * @author Whis
 *
 */
public final class BasicTexture {
    /**
     * The {@link BasicTextureFrame}s and {@link BasicTexturePalette}s.
     */
    private final BasicTextureFrame[] frames;
    private final BasicTexturePalette[] palettes;

    /**
     * Creates a new {@link BasicTexture}.
     */
    public BasicTexture(BasicTextureFrame[] frames, BasicTexturePalette[] palettes) {
        this.frames = frames;
        this.palettes = palettes;
    }

    public BasicTextureFrame frame(int id) {
        Preconditions.checkElementIndex(id, frames.length);

        return frames[id];
    }

    public BasicTexturePalette palette(int id) {
        Preconditions.checkElementIndex(id, palettes.length);

        return palettes[id];
    }
}
