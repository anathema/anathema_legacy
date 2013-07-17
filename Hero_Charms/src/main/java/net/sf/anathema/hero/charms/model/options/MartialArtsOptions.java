package net.sf.anathema.hero.charms.model.options;

import net.sf.anathema.hero.charms.compiler.CharmProvider;
import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.hero.charms.model.CharmIdMap;
import net.sf.anathema.hero.charms.model.ICharmGroup;
import net.sf.anathema.character.main.magic.charm.ICharmLearnableArbitrator;
import net.sf.anathema.hero.charms.model.rules.CharmsRules;
import net.sf.anathema.hero.charmtree.martial.MartialArtsLevel;

import static net.sf.anathema.charms.MartialArtsUtilities.isMartialArts;

public class MartialArtsOptions implements CharmIdMap, ICharmLearnableArbitrator {

  private final MartialArtsCharmTree martialArtsCharmTree;

  public MartialArtsOptions(CharmProvider charmProvider, CharmsRules charmsRules) {
    MartialArtsLevel standardLevel = charmsRules.getMartialArtsRules().getStandardLevel();
    this.martialArtsCharmTree = new MartialArtsCharmTree(charmProvider, standardLevel);
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
}
