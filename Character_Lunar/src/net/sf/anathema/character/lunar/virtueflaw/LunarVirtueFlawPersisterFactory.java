package net.sf.anathema.character.lunar.virtueflaw;

import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersister;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersisterFactory;
import net.sf.anathema.character.lunar.virtueflaw.persistence.LunarVirtueFlawPersister;
import net.sf.anathema.framework.messaging.IAnathemaMessaging;

public class LunarVirtueFlawPersisterFactory implements IAdditionalPersisterFactory {

  public IAdditionalPersister createPersister(IAnathemaMessaging messaging) {
    return new LunarVirtueFlawPersister();
  }
}