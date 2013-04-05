package net.sf.anathema.framework.view;

import net.sf.anathema.lib.resources.Resources;

public class EnumSelectCellRenderer extends AbstractSelectCellRenderer<Enum> {

  private final String resourceKeyPrefix;
  private final String resourceKeySuffix;

  public EnumSelectCellRenderer(String resourceKeyPrefix, Resources resources) {
    super(resources);
    this.resourceKeyPrefix = resourceKeyPrefix;
    this.resourceKeySuffix = null;
  }

  @Override
  protected String getCustomizedDisplayValue(Enum value) {
    StringBuilder builder = new StringBuilder();
    if (resourceKeyPrefix != null) {
      builder.append(resourceKeyPrefix);
    }
    builder.append(value.name());
    if (resourceKeySuffix != null) {
      builder.append(resourceKeySuffix);
    }
    return getResources().getString(builder.toString());
  }
}
