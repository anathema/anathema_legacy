package net.sf.anathema.cascades.presenter;

import net.sf.anathema.character.generic.impl.magic.MartialArtsUtilities;
import net.sf.anathema.character.generic.impl.magic.charm.CharmTree;
import net.sf.anathema.character.generic.impl.magic.charm.MartialArtsCharmTree;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
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

public class CascadeGroupCollection implements CharmGroupCollection, EditionCharmGroups {
  private ITemplateRegistry templateRegistry;
  private CharmTreeIdentificateMap treeIdentificateMap;

  public CascadeGroupCollection(ITemplateRegistry templateRegistry, CharmTreeIdentificateMap treeIdentificateMap) {
    this.templateRegistry = templateRegistry;
    this.treeIdentificateMap = treeIdentificateMap;
  }

  @Override
  public ICharmGroup[] getCharmGroups() {
    return getCharmGroups(ExaltedEdition.SecondEdition);
  }

  @Override
  public ICharmGroup[] getCharmGroups(IExaltedEdition edition) {
    return getCharmGroupsFor(edition);
  }

  private ICharmGroup[] getCharmGroupsFor(IExaltedEdition edition) {
    List<ICharmGroup> allCharmGroups = new ArrayList<ICharmGroup>();
    initCharacterTypeCharms(allCharmGroups, edition);
    initMartialArtsCharms(allCharmGroups, edition);
    return allCharmGroups.toArray(new ICharmGroup[allCharmGroups.size()]);
  }

  private void initCharacterTypeCharms(List<ICharmGroup> allCharmGroups, IExaltedEdition edition) {
    for (ICharacterType type : CharacterType.values()) {
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

  private void initMartialArtsCharms(List<ICharmGroup> allCharmGroups, IExaltedEdition edition) {
    ICharacterTemplate template = templateRegistry.getDefaultTemplate(CharacterType.SIDEREAL, edition);
    for (IExaltedRuleSet ruleSet : ExaltedRuleSet.getRuleSetsByEdition(edition)) {
      ICharmTree martialArtsTree = new MartialArtsCharmTree(template.getMagicTemplate().getCharmTemplate(), ruleSet);
      treeIdentificateMap.put(MartialArtsUtilities.MARTIAL_ARTS, martialArtsTree);
      allCharmGroups.addAll(Arrays.asList(martialArtsTree.getAllCharmGroups()));
    }
  }

  private void registerUniqueCharms(List<ICharmGroup> allCharmGroups, ICharmTemplate charmTemplate, IExaltedRuleSet rules) {
    if (!charmTemplate.hasUniqueCharms()) {
      return;
    }
    IUniqueCharmType uniqueType = charmTemplate.getUniqueCharmType();
    ICharmTree uniqueTree = new CharmTree(charmTemplate.getUniqueCharms(rules));
    registerGroups(allCharmGroups, uniqueType.getId(), uniqueTree);
  }

  private void registerTypeCharms(List<ICharmGroup> allCharmGroups, ICharacterType type,
                                  ICharacterTemplate defaultTemplate, IExaltedRuleSet ruleSet) {
    ICharmTree typeTree = getTypeCharmTree(defaultTemplate, ruleSet);
    registerGroups(allCharmGroups, type, typeTree);
  }

  private ICharmTree getTypeCharmTree(ICharacterTemplate defaultTemplate, IExaltedRuleSet ruleSet) {
    return new CharmTree(defaultTemplate.getMagicTemplate().getCharmTemplate(), ruleSet);
  }

  private void registerGroups(List<ICharmGroup> allCharmGroups, IIdentificate typeId, ICharmTree charmTree) {
    ICharmGroup[] groups = charmTree.getAllCharmGroups();
    if (groups.length != 0) {
      treeIdentificateMap.put(typeId, charmTree);
      allCharmGroups.addAll(Arrays.asList(groups));
    }
  }
}