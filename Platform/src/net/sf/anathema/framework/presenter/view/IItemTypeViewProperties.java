package net.sf.anathema.framework.presenter.view;

import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;

public interface IItemTypeViewProperties {

  RelativePath getIcon();

  String getLabelKey();

  AgnosticUIConfiguration<PrintNameFile> getItemTypeUI();
}