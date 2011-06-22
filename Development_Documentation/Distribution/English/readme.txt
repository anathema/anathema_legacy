===------------------------------------------------------------------------------===
	    ANATHEMA v3.0.0 "MALFEAS"
===------------------------------------------------------------------------------===

Anathema Read Me
(C) 2004-2008 by Sandra Sieroux and Urs Reupke
(C) 2010-2011 by Urs Reupke, Sandra Sieroux, Paul Adams, Eric Astor,
                 Douglas Fotheringham, Sean Mattox, Scott Olson, and Rob Rutherford
Exalted (C) 2001-2011 by White Wolf Publishing, Inc.
http://anathema.sf.net

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

Enjoy.

===------------------------------------------------------------------------------===
		III - Download & Installation
===------------------------------------------------------------------------------===

Anathema is available free at http://sourceforge.net/projects/anathema

Though the .zip-download covers all, we recommend using the OS-specific downloads, if available.

Guides covering installation and general use are available from the project page.

===------------------------------------------------------------------------------===
		III - Launching
===------------------------------------------------------------------------------===

Run the program by typing "java -jar anathema.jar".
Windows users will want to use the executable provided, same goes for Linux and the 
shell script. Automatic desktop integration on Mac OS X is handled by the .dmg
download; if no .dmg is available, Mac users should take the application out of the
Mac .zip file and drag it to the Applications folder.

Linux users should make sure that either r/w-rights are granted on the Anathema directory or
that a proper directory is specified at startup, using the '-Drepository' parameter, e.g.
  java -Drepository="C:\AnathemaRepository" -jar anathema.jar
to store files in the folder AnathemaRepository on drive C.
The shell script takes care of this.

Anathema needs at least Java Runtime Environment (JRE) 5.0 to run.
For the Windows executable, you will need JRE 6.0.
To determine your version please type "java -version". 
You can always get the most up to date version at "http//java.sun.com".

===------------------------------------------------------------------------------===
		IV - Features
===------------------------------------------------------------------------------===

+ Generate and maintain all 5 core exalt types (1E)
+ Generate and maintain Solar, Lunar, Sidereal, Dragon-Blooded, Infernal, and Abyssal
  exalt types (2E)
+ Generate character descriptions (currently disabled)
+ Print character sheets and descriptions to PDF
+ Organize your series' plot
+ Manage a music database for your series
+ View many an Exalt's Charm data

See versions.txt for a version history.

===------------------------------------------------------------------------------===
		V - Customization
===------------------------------------------------------------------------------===

To configure Anathema, visit the "Preferences" dialog available in the "Extras" menu.
All settings need for Anathema to be restarted to take effect.

The full extent of these options is described in the Anathema installation guide
available on the project page.

===------------------------------------------------------------------------------===
		VI - Bugs
===------------------------------------------------------------------------------===

Despite our efforts to the contrary, it's most probable that Anathema isn't entirely 
free of bugs. If you should encounter any problems, please report it via the Sourceforge 
project page, using the "Bugs" tracker. 
Thanks for helping us improve!

Known Issues:
---------------------
-Outcaste DB with the Breeding Background pay more than they should if Breeding is not
the first item on the list. 

-The "Merits and Flaws" tab changes size when items are selected.

-A problem has been reported with saving equipment to the database,
but it appears to be quite elusive. Please contact us should you encounter this bug.

-"Start Maximized" doesn't work on some Linux systems.

The new (as of Ink Monkeys 35) rules on modifying Combos are supported to a limited degree:
Combos created at character creation cannot be modified, and the program does not charge the
character 1 xp for modifying combos, but the program allows the modification to happen.  If
a combo is modified, please deduct 1 xp to follow this rule.

Note: the Dreams of the First Age Solar templates give 2 more bonus dots than the the rules
do, to allow the player to place the free manse and free cult dots that they automatically get.
Also, the Solar types that get 1 or 2 dots of an Ability of their choice from a selection of
options, those dots will be the first dots chosen from that range and are not deletable, so
choose carefully. These templates need some revising as well, but are a start.

See versions.txt for a history of bugs fixed.

===------------------------------------------------------------------------------===
		VII - Contact
===------------------------------------------------------------------------------===

Anathema was created  by Sandra Sieroux and Urs Reupke, with further development
(starting with version 1.4.1) by Scott Olson, Eric Astor, Rob Rutherford, Paul Adams,
Sean Mattox, Douglas Fotheringham, and others. Contact us at
anathema-developers@lists.sourceforge.net with questions, comments or suggestions.

For bug reports you should use the SourceForge.net Tracking System at
http://sourceforge.net/projects/anathema

Visit the Anathema website at http://anathema.sf.net

===------------------------------------------------------------------------------===
		VIII - Contribute
===------------------------------------------------------------------------------===

Anathema is open source, and thus, you have free access
to the source code. If you think something is at odds, or have
a great idea for a new feature or another improvement, just 
implement it and send it to us for reviewing and integration.

If you are interested in seeing Anathema in your native language,
please contact us.

If you're interested in becoming a member of the Anathema
development team, please contact us, at anathema-developers@lists.sourceforge.net.

===------------------------------------------------------------------------------===
		IX - Thanks
===------------------------------------------------------------------------------===

Credit for the creation of the Anathema project goes to Urs Reupke, without whom the
project would never have gotten off the ground.

Special Thanks to vestrial, for staying with Urs on this project despite her urges to the contrary.
A huge "Thank you" to Mr. Ricardo Rodriguez who has translated the program to spanish.
Same to Martin, for the cool new artwork.

Paul Adams provided the inspiration that got me (Scott) started working on Anathema
plugins, which led inevitably to the current work, with his creation of new plugins to
support Anathema, and his sterling work on the new plugins for 1.4.2 and 1.5.0 (and
getting us pointed in the right direction for future expansions).

Thanks also to Rob Rutherford and Eric Astor, without whom 1.4.2 probably wouldn't have
gotten done in 2010, and 1.5.0 wouldn't have been done until 2011-12.  Their knowledge
of Java and Eclipse has been invaluable, as have been their coding skills. 

Sean Mattox provided a new burst of energy and skill when he joined up and hammered out
the code for 2E Lunars in record time.  His arrival, along with that of Douglas Fotheringham,
got 2.0 out the door long before I'd expected it.

Philip "The_Burrito" Markus provided much of the Necromancy spell and Lunar Charm data.
Patches did the same for the Dragon Kings.
Thanks to Weltenreiter and Bluerps who long ago helped organizing the data.
To uteck, for efforts in making the user's guide.

Ricardo also deserves to be mentioned for finding innummerable errors & typos within
both code and database. As I said before: You rock!
Thanks to everyone pointing out bugs, be they large or small over the
past two years. 

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