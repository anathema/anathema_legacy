package net.sf.anathema.character.lunar.virtueflaw;

import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersister;
import net.sf.anathema.character.generic.framework.additionaltemplate.persistence.IAdditionalPersisterFactory;
import net.sf.anathema.character.library.virtueflaw.persistence.VirtueFlawPersister;

public class LunarVirtueFlawPersisterFactory implements IAdditionalPersisterFactory {

  public IAdditionalPersister createPersister() {
    return new VirtueFlawPersister();
  }
}