package net.sf.anathema.character.equipment.impl.module;

import net.sf.anathema.ProxySplashscreen;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.initialization.reflections.ResourceLoader;
import net.sf.anathema.lib.resources.ResourceFile;
import net.sf.anathema.lib.resources.Resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class ItemInitializer {

  private final IApplicationModel anathemaModel;
  private final Resources resources;
  private final RepositoryItemInitializationStrategy strategy;

  public ItemInitializer(IApplicationModel anathemaModel, Resources resources, RepositoryItemInitializationStrategy strategy) {
    this.anathemaModel = anathemaModel;
    this.resources = resources;
    this.strategy = strategy;
  }

  public void initialize() {
    if (strategy.shouldInitializeData()) {
      ProxySplashscreen.getInstance().displayStatusMessage(resources.getString(strategy.getMessageKey()));
      populateRepository(strategy, anathemaModel.getResourceLoader());
    }
  }

  private void populateRepository(RepositoryItemInitializationStrategy strategy, ResourceLoader reflections) {
    try {
      for (ResourceFile file : reflections.getResourcesMatching(strategy.getItemPattern())) {
        String itemJSON = getStringFromStream(file.getURL().openStream());
        strategy.storeInRepository(itemJSON);
      }
    } catch (IOException e) {
      throw new RuntimeException("Could not create default database.");
    }
  }

  private String getStringFromStream(InputStream stream) {
    return new Scanner(stream).useDelimiter("\\A").next();
  }
}