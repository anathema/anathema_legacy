package net.sf.anathema.framework.view.renderer;

import javax.swing.Icon;

import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;

public class IdentificateObjectUi extends AbstractSelectObjectUi<IIdentificate> {
  
  private final String resourceKeyPrefix;

  public IdentificateObjectUi(IResources resources, String keyPrefix) {
    super(resources);
    this.resourceKeyPrefix = keyPrefix;
  }

  @Override
  protected String getNonNullLabel(IIdentificate value) {
    return getResources().getString(resourceKeyPrefix + value.getId());
  }

  @Override
  protected Icon getNonNullIcon(IIdentificate value) {
    return null;
  }
}