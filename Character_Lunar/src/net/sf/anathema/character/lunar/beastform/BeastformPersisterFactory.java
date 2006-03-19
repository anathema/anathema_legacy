package net.sf.anathema.character.lunar.beastform;

import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersister;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersisterFactory;
import net.sf.anathema.character.lunar.beastform.persistence.BeastformPersister;

public class BeastformPersisterFactory implements IAdditionalPersisterFactory {

  public IAdditionalPersister createPersister() {
    return new BeastformPersister();
  }
}