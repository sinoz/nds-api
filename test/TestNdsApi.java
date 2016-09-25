import nds_api.RomFinder;
import nds_api.RomHeader;
import nds_api.RomHeaderReader;
import nds_api.RomMapping;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

public final class TestNdsApi {
    @Test
    public void testNdsApi() throws IOException {
        Path romsDirectory = Paths.get("roms/");

        RomFinder finder = new RomFinder(romsDirectory, "nds");
        Set<Path> romPaths = finder.find();

        Assert.assertTrue(!romPaths.isEmpty());

        for (Path romPath : romPaths) {
            try (FileChannel channel = FileChannel.open(romPath)) {
                RomMapping mapping = new RomMapping(channel);

                RomHeaderReader headerReader = new RomHeaderReader(mapping);
                RomHeader header = headerReader.read();

                Assert.assertTrue(header.getTitle().toString().startsWith("IP"));
            }
        }
    }
}
