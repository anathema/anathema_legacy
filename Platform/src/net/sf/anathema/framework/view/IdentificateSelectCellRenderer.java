package net.sf.anathema.framework.view;

import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;

public class IdentificateSelectCellRenderer extends AbstractSelectCellRenderer<IIdentificate> {

  private static final long serialVersionUID = -108630268359225612L;
  private final String resourceKeyPrefix;
  private final String resourceKeySuffix;

  public IdentificateSelectCellRenderer(String resourceKeyPrefix, IResources resources) {
    super(resources);
    this.resourceKeyPrefix = resourceKeyPrefix;
    this.resourceKeySuffix = null;
  }

  public IdentificateSelectCellRenderer(String resourceKeyPrefix, String resourceKeySuffix, IResources resources) {
    super(resources);
    this.resourceKeyPrefix = resourceKeyPrefix;
    this.resourceKeySuffix = resourceKeySuffix;
  }

  @Override
  protected String getCustomizedDisplayValue(IIdentificate value) {
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