//Copyright (c) 2004 by disy Informationssysteme GmbH
package net.sf.anathema.gis.platform.util;

import net.disy.commons.swing.filechooser.IFileChooserProvider;
import net.disy.commons.swing.filechooser.chooser.DefaultFileChooser;
import net.disy.commons.swing.filechooser.chooser.IFileChooser;

// NOT_PUBLISHED
public class DefaultFileChooserProvider implements IFileChooserProvider {
  private final IFileChooser fileChooser = new DefaultFileChooser();

  @Override
  public IFileChooser getFileChooser() {
    return fileChooser;
  }
}