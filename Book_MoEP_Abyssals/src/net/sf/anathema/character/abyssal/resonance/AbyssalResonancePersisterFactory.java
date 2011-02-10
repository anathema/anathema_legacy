package net.sf.anathema.character.abyssal.resonance;

import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersister;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersisterFactory;
import net.sf.anathema.character.library.virtueflaw.persistence.VirtueFlawPersister;
import net.sf.anathema.framework.messaging.IAnathemaMessaging;

public class AbyssalResonancePersisterFactory implements IAdditionalPersisterFactory {

  public IAdditionalPersister createPersister(IAnathemaMessaging messaging) {
    return new VirtueFlawPersister();
  }
}