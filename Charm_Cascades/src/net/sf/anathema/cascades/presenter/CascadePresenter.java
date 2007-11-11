package net.sf.anathema.cascades.presenter;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.anathema.cascades.module.ICascadeViewFactory;
import net.sf.anathema.cascades.presenter.view.ICascadeView;
import net.sf.anathema.character.generic.framework.configuration.AnathemaCharacterPreferences;
import net.sf.anathema.character.generic.impl.magic.MartialArtsUtilities;
import net.sf.anathema.character.generic.impl.magic.charm.CharmTree;
import net.sf.anathema.character.generic.impl.magic.charm.MartialArtsCharmTree;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmGroup;
import net.sf.anathema.character.generic.magic.charms.ICharmTree;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.ITemplateRegistry;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.charmtree.presenter.AbstractCascadeSelectionPresenter;
import net.sf.anathema.framework.view.IdentificateSelectCellRenderer;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.gui.widgets.ChangeableJComboBox;
import net.sf.anathema.lib.gui.widgets.IChangeableJComboBox;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.platform.svgtree.presenter.view.IDocumentLoadedListener;

public class CascadePresenter extends AbstractCascadeSelectionPresenter implements ICascadePresenter {

  private CascadeCharmGroupChangeListener selectionListener;
  private IExaltedRuleSet selectedRuleset;
  private IIdentificate selectedType;
  private final Map<IExaltedRuleSet, CharmTreeIdentificateMap> charmMapsByRules = new HashMap<IExaltedRuleSet, CharmTreeIdentificateMap>();
  private final CascadeCharmTreeViewProperties viewProperties;
  private final ICascadeView view;

  public CascadePresenter(IResources resources, ITemplateRegistry templateRegistry, ICascadeViewFactory factory) {
    super(resources, templateRegistry);
    this.viewProperties = new CascadeCharmTreeViewProperties(resources, charmMapsByRules);
    this.view = factory.createCascadeView(viewProperties);
  }

  public void initPresentation() {
    for (IExaltedRuleSet ruleSet : ExaltedRuleSet.values()) {
      charmMapsByRules.put(ruleSet, new CharmTreeIdentificateMap());
    }
    List<IIdentificate> supportedCharmTypes = new ArrayList<IIdentificate>();
    List<ICharmGroup> allCharmGroups = new ArrayList<ICharmGroup>();
    initCharacterTypeCharms(supportedCharmTypes, allCharmGroups);
    initMartialArts(supportedCharmTypes, allCharmGroups);
    createCharmTypeSelector(
        supportedCharmTypes.toArray(new IIdentificate[supportedCharmTypes.size()]),
        view,
        "CharmTreeView.GUI.CharmType"); //$NON-NLS-1$
    this.selectionListener = new CascadeCharmGroupChangeListener(view, viewProperties, getTemplateRegistry());
    createCharmGroupSelector(view, selectionListener, allCharmGroups.toArray(new ICharmGroup[allCharmGroups.size()]));
    initRules();
    initCharmTypeSelectionListening();
    view.addDocumentLoadedListener(new IDocumentLoadedListener() {
      public void documentLoaded() {
        for (ICharm charm : selectionListener.getCurrentGroup().getAllCharms()) {
          view.setCharmVisuals(charm.getId(), Color.WHITE);
        }
      }
    });
  }

  private void initCharacterTypeCharms(List<IIdentificate> supportedCharmTypes, List<ICharmGroup> allCharmGroups) {
    for (ICharacterType type : CharacterType.values()) {
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

  private void initMartialArts(List<IIdentificate> supportedCharmTypes, List<ICharmGroup> allCharmGroups) {
    for (IExaltedEdition edition : ExaltedEdition.values()) {
      ICharacterTemplate template = getTemplateRegistry().getDefaultTemplate(CharacterType.SIDEREAL, edition);
      for (IExaltedRuleSet ruleSet : ExaltedRuleSet.getRuleSetsByEdition(edition)) {
        ICharmTree martialArtsTree = new MartialArtsCharmTree(template.getMagicTemplate().getCharmTemplate(), ruleSet);
        getCharmTreeMap(ruleSet).put(MartialArtsUtilities.MARTIAL_ARTS, martialArtsTree);
        allCharmGroups.addAll(Arrays.asList(martialArtsTree.getAllCharmGroups()));
      }
    }
    supportedCharmTypes.add(MartialArtsUtilities.MARTIAL_ARTS);
  }

  private void initRules() {
    IChangeableJComboBox<IExaltedRuleSet> rulesComboBox = new ChangeableJComboBox<IExaltedRuleSet>(
        ExaltedRuleSet.values(),
        false);
    rulesComboBox.setRenderer(new IdentificateSelectCellRenderer("Ruleset.", getResources())); //$NON-NLS-1$
    view.addRuleSetComponent(rulesComboBox.getComponent(), getResources().getString("CharmCascades.RuleSetBox.Title")); //$NON-NLS-1$
    rulesComboBox.addObjectSelectionChangedListener(new IObjectValueChangedListener<IExaltedRuleSet>() {
      public void valueChanged(IExaltedRuleSet newValue) {
        IExaltedEdition currentEdition = null;
        if (selectedRuleset != null) {
          currentEdition = selectedRuleset.getEdition();
        }
        selectedRuleset = newValue;
        viewProperties.setRules(selectedRuleset);
        if (selectedRuleset.getEdition() == currentEdition) {
          return;
        }
        selectionListener.setEdition(selectedRuleset.getEdition());
        final IIdentificate[] cascadeTypes = getCharmTreeMap(selectedRuleset).keySet().toArray(new IIdentificate[0]);
        // TODO: Mach mich schön.
        Arrays.sort(cascadeTypes, new Comparator<IIdentificate>() {
          public int compare(IIdentificate o1, IIdentificate o2) {
            final boolean firstCharacterType = o1 instanceof ICharacterType;
            final boolean secondCharacterType = o2 instanceof CharacterType;
            if (firstCharacterType) {
              if (secondCharacterType) {
                return ((ICharacterType) o1).compareTo((CharacterType) o2);
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
        view.unselect();
        view.fillCharmGroupBox(new IIdentificate[0]);
      }
    });
    rulesComboBox.setSelectedObject(AnathemaCharacterPreferences.getDefaultPreferences().getPreferredRuleset());
  }

  private CharmTreeIdentificateMap getCharmTreeMap(IExaltedRuleSet ruleSet) {
    return charmMapsByRules.get(ruleSet);
  }

  private void initCharmTypeSelectionListening() {
    view.addCharmTypeSelectionListener(new IObjectValueChangedListener<IIdentificate>() {
      public void valueChanged(IIdentificate cascadeType) {
        handleTypeSelectionChange(cascadeType);
      }
    });
  }

  private void handleTypeSelectionChange(IIdentificate cascadeType) {
    this.selectedType = cascadeType;
    if (cascadeType == null) {
      view.fillCharmGroupBox(new IIdentificate[0]);
      return;
    }
    final ICharmTree charmTree = getCharmTree(selectedType);
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