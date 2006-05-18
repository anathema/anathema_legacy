package net.sf.anathema.framework.view.renderer;

import javax.swing.Icon;

import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;

public class IdentificateObjectUi extends AbstractSelectObjectUi<IIdentificate> {

  public IdentificateObjectUi(IResources resources) {
    super(resources);
  }

  @Override
  protected String getNonNullLabel(IIdentificate value) {
    return getResources().getString("Caste." + value.getId()); //$NON-NLS-1$
  }

  @Override
  protected Icon getNonNullIcon(IIdentificate value) {
    return null;
  }
}