package net.sf.anathema.character.linguistics;

import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersister;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersisterFactory;
import net.sf.anathema.character.linguistics.persistence.LinguisticsPersister;
import net.sf.anathema.framework.messaging.IMessaging;

public class LinguisticsPersisterFactory implements IAdditionalPersisterFactory {

  @Override
  public IAdditionalPersister createPersister(IMessaging messaging) {
    return new LinguisticsPersister();
  }
}