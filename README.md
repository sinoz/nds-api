# NDS API
A low level API that allows users to extract or make raw use of resources such as images from NDS ROMs. The framework aims for high performing I/O operations and makes use of non-blocking NIO to achieve this. It also aims for a complete thread-safe usage so users may perform I/O operations concurrently. It is recommended to build a domain layer on top of this API.

#### The following file system formats are supported:
  - [x] NARC (Nitro Archive)
  - [ ] BTX0 (Basic Texture)
  - [ ] NCGR (Nitro Graphic Resource)
  - [ ] NCLR (Nitro Colour Resource)

#### Requirements
The framework utilizes SBT for dependency management and refers to the following dependencies:
  * Netty rev4.1.5.Final
  * Guava rev19
  * ScalaTest rev3.0
  
The project is compiled to and thus requires at least Java 7. The Scala based tests are compiled with Scala 2.11.8.

#### Credits
I would like to give credit to the [Tinke](https://github.com/pleonex/tinke) project as it was used as a reference to figure out the complex data structures of the Nitro file system. The NDS documentation of [problemkaput](http://problemkaputt.de/gbatek.htm) was also used as a reference.
