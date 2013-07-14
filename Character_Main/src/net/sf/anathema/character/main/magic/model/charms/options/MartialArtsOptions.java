package net.sf.anathema.character.main.magic.model.charms.options;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.charm.CharmIdMap;
import net.sf.anathema.character.main.magic.model.charm.ICharmGroup;
import net.sf.anathema.character.main.magic.model.charmtree.ICharmLearnableArbitrator;
import net.sf.anathema.character.main.magic.model.charmtree.MartialArtsCharmTree;
import net.sf.anathema.character.main.magic.cache.CharmProvider;
import net.sf.anathema.character.main.template.magic.CharmTemplate;
import net.sf.anathema.character.main.template.magic.MartialArtsRules;
import net.sf.anathema.hero.magic.model.martial.MartialArtsLevel;
import net.sf.anathema.hero.model.Hero;

import static net.sf.anathema.hero.magic.model.martial.MartialArtsUtilities.isMartialArts;

public class MartialArtsOptions implements CharmIdMap, ICharmLearnableArbitrator {

  private final MartialArtsCharmTree martialArtsCharmTree;
  private Hero hero;

  public MartialArtsOptions(Hero hero, CharmProvider charmProvider) {
    this.hero = hero;
    MartialArtsLevel standardLevel = getNativeCharmTemplate().getMartialArtsRules().getStandardLevel();
    this.martialArtsCharmTree = new MartialArtsCharmTree(charmProvider, standardLevel);
  }

  private CharmTemplate getNativeCharmTemplate() {
    return DefaultCharmTemplateRetriever.getNativeTemplate(hero);
  }

  @Override
  public Charm getCharmById(String charmId) {
    return martialArtsCharmTree.getCharmById(charmId);
  }

  public ICharmGroup[] getAllCharmGroups() {
    return martialArtsCharmTree.getAllCharmGroups();
  }

  @Override
  public boolean isLearnable(Charm charm) {
    return !isMartialArts(charm) || martialArtsCharmTree.isLearnable(charm);
  }

  public MartialArtsRules getMartialArtsRulesForCharacterType() {
    return getNativeCharmTemplate().getMartialArtsRules();
  }
}
