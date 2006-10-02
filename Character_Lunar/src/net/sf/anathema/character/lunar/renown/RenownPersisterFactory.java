package net.sf.anathema.character.lunar.renown;

import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersister;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersisterFactory;
import net.sf.anathema.character.lunar.renown.persistence.RenownPersister;
import net.sf.anathema.framework.messaging.IAnathemaMessaging;

public class RenownPersisterFactory implements IAdditionalPersisterFactory {

  public IAdditionalPersister createPersister(IAnathemaMessaging messaging) {
    return new RenownPersister();
  }
}