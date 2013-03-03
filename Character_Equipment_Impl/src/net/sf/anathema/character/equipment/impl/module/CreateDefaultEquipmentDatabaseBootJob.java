package net.sf.anathema.character.equipment.impl.module;

import net.sf.anathema.ProxySplashscreen;
import net.sf.anathema.character.equipment.impl.item.model.gson.EquipmentGson;
import net.sf.anathema.character.equipment.impl.item.model.gson.GsonEquipmentDatabase;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.initialization.BootJob;
import net.sf.anathema.initialization.IAnathemaBootJob;
import net.sf.anathema.initialization.reflections.ResourceLoader;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.resources.ResourceFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

@BootJob
@Weight(weight = 11)
public class CreateDefaultEquipmentDatabaseBootJob implements IAnathemaBootJob {

  private final static String EQUIPMENT_REGEX = "^.*\\.item$";

  @Override
  public void run(IResources resources, IAnathemaModel anathemaModel) {
    GsonEquipmentDatabase database = GsonEquipmentDatabase.CreateFrom(anathemaModel);
    boolean thereIsNoDataYet = database.isEmpty();
    if (thereIsNoDataYet) {
      ProxySplashscreen.getInstance().displayStatusMessage(
              resources.getString("Equipment.Bootjob.DefaultDatabaseSplashmessage")); //$NON-NLS-1$
      populateRepository(database, anathemaModel.getResourceLoader());
    }
  }

  private void populateRepository(GsonEquipmentDatabase database, ResourceLoader reflections) {
    EquipmentGson gson = new EquipmentGson();
    try {
      for (ResourceFile file : reflections.getResourcesMatching(EQUIPMENT_REGEX)) {
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