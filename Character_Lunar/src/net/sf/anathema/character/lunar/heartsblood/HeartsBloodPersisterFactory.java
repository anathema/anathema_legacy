package net.sf.anathema.character.lunar.heartsblood;

import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersister;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersisterFactory;
import net.sf.anathema.character.lunar.heartsblood.persistence.HeartsBloodPersister;

public class HeartsBloodPersisterFactory implements IAdditionalPersisterFactory {

  public IAdditionalPersister createPersister() {
    return new HeartsBloodPersister();
  }
}