package net.sf.anathema.character.db.template;

import net.sf.anathema.character.generic.impl.magic.persistence.ICharmCache;
import net.sf.anathema.lib.exception.PersistenceException;

public class TerrestrialMartialArtistCharmTemplate extends DefaultTerrestrialCharmTemplate {

  public TerrestrialMartialArtistCharmTemplate(ICharmCache charmProvider) throws PersistenceException {
    super(charmProvider);
  }

  @Override
  protected boolean mayLearnHighLevelAtCreation() {
    return true;
  }
}