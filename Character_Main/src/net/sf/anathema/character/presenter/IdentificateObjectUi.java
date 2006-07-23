package net.sf.anathema.character.presenter;

import net.sf.anathema.framework.view.renderer.AbstractSelectObjectUi;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;

public abstract class IdentificateObjectUi extends AbstractSelectObjectUi<IIdentificate> {

  public IdentificateObjectUi(IResources resources) {
    super(resources);
  }

  @Override
  protected String getNonNullLabel(IIdentificate value) {
    return getResources().getString("Caste." + value.getId()); //$NON-NLS-1$
  }
}