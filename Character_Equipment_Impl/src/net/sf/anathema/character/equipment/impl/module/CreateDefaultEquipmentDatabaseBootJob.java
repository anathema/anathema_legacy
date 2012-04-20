package net.sf.anathema.character.equipment.impl.module;

import net.sf.anathema.ProxySplashscreen;
import net.sf.anathema.character.equipment.impl.item.model.gson.EquipmentGson;
import net.sf.anathema.character.equipment.impl.item.model.gson.GsonEquipmentDatabase;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.view.IAnathemaView;
import net.sf.anathema.initialization.BootJob;
import net.sf.anathema.initialization.IAnathemaBootJob;
import net.sf.anathema.initialization.reflections.AnathemaReflections;
import net.sf.anathema.lib.resources.IAnathemaResourceFile;
import net.sf.anathema.lib.resources.IResources;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

@BootJob
public class CreateDefaultEquipmentDatabaseBootJob implements IAnathemaBootJob {

  private final static String EQUIPMENT_REGEX = "^.*\\.item$";

  @Override
  public void run(IResources resources, IAnathemaModel anathemaModel, IAnathemaView view) {
    /* Once bootjob ordering is in place, any of this class' variables/functions with
the word 'legacy' in it, and any code that calls it, can be safely removed.
Just make sure this runs *after* DatabaseConversionBootJob. */
    File legacyDatabaseFile = new File(anathemaModel.getRepository().getRepositoryPath(),
            "equipment" + File.separatorChar + "Equipment.yap");
    GsonEquipmentDatabase database = GsonEquipmentDatabase.CreateFrom(anathemaModel);
    boolean thereIsNoDataYet = !legacyDatabaseFile.exists() && database.isEmpty();
    if (thereIsNoDataYet) {
      ProxySplashscreen.getInstance().displayStatusMessage(
              resources.getString("Equipment.Bootjob.DefaultDatabaseSplashmessage")); //$NON-NLS-1$
      populateRepository(database, anathemaModel.getReflections());
    }
  }

  private void populateRepository(GsonEquipmentDatabase database, AnathemaReflections reflections) {
    EquipmentGson gson = new EquipmentGson();
    try {
      for (IAnathemaResourceFile file : reflections.getResourcesMatching(EQUIPMENT_REGEX)) {
        String itemJSON = getStringFromStream(file.getURL().openStream());
        database.saveTemplateNoOverwrite(gson.fromJson(itemJSON));
      }
    } catch (IOException e) {
      throw new RuntimeException("Could not create default database.");
    }
  }

  private String getStringFromStream(InputStream stream) {
    return new Scanner(stream).useDelimiter("\\A").next();
  }
}