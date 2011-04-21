package net.sf.anathema.character.ghost.fetters;

import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersister;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersisterFactory;
import net.sf.anathema.character.ghost.fetters.persistence.GhostFettersPersister;
import net.sf.anathema.framework.messaging.IAnathemaMessaging;

public class GhostFettersPersisterFactory implements IAdditionalPersisterFactory {

  public IAdditionalPersister createPersister(IAnathemaMessaging messaging) {
    return new GhostFettersPersister();
  }
}