package net.sf.anathema.framework.view;

import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;

public class IdentificateSelectCellRenderer extends AbstractSelectCellRenderer {

  private final String resourceKeyPrefix;

  public IdentificateSelectCellRenderer(String resourceKeyPrefix, IResources resources) {
    super(resources);
    this.resourceKeyPrefix = resourceKeyPrefix;
  }

  @Override
  protected Object getCustomizedDisplayValue(Object value) {
    return getResources().getString(resourceKeyPrefix + ((IIdentificate) value).getId());
  }
}