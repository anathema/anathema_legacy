---Anathema Complete Installer (anathema-v0.11.exe)---

If you have any comments, problems, or suggestions relating to the Windows installer, please direct them to Jontu Kontar at pauladams@hotmail.com.  Even though this installer encases Urskr's program, it is unlikely that he could assist you with it.

Just a bit of background:  This installer was made by Inno Setup 5 and it has been surprisingly simple to use.  It took 4 hours to work out how to do the first multi-lingual installer.  However, I am still fine tuning the script and I have a few plans for the installer that have yet to find a form.

By design, the installer won't delete any files, directories, or registry keys that it didn't create.  Thus any character or plugin based data in the "Repository" should be safe when you decided to uninstall or upgrade Anathema.  If you want to completely remove Anathema, you will have to delete the "Anathema" directory created in "Program Files".  (Eventually, I intend to have a prompt that will let you delete everying but that will take time).

For those who are interested, the installer supports a variety of switches.  The most common of them are "/verysilent" (uses the default language and components), "/lang=" (specifies a language to use, aka. spanish), and "/log=" (this creates a log of everything the installer did, default is in TEMP or specify a path and file).

Example:

anathema-v0.11.exe /verysilent /lang=spanish /log=C:\anathema.txt

This would install the minimal set files required for Anathema v0.11 to run, no prompts, in spanish, and creates a log in the root of your C: drive.

---Anathema's Jar launcher (anathema.exe)---

This file was created with "Launch4j" to execute the actual Anathema program (anathema.jar), since Windows doesn't always play well with JAR files.

Like with the installer there are options that you can enable with this file.  All you need to do is edit "anathema.ini" and place the switches you want executed on a single line (each switch must be on its own line).  A list of commands can be obtained by opening the command prompt (Start -> Run -> cmd.exe) and typing "java".  The most common command is "-server" but it is only usable of you have the JDK installed (and/or the appropriate "jvm.dll").

Example (anathema.ini):

-server
-version:1.5.0

These switches would run Anathema with the "server" JVM and would require the 1.5.0 (aka. J2SE 5.0) version of that file.

----

PS.  Currently, this part project is still in Beta testing.  Once it is substantially complete, I intend to release the files that I used to create it back to the Anathema project.  If you are interested in helping, send me an e-mail and I give you access to the files you need.
