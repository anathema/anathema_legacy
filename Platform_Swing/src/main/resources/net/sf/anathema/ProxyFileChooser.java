package net.sf.anathema;

import net.sf.anathema.lib.gui.file.Extension;
import net.sf.anathema.lib.gui.file.FileChooserConfiguration;
import net.sf.anathema.lib.gui.file.SingleFileChooser;

import java.nio.file.Path;

public class ProxyFileChooser implements SingleFileChooser {

  private SingleFileChooser delegate;

  public void setDelegate(SingleFileChooser delegate) {
    this.delegate = delegate;
  }

  @Override
  public Path selectSaveFile(FileChooserConfiguration configuration) {
    return delegate.selectSaveFile(configuration);
  }

  @Override
  public Path selectLoadFile(Extension extension) {
    return delegate.selectLoadFile(extension);
  }
}
