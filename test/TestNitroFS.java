import nds_api.RomFinder;
import nds_api.RomHeader;
import nds_api.RomHeaderReader;
import nds_api.RomMapping;
import nds_api.fs.FileSystem;
import nds_api.fs.FileSystemReader;
import org.junit.Test;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

public final class TestNitroFS {
    @Test
    public void testNdsApi() throws IOException {
        Path romsDirectory = Paths.get("roms/");

        RomFinder finder = new RomFinder(romsDirectory, "nds");
        Set<Path> romPaths = finder.find();

        for (Path romPath : romPaths) {
            try (FileChannel channel = FileChannel.open(romPath)) {
                RomMapping mapping = new RomMapping(channel);

                RomHeaderReader headerReader = new RomHeaderReader(mapping);
                RomHeader header = headerReader.read();

                FileSystemReader fsReader = new FileSystemReader(mapping, header);
                FileSystem fs = fsReader.read();
            }
        }
    }
}
