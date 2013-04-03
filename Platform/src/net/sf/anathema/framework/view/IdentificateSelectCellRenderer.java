package net.sf.anathema.framework.view;

import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.Identified;

public class IdentificateSelectCellRenderer extends AbstractSelectCellRenderer<Identified> {

  private final String resourceKeyPrefix;

  public IdentificateSelectCellRenderer(IResources resources) {
    this("", resources);
  }

  public IdentificateSelectCellRenderer(String resourceKeyPrefix, IResources resources) {
    super(resources);
    this.resourceKeyPrefix = resourceKeyPrefix;
  }

  @Override
  protected String getCustomizedDisplayValue(Identified value) {
    StringBuilder builder = new StringBuilder();
    builder.append(resourceKeyPrefix);
    builder.append(value.getId());
    return getResources().getString(builder.toString());
  }
}