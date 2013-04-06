package net.sf.anathema.character.equipment.impl.module;

import net.sf.anathema.ProxySplashscreen;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.swing.MessageUtilities;
import net.sf.anathema.initialization.BootJob;
import net.sf.anathema.initialization.IBootJob;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.message.Message;
import net.sf.anathema.lib.message.MessageType;
import net.sf.anathema.lib.resources.Resources;

import java.nio.file.Files;
import java.nio.file.Path;

@BootJob
@Weight(weight = 10)
public class DatabaseConversionBootJob implements IBootJob {

  public static final String OLD_DATABASE_FILE = "Equipment.yap";
  public static final String OLD_DATABASE_FOLDER = "equipment";

  @Override
  public void run(Resources resources, IApplicationModel anathemaModel) {
    ProxySplashscreen.getInstance().displayStatusMessage(resources.getString("Equipment.Bootjob.Splashmessage"));
    Path oldFile = anathemaModel.getRepository().getDataBaseDirectory(OLD_DATABASE_FOLDER).resolve(OLD_DATABASE_FILE);
    if (!Files.exists(oldFile)) {
      return;
    }
    MessageUtilities.indicateMessage(DatabaseConversionBootJob.class, null, new Message(
            "Anathema no longer supports your equipment database.\nTo convert the database, please uninstall this version of Anathema and install Anathema 4.1.1, then start it once with the current repository to convert your data.\nAfterwards, install this version again." +
            "You can find Anathema 4.1.1 at http://sourceforge.net/projects/anathema/files/Anathema%204.1.1/" +
            "\n\nAnathema will now quit.", MessageType.WARNING));
    System.exit(0);
  }
}