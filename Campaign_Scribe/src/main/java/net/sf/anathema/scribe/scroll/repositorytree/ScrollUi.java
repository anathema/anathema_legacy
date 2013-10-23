package net.sf.anathema.scribe.scroll.repositorytree;

import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.ConfigurableTooltip;

public class ScrollUi implements AgnosticUIConfiguration<PrintNameFile> {

  @Override
  public RelativePath getIconsRelativePath(PrintNameFile value) {
    return NO_ICON;
  }

  @Override
  public String getLabel(PrintNameFile value) {
    return value.getPrintName();
  }

  @Override
  public void configureTooltip(PrintNameFile item, ConfigurableTooltip configurableTooltip) {
    //nothing to do
  }
}
