package net.sf.anathema.character.infernal.urge;

import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersister;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersisterFactory;
import net.sf.anathema.character.infernal.urge.persistence.InfernalUrgePersister;
import net.sf.anathema.framework.messaging.IAnathemaMessaging;

public class InfernalUrgePersisterFactory implements IAdditionalPersisterFactory {

  public IAdditionalPersister createPersister(IAnathemaMessaging messaging) {
    return new InfernalUrgePersister();
  }
}