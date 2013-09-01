package net.sf.anathema.initialization.initialitems;

import net.sf.anathema.ProxySplashscreen;
import net.sf.anathema.initialization.reflections.ResourceLoader;
import net.sf.anathema.framework.environment.resources.ResourceFile;
import net.sf.anathema.framework.environment.Resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class ItemInitializer {

  private final Resources resources;
  private final RepositoryItemInitializationStrategy strategy;
  private ResourceLoader loader;

  public ItemInitializer(Resources resources, RepositoryItemInitializationStrategy strategy,
                         ResourceLoader resourceLoader) {
    this.resources = resources;
    this.strategy = strategy;
    this.loader = resourceLoader;
  }

  public void initialize() {
    if (strategy.shouldInitializeData()) {
      ProxySplashscreen.getInstance().displayStatusMessage(resources.getString(strategy.getMessageKey()));
      populateRepository();
    }
  }

  private void populateRepository() {
    try {
      for (ResourceFile file : loader.getResourcesMatching(strategy.getItemPattern())) {
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