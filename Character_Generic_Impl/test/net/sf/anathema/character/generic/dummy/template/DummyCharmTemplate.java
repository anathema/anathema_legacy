package net.sf.anathema.character.generic.dummy.template;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.impl.template.magic.DefaultMartialArtsRules;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;
import net.sf.anathema.character.generic.template.magic.ICharmTemplate;
import net.sf.anathema.character.generic.template.magic.IMartialArtsRules;
import net.sf.anathema.character.generic.template.magic.IUniqueCharmType;

public class DummyCharmTemplate implements ICharmTemplate {

  @Override
  public ICharm[] getCharms() {
    return new ICharm[0];
  }
  
  @Override
  public ICharm[] getUniqueCharms() {
	return new ICharm[0];
  }

  @Override
  public ICharm[] getMartialArtsCharms() {
    return new ICharm[0];
  }

  @Override
  public boolean canLearnCharms() {
    return false;
  }

  @Override
  public IMartialArtsRules getMartialArtsRules() {
    DefaultMartialArtsRules defaultMartialArtsRules = new DefaultMartialArtsRules(MartialArtsLevel.Mortal);
    defaultMartialArtsRules.setHighLevelAtCreation(true);
    return defaultMartialArtsRules;
  }

  @Override
  public boolean hasUniqueCharms() {
    return false;
  }

  @Override
  public boolean isAllowedAlienCharms(ICasteType caste) {
    return false;
  }

  @Override
  public IUniqueCharmType getUniqueCharmType() {
	  return null;
  }
}