package net.sf.anathema.charmentry.module;

import net.sf.anathema.framework.module.AbstractAnathemaModule;
import net.sf.anathema.framework.resources.IAnathemaResources;

public class CharmEntryModule extends AbstractAnathemaModule {

  @Override
  public void initAnathemaResources(IAnathemaResources resources) {
    super.initAnathemaResources(resources);
    resources.addStringResourceHandler(createStringProvider("CharmEntry", resources.getLocale())); //$NON-NLS-1$    
  }
}