package net.sf.anathema.character.impl.model.advance;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.impl.model.advance.models.AbilityExperienceModel;
import net.sf.anathema.character.impl.model.advance.models.AttributeExperienceModel;
import net.sf.anathema.character.impl.model.advance.models.BackgroundExperienceModel;
import net.sf.anathema.character.impl.model.advance.models.CharmExperienceModel;
import net.sf.anathema.character.impl.model.advance.models.ComboExperienceModel;
import net.sf.anathema.character.impl.model.advance.models.EssenceExperienceModel;
import net.sf.anathema.character.impl.model.advance.models.MiscellaneousExperienceModel;
import net.sf.anathema.character.impl.model.advance.models.SpecialtyExperienceModel;
import net.sf.anathema.character.impl.model.advance.models.SpellExperienceModel;
import net.sf.anathema.character.impl.model.advance.models.VirtueExperienceModel;
import net.sf.anathema.character.impl.model.advance.models.WillpowerExperienceModel;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.model.advance.IExperiencePointManagement;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;
import net.sf.anathema.character.presenter.overview.IValueModel;

public class ExperiencePointManagement implements IExperiencePointManagement {

  private final IBasicCharacterData basicCharacter;
  private final IPointCostCalculator calculator;
  private final ICharacterStatistics statistics;
  private final ICoreTraitConfiguration traitConfiguration;

  public ExperiencePointManagement(ICharacterStatistics statistics) {
    this.statistics = statistics;
    this.basicCharacter = statistics.getCharacterContext().getBasicCharacterContext();
    this.traitConfiguration = statistics.getTraitConfiguration();
    this.calculator = new ExperiencePointCostCalculator(statistics.getCharacterTemplate().getExperienceCost());
  }

  private IValueModel<Integer> getAbilityModel() {
    return new AbilityExperienceModel(traitConfiguration, calculator, statistics);
  }

  @SuppressWarnings("unchecked")
  public IValueModel<Integer>[] getAllModels() {
    return new IValueModel[] {
        getAttributeModel(),
        getAbilityModel(),
        getSpecialtyModel(),
        getCharmModel(),
        getComboModel(),
        getSpellModel(),
        getVirtueModel(),
        getWillpowerModel(),
        getEssenceModel(),
        getBackgroundModel(),
        getMiscModel() };
  }

  private IValueModel<Integer> getAttributeModel() {
    return new AttributeExperienceModel(traitConfiguration, calculator);
  }

  private IValueModel<Integer> getBackgroundModel() {
    return new BackgroundExperienceModel(traitConfiguration,statistics.getCharacterTemplate().getExperienceCost());
  }

  private IValueModel<Integer> getCharmModel() {
    return new CharmExperienceModel(traitConfiguration, calculator, statistics, basicCharacter);
  }

  private IValueModel<Integer> getComboModel() {
    return new ComboExperienceModel(statistics, calculator);
  }

  private IValueModel<Integer> getEssenceModel() {
    return new EssenceExperienceModel(traitConfiguration, calculator);
  }

  public int getMiscGain() {
    int total = 0;
    for (IAdditionalModel model : statistics.getExtendedConfiguration().getAdditionalModels()) {
      total += model.getExperienceCalculator().calculateGain();
    }
    return total;
  }

  private IValueModel<Integer> getMiscModel() {
    return new MiscellaneousExperienceModel(statistics);
  }

  private IValueModel<Integer> getSpecialtyModel() {
    return new SpecialtyExperienceModel(traitConfiguration, calculator);
  }

  private IValueModel<Integer> getSpellModel() {
    return new SpellExperienceModel(statistics, calculator, basicCharacter, traitConfiguration);
  }

  public int getTotalCosts() {
    int experienceCosts = 0;
    experienceCosts += getAbilityModel().getValue();
    experienceCosts += getAttributeModel().getValue();
    experienceCosts += getBackgroundModel().getValue();
    experienceCosts += getCharmModel().getValue();
    experienceCosts += getComboModel().getValue();
    experienceCosts += getEssenceModel().getValue();
    experienceCosts += getSpecialtyModel().getValue();
    experienceCosts += getSpellModel().getValue();
    experienceCosts += getVirtueModel().getValue();
    experienceCosts += getWillpowerModel().getValue();
    experienceCosts += getMiscModel().getValue();
    return experienceCosts;
  }

  private IValueModel<Integer> getVirtueModel() {
    return new VirtueExperienceModel(traitConfiguration, calculator);
  }

  private IValueModel<Integer> getWillpowerModel() {
    return new WillpowerExperienceModel(traitConfiguration, calculator);
  }

  @Override
  public String toString() {
    return "Abilities: " //$NON-NLS-1$
        + getAbilityModel().getValue()
        + "\nAttributes: " //$NON-NLS-1$
        + getAttributeModel().getValue()
        + "\nCharms: " //$NON-NLS-1$
        + getCharmModel().getValue()
        + "\nCombo:" //$NON-NLS-1$
        + getComboModel().getValue()
        + "\nEssence: " //$NON-NLS-1$
        + getEssenceModel().getValue()
        + "\nSpecialties: " //$NON-NLS-1$
        + getSpecialtyModel().getValue()
        + "\nSpells: " //$NON-NLS-1$
        + getSpellModel().getValue()
        + "\nVirtues: " //$NON-NLS-1$
        + getVirtueModel().getValue()
        + "\nWillpower: " //$NON-NLS-1$
        + getWillpowerModel().getValue()
        + "\nMisc: " //$NON-NLS-1$
        + getMiscModel().getValue()
        + "\nOverall: " + getTotalCosts(); //$NON-NLS-1$
  }
}