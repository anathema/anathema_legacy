package net.sf.anathema.character.intimacies;

import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersister;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersisterFactory;
import net.sf.anathema.character.intimacies.persistence.IntimaciesPersister;
import net.sf.anathema.framework.messaging.IMessaging;

public class IntimaciesPersisterFactory implements IAdditionalPersisterFactory {

  @Override
  public IAdditionalPersister createPersister(IMessaging messaging) {
    return new IntimaciesPersister();
  }
}