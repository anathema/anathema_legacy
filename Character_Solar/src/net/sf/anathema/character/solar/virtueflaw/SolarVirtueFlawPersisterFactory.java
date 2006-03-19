package net.sf.anathema.character.solar.virtueflaw;

import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersister;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersisterFactory;
import net.sf.anathema.character.solar.virtueflaw.persistence.SolarVirtueFlawPersister;

public class SolarVirtueFlawPersisterFactory implements IAdditionalPersisterFactory {

  public IAdditionalPersister createPersister() {
    return new SolarVirtueFlawPersister();
  }
}