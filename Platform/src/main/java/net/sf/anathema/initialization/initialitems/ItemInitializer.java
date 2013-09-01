package net.sf.anathema.initialization.initialitems;

import net.sf.anathema.ProxySplashscreen;
import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.environment.resources.ResourceFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class ItemInitializer {

  private final RepositoryItemInitializationStrategy strategy;
  private final Environment environment;

  public ItemInitializer(Environment environment, RepositoryItemInitializationStrategy strategy) {
    this.strategy = strategy;
    this.environment = environment;
  }

  public void initialize() {
    if (strategy.shouldInitializeData()) {
      ProxySplashscreen.getInstance().displayStatusMessage(environment.getString(strategy.getMessageKey()));
      populateRepository();
    }
  }

  private void populateRepository() {
    try {
      for (ResourceFile file : environment.getResourcesMatching(strategy.getItemPattern())) {
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