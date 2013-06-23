package net.sf.anathema.character.presenter.magic.combo;

import net.sf.anathema.character.generic.caste.CasteType;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.description.MagicDescriptionProvider;
import net.sf.anathema.character.main.model.charms.CharmsModel;
import net.sf.anathema.character.main.model.charms.CharmsModelFetcher;
import net.sf.anathema.character.main.model.combos.CombosModel;
import net.sf.anathema.character.main.model.combos.CombosModelFetcher;
import net.sf.anathema.character.main.model.concept.HeroConceptFetcher;
import net.sf.anathema.character.main.model.experience.ExperienceModelFetcher;
import net.sf.anathema.hero.model.Hero;

public class ComboConfigurationModel {

  private final Hero hero;
  private final MagicDescriptionProvider magicDescriptionProvider;

  public ComboConfigurationModel(Hero hero, MagicDescriptionProvider magicDescriptionProvider) {
    this.hero = hero;
    this.magicDescriptionProvider = magicDescriptionProvider;
  }

  public boolean isAlienCharmsAllowed() {
    CasteType caste = HeroConceptFetcher.fetch(hero).getCaste().getType();
    return hero.getTemplate().getMagicTemplate().getCharmTemplate().isAllowedAlienCharms(caste);
  }

  public CharmsModel getCharmConfiguration() {
    return CharmsModelFetcher.fetch(hero);
  }

  public CombosModel getCombos() {
    return CombosModelFetcher.fetch(hero);
  }

  public MagicDescriptionProvider getMagicDescriptionProvider() {
    return magicDescriptionProvider;
  }

  public ICharm[] getLearnedCharms() {
    return CharmsModelFetcher.fetch(hero).getLearnedCharms(isExperienced());
  }

  public boolean isExperienced() {
    return ExperienceModelFetcher.fetch(hero).isExperienced();
  }
}
