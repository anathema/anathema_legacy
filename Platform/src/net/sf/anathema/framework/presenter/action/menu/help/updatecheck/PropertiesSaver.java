package net.sf.anathema.framework.presenter.action.menu.help.updatecheck;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesSaver {
  private String filename;

  public PropertiesSaver(String filename) {
    this.filename = filename;
  }

  public void save(Properties properties) throws IOException {
    File file = new File(".", filename);
    saveConfigurationTo(file, properties);
  }

  private void saveConfigurationTo(File file, Properties properties) throws IOException {
    FileOutputStream stream = null;
    try {
      stream = new FileOutputStream(file);
      properties.store(stream, "");
    } finally {
      IOUtils.closeQuietly(stream);
    }
  }
}