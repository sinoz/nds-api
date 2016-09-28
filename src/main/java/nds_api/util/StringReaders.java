package nds_api.util;

import io.netty.buffer.ByteBuf;

/**
 *
 * Contains a collection of static utility methods related to reading {@link String} types
 * from {@link ByteBuf}s.
 *
 * @author Whis
 *
 */
public final class StringReaders {
    /**
     * Reads a series of bytes from the given {@link ByteBuf} of the given length and translates
     * these bytes to a {@link String} type.
     */
    public static String readString(ByteBuf buf, int length) {
        StringBuilder bldr = new StringBuilder();

        for (int i = 0; i < length; i++) {
            if (!buf.isReadable()) {
                break;
            }

            bldr.append((char) buf.readUnsignedByte());
        }

        return bldr.toString();
    }
}
