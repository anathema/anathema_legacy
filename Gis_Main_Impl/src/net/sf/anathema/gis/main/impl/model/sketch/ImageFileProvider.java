package net.sf.anathema.gis.main.impl.model.sketch;

import java.awt.Component;
import java.io.File;

import net.disy.commons.swing.filechooser.SmartFileChooser;
import net.disy.commons.swing.filechooser.filefilter.ExtensionFileFilter;
import net.disy.commons.swing.filechooser.result.FileChooserOpenResult;
import de.disy.lib.gui.filechooser.IFileProvider;
import de.disy.lib.gui.filechooser.configuration.OneFileFilterOpenConfiguration;

public class ImageFileProvider implements IFileProvider {

  public File getFile(Component parent) {
    OneFileFilterOpenConfiguration configuration = new OneFileFilterOpenConfiguration(
        false,
        ExtensionFileFilter.GIF_FILE_FILTER);
    SmartFileChooser fileChooser = SmartFileChooser.getInstance();
    FileChooserOpenResult result = fileChooser.performOpenFileChooser(parent, configuration);
    if (result.isCanceled()) {
      return null;
    }
    return result.getSelectedFile();
  }
}