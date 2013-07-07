package net.sf.anathema.magic.description.module;

import net.sf.anathema.character.main.magic.description.MagicDescriptionProvider;
import net.sf.anathema.character.main.magic.description.MagicDescriptionProviderFactory;
import net.sf.anathema.character.main.magic.description.RegisteredMagicDescriptionProviderFactory;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.magic.description.persistence.MagicDescriptionDataBase;
import net.sf.anathema.magic.description.persistence.RepositoryMagicDescriptionDataBase;

@RegisteredMagicDescriptionProviderFactory
public class RepositoryMagicDescriptionProviderFactory implements MagicDescriptionProviderFactory {

  @Override
  public MagicDescriptionProvider create(IApplicationModel anathemaModel) {
    MagicDescriptionDataBase dataBase = RepositoryMagicDescriptionDataBase.CreateFrom(anathemaModel);
    return new RepositoryMagicDescriptionProvider(dataBase);
  }
}
