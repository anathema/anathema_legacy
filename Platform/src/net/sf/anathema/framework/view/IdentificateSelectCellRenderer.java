package net.sf.anathema.framework.view;

import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;

public class IdentificateSelectCellRenderer extends AbstractSelectCellRenderer {

  private final String resourceKeyPrefix;
  private final String resourceKeySuffix;

  public IdentificateSelectCellRenderer(String resourceKeyPrefix, IResources resources) {
    this(resourceKeyPrefix, null, resources);
  }

  public IdentificateSelectCellRenderer(String resourceKeyPrefix, String resourceKeySuffix, IResources resources) {
    super(resources);
    this.resourceKeyPrefix = resourceKeyPrefix;
    this.resourceKeySuffix = resourceKeySuffix;
  }

  @Override
  protected Object getCustomizedDisplayValue(Object value) {
    StringBuilder builder = new StringBuilder();
    if (resourceKeyPrefix != null) {
      builder.append(resourceKeyPrefix);
    }
    builder.append(((IIdentificate) value).getId());
    if (resourceKeySuffix != null) {
      builder.append(resourceKeySuffix);
    }
    return getResources().getString(builder.toString());
  }
}