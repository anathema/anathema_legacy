> Gradle will set itself up during the first build on any system. It requires an active internet connection to do so.

Set Up
======
### Developing with IntelliJ IDEA ###
1. Run ``gradlew idea`` to create the IDEA project files.
2. Import the project into IDEA.
3. In the 'Project Structure' Dialog (Ctrl+Alt+Shift+S), set the Project SDK to a SDK > 6.0 and the language version to 6.0.

Deployment
==========
### Building a plain zip ###
> The windows executable will only be built on Windows systems.

Run ``gradlew buildZip``. Done.

### Building a Windows installer ###
> This will only work on Windows.

1. Install [NSIS](http://nsis.sourceforge.net/Download "Our installer-framework of choice").
2. Install the [NSIS Access Control Plugin] (http://nsis.sourceforge.net/AccessControl_plug-in "We need it to grant permissions on the repository-folder.") by extracting the ZIP into your NSIS folder.
3. In either your home directory or your clone of anathema, append a property ``nsis_path`` to ``gradle.properties``. Make it point to the NSIS folder, e.g. ``nsis_path=C:/dev/NSIS``.
4. Run ``gradlew buildWindowsInstaller``.

### Building a Macintosh Disk Image ###
> This will only work on Mac OS X.

1. Run ``gradlew buildZip`` to compile the distribution.
2. Run ``gradlew -b macApplication.gradle buildMacApplication`` to build the Applicaton Folder.
3. Run ``gradlew -b macDmg.gradle buildDmgOnMacOS`` to build the Disk Image.