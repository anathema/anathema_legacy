package net.sf.anathema.character.thaumaturgy.persistence;

import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersister;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersisterFactory;
import net.sf.anathema.framework.messaging.IAnathemaMessaging;

public class ThaumaturgyPersisterFactory implements IAdditionalPersisterFactory {

  public IAdditionalPersister createPersister(IAnathemaMessaging messaging) {
    return new ThaumaturgyModelPersister();
  }
}