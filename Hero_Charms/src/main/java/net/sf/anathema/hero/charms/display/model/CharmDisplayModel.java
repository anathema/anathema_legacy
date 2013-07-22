package net.sf.anathema.hero.charms.display.model;

import net.sf.anathema.character.main.magic.description.MagicDescriptionProvider;
import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.hero.charms.model.learn.ILearningCharmGroup;
import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.hero.charms.model.CharmsModelFetcher;
import net.sf.anathema.hero.concept.CasteSelection;
import net.sf.anathema.hero.concept.HeroConceptFetcher;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.control.ChangeListener;

public class CharmDisplayModel {
  private Hero hero;
  private MagicDescriptionProvider magicDescriptionProvider;

  public CharmDisplayModel(Hero hero, MagicDescriptionProvider magicDescriptionProvider) {
    this.hero = hero;
    this.magicDescriptionProvider = magicDescriptionProvider;
  }

  public boolean isAllowedAlienCharms() {
    return getCharmModel().isAlienCharmAllowed();
  }

  public void addCasteChangeListener(ChangeListener listener) {
    getCaste().addChangeListener(listener);
  }

  public CharmsModel getCharmModel() {
    return CharmsModelFetcher.fetch(hero);
  }

  private CasteSelection getCaste() {
    return HeroConceptFetcher.fetch(hero).getCaste();
  }

  public void toggleLearned(String charmId) {
    CharmsModel charms = getCharmModel();
    ILearningCharmGroup charmGroup = getCharmGroupByCharmId(charmId);
    charmGroup.toggleLearned(charms.getCharmById(charmId));
  }

  private ILearningCharmGroup getCharmGroupByCharmId(String charmId) {
    CharmsModel charms = getCharmModel();
    Charm charm = charms.getCharmById(charmId);
    return charms.getGroup(charm);
  }

  public MagicDescriptionProvider getMagicDescriptionProvider() {
    return magicDescriptionProvider;
  }
}
