package net.sf.anathema.character.sidereal.colleges.persistence;

import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersister;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersisterFactory;

public class SiderealCollegePersisterFactory implements IAdditionalPersisterFactory {

  public IAdditionalPersister createPersister() {
    return new SiderealCollegePersister();
  }
}