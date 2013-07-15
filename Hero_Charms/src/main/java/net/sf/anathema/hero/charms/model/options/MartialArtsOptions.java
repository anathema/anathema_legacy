package net.sf.anathema.hero.charms.model.options;

import net.sf.anathema.character.main.magic.cache.CharmProvider;
import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.charm.CharmIdMap;
import net.sf.anathema.character.main.magic.model.charm.ICharmGroup;
import net.sf.anathema.character.main.magic.model.charmtree.ICharmLearnableArbitrator;
import net.sf.anathema.character.main.magic.model.charmtree.MartialArtsCharmTree;
import net.sf.anathema.hero.charms.model.rules.CharmsRules;
import net.sf.anathema.hero.magic.model.martial.MartialArtsLevel;

import static net.sf.anathema.hero.magic.model.martial.MartialArtsUtilities.isMartialArts;

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
