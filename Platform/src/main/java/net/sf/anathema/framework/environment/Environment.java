package net.sf.anathema.framework.environment;

import net.sf.anathema.lib.gui.file.SingleFileChooser;

public interface Environment extends Resources, ExceptionHandler, Preferences, ResourceLoader, ObjectFactory, SingleFileChooser {
  //nothing to do
}