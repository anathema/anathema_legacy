package net.sf.anathema.character.intimacies;

import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersister;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersisterFactory;
import net.sf.anathema.character.intimacies.persistence.IntimaciesPersister;

public class IntimaciesPersisterFactory implements IAdditionalPersisterFactory {

  public IAdditionalPersister createPersister() {
    return new IntimaciesPersister();
  }
}