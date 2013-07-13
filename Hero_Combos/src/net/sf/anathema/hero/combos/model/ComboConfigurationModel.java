package net.sf.anathema.hero.combos.model;

import net.sf.anathema.character.main.magic.description.MagicDescriptionProvider;
import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.charms.options.DefaultCharmTemplateRetriever;
import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.hero.charms.model.CharmsModelFetcher;
import net.sf.anathema.hero.combos.display.presenter.CombosModel;
import net.sf.anathema.hero.concept.CasteType;
import net.sf.anathema.hero.concept.HeroConceptFetcher;
import net.sf.anathema.hero.experience.ExperienceModelFetcher;
import net.sf.anathema.hero.model.Hero;

import java.util.ArrayList;
import java.util.List;

public class ComboConfigurationModel {

  private final Hero hero;
  private final MagicDescriptionProvider magicDescriptionProvider;

  public ComboConfigurationModel(Hero hero, MagicDescriptionProvider magicDescriptionProvider) {
    this.hero = hero;
    this.magicDescriptionProvider = magicDescriptionProvider;
  }

  public boolean isAlienCharmsAllowed() {
    CasteType caste = HeroConceptFetcher.fetch(hero).getCaste().getType();
    return DefaultCharmTemplateRetriever.getNativeTemplate(hero).isAllowedAlienCharms(caste);
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

  public List<Charm> getEligibleCharms() {
    Charm[] learnedCharms = CharmsModelFetcher.fetch(hero).getLearnedCharms(isExperienced());
    ArrayList<Charm> eligibleCharms = new ArrayList<>();
    for (Charm charm : learnedCharms) {
      if (getCombos().isComboLegal(charm)) {
        eligibleCharms.add(charm);
      }
    }
    return eligibleCharms;
  }

  public boolean isExperienced() {
    return ExperienceModelFetcher.fetch(hero).isExperienced();
  }
}
