package net.sf.anathema.character.linguistics;

import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersister;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersisterFactory;
import net.sf.anathema.character.linguistics.persistence.LinguisticsPersister;
import net.sf.anathema.framework.messaging.IAnathemaMessaging;

public class LinguisticsPersisterFactory implements IAdditionalPersisterFactory {

  public IAdditionalPersister createPersister(IAnathemaMessaging messaging) {
    return new LinguisticsPersister();
  }
}