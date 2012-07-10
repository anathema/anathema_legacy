This information should be put on the wiki.

Most of the files are pulled from the windows install, but the makensis binary needs to be compiled for other platforms.  I'm using symbolic links on the posix platforms to link the shared files in the bin_win folder, so we're only using the one copy of those.

### Build Instructions:
- Download the NSIS 2.46 source code [here](http://sourceforge.net/projects/nsis/files/NSIS%202/2.46/nsis-2.46-src.tar.bz2/download)
- Requires scons to be installed by whatever means you choose.
- Compile makensis using this: scons SKIPSTUBS=all SKIPPLUGINS=all SKIPUTILS=all SKIPMISC=all NSIS_CONFIG_CONST_DATA_PATH=no PREFIX=. install-compiler

Once the makensis binary has been compiled, it needs to be added to Development_Distribution/Windows/Binaries/NSIS/bin_linux or bin_solaris.



#### Resources:
- http://wiki.koshatul.com/Installing_Nullsoft_Scriptable_Installer_System_\(NSIS\)_on_BSD\)
- http://nsis.sourceforge.net/Docs/AppendixG.html
- http://www.codedrop.ca/blog/archives/9)
