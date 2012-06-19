package net.sf.anathema.character.lunar.beastform;

import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersister;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersisterFactory;
import net.sf.anathema.character.lunar.beastform.persistence.BeastformPersister;
import net.sf.anathema.framework.messaging.IMessaging;

public class BeastformPersisterFactory implements IAdditionalPersisterFactory {

  @Override
  public IAdditionalPersister createPersister(IMessaging messaging) {
    return new BeastformPersister();
  }
}