package net.sf.anathema.character.sidereal.flawedfate;

import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersister;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersisterFactory;
import net.sf.anathema.character.library.virtueflaw.persistence.VirtueFlawPersister;
import net.sf.anathema.framework.messaging.IMessaging;

public class SiderealFlawedFatePersisterFactory implements IAdditionalPersisterFactory {

  @Override
  public IAdditionalPersister createPersister(IMessaging messaging) {
    return new VirtueFlawPersister();
  }
}