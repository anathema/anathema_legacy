package net.sf.anathema.framework.presenter.action.menu.help.updatecheck;

import de.idos.updates.UpdateSystem;
import de.idos.updates.store.ProgressReportAdapter;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class ConfigureAnathema extends ProgressReportAdapter {
  private final UpdateSystem updateSystem;

  public ConfigureAnathema(UpdateSystem updateSystem) {
    this.updateSystem = updateSystem;
  }

  @Override
  public void finishedInstallation() {
    try {
      File folderForVersionToRun = updateSystem.getFolderForVersionToRun();
      Properties properties = new Properties();
      properties.setProperty("library.folder", FilenameUtils.separatorsToUnix(folderForVersionToRun.getAbsolutePath()));
      new PropertiesSaver("anathema.properties").save(properties);
    } catch (IOException e) {
      //handle exception
    }
  }
}
