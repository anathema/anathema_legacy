package net.sf.anathema.hero.framework.perspective.sheet;

import net.sf.anathema.framework.view.SwingApplicationFrame;
import net.sf.anathema.lib.resources.Resources;

import javax.swing.JOptionPane;
import java.nio.file.Files;
import java.nio.file.Path;

public class ControlledFileChooser implements FileChooser {

  private Resources resources;

  public ControlledFileChooser(Resources resources) {
    this.resources = resources;
  }

  @Override
  public Path getPrintFile() {
    Path selectedFile = FxFileChooser.selectSaveFile();
    if (selectedFile == null) {
      return null;
    }
    if (!checkFileAllowed(selectedFile)) {
      return null;
    }
    return selectedFile;
  }

  private boolean checkFileAllowed(Path selectedFile) {
    String message = resources.getString("Anathema.Reporting.PrintAction.OverwriteMessage");
    String title = resources.getString("Anathema.Reporting.PrintAction.OverwriteTitle");
    return !Files.exists(selectedFile) || JOptionPane.showConfirmDialog(SwingApplicationFrame.getParentComponent(), message, title, JOptionPane.YES_NO_OPTION) != 1;
  }
}
