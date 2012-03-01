package net.sf.anathema.cascades.presenter;

import net.sf.anathema.cascades.module.ICascadeViewFactory;
import net.sf.anathema.cascades.presenter.view.ICascadeView;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.configuration.AnathemaCharacterPreferences;
import net.sf.anathema.character.generic.impl.magic.MartialArtsUtilities;
import net.sf.anathema.character.generic.impl.magic.charm.CharmTree;
import net.sf.anathema.character.generic.impl.magic.charm.MartialArtsCharmTree;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.magic.charms.GroupCharmTree;
import net.sf.anathema.character.generic.magic.charms.ICharmGroup;
import net.sf.anathema.character.generic.magic.charms.ICharmTree;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.ITemplateRegistry;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.charmtree.presenter.AbstractCascadePresenter;
import net.sf.anathema.charmtree.presenter.view.CharmDisplayPropertiesMap;
import net.sf.anathema.framework.view.IdentificateSelectCellRenderer;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;

import java.util.*;

public class CascadePresenter extends AbstractCascadePresenter implements ICascadePresenter {

  private CascadeCharmGroupChangeListener selectionListener;
  private ProxyRuleSet selectedRuleSet = new ProxyRuleSet();
  private final Map<IExaltedRuleSet, CharmTreeIdentificateMap> charmMapsByRules = new HashMap<IExaltedRuleSet, CharmTreeIdentificateMap>();
  private final ICascadeView view;
  private ITemplateRegistry templateRegistry;

  public CascadePresenter(IResources resources, ICharacterGenerics generics, ICascadeViewFactory factory) {
    super(resources, new CascadeCharmTypes());
    CascadeCharmTreeViewProperties viewProperties = new CascadeCharmTreeViewProperties(resources, generics, charmMapsByRules, selectedRuleSet);
    this.view = factory.createCascadeView(viewProperties);
    this.templateRegistry = generics.getTemplateRegistry();
    this.selectionListener = new CascadeCharmGroupChangeListener(view, viewProperties, filterSet, new CharmDisplayPropertiesMap(templateRegistry));
    for (IExaltedRuleSet ruleSet : ExaltedRuleSet.values()) {
      charmMapsByRules.put(ruleSet, new CharmTreeIdentificateMap());
    }
    selectedRuleSet.setDelegate(AnathemaCharacterPreferences.getDefaultPreferences().getPreferredRuleset());
    setChangeListener(selectionListener);
    setView(view);
    setSpecialPresenter(new NullSpecialCharmPresenter());
    setCharmGroupInformer(selectionListener);
    setCharmDye(new CascadeCharmDye(view));
    setAlienCharmPresenter(new NullAlienCharmPresenter());
  }

  @Override
  public void initPresentation() {
    super.initPresentation();
    initRulesSelection();
    view.initGui();
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
        if (defaultTemplate.getMagicTemplate().getCharmTemplate().canLearnCharms(edition.getDefaultRuleset())) {
          for (IExaltedRuleSet ruleSet : ExaltedRuleSet.getRuleSetsByEdition(edition)) {
            CharmTree charmTree = new CharmTree(defaultTemplate.getMagicTemplate().getCharmTemplate(), ruleSet);
            ICharmGroup[] groups = charmTree.getAllCharmGroups();
            if (groups.length != 0) {
              getCharmTreeMap(ruleSet).put(type, charmTree);
              allCharmGroups.addAll(Arrays.asList(groups));
            }
          }
        }
      }
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

  private void initRulesSelection() {
    IdentificateSelectCellRenderer renderer = new IdentificateSelectCellRenderer("Ruleset.", getResources());
    String title = getResources().getString("CharmCascades.RuleSetBox.Title");
    view.addRuleSetComponent(ExaltedRuleSet.values(), renderer, title);
    view.addRuleChangeListener(new RulesChangedListener(selectedRuleSet, view, selectionListener, charmMapsByRules));
    view.selectRules(selectedRuleSet.getDelegate());
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