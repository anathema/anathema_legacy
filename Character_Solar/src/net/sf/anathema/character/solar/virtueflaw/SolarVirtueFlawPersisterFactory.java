package net.sf.anathema.character.solar.virtueflaw;

import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersister;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersisterFactory;
import net.sf.anathema.character.library.virtueflaw.persistence.DescriptiveVirtueFlawPersister;
import net.sf.anathema.framework.messaging.IMessaging;

public class SolarVirtueFlawPersisterFactory implements IAdditionalPersisterFactory {

  @Override
  public IAdditionalPersister createPersister(IMessaging messaging) {
    return new DescriptiveVirtueFlawPersister();
  }
}