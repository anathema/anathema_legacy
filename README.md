Building a plain zip
--------------------
Run ``gradlew buildZip``. Done.

Building a windows installer
----------------------------
> This will only work on windows.

1. Install [NSIS](http://nsis.sourceforge.net/Download "Our installer-framework of choice").
2. Install the [NSIS Access Control Plugin] (http://nsis.sourceforge.net/AccessControl_plug-in "We need it to grant permissions on the repository-folder.") by extracting the ZIP into your NSIS folder.
3. In either your home directory or your clone of anathema, append a property ``nsis_path`` to ``gradle.properties``. Make it point to the NSIS folder, e.g. ``nsis_path=C:/dev/NSIS``.
4. Run ``gradlew buildWindowsInstaller``.
