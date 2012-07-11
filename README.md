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
### Launching Anathema (IDE)###
Launch ``net.sf.anathema.AnathemaBootLoader``

### Launching Anathema (Command Prompt)###
Launch `gradlew run`

### Running the test suite###
Run ``gradlew test``

### Adding a dependency ###
1. Add the dependency entry to the module's ``build.gradle``.
2. Run ``gradlew eclipseClasspath`` or ``gradlew ideaModule`` respectively.

### Adding a new module ###
> IntelliJ IDEA users best run this outside of the IDE, else it might not pick up all changes.

1. Run ``gradlew createModule -PmoduleName=MODULENAME``
2. In ``settings.gradle``, add your module name.
4. Run ``gradlew eclipseProject eclipseClasspath eclipseJdt`` or ``gradlew idea``.
5. [Eclipse Only] import the project.

Deployment
==========
These commands just build the distribution artifacts. For the full process, see the [wiki](https://github.com/anathema/anathema/wiki/How-to-release-a-new-version).
### Building a Multi-Platform Release ###
> This works on any OS.  It creates a zip file containing a multi-platform executable anathema.jar and a Windows anathema.exe launcher.

Run ``gradlew releaseZip``

### Building a Windows Release ###
> This works on any OS.  It creates a Windows installer.

Run ``gradlew releaseWin``

### Building a Macintosh Release ###
> This works on any OS.  It creates a zip file containing a Mac application bundle.

Run ``gradlew releaseMac``

### Building All Platforms for Release
> Builds the Zip, Windows, and Mac distributions

Run ``gradlew release``