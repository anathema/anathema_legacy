package net.sf.anathema.cascades.presenter;

import net.sf.anathema.character.generic.impl.magic.MartialArtsUtilities;
import net.sf.anathema.character.generic.impl.magic.charm.CharmTree;
import net.sf.anathema.character.generic.impl.magic.charm.MartialArtsCharmTree;
import net.sf.anathema.character.generic.impl.magic.persistence.ICharmCache;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmGroup;
import net.sf.anathema.character.generic.magic.charms.ICharmTree;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.ITemplateRegistry;
import net.sf.anathema.character.generic.template.magic.ICharmTemplate;
import net.sf.anathema.character.generic.template.magic.IUniqueCharmType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.charmtree.presenter.CharmGroupCollection;
import net.sf.anathema.lib.util.IIdentificate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CascadeGroupCollection implements CharmGroupCollection {
  private ITemplateRegistry templateRegistry;
  private ICharmCache cache;
  private Map<IExaltedRuleSet, CharmTreeIdentificateMap> charmMapsByRules;

  public CascadeGroupCollection(ITemplateRegistry templateRegistry, ICharmCache cache,
                                Map<IExaltedRuleSet, CharmTreeIdentificateMap> charmMapsByRules) {
    this.templateRegistry = templateRegistry;
    this.cache = cache;
    this.charmMapsByRules = charmMapsByRules;
  }

  @Override
  public ICharmGroup[] getCharmGroups() {
    return getCharmGroupsFor(ExaltedEdition.values());
  }

  public ICharmGroup[] getCharmGroups(IExaltedEdition edition) {
    return getCharmGroupsFor(edition);
  }

  private ICharmGroup[] getCharmGroupsFor(IExaltedEdition... editions) {
    List<ICharmGroup> allCharmGroups = new ArrayList<ICharmGroup>();
    initCharacterTypeCharms(allCharmGroups, editions);
    initMartialArtsCharms(allCharmGroups, editions);
    return allCharmGroups.toArray(new ICharmGroup[allCharmGroups.size()]);
  }

  private void initCharacterTypeCharms(List<ICharmGroup> allCharmGroups, IExaltedEdition[] editions) {
    for (ICharacterType type : CharacterType.values()) {
      for (IExaltedEdition edition : editions) {
        ICharacterTemplate template = templateRegistry.getDefaultTemplate(type, edition);
        if (template == null) {
          continue;
        }
        ICharmTemplate charmTemplate = template.getMagicTemplate().getCharmTemplate();
        if (charmTemplate.canLearnCharms(edition.getDefaultRuleset())) {
          for (IExaltedRuleSet ruleSet : ExaltedRuleSet.getRuleSetsByEdition(edition)) {
            registerTypeCharms(allCharmGroups, type, template, ruleSet);
            registerUniqueCharms(allCharmGroups, charmTemplate, ruleSet);
          }
        }
      }
    }
  }

  private void initMartialArtsCharms(List<ICharmGroup> allCharmGroups, IExaltedEdition[] editions) {
    for (IExaltedEdition edition : editions) {
      ICharacterTemplate template = templateRegistry.getDefaultTemplate(CharacterType.SIDEREAL, edition);
      for (IExaltedRuleSet ruleSet : ExaltedRuleSet.getRuleSetsByEdition(edition)) {
        ICharmTree martialArtsTree = new MartialArtsCharmTree(template.getMagicTemplate().getCharmTemplate(), ruleSet);
        getCharmTreeMap(ruleSet).put(MartialArtsUtilities.MARTIAL_ARTS, martialArtsTree);
        allCharmGroups.addAll(Arrays.asList(martialArtsTree.getAllCharmGroups()));
      }
    }
  }

  private void registerUniqueCharms(List<ICharmGroup> allCharmGroups, ICharmTemplate charmTemplate,
                                    IExaltedRuleSet ruleSet) {
    if (!charmTemplate.hasUniqueCharms()) {
      return;
    }
    IUniqueCharmType uniqueType = charmTemplate.getUniqueCharmType();
    ICharmTree uniqueTree = getUniqueCharmTree(ruleSet, uniqueType);
    registerGroups(allCharmGroups, ruleSet, uniqueType.getId(), uniqueTree);
  }

  private void registerTypeCharms(List<ICharmGroup> allCharmGroups, ICharacterType type,
                                  ICharacterTemplate defaultTemplate, IExaltedRuleSet ruleSet) {
    ICharmTree typeTree = getTypeCharmTree(defaultTemplate, ruleSet);
    registerGroups(allCharmGroups, ruleSet, type, typeTree);
  }

  private ICharmTree getTypeCharmTree(ICharacterTemplate defaultTemplate, IExaltedRuleSet ruleSet) {
    return new CharmTree(defaultTemplate.getMagicTemplate().getCharmTemplate(), ruleSet);
  }

  private ICharmTree getUniqueCharmTree(IExaltedRuleSet ruleSet, IUniqueCharmType uniqueType) {
    IIdentificate typeId = uniqueType.getId();
    ICharm[] charms = cache.getCharms(typeId, ruleSet);
    return new CharmTree(charms);
  }

  private void registerGroups(List<ICharmGroup> allCharmGroups, IExaltedRuleSet ruleSet, IIdentificate typeId,
                              ICharmTree charmTree) {
    ICharmGroup[] groups = charmTree.getAllCharmGroups();
    if (groups.length != 0) {
      getCharmTreeMap(ruleSet).put(typeId, charmTree);
      allCharmGroups.addAll(Arrays.asList(groups));
    }
  }

  private CharmTreeIdentificateMap getCharmTreeMap(IExaltedRuleSet ruleSet) {
    return charmMapsByRules.get(ruleSet);
  }
}