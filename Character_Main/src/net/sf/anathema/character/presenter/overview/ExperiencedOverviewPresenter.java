package net.sf.anathema.character.presenter.overview;

import net.sf.anathema.character.generic.framework.additionaltemplate.listening.GlobalCharacterChangeAdapter;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.model.advance.IExperiencePointConfigurationListener;
import net.sf.anathema.character.model.advance.IExperiencePointEntry;
import net.sf.anathema.character.model.advance.IExperiencePointManagement;
import net.sf.anathema.character.view.overview.IExperienceOverviewView;
import net.sf.anathema.character.view.overview.IOverviewViewProperties;
import net.sf.anathema.lib.control.legality.LegalityColorProvider;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.labelledvalue.ILabelledAlotmentView;
import net.sf.anathema.lib.workflow.labelledvalue.ILabelledValueView;

public class ExperiencedOverviewPresenter {

  private final IExperiencePointManagement management;
  private final IExperienceOverviewView view;
  private final ICharacterStatistics statistics;

  private ILabelledValueView<Integer> attributeView;
  private ILabelledValueView<Integer> abilityView;
  private ILabelledValueView<Integer> specialtyView;
  private ILabelledValueView<Integer> charmView;
  private ILabelledValueView<Integer> comboView;
  private ILabelledValueView<Integer> spellView;
  private ILabelledValueView<Integer> virtueView;
  private ILabelledValueView<Integer> willpowerView;
  private ILabelledValueView<Integer> essenceView;
  private ILabelledAlotmentView totalView;
  private final IResources resources;
  private ILabelledValueView<Integer> miscView;

  public ExperiencedOverviewPresenter(
      IResources resources,
      final ICharacterStatistics statistics,
      IExperienceOverviewView experiencePointView,
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
    initAttributes();
    initAbilities();
    initCharms();
    initCombos();
    initSpells();
    initVirtues();
    initWillpower();
    initEssence();
    initMisc();
    initTotal();
    calculateXPCost();
    view.initGui(new IOverviewViewProperties() {
      public String getExperienceTitle() {
        return getString("Overview.Experience.Title"); //$NON-NLS-1$
      }
    });
  }

  private void initMisc() {
    miscView = view.addExperiencePointCategory(getString("Overview.MiscPointsCategory")); //$NON-NLS-1$
  }

  private void initTotal() {
    totalView = view.addTotalExperienceOverviewCategory(getString("Experience.Total")); //$NON-NLS-1$
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

  private void initEssence() {
    essenceView = view.addExperiencePointCategory(getString("Essence.Name")); //$NON-NLS-1$
  }

  private void initWillpower() {
    willpowerView = view.addExperiencePointCategory(getString("WillpowerType.Name")); //$NON-NLS-1$
  }

  private void initVirtues() {
    virtueView = view.addExperiencePointCategory(getString("Overview.VirtueCategory")); //$NON-NLS-1$
  }

  private void initSpells() {
    if (!statistics.getCharacterTemplate().getMagicTemplate().getSpellMagic().knowsSpellMagic()) {
      return;
    }
    spellView = view.addExperiencePointCategory(getString("Overview.Experience.Spells")); //$NON-NLS-1$
  }

  private void initCombos() {
    if (!statistics.getCharacterTemplate().getMagicTemplate().getCharmTemplate().knowsCharms()) {
      return;
    }
    comboView = view.addExperiencePointCategory(getString("Overview.Experience.Combos")); //$NON-NLS-1$
  }

  private void initCharms() {
    if (!statistics.getCharacterTemplate().getMagicTemplate().getCharmTemplate().knowsCharms()) {
      return;
    }
    charmView = view.addExperiencePointCategory(getString("Overview.Charms.Title")); //$NON-NLS-1$
  }

  private void initAbilities() {
    abilityView = view.addExperiencePointCategory(getString("Overview.Abilities.Title")); //$NON-NLS-1$
    specialtyView = view.addExperiencePointCategory(getString("Overview.Experience.Specialties")); //$NON-NLS-1$
  }

  private void initAttributes() {
    attributeView = view.addExperiencePointCategory(getString("Overview.Attributes.Title")); //$NON-NLS-1$
  }

  private void calculateXPCost() {
    attributeView.setValue(management.getAttributeCosts());
    abilityView.setValue(management.getAbilityCosts());
    specialtyView.setValue(management.getSpecialtyCosts());
    if (statistics.getCharacterTemplate().getMagicTemplate().getCharmTemplate().knowsCharms()) {
      charmView.setValue(management.getCharmCosts());
      comboView.setValue(management.getComboCosts());
    }
    if (statistics.getCharacterTemplate().getMagicTemplate().getSpellMagic().knowsSpellMagic()) {
      spellView.setValue(management.getSpellCosts());
    }
    virtueView.setValue(management.getVirtueCosts());
    willpowerView.setValue(management.getWillpowerCosts());
    essenceView.setValue(management.getEssenceCosts());
    miscView.setValue(management.getMiscCosts());
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