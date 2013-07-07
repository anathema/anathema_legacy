package net.sf.anathema.character.main.charm.options;

import net.sf.anathema.character.main.magic.charms.MartialArtsCharmTree;
import net.sf.anathema.character.main.magic.ICharm;
import net.sf.anathema.character.main.magic.charms.CharmIdMap;
import net.sf.anathema.character.main.magic.charms.ICharmGroup;
import net.sf.anathema.character.main.magic.charms.ICharmLearnableArbitrator;
import net.sf.anathema.character.main.template.magic.ICharmTemplate;
import net.sf.anathema.character.main.template.magic.MartialArtsRules;
import net.sf.anathema.hero.model.Hero;

import static net.sf.anathema.character.main.magic.MartialArtsUtilities.isMartialArtsCharm;

public class MartialArtsOptions implements CharmIdMap, ICharmLearnableArbitrator {

  private final MartialArtsCharmTree martialArtsCharmTree;
  private Hero hero;

  public MartialArtsOptions(Hero hero) {
    this.hero = hero;
    this.martialArtsCharmTree = new MartialArtsCharmTree(getNativeCharmTemplate());
  }

  private ICharmTemplate getNativeCharmTemplate() {
    return DefaultCharmTemplateRetriever.getNativeTemplate(hero);
  }

  @Override
  public ICharm getCharmById(String charmId) {
    return martialArtsCharmTree.getCharmById(charmId);
  }

  public ICharmGroup[] getAllCharmGroups() {
    return martialArtsCharmTree.getAllCharmGroups();
  }

  @Override
  public boolean isLearnable(ICharm charm) {
    return !isMartialArtsCharm(charm) || martialArtsCharmTree.isLearnable(charm);
  }

  public MartialArtsRules getMartialArtsRulesForCharacterType() {
    return getNativeCharmTemplate().getMartialArtsRules();
  }
}
