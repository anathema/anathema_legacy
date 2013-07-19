> Gradle will set itself up during the first build on any system. It requires an active internet connection to do so.

Set Up
======
### Developing with IntelliJ IDEA ###
1. Run ``gradlew idea`` to create the IDEA project files.
2. Import the project into IDEA.
3. (If necessary,) open the 'Project Structure' Dialog (Ctrl+Alt+Shift+S) and set the Project SDK to a SDK > 6.0.

### Developing with Eclipse ###
1. Run ``gradlew eclipse`` to create the Eclipse project files.
2. Import all projects into Eclipse.

### Developing with NetBeans ###
1. Have the [Gradle Support](http://plugins.netbeans.org/plugin/44510/gradle-support) plugin installed.
2. Optional: [Configure](https://github.com/kelemen/netbeans-gradle-project/wiki/Global-Settings) your global preferences.
3. Open the project as you would open any other project in NetBeans.

Development
===========
### Launching Anathema (IDE)###
Launch ``net.sf.anathema.AnathemaBootLoader``

### Launching Anathema (Command Prompt)###
Run `gradlew run`

### Running the test suite###
Run ``gradlew test``

### Adding a dependency ###
1. Add the dependency entry to the module's ``build.gradle``.
2. Run ``gradlew eclipseClasspath`` or ``gradlew ideaModule`` respectively.

### Adding a new module ###
> IntelliJ IDEA users best run this outside of the IDE, else it might not pick up all changes.

1. Run ``gradlew createModule``
2. Regenerate your IDE's workspace as per the "Developing with..." sections above.

### Building Anathema ###
> This command builds the distribution artifacts for Linux, OS X and Windows.
> For the full process, see the [wiki](https://github.com/anathema/anathema/wiki/How-to-release-a-new-version).

1. Run ``gradlew clean build``
2. Fetch your artifacts from ``./build/Anathema x.x.x``