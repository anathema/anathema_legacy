package net.sf.anathema.character.lunar.heartsblood;

import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersister;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersisterFactory;
import net.sf.anathema.character.lunar.heartsblood.persistence.HeartsBloodPersister;
import net.sf.anathema.framework.messaging.IAnathemaMessaging;

public class HeartsBloodPersisterFactory implements IAdditionalPersisterFactory {

  public IAdditionalPersister createPersister(IAnathemaMessaging messaging) {
    return new HeartsBloodPersister();
  }
}