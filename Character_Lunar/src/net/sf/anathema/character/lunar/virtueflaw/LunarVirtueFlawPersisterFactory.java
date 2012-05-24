package net.sf.anathema.character.lunar.virtueflaw;

import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersister;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersisterFactory;
import net.sf.anathema.character.library.virtueflaw.persistence.DescriptiveVirtueFlawPersister;
import net.sf.anathema.framework.messaging.IAnathemaMessaging;

public class LunarVirtueFlawPersisterFactory implements IAdditionalPersisterFactory {

  @Override
  public IAdditionalPersister createPersister(IAnathemaMessaging messaging) {
    return new DescriptiveVirtueFlawPersister();
  }
}