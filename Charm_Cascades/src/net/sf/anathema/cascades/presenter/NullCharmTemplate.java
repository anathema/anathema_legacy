package net.sf.anathema.cascades.presenter;

import net.sf.anathema.character.generic.caste.CasteType;
import net.sf.anathema.character.generic.impl.template.magic.DefaultMartialArtsRules;
import net.sf.anathema.character.generic.impl.template.magic.NullCharmSet;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;
import net.sf.anathema.character.generic.template.magic.ICharmTemplate;
import net.sf.anathema.character.generic.template.magic.MartialArtsRules;

public class NullCharmTemplate extends NullCharmSet implements ICharmTemplate {

  @Override
  public boolean canLearnCharms() {
    return false;
  }

  @Override
  public boolean isAllowedAlienCharms(CasteType caste) {
    return false;
  }

  @Override
  public MartialArtsRules getMartialArtsRules() {
    return new DefaultMartialArtsRules(MartialArtsLevel.Mortal);
  }
}
