package net.sf.anathema.framework.view;

import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identifier;

public class IdentificateSelectCellRenderer extends AbstractSelectCellRenderer<Identifier> {

  private final String resourceKeyPrefix;

  public IdentificateSelectCellRenderer(Resources resources) {
    this("", resources);
  }

  public IdentificateSelectCellRenderer(String resourceKeyPrefix, Resources resources) {
    super(resources);
    this.resourceKeyPrefix = resourceKeyPrefix;
  }

  @Override
  protected String getCustomizedDisplayValue(Identifier value) {
    StringBuilder builder = new StringBuilder();
    builder.append(resourceKeyPrefix);
    builder.append(value.getId());
    return getResources().getString(builder.toString());
  }
}