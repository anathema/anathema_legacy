package net.sf.anathema.framework.view;

import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.Identified;

public class IdentificateSelectCellRenderer extends AbstractSelectCellRenderer<Identified> {

  private final String resourceKeyPrefix;
  private final String resourceKeySuffix;

  public IdentificateSelectCellRenderer(String resourceKeyPrefix, IResources resources) {
    super(resources);
    this.resourceKeyPrefix = resourceKeyPrefix;
    this.resourceKeySuffix = null;
  }

  @Override
  protected String getCustomizedDisplayValue(Identified value) {
    StringBuilder builder = new StringBuilder();
    if (resourceKeyPrefix != null) {
      builder.append(resourceKeyPrefix);
    }
    builder.append(value.getId());
    if (resourceKeySuffix != null) {
      builder.append(resourceKeySuffix);
    }
    return getResources().getString(builder.toString());
  }
}