package net.sf.anathema.hero.magic.model;

import net.sf.anathema.character.main.magic.model.charm.ICharm;
import net.sf.anathema.character.main.magic.description.MagicDescriptionProvider;
import net.sf.anathema.character.main.template.magic.ICharmTemplate;
import net.sf.anathema.hero.charms.CharmsModel;
import net.sf.anathema.hero.charms.CharmsModelFetcher;
import net.sf.anathema.hero.concept.HeroConceptFetcher;
import net.sf.anathema.character.main.magic.model.charms.ILearningCharmGroup;
import net.sf.anathema.character.main.magic.model.charms.options.DefaultCharmTemplateRetriever;
import net.sf.anathema.hero.concept.CasteSelection;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.control.ChangeListener;

public class CharacterCharmModel {
  private Hero hero;
  private MagicDescriptionProvider magicDescriptionProvider;

  public CharacterCharmModel(Hero hero, MagicDescriptionProvider magicDescriptionProvider) {
    this.hero = hero;
    this.magicDescriptionProvider = magicDescriptionProvider;
  }

  public boolean isAllowedAlienCharms() {
    ICharmTemplate charmTemplate = DefaultCharmTemplateRetriever.getNativeTemplate(hero);
    return charmTemplate.isAllowedAlienCharms(getCaste().getType());
  }

  public void addCasteChangeListener(ChangeListener listener) {
    getCaste().addChangeListener(listener);
  }

  public CharmsModel getCharmConfiguration() {
    return CharmsModelFetcher.fetch(hero);
  }

  private CasteSelection getCaste() {
    return HeroConceptFetcher.fetch(hero).getCaste();
  }

  public void toggleLearned(String charmId) {
    CharmsModel charms = getCharmConfiguration();
    ILearningCharmGroup charmGroup = getCharmGroupByCharmId(charmId);
    charmGroup.toggleLearned(charms.getCharmById(charmId));
  }

  private ILearningCharmGroup getCharmGroupByCharmId(String charmId) {
    CharmsModel charms = getCharmConfiguration();
    ICharm charm = charms.getCharmById(charmId);
    return charms.getGroup(charm);
  }

  public MagicDescriptionProvider getMagicDescriptionProvider() {
    return magicDescriptionProvider;
  }
}
