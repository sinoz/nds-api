import java.nio.channels.FileChannel
import java.nio.file.Paths

import nds_api.{RomFinder, RomHeaderReader, RomMapping}
import org.scalatest.FlatSpec

class TestRomHeader extends FlatSpec {
  // to convert java collections to make use of scala iterators
  import scala.collection.JavaConversions._

  "The game title" should "start with IP" in {
    val finder = new RomFinder(Paths.get("roms/"), "nds")
    finder.find() foreach { romPath =>
      val channel = FileChannel.open(romPath)
      val mapping = new RomMapping(channel)

      val headerReader = new RomHeaderReader(mapping)
      val header = headerReader.read()

      val title = header.getTitle()

      assert(title.toString().startsWith("IP"))

      channel.close()
    }
  }
}
