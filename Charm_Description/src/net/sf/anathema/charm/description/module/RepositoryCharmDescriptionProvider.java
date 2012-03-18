package net.sf.anathema.charm.description.module;

import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.description.CharmDescription;
import net.sf.anathema.character.generic.magic.description.CharmDescriptionProvider;
import net.sf.anathema.charm.description.persistence.CharmDescriptionDataBase;

public class RepositoryCharmDescriptionProvider implements CharmDescriptionProvider {

  private CharmDescriptionDataBase dataBase;

  public RepositoryCharmDescriptionProvider(CharmDescriptionDataBase dataBase) {
    this.dataBase = dataBase;
  }

  @Override
  public CharmDescription getCharmDescription(IMagic magic) {
    String description = dataBase.loadDescription(magic.getId());
    return new DirectCharmDescription(description);
  }
}
