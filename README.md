> Gradle will set itself up during the first build on any system. It requires an active internet connection to do so.

Set Up
======
### Developing with IntelliJ IDEA ###
1. Run ``gradlew idea`` to create the IDEA project files.
2. Import the project into IDEA.
3. (If necessary,) open the 'Project Structure' Dialog (Ctrl+Alt+Shift+S) and set the Project SDK to a SDK > 6.0.

### Developing with Eclipse ###
1. Run ``gradlew eclipseProject eclipseClasspath eclipseJdt`` to create the Eclipse project files.
2. Import all projects into Eclipse.

Development
===========
### Launching Anathema from an IDE ###
Launch ``net.sf.anathema.AnathemaDevelopmentBootLoader``.

### Adding a dependency ###
1. Add the dependency entry to the modules ``build.gradle``.
2. Run ``gradlew eclipseClasspath`` or ``gradlew ideaModule`` respectively.

Deployment
==========
### Building a plain zip ###
> This works on any operating system. However, the Windows executable will only be included on Windows systems.

Run ``gradlew buildZip``. Done.

### Building a Windows installer ###
> This only works on Windows.

1. Install [NSIS](http://nsis.sourceforge.net/Download "Our installer-framework of choice").
2. Install the [NSIS Access Control Plugin] (http://nsis.sourceforge.net/AccessControl_plug-in "We need it to grant permissions on the repository-folder.") by extracting the ZIP into your NSIS folder.
3. In either your home directory or your clone of anathema, append a property ``nsis_path`` to ``gradle.properties``. Make it point to the NSIS folder, e.g. ``nsis_path=C:/dev/NSIS``.
4. Run ``gradlew buildWindowsInstaller``.

### Building a Macintosh Disk Image ###
> This only works on Mac OS X.

1. Run ``gradlew buildZip`` to compile the distribution.
2. Run ``gradlew -b macApplication.gradle buildMacApplication`` to build the Applicaton Folder.
3. Run ``gradlew -b macDmg.gradle buildDmgOnMacOS`` to build the Disk Image.