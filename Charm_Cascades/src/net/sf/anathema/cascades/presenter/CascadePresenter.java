package net.sf.anathema.cascades.presenter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.anathema.cascades.module.CharmCascadeModuleView;
import net.sf.anathema.cascades.presenter.view.ICascadeView;
import net.sf.anathema.character.generic.impl.magic.charm.CharmTree;
import net.sf.anathema.character.generic.impl.magic.charm.MartialArtsCharmTree;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.magic.charms.ICharmGroup;
import net.sf.anathema.character.generic.magic.charms.ICharmTree;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.ITemplateRegistry;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.charmtree.presenter.AbstractCascadeSelectionPresenter;
import net.sf.anathema.charmtree.presenter.view.AbstractCharmGroupChangeListener;
import net.sf.anathema.charmtree.presenter.view.IDocumentLoadedListener;
import net.sf.anathema.charmtree.presenter.view.IExaltTypeChangedListener;
import net.sf.anathema.framework.view.IdentificateSelectCellRenderer;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.gui.widgets.ChangeableJComboBox;
import net.sf.anathema.lib.gui.widgets.IChangeableJComboBox;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;

public class CascadePresenter extends AbstractCascadeSelectionPresenter implements ICascadePresenter {

  private AbstractCharmGroupChangeListener selectionListener;
  private IExaltedRuleSet selectedRuleset;
  private IIdentificate selectedType;
  private final Map<IExaltedRuleSet, CharmTreeIdentificateMap> charmMapsByRules = new HashMap<IExaltedRuleSet, CharmTreeIdentificateMap>();
  private final CascadeCharmTreeViewProperties viewProperties;

  public CascadePresenter(IResources resources, ITemplateRegistry templateRegistry) {
    super(resources, templateRegistry);
    this.viewProperties = new CascadeCharmTreeViewProperties(resources);
  }

  public void initPresentation(CharmCascadeModuleView view) {
    final ICascadeView cascadeView = view.addCascadeView(viewProperties);
    this.selectionListener = new CascadeCharmGroupChangeListener(
        cascadeView,
        viewProperties,
        this,
        getTemplateRegistry());
    for (IExaltedRuleSet ruleSet : ExaltedRuleSet.values()) {
      charmMapsByRules.put(ruleSet, new CharmTreeIdentificateMap());
    }
    List<IIdentificate> supportedCharmTypes = new ArrayList<IIdentificate>();
    List<ICharmGroup> allCharmGroups = new ArrayList<ICharmGroup>();
    for (CharacterType type : CharacterType.values()) {
      ICharacterTemplate defaultTemplate = getTemplateRegistry().getDefaultTemplate(type, ExaltedEdition.FirstEdition);
      if (defaultTemplate.getMagicTemplate().getCharmTemplate().knowsCharms()) {
        for (IExaltedRuleSet ruleSet : ExaltedRuleSet.values()) {
          CharmTree charmTree = new CharmTree(defaultTemplate.getMagicTemplate().getCharmTemplate(), ruleSet);
          getCharmTreeMap(ruleSet).put(type, charmTree);
          allCharmGroups.addAll(Arrays.asList(charmTree.getAllCharmGroups()));
        }
        supportedCharmTypes.add(type);
      }
    }
    ICharacterTemplate siderealTemplate = getTemplateRegistry().getDefaultTemplate(
        CharacterType.SIDEREAL,
        ExaltedEdition.FirstEdition);
    for (IExaltedRuleSet ruleSet : ExaltedRuleSet.values()) {
      ICharmTree martialArtsTree = new MartialArtsCharmTree(
          siderealTemplate.getMagicTemplate().getCharmTemplate(),
          ruleSet);
      getCharmTreeMap(ruleSet).put(MARTIAL_ARTS, martialArtsTree);
      allCharmGroups.addAll(Arrays.asList(martialArtsTree.getAllCharmGroups()));
    }
    supportedCharmTypes.add(MARTIAL_ARTS);
    createCharmTypeSelector(
        supportedCharmTypes.toArray(new IIdentificate[supportedCharmTypes.size()]),
        cascadeView,
        "CharmTreeView.GUI.CharmType"); //$NON-NLS-1$
    createCharmGroupSelector(
        cascadeView,
        selectionListener,
        allCharmGroups.toArray(new ICharmGroup[allCharmGroups.size()]));
    initRules(cascadeView);
    initCharmTypeSelectionListening(cascadeView);
    cascadeView.addDocumentLoadedListener(new IDocumentLoadedListener() {
      public void documentLoaded() {
        selectionListener.updateColors();
      }
    });
  }

  private void initRules(ICascadeView view) {
    IChangeableJComboBox<IExaltedRuleSet> rulesComboBox = new ChangeableJComboBox<IExaltedRuleSet>(
        ExaltedRuleSet.values(),
        false);
    rulesComboBox.setRenderer(new IdentificateSelectCellRenderer("Ruleset.", getResources())); //$NON-NLS-1$
    view.addRuleSetComponent(rulesComboBox.getComponent(), getResources().getString("CharmCascades.RuleSetBox.Title")); //$NON-NLS-1$
    rulesComboBox.addObjectSelectionChangedListener(new IObjectValueChangedListener() {
      public void valueChanged(Object oldValue, Object newValue) {
        selectedRuleset = (IExaltedRuleSet) newValue;
        viewProperties.setCharmTree(getCharmTree(selectedType));
      }
    });
    rulesComboBox.setSelectedObject(ExaltedRuleSet.CoreRules);
  }

  private CharmTreeIdentificateMap getCharmTreeMap(IExaltedRuleSet ruleSet) {
    return charmMapsByRules.get(ruleSet);
  }

  private void initCharmTypeSelectionListening(final ICascadeView view) {
    view.addCharmTypeSelectionListener(new IExaltTypeChangedListener() {
      public void valueChanged(Object cascadeType) {
        handleTypeSelectionChange(cascadeType, view);
      }
    });
  }

  private void handleTypeSelectionChange(Object cascadeType, ICascadeView view) {
    ICharmGroup[] allCharmGroups;
    CharmTreeIdentificateMap charmTreeMap = getCharmTreeMap(selectedRuleset);
    selectedType = (IIdentificate) cascadeType;
    allCharmGroups = charmTreeMap.get(selectedType).getAllCharmGroups();
    view.fillCharmGroupBox(sortCharmGroups(allCharmGroups));
  }

  public ICharmTree getCharmTree(IIdentificate type) {
    return getCharmTreeMap(selectedRuleset).get(type);
  }
}