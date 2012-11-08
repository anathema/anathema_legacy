package net.sf.anathema.character.equipment.impl.module;

import net.sf.anathema.ProxySplashscreen;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.message.MessageUtilities;
import net.sf.anathema.framework.view.MainView;
import net.sf.anathema.initialization.BootJob;
import net.sf.anathema.initialization.IAnathemaBootJob;
import net.sf.anathema.lib.message.Message;
import net.sf.anathema.lib.message.MessageType;
import net.sf.anathema.lib.resources.IResources;

import java.nio.file.Files;
import java.nio.file.Path;

@BootJob
public class DatabaseConversionBootJob implements IAnathemaBootJob {

  public static final String OLD_DATABASE_FILE = "Equipment.yap"; //$NON-NLS-1$
  public static final String OLD_DATABASE_FOLDER = "equipment"; //$NON-NLS-1$

  @Override
  public void run(IResources resources, IAnathemaModel anathemaModel, MainView view) {
    ProxySplashscreen.getInstance().displayStatusMessage(
            resources.getString("Equipment.Bootjob.Splashmessage")); //$NON-NLS-1$
    Path oldFile = anathemaModel.getRepository().getDataBaseDirectory(OLD_DATABASE_FOLDER).resolve(OLD_DATABASE_FILE);
    if (!Files.exists(oldFile)) {
      return;
    }
    MessageUtilities.indicateMessage(DatabaseConversionBootJob.class, null, new Message(
            "Anathema no longer supports your equipment database.\nTo convert the database, please uninstall this version of Anathema and install Anathema 4.1.1, then start it once with the current repository to convert your data.\nAfterwards, install this version again.\n\nAnathema will now quit.",
            MessageType.WARNING));
    System.exit(0);
  }
}