package net.sf.anathema.character.impl.model.advance;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.impl.model.advance.models.AbilityExperienceModel;
import net.sf.anathema.character.impl.model.advance.models.AttributeExperienceModel;
import net.sf.anathema.character.impl.model.advance.models.CharmExperienceModel;
import net.sf.anathema.character.impl.model.advance.models.EssenceExperienceModel;
import net.sf.anathema.character.impl.model.advance.models.MiscellaneousExperienceModel;
import net.sf.anathema.character.impl.model.advance.models.SpecialtyExperienceModel;
import net.sf.anathema.character.impl.model.advance.models.SpellExperienceModel;
import net.sf.anathema.character.impl.model.advance.models.VirtueExperienceModel;
import net.sf.anathema.character.impl.model.advance.models.WillpowerExperienceModel;
import net.sf.anathema.character.main.abilities.AbilityModelFetcher;
import net.sf.anathema.character.main.traits.model.TraitMap;
import net.sf.anathema.character.main.traits.model.TraitModelFetcher;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.model.advance.IExperiencePointManagement;
import net.sf.anathema.character.presenter.overview.IValueModel;

public class ExperiencePointManagement implements IExperiencePointManagement {

  private final IBasicCharacterData basicCharacter;
  private final IPointCostCalculator calculator;
  private final ICharacter character;
  private final TraitMap traitMap;

  public ExperiencePointManagement(ICharacter character) {
    this.character = character;
    this.basicCharacter = character.getCharacterContext().getBasicCharacterContext();
    this.traitMap = TraitModelFetcher.fetch(character);
    this.calculator = new ExperiencePointCostCalculator(character.getHeroTemplate().getExperienceCost());
  }

  private IValueModel<Integer> getAbilityModel() {
    return new AbilityExperienceModel(traitMap, calculator, character);
  }

  @Override
  @SuppressWarnings("unchecked")
  public IValueModel<Integer>[] getAllModels() {
    return new IValueModel[]{getAttributeModel(), getAbilityModel(), getSpecialtyModel(), getCharmModel(), getSpellModel(), getVirtueModel(),
            getWillpowerModel(), getEssenceModel(), getMiscModel()};
  }

  private IValueModel<Integer> getAttributeModel() {
    return new AttributeExperienceModel(traitMap, calculator, character);
  }

  private IValueModel<Integer> getCharmModel() {
    return new CharmExperienceModel(traitMap, calculator, character, basicCharacter);
  }

  private IValueModel<Integer> getEssenceModel() {
    return new EssenceExperienceModel(traitMap, calculator);
  }

  @Override
  public int getMiscGain() {
    int total = 0;
    for (IAdditionalModel model : character.getExtendedConfiguration().getAdditionalModels()) {
      total += model.getExperienceCalculator().calculateGain();
    }
    return total;
  }

  private IValueModel<Integer> getMiscModel() {
    return new MiscellaneousExperienceModel(character);
  }

  private IValueModel<Integer> getSpecialtyModel() {
    return new SpecialtyExperienceModel(AbilityModelFetcher.fetch(character), calculator);
  }

  private IValueModel<Integer> getSpellModel() {
    return new SpellExperienceModel(character, calculator, basicCharacter, traitMap);
  }

  @Override
  public int getTotalCosts() {
    int experienceCosts = 0;
    experienceCosts += getAbilityModel().getValue();
    experienceCosts += getAttributeModel().getValue();
    experienceCosts += getCharmModel().getValue();
    experienceCosts += getEssenceModel().getValue();
    experienceCosts += getSpecialtyModel().getValue();
    experienceCosts += getSpellModel().getValue();
    experienceCosts += getVirtueModel().getValue();
    experienceCosts += getWillpowerModel().getValue();
    experienceCosts += getMiscModel().getValue();
    return experienceCosts;
  }

  private IValueModel<Integer> getVirtueModel() {
    return new VirtueExperienceModel(traitMap, calculator);
  }

  private IValueModel<Integer> getWillpowerModel() {
    return new WillpowerExperienceModel(traitMap, calculator);
  }

  @Override
  public String toString() {
    return "Abilities: "
           + getAbilityModel().getValue() + "\nAttributes: "
           + getAttributeModel().getValue() + "\nCharms: "
           + getCharmModel().getValue() + "\nEssence: "
           + getEssenceModel().getValue() + "\nSpecialties: "
           + getSpecialtyModel().getValue() + "\nSpells: "
           + getSpellModel().getValue() + "\nVirtues: "
           + getVirtueModel().getValue() + "\nWillpower: "
           + getWillpowerModel().getValue() + "\nMisc: "
           + getMiscModel().getValue() + "\nOverall: " + getTotalCosts();
  }
}