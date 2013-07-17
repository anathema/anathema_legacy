package net.sf.anathema.magic.description.module;

import net.sf.anathema.character.main.magic.description.MagicDescription;
import net.sf.anathema.character.main.magic.description.MagicDescriptionProvider;
import net.sf.anathema.character.main.magic.basic.Magic;
import net.sf.anathema.magic.description.persistence.MagicDescriptionDataBase;

public class RepositoryMagicDescriptionProvider implements MagicDescriptionProvider {

  private MagicDescriptionDataBase dataBase;

  public RepositoryMagicDescriptionProvider(MagicDescriptionDataBase dataBase) {
    this.dataBase = dataBase;
  }

  @Override
  public MagicDescription getCharmDescription(Magic magic) {
    String description = dataBase.loadDescription(magic.getId());
    return new DirectMagicDescription(description);
  }
}
