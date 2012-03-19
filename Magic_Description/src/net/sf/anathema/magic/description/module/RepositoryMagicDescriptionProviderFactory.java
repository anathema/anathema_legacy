package net.sf.anathema.magic.description.module;

import net.sf.anathema.character.generic.magic.description.MagicDescriptionProvider;
import net.sf.anathema.character.generic.magic.description.MagicDescriptionProviderFactory;
import net.sf.anathema.character.generic.magic.description.RegisteredMagicDescriptionProviderFactory;
import net.sf.anathema.magic.description.persistence.MagicDescriptionDataBase;
import net.sf.anathema.magic.description.persistence.RepositoryMagicDescriptionDataBase;
import net.sf.anathema.framework.IAnathemaModel;

@RegisteredMagicDescriptionProviderFactory
public class RepositoryMagicDescriptionProviderFactory implements MagicDescriptionProviderFactory {

  @Override
  public MagicDescriptionProvider create(IAnathemaModel anathemaModel) {
    MagicDescriptionDataBase dataBase = RepositoryMagicDescriptionDataBase.CreateFrom(anathemaModel);
    return new RepositoryMagicDescriptionProvider(dataBase);
  }
}
