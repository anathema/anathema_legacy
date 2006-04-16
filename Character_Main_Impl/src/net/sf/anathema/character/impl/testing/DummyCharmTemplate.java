package net.sf.anathema.character.impl.testing;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.template.magic.ICharmTemplate;
import net.sf.anathema.character.generic.template.magic.IGenericCharmConfiguration;

public class DummyCharmTemplate implements ICharmTemplate {

  private MartialArtsLevel martialArtsLevel = MartialArtsLevel.Mortal;

  public ICharm[] getCharms(IExaltedRuleSet rules) {
    return new ICharm[0];
  }

  public ICharm[] getMartialArtsCharms(IExaltedRuleSet rules) {
    return new ICharm[0];
  }

  public MartialArtsLevel getMartialArtsLevel() {
    return martialArtsLevel;
  }

  public boolean knowsCharms() {
    return false;
  }

  public boolean isMartialArtsCharmAllowed(
      ICharm martialArtsCharm,
      IGenericCharmConfiguration charmConfiguration,
      boolean isExperienced) {
    return false;
  }

  public void setMartialArtsLevel(MartialArtsLevel martialArtsLevel) {
    this.martialArtsLevel = martialArtsLevel;
  }

  public boolean isAllowedAlienCharms(ICasteType caste) {
    return false;
  }
}