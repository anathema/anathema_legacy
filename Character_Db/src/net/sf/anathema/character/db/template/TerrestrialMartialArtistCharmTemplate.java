package net.sf.anathema.character.db.template;

import net.sf.anathema.character.generic.impl.magic.persistence.ICharmCache;

public class TerrestrialMartialArtistCharmTemplate extends DefaultTerrestrialCharmTemplate {

  public TerrestrialMartialArtistCharmTemplate(ICharmCache charmProvider) {
    super(charmProvider);
  }

  @Override
  protected boolean mayLearnHighLevelAtCreation() {
    return true;
  }
}