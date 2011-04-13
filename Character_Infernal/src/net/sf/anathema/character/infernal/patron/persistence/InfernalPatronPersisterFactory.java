package net.sf.anathema.character.infernal.patron.persistence;

import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersister;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersisterFactory;
import net.sf.anathema.framework.messaging.IAnathemaMessaging;

public class InfernalPatronPersisterFactory implements IAdditionalPersisterFactory {

  public IAdditionalPersister createPersister(IAnathemaMessaging messaging) {
    return new InfernalPatronPersister();
  }
}