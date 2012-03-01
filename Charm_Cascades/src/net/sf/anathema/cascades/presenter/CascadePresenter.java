package net.sf.anathema.cascades.presenter;

import com.google.common.collect.Lists;
import net.sf.anathema.cascades.module.ICascadeViewFactory;
import net.sf.anathema.cascades.presenter.view.ICascadeView;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.configuration.AnathemaCharacterPreferences;
import net.sf.anathema.character.generic.impl.magic.MartialArtsUtilities;
import net.sf.anathema.character.generic.impl.magic.charm.CharmTree;
import net.sf.anathema.character.generic.impl.magic.charm.MartialArtsCharmTree;
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
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.presenter.charm.EssenceLevelCharmFilter;
import net.sf.anathema.character.presenter.charm.SourceBookCharmFilter;
import net.sf.anathema.charmtree.presenter.AbstractCascadePresenter;
import net.sf.anathema.charmtree.presenter.view.CharmDisplayPropertiesMap;
import net.sf.anathema.framework.view.IdentificateSelectCellRenderer;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.gui.widgets.ChangeableJComboBox;
import net.sf.anathema.lib.gui.widgets.IChangeableJComboBox;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;

import java.awt.*;
import java.util.*;
import java.util.List;

import static java.util.Arrays.sort;

public class CascadePresenter extends AbstractCascadePresenter implements ICascadePresenter {

  private CascadeCharmGroupChangeListener selectionListener;
  private IExaltedRuleSet selectedRuleset;
  private final Map<IExaltedRuleSet, CharmTreeIdentificateMap> charmMapsByRules = new HashMap<IExaltedRuleSet, CharmTreeIdentificateMap>();
  private final CascadeCharmTreeViewProperties viewProperties;
  private final ICascadeView view;
  private ITemplateRegistry templateRegistry;

  public CascadePresenter(IResources resources, ICharacterGenerics generics, ICascadeViewFactory factory) {
    super(resources);
    this.viewProperties = new CascadeCharmTreeViewProperties(resources, generics, charmMapsByRules);
    this.view = factory.createCascadeView(viewProperties);
    this.templateRegistry = generics.getTemplateRegistry();
    this.selectionListener = new CascadeCharmGroupChangeListener(view, viewProperties, templateRegistry, filterSet,
                                                                 new CharmDisplayPropertiesMap(templateRegistry));
    setView(view);
    setSpecialPresenter(new NullSpecialCharmPresenter());
  }

  @Override
  public void initPresentation() {
    initPresentationInternal();
    for (IExaltedRuleSet ruleSet : ExaltedRuleSet.values()) {
      charmMapsByRules.put(ruleSet, new CharmTreeIdentificateMap());
    }
    List<ICharmGroup> allCharmGroups = new ArrayList<ICharmGroup>();
    initCharacterTypeCharms(allCharmGroups);
    initMartialArts(allCharmGroups);
    createCharmGroupSelector(view, selectionListener, allCharmGroups.toArray(new ICharmGroup[allCharmGroups.size()]));
    initRules();
    initFilters();
    createFilterButton(view);
  }

  @Override
  protected List<IIdentificate> getCurrentCharacterTypes() {
    return Lists.<IIdentificate>newArrayList(CharacterType.values());
  }

  @Override
  protected void setCharmVisuals() {
    for (ICharm charm : selectionListener.getCurrentGroup().getAllCharms()) {
      view.setCharmVisuals(charm.getId(), Color.WHITE);
    }
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

  private void initMartialArts(List<ICharmGroup> allCharmGroups) {
    for (IExaltedEdition edition : ExaltedEdition.values()) {
      ICharacterTemplate template = templateRegistry.getDefaultTemplate(CharacterType.SIDEREAL, edition);
      for (IExaltedRuleSet ruleSet : ExaltedRuleSet.getRuleSetsByEdition(edition)) {
        ICharmTree martialArtsTree = new MartialArtsCharmTree(template.getMagicTemplate().getCharmTemplate(), ruleSet);
        getCharmTreeMap(ruleSet).put(MartialArtsUtilities.MARTIAL_ARTS, martialArtsTree);
        allCharmGroups.addAll(Arrays.asList(martialArtsTree.getAllCharmGroups()));
      }
    }
  }

  private void initRules() {
    IChangeableJComboBox<IExaltedRuleSet> rulesComboBox = new ChangeableJComboBox<IExaltedRuleSet>(ExaltedRuleSet.values(), false);
    rulesComboBox.setRenderer(new IdentificateSelectCellRenderer("Ruleset.", getResources())); //$NON-NLS-1$
    view.addRuleSetComponent(rulesComboBox.getComponent(), getResources().getString("CharmCascades.RuleSetBox.Title")); //$NON-NLS-1$
    rulesComboBox.addObjectSelectionChangedListener(new IObjectValueChangedListener<IExaltedRuleSet>() {
      @Override
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
        Set<IIdentificate> typeSet = getCharmTreeMap(selectedRuleset).keySet();
        IIdentificate[] cascadeTypes = typeSet.toArray(new IIdentificate[typeSet.size()]);
        sort(cascadeTypes, new ByCharacterType());
        view.fillCharmTypeBox(cascadeTypes);
        view.unselect();
        view.fillCharmGroupBox(new IIdentificate[0]);
      }
    });
    rulesComboBox.setSelectedObject(AnathemaCharacterPreferences.getDefaultPreferences().getPreferredRuleset());
  }

  private void initFilters() {
    SourceBookCharmFilter sourceFilter = new SourceBookCharmFilter(selectedRuleset.getEdition());
    EssenceLevelCharmFilter essenceLevelFilter = new EssenceLevelCharmFilter();
    filterSet.init(sourceFilter, essenceLevelFilter);
  }

  private CharmTreeIdentificateMap getCharmTreeMap(IExaltedRuleSet ruleSet) {
    return charmMapsByRules.get(ruleSet);
  }

  @Override
  protected GroupCharmTree getCharmTree(IIdentificate type) {
    return getCharmTreeMap(selectedRuleset).get(type);
  }

  private static class ByCharacterType implements Comparator<IIdentificate> {

    @Override
    public int compare(IIdentificate o1, IIdentificate o2) {
      boolean firstCharacterType = o1 instanceof ICharacterType;
      boolean secondCharacterType = o2 instanceof CharacterType;
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
  }
}