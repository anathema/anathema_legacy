package net.sf.anathema.character.presenter.overview;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.framework.additionaltemplate.listening.GlobalCharacterChangeAdapter;
import net.sf.anathema.character.library.overview.IOverviewCategory;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.model.advance.IExperiencePointConfigurationListener;
import net.sf.anathema.character.model.advance.IExperiencePointEntry;
import net.sf.anathema.character.model.advance.IExperiencePointManagement;
import net.sf.anathema.character.view.overview.IOverviewView;
import net.sf.anathema.lib.control.legality.LegalityColorProvider;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.labelledvalue.ILabelledAlotmentView;
import net.sf.anathema.lib.workflow.labelledvalue.IValueView;

public class ExperiencedOverviewPresenter {

  private final IExperiencePointManagement management;
  private final IOverviewView view;
  private final ICharacterStatistics statistics;
  private final IResources resources;
  private final List<IOverviewSubPresenter> presenters = new ArrayList<IOverviewSubPresenter>();

  private IValueView<Integer> attributeView;
  private IValueView<Integer> abilityView;
  private IValueView<Integer> specialtyView;
  private IValueView<Integer> charmView;
  private IValueView<Integer> comboView;
  private IValueView<Integer> spellView;
  private IValueView<Integer> virtueView;
  private IValueView<Integer> willpowerView;
  private IValueView<Integer> essenceView;
  private ILabelledAlotmentView totalView;
  private IValueView<Integer> miscView;

  public ExperiencedOverviewPresenter(
      IResources resources,
      final ICharacterStatistics statistics,
      IOverviewView experiencePointView,
      IExperiencePointManagement management) {
    this.resources = resources;
    this.statistics = statistics;
    statistics.getCharacterContext().getCharacterListening().addChangeListener(new GlobalCharacterChangeAdapter() {
      @Override
      public void characterChanged() {
        if (statistics.isExperienced()) {
          calculateXPCost();
        }
      }
    });
    this.management = management;
    this.view = experiencePointView;
  }

  public void init() {
    IOverviewCategory category = view.addOverviewCategory(getString("Overview.Experience.Title")); //$NON-NLS-1$
    initAttributes(category);
    initAbilities(category);
    initCharms(category);
    initCombos(category);
    initSpells(category);
    initVirtues(category);
    initWillpower(category);
    initEssence(category);
    initMisc(category);
    initTotal(category);
    calculateXPCost();
    view.initGui();
  }

  private void initMisc(IOverviewCategory category) {
    miscView = category.addIntegerValueView(getString("Overview.MiscPointsCategory"), 2); //$NON-NLS-1$
    presenters.add(new ValueSubPresenter(management.getMiscModel(), miscView));
  }

  private void initTotal(IOverviewCategory category) {
    totalView = category.addAlotmentView(getString("Experience.Total"), 4); //$NON-NLS-1$
    statistics.getExperiencePoints().addExperiencePointConfigurationListener(
        new IExperiencePointConfigurationListener() {
          public void entryAdded(IExperiencePointEntry entry) {
            setAlotment();
          }

          public void entryRemoved(IExperiencePointEntry entry) {
            setAlotment();
          }

          public void entryChanged(IExperiencePointEntry entry) {
            setAlotment();
          }
        });
    setAlotment();
  }

  private void setAlotment() {
    totalView.setAlotment(getTotalXP());
    setTotalViewColor();
  }

  private int getTotalXP() {
    return statistics.getExperiencePoints().getTotalExperiencePoints() + management.getMiscGain();
  }

  private void initEssence(IOverviewCategory category) {
    essenceView = category.addIntegerValueView(getString("Essence.Name"), 2); //$NON-NLS-1$
    presenters.add(new ValueSubPresenter(management.getEssenceModel(), essenceView));
  }

  private void initWillpower(IOverviewCategory category) {
    willpowerView = category.addIntegerValueView(getString("WillpowerType.Name"), 2); //$NON-NLS-1$
    presenters.add(new ValueSubPresenter(management.getWillpowerModel(), willpowerView));
  }

  private void initVirtues(IOverviewCategory category) {
    virtueView = category.addIntegerValueView(getString("Overview.VirtueCategory"), 2); //$NON-NLS-1$
    presenters.add(new ValueSubPresenter(management.getVirtueModel(), virtueView));
  }

  private void initSpells(IOverviewCategory category) {
    if (!statistics.getCharacterTemplate().getMagicTemplate().getSpellMagic().knowsSpellMagic()) {
      return;
    }
    spellView = category.addIntegerValueView(getString("Overview.Experience.Spells"), 2); //$NON-NLS-1$
    presenters.add(new ValueSubPresenter(management.getSpellModel(), spellView));
  }

  private void initCombos(IOverviewCategory category) {
    if (!statistics.getCharacterTemplate().getMagicTemplate().getCharmTemplate().knowsCharms()) {
      return;
    }
    comboView = category.addIntegerValueView(getString("Overview.Experience.Combos"), 2); //$NON-NLS-1$
    presenters.add(new ValueSubPresenter(management.getComboModel(), comboView));
  }

  private void initCharms(IOverviewCategory category) {
    if (!statistics.getCharacterTemplate().getMagicTemplate().getCharmTemplate().knowsCharms()) {
      return;
    }
    charmView = category.addIntegerValueView(getString("Overview.Charms.Title"), 2); //$NON-NLS-1$
    presenters.add(new ValueSubPresenter(management.getCharmModel(), charmView));
  }

  private void initAbilities(IOverviewCategory category) {
    abilityView = category.addIntegerValueView(getString("Overview.Abilities.Title"), 2); //$NON-NLS-1$
    presenters.add(new ValueSubPresenter(management.getAbilityModel(), abilityView));
    specialtyView = category.addIntegerValueView(getString("Overview.Experience.Specialties"), 2); //$NON-NLS-1$
    presenters.add(new ValueSubPresenter(management.getSpecialtyModel(), specialtyView));
  }

  private void initAttributes(IOverviewCategory category) {
    attributeView = category.addIntegerValueView(getString("Overview.Attributes.Title"), 2); //$NON-NLS-1$
    presenters.add(new ValueSubPresenter(management.getAttributeModel(), attributeView));
  }

  private void calculateXPCost() {
    for (IOverviewSubPresenter presenter:presenters) {
      presenter.update();
    }
    setAlotment();
    totalView.setValue(management.getTotalCosts());
    setTotalViewColor();
  }

  private void setTotalViewColor() {
    boolean overspent = management.getTotalCosts() > getTotalXP();
    totalView.setTextColor(overspent ? LegalityColorProvider.COLOR_HIGH : LegalityColorProvider.COLOR_OKAY);
  }

  private String getString(String string) {
    return resources.getString(string);
  }
}