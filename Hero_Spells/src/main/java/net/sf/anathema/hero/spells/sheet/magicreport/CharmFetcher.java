package net.sf.anathema.hero.spells.sheet.magicreport;

import net.sf.anathema.character.magic.charm.Charm;
import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.hero.charms.model.CharmsModelFetcher;
import net.sf.anathema.hero.experience.ExperienceModelFetcher;
import net.sf.anathema.hero.model.Hero;

public class CharmFetcher {
  
  public Charm[] getCharms(Hero hero){
    boolean experienced = ExperienceModelFetcher.fetch(hero).isExperienced();
    CharmsModel charmsModel = CharmsModelFetcher.fetch(hero);
    if (charmsModel == CharmsModelFetcher.NO_MODEL) {
      return new Charm[0];
    }
    return charmsModel.getLearnedCharms(experienced);
  }
  
  public boolean hasCharms(Hero hero){
    return getCharms(hero).length > 0;
  }
}