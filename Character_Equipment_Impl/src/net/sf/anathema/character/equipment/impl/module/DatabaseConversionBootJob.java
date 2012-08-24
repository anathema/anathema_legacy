package net.sf.anathema.character.equipment.impl.module;

import net.sf.anathema.ProxySplashscreen;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.message.MessageUtilities;
import net.sf.anathema.framework.view.IAnathemaView;
import net.sf.anathema.initialization.BootJob;
import net.sf.anathema.initialization.IAnathemaBootJob;
import net.sf.anathema.lib.message.Message;
import net.sf.anathema.lib.message.MessageType;
import net.sf.anathema.lib.resources.IResources;

import java.io.File;

@BootJob
public class DatabaseConversionBootJob implements IAnathemaBootJob {

  private static final String OLD_DATABASE_FILE = "Equipment.yap"; //$NON-NLS-1$
  private static final String OLD_DATABASE_FOLDER = "equipment"; //$NON-NLS-1$

  @Override
  public void run(IResources resources, IAnathemaModel anathemaModel, IAnathemaView view) {
    ProxySplashscreen.getInstance().displayStatusMessage(
            resources.getString("Equipment.Bootjob.Splashmessage")); //$NON-NLS-1$
    File databaseFile = new File(anathemaModel.getRepository().getDataBaseDirectory(OLD_DATABASE_FOLDER),
            OLD_DATABASE_FILE);
    if (!databaseFile.exists()) {
      return;
    }
    MessageUtilities.indicateMessage(DatabaseConversionBootJob.class, null, new Message(
            "Anathema no longer supports your equipment database.\nTo convert the database, please uninstall this version of Anathema and install Anathema 4.1.1, then start it once with the current repository to convert your data.\nAfterwards, install this version again.\n\nAnathema will now quit.",
            MessageType.WARNING));
    System.exit(0);
  }
}