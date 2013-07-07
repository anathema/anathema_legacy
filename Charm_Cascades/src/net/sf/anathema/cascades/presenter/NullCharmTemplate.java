package net.sf.anathema.cascades.presenter;

import net.sf.anathema.character.main.caste.CasteType;
import net.sf.anathema.character.main.template.magic.DefaultMartialArtsRules;
import net.sf.anathema.character.main.template.magic.NullCharmSet;
import net.sf.anathema.character.main.magic.charms.MartialArtsLevel;
import net.sf.anathema.character.main.template.magic.ICharmTemplate;
import net.sf.anathema.character.main.template.magic.MartialArtsRules;

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
