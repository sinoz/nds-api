# NDS API
A simple to use framework that allows users to extract or make raw use of resources such as images from NDS ROMs. The framework aims for high performing I/O operations and makes use of non-blocking NIO to achieve this. It also aims for a complete thread-safe usage so users may perform I/O operations concurrently.

#### The following file system formats are supported:
  - [ ] NARC (Nitro Archive)
  - [ ] BTX0 (Basic Texture)
  - [ ] NCGR (Nitro Graphic Resource)
  - [ ] NCLR (Nitro Colour Resource)

#### Requirements
The framework makes use of the following dependencies:
  * Netty rev4.1.0
  * Guava rev19
  * JUnit 4.12
  
The project is compiled to and thus requires at least Java 7.

#### Credits
I would like to give credit to the [Tinke](https://github.com/pleonex/tinke) project as it was used as a reference to figure out the complex data structures of the Nitro file system.
