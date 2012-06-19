package net.sf.anathema.character.ghost.fetters;

import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersister;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersisterFactory;
import net.sf.anathema.character.ghost.fetters.persistence.GhostFettersPersister;
import net.sf.anathema.framework.messaging.IMessaging;

public class GhostFettersPersisterFactory implements IAdditionalPersisterFactory {

  @Override
  public IAdditionalPersister createPersister(IMessaging messaging) {
    return new GhostFettersPersister();
  }
}