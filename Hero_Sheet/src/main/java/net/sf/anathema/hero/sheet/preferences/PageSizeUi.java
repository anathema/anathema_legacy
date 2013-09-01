package net.sf.anathema.hero.sheet.preferences;

import net.sf.anathema.framework.reporting.pdf.PageSize;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.ConfigurableTooltip;
import net.sf.anathema.framework.environment.Resources;

public class PageSizeUi implements AgnosticUIConfiguration<PageSize> {
  private Resources resources;

  public PageSizeUi(Resources resources) {
    this.resources = resources;
  }

  @Override
  public RelativePath getIconsRelativePath(PageSize value) {
    return NO_ICON;
  }

  @Override
  public String getLabel(PageSize value) {
    if (value == null) {
      return NO_LABEL;
    }
    return resources.getString("PageSize." + value.name());
  }

  @Override
  public void configureTooltip(PageSize item, ConfigurableTooltip configurableTooltip) {
    configurableTooltip.showNoTooltip();
  }
}
