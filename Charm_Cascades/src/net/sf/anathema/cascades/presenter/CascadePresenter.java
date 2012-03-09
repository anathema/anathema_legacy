package net.sf.anathema.cascades.presenter;

import net.sf.anathema.cascades.module.ICascadeViewFactory;
import net.sf.anathema.cascades.presenter.view.ICascadeView;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.configuration.AnathemaCharacterPreferences;
import net.sf.anathema.character.generic.impl.magic.MartialArtsUtilities;
import net.sf.anathema.character.generic.impl.magic.charm.CharmTree;
import net.sf.anathema.character.generic.impl.magic.charm.MartialArtsCharmTree;
import net.sf.anathema.character.generic.impl.magic.persistence.CharmCache;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.GroupCharmTree;
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
import net.sf.anathema.charmtree.presenter.AbstractCascadePresenter;
import net.sf.anathema.charmtree.presenter.view.CharmDisplayPropertiesMap;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CascadePresenter extends AbstractCascadePresenter implements ICascadePresenter {

  private ProxyRuleSet selectedRuleSet = new ProxyRuleSet();
  private final Map<IExaltedRuleSet, CharmTreeIdentificateMap> charmMapsByRules = new HashMap<IExaltedRuleSet, CharmTreeIdentificateMap>();
  private ITemplateRegistry templateRegistry;

  public CascadePresenter(IResources resources, ICharacterGenerics generics, ICascadeViewFactory factory) {
    super(resources);
    CascadeCharmTreeViewProperties viewProperties = new CascadeCharmTreeViewProperties(resources, generics,
            charmMapsByRules, selectedRuleSet);
    ICascadeView view = factory.createCascadeView(viewProperties);
    this.templateRegistry = generics.getTemplateRegistry();
    for (IExaltedRuleSet ruleSet : ExaltedRuleSet.values()) {
      charmMapsByRules.put(ruleSet, new CharmTreeIdentificateMap());
    }
    selectedRuleSet.setDelegate(AnathemaCharacterPreferences.getDefaultPreferences().getPreferredRuleset());
    CascadeCharmGroupChangeListener selectionListener = new CascadeCharmGroupChangeListener(view, viewProperties,
            filterSet, new CharmDisplayPropertiesMap(templateRegistry), selectedRuleSet.getEdition());
    setCharmTypes(new CascadeCharmTypes(generics.getTemplateRegistry(), selectedRuleSet));
    setChangeListener(selectionListener);
    setView(view);
    setCharmDye(new CascadeCharmDye(view, selectionListener));
    setRulesPresenter(
            new CascadeRulesPresenter(getResources(), view, selectedRuleSet, selectionListener, charmMapsByRules));
  }

  @Override
  protected ICharmGroup[] getCharmGroups() {
    List<ICharmGroup> allCharmGroups = new ArrayList<ICharmGroup>();
    initCharacterTypeCharms(allCharmGroups);
    initMartialArtsCharms(allCharmGroups);
    return allCharmGroups.toArray(new ICharmGroup[allCharmGroups.size()]);
  }

  private void initCharacterTypeCharms(List<ICharmGroup> allCharmGroups) {
    for (ICharacterType type : CharacterType.values()) {
      for (IExaltedEdition edition : ExaltedEdition.values()) {
        ICharacterTemplate defaultTemplate = templateRegistry.getDefaultTemplate(type, edition);
        if (defaultTemplate == null) {
          continue;
        }
        ICharmTemplate charmTemplate = defaultTemplate.getMagicTemplate().getCharmTemplate();
        if (charmTemplate.canLearnCharms(edition.getDefaultRuleset())) {
          for (IExaltedRuleSet ruleSet : ExaltedRuleSet.getRuleSetsByEdition(edition)) {
            registerTypeCharms(allCharmGroups, type, defaultTemplate, ruleSet);
            registerUniqueCharms(allCharmGroups, charmTemplate, ruleSet);
          }
        }
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
    ICharm[] charms = CharmCache.getInstance().getCharms(typeId, ruleSet);
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

  private void initMartialArtsCharms(List<ICharmGroup> allCharmGroups) {
    for (IExaltedEdition edition : ExaltedEdition.values()) {
      ICharacterTemplate template = templateRegistry.getDefaultTemplate(CharacterType.SIDEREAL, edition);
      for (IExaltedRuleSet ruleSet : ExaltedRuleSet.getRuleSetsByEdition(edition)) {
        ICharmTree martialArtsTree = new MartialArtsCharmTree(template.getMagicTemplate().getCharmTemplate(), ruleSet);
        getCharmTreeMap(ruleSet).put(MartialArtsUtilities.MARTIAL_ARTS, martialArtsTree);
        allCharmGroups.addAll(Arrays.asList(martialArtsTree.getAllCharmGroups()));
      }
    }
  }

  @Override
  protected CascadeFilterContainer getFilterContainer() {
    return new CascadeFilterContainer(selectedRuleSet);
  }

  private CharmTreeIdentificateMap getCharmTreeMap(IExaltedRuleSet ruleSet) {
    return charmMapsByRules.get(ruleSet);
  }

  @Override
  protected GroupCharmTree getCharmTree(IIdentificate type) {
    CharmTreeIdentificateMap charmTreeMap = getCharmTreeMap(selectedRuleSet.getDelegate());
    return charmTreeMap.get(type);
  }


}