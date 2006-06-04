===------------------------------------------------------------------------------===
	    ANATHEMA v0.11 "KIRIGHAST"
===------------------------------------------------------------------------------===

Anathema Read Me
(C) 2004-2006 by Sandra Sieroux and Urs Reupke
Exalted (C) 2001-2006 by White Wolf Publishing, Inc.
http://anathema.sf.net

Anathema is still in beta stages.
Please come back regularly to stay up to date.


===------------------------------------------------------------------------------===
	            TABLE OF CONTENTS
===------------------------------------------------------------------------------===

	I - About Anathema
	II - Download & Installation
	III - Launching
	IV - Features
	V - Customization
	VI - Bugs
	VII - Contact
	VIII - Contribute
	IX - Thank You!
	X - License


===------------------------------------------------------------------------------===
		I - About Anathema
===------------------------------------------------------------------------------===

Anathema is an exhaustive suite for all aspects of Exalted series management, 
featuring character and campaign management, media library, script notes and more.

That's what we aim for. 
This release is just one more of many steps on the long road ahead. 
Enjoy.


===------------------------------------------------------------------------------===
		III - Download & Installation
===------------------------------------------------------------------------------===

Anathema is available free at http://sourceforge.net/projects/anathema

The program comes in two flavors:

-Anathema_user.zip 
	a.k.a Anathema-in-a-Box
	Contains all libraries and the project binaries.
	Extract to a fresh folder (maintaining path) and you're all set.
	If you are a first time user, this is the package for you.

-Anathema_developer.zip 
	Contains all libraries and the project binaries, as well as the most 
	recent source code.
	Extract to a fresh folder (maintaining path) and you will find Anathema 
	all set up with the sources ready for inspection and change.
	If you are a first time user and interested in the "How?" as well as in the
	"What?", go ahead and download.
	
At first launch, Linux users need to grant read and write permissions on the Anathema
main directory or specify a repository directory. (See "Section III - Launching")

A guide covering download, installation and launching is available from the project page.

===------------------------------------------------------------------------------===
		III - Launching
===------------------------------------------------------------------------------===

Run the program by typing "java -jar anathema.jar".
Windows users might try to run "javaw -jar anathema.jar" instead.

Linux users should make sure that either r/w-rights are granted on the Anathema directory or
that a proper directory is specified at startup, using the '-Drepository' parameter, e.g.
  java -Drepository="C:\AnathemaRepository" -jar anathema.jar
to store files in the folder AnathemaRepository on drive C.

Anathema needs at least Java Runtime Environment 5.0 to run.
To determine your version please type "java -version". 
You can always get the most up to date version at "http//java.sun.com".

A guide covering download, installation and launching is available from the project page.

===------------------------------------------------------------------------------===
		IV - Features
===------------------------------------------------------------------------------===

After all this talk, you surely ask yourself: "What does Anathema do for me?"
Here you are:

+ Generate and maintain all 5 core exalt types
+ Generate character descriptions
+ Print character sheets and descriptions to PDF
+ Organize your series' plot
+ Manage a music database for your series
+ View many an Exalts Charm data

See versions.txt for a complete version history.

If you are missing a feature, feel free to report it using the "RFE" tracker on the
SourceForge Project site or by contacting us directly.


===------------------------------------------------------------------------------===
		V - Customization
===------------------------------------------------------------------------------===

To configure Anathema, visit the "Preferences" dialog available in the "Extras" menu.
The dialog allows to switch the program language from English to Spanish. 
(Contact us if you want to help realize Anathema in your language!)

Apart from those in program-settings, user can customize the list of natures and willpower 
recovery conditions. See the instructions provided in /data/nature_instructions.txt for details.

All settings need for Anathema to be restarted to take effect.

The full extent of these options is described in the Anathema installation guide available on the project page.

===------------------------------------------------------------------------------===
		VI - Bugs
===------------------------------------------------------------------------------===

Despite our efforts to the contrary, it's most probable that Anathema isn't entirely 
free of bugs. If you should encounter any problems, please drop us a note 
(see Section V - Contact for details) or report it via the Sourceforge project site, 
using the "Bugs" tracker. 
Thanks for helping us improve!

Known Issues:
---------------------
The Combo rules for Lunar illegally allow for Martial Arts Charms to be comboed with attribute based Charms.
This will be fixed in the next version.

Sidereals still lack the "Connections" background.

The "Mrits and Flaws" tab changes size when items are selected.

A problem has been reported with re-loading characters after closing them,
but it seems quite elusive. Please contact us should you encounter this bug.

See versions.txt for a history of bugs fixed.

===------------------------------------------------------------------------------===
		VII - Contact
===------------------------------------------------------------------------------===

Anathema is developed by Sandra Sieroux and Urs Reupke.
Contact us at 
urskr@users.sourceforge.net
vestrial@users.sourceforge.net 
with any questions, comments or suggestions.

For bug reports and feature requests, you could as well use the 
SourceForge.net Tracking System at
http://sourceforge.net/projects/anathema

You can also reach us via Private Message at rpg.net forums - 
just send your message to the user "UKR".

Visit the Anathema website at http://anathema.sf.net


===------------------------------------------------------------------------------===
		VIII - Contribute
===------------------------------------------------------------------------------===

Anathema is open source, and thus, you have free access
to the source code. If you think something is at odds, or have
a great idea for a new feature or another improvement, just 
implement it and send it to us for reviewing and integration.

If you are interested in seeing Anathema in your native language,
please contact us - we're interested in translators from all around 
the world (and it's really simple).

Other ways to contribute are by creating database files for Charms and
Spells - the easiest way to fame eternal.

However at this point we don't accept other people into the development team. 
The reasons for this are manifold, if you're interested in them, feel free to 
send a mail.
Currently, we're considering how to allow for plugins to be written, so you
can more easily participate.


===------------------------------------------------------------------------------===
		IX - Thank you
===------------------------------------------------------------------------------===

Thanks to the everyone pointing out bugs, be they large or small.

Praises go to Xanatos for the nice icon.
Kudos to uteck for providing submitting the draft for the User's Guide and his 
wife for beta testing.

Philip "The_Burrito" Markus provided much of the Necromancy spell and Lunar Charm data.
Patches did the same for the Dragon Kings.
Thanks to Weltenreiter and Bluerps who long ago helped organizing the data.

We extend a huge "Thank you" to Mr. Ricardo Rodriguez and Ms. Rafaela Lombardo, 
who has translated the program and the website, respectively, to spanish.
Ricardo also deserves to be mentioned for finding innummerable errors & typos within
both code and database. As I said before: You rock!

At http://hd42.de you can visit Mr. Daniel Hohenberger, who first wrote software to 
generate charm tree images. Thank you, you saved us lots of work and inspired us to 
create the fully automated system.

If you are interested in the original version of our character sheets, please
visit voidstate at http://www.voidstate.com. Praises to him, too.


===------------------------------------------------------------------------------===
		X - License
===------------------------------------------------------------------------------===

Anathema is published under the GNU General Public License (GPL).
You may use and distribute it freely.
Please note that Anathema makes extensive use of third party
software which might be governed by other licenses.
See license.txt for details.


===------------------------------------------------------------------------------===
		End of Read Me.
===------------------------------------------------------------------------------===