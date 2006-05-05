package net.sf.anathema.cascades.presenter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
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
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.ITemplateRegistry;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.charmtree.presenter.AbstractCascadeSelectionPresenter;
import net.sf.anathema.charmtree.presenter.view.IDocumentLoadedListener;
import net.sf.anathema.charmtree.presenter.view.IExaltTypeChangedListener;
import net.sf.anathema.framework.view.IdentificateSelectCellRenderer;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.gui.widgets.ChangeableJComboBox;
import net.sf.anathema.lib.gui.widgets.IChangeableJComboBox;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;

public class CascadePresenter extends AbstractCascadeSelectionPresenter implements ICascadePresenter {

  private CascadeCharmGroupChangeListener selectionListener;
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
    initCharacterTypeCharms(supportedCharmTypes, allCharmGroups);
    initFirstEditionMartialArts(allCharmGroups);
    // initSecondEditionMartialArts(allCharmGroups);
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

  private void initCharacterTypeCharms(List<IIdentificate> supportedCharmTypes, List<ICharmGroup> allCharmGroups) {
    for (CharacterType type : CharacterType.values()) {
      for (IExaltedEdition edition : ExaltedEdition.values()) {
        ICharacterTemplate defaultTemplate = getTemplateRegistry().getDefaultTemplate(type, edition);
        if (defaultTemplate == null) {
          continue;
        }
        if (defaultTemplate.getMagicTemplate().getCharmTemplate().knowsCharms(edition.getDefaultRuleset())) {
          for (IExaltedRuleSet ruleSet : ExaltedRuleSet.getRuleSetsByEdition(edition)) {
            CharmTree charmTree = new CharmTree(defaultTemplate.getMagicTemplate().getCharmTemplate(), ruleSet);
            getCharmTreeMap(ruleSet).put(type, charmTree);
            allCharmGroups.addAll(Arrays.asList(charmTree.getAllCharmGroups()));
          }
          supportedCharmTypes.add(type);
        }
      }
    }
  }

  private void initSecondEditionMartialArts(List<ICharmGroup> allCharmGroups) {
    ICharacterTemplate template = getTemplateRegistry().getDefaultTemplate(
        CharacterType.SOLAR,
        ExaltedEdition.SecondEdition);
    for (IExaltedRuleSet ruleSet : ExaltedRuleSet.getRuleSetsByEdition(ExaltedEdition.SecondEdition)) {
      ICharmTree martialArtsTree = new MartialArtsCharmTree(template.getMagicTemplate().getCharmTemplate(), ruleSet);
      getCharmTreeMap(ruleSet).put(MARTIAL_ARTS, martialArtsTree);
      allCharmGroups.addAll(Arrays.asList(martialArtsTree.getAllCharmGroups()));
    }
  }

  private void initFirstEditionMartialArts(List<ICharmGroup> allCharmGroups) {
    ICharacterTemplate siderealTemplate = getTemplateRegistry().getDefaultTemplate(
        CharacterType.SIDEREAL,
        ExaltedEdition.FirstEdition);
    for (IExaltedRuleSet ruleSet : ExaltedRuleSet.getRuleSetsByEdition(ExaltedEdition.FirstEdition)) {
      ICharmTree martialArtsTree = new MartialArtsCharmTree(
          siderealTemplate.getMagicTemplate().getCharmTemplate(),
          ruleSet);
      getCharmTreeMap(ruleSet).put(MARTIAL_ARTS, martialArtsTree);
      allCharmGroups.addAll(Arrays.asList(martialArtsTree.getAllCharmGroups()));
    }
  }

  private void initRules(final ICascadeView view) {
    IChangeableJComboBox<IExaltedRuleSet> rulesComboBox = new ChangeableJComboBox<IExaltedRuleSet>(
        ExaltedRuleSet.values(),
        false);
    rulesComboBox.setRenderer(new IdentificateSelectCellRenderer("Ruleset.", getResources())); //$NON-NLS-1$
    view.addRuleSetComponent(rulesComboBox.getComponent(), getResources().getString("CharmCascades.RuleSetBox.Title")); //$NON-NLS-1$
    rulesComboBox.addObjectSelectionChangedListener(new IObjectValueChangedListener() {
      public void valueChanged(Object newValue) {
        IExaltedEdition currentEdition = null;
        if (selectedRuleset != null) {
          currentEdition = selectedRuleset.getEdition();
        }
        selectionListener.setEdition(currentEdition);
        selectedRuleset = (IExaltedRuleSet) newValue;
        viewProperties.setCharmTree(getCharmTree(selectedType));
        if (selectedRuleset.getEdition() == currentEdition) {
          return;
        }
        final IIdentificate[] cascadeTypes = getCharmTreeMap(selectedRuleset).keySet().toArray(new IIdentificate[0]);
        Arrays.sort(cascadeTypes, new Comparator<IIdentificate>() {
          public int compare(IIdentificate o1, IIdentificate o2) {
            final boolean firstCharacterType = o1 instanceof CharacterType;
            final boolean secondCharacterType = o2 instanceof CharacterType;
            if (firstCharacterType) {
              if (secondCharacterType) {
                return ((CharacterType) o1).compareTo((CharacterType) o2);
              }
              return -1;
            }
            if (secondCharacterType) {
              return 1;
            }
            return 0;
          }
        });
        view.fillCharmTypeBox(cascadeTypes);
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
    this.selectedType = (IIdentificate) cascadeType;
    if (cascadeType == null) {
      view.fillCharmGroupBox(new IIdentificate[0]);
      return;
    }
    CharmTreeIdentificateMap charmTreeMap = getCharmTreeMap(selectedRuleset);
    final ICharmTree charmTree = charmTreeMap.get(selectedType);
    if (charmTree == null) {
      view.fillCharmGroupBox(new IIdentificate[0]);
      return;
    }
    ICharmGroup[] allCharmGroups = charmTree.getAllCharmGroups();
    view.fillCharmGroupBox(sortCharmGroups(allCharmGroups));
  }

  public ICharmTree getCharmTree(IIdentificate type) {
    return getCharmTreeMap(selectedRuleset).get(type);
  }
}