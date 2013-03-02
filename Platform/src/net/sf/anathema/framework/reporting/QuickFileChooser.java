package net.sf.anathema.framework.reporting;

import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.lang.StringUtilities;
import net.sf.anathema.lib.resources.IResources;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class QuickFileChooser implements FileChooser {

  private IItem item;
  private IResources resources;

  public QuickFileChooser(IItem item, IResources resources) {
    this.item = item;
    this.resources = resources;
  }

  @Override
  public Path getPrintFile() {
      try {
        String baseName = getBaseName(item);
        while (baseName.length() < 3) {
          baseName = baseName.concat("_");
        }
        Path path = Files.createTempFile(baseName, PrintCommand.PDF_EXTENSION);
        path.toFile().deleteOnExit();
        return path;
      } catch (IOException e) {
        throw new RuntimeException(resources.getString("Anathema.Reporting.Message.FileCreationFailed"), e);
      }
    }

  private String getBaseName(IItem item) {
    return StringUtilities.getFileNameRepresentation(item.getDisplayName());
  }
}
