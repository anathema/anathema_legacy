package net.sf.anathema.character.infernal.urge;

import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersister;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersisterFactory;
import net.sf.anathema.character.infernal.urge.persistence.InfernalUrgePersister;
import net.sf.anathema.framework.messaging.IMessaging;

public class InfernalUrgePersisterFactory implements IAdditionalPersisterFactory {

  @Override
  public IAdditionalPersister createPersister(IMessaging messaging) {
    return new InfernalUrgePersister();
  }
}