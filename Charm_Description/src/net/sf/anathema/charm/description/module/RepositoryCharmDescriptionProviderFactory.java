package net.sf.anathema.charm.description.module;

import net.sf.anathema.character.generic.magic.description.CharmDescriptionProvider;
import net.sf.anathema.character.generic.magic.description.CharmDescriptionProviderFactory;
import net.sf.anathema.character.generic.magic.description.RegisteredCharmDescriptionProviderFactory;
import net.sf.anathema.charm.description.persistence.CharmDescriptionDataBase;
import net.sf.anathema.charm.description.persistence.RepositoryCharmDescriptionDataBase;
import net.sf.anathema.framework.IAnathemaModel;

@RegisteredCharmDescriptionProviderFactory
public class RepositoryCharmDescriptionProviderFactory implements CharmDescriptionProviderFactory {

  @Override
  public CharmDescriptionProvider create(IAnathemaModel anathemaModel) {
    CharmDescriptionDataBase dataBase = RepositoryCharmDescriptionDataBase.CreateFrom(anathemaModel);
    return new RepositoryCharmDescriptionProvider(dataBase);
  }
}
