package net.sf.anathema.character.impl.model.advance;

import net.sf.anathema.character.generic.additionaltemplate.HeroModelExperienceCalculator;
import net.sf.anathema.character.impl.model.advance.models.AbilityExperienceModel;
import net.sf.anathema.character.impl.model.advance.models.AttributeExperienceModel;
import net.sf.anathema.character.impl.model.advance.models.CharmExperienceModel;
import net.sf.anathema.character.impl.model.advance.models.EssenceExperienceModel;
import net.sf.anathema.character.impl.model.advance.models.MiscellaneousExperienceModel;
import net.sf.anathema.character.impl.model.advance.models.SpellExperienceModel;
import net.sf.anathema.character.impl.model.advance.models.VirtueExperienceModel;
import net.sf.anathema.character.impl.model.advance.models.WillpowerExperienceModel;
import net.sf.anathema.character.main.model.traits.TraitMap;
import net.sf.anathema.character.main.model.traits.TraitModelFetcher;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.model.advance.IExperiencePointManagement;
import net.sf.anathema.character.presenter.overview.IValueModel;
import net.sf.anathema.hero.points.PointModelFetcher;

import java.util.ArrayList;
import java.util.List;

public class ExperiencePointManagement implements IExperiencePointManagement {

  private final IPointCostCalculator calculator;
  private final ICharacter character;
  private final TraitMap traitMap;

  public ExperiencePointManagement(ICharacter character) {
    this.character = character;
    this.traitMap = TraitModelFetcher.fetch(character);
    this.calculator = new ExperiencePointCostCalculator(character.getTemplate().getExperienceCost());
  }

  private IValueModel<Integer> getAbilityModel() {
    return new AbilityExperienceModel(traitMap, calculator, character);
  }

  @Override
  public List<IValueModel<Integer>> getAllModels() {
    final List<IValueModel<Integer>> allModels = new ArrayList<>();
    // todo (sandra): Sorting
    allModels.add(getAbilityModel());
    allModels.add(getAttributeModel());
    allModels.add(getCharmModel());
    allModels.add(getEssenceModel());
    allModels.add(getSpellModel());
    allModels.add(getVirtueModel());
    allModels.add(getWillpowerModel());
    allModels.add(getMiscModel());
    for (IValueModel<Integer>  model : PointModelFetcher.fetch(character).getExperienceOverviewModels()) {
      allModels.add(model);
    }
    return allModels;
  }

  private IValueModel<Integer> getAttributeModel() {
    return new AttributeExperienceModel(traitMap, calculator, character);
  }

  private IValueModel<Integer> getCharmModel() {
    return new CharmExperienceModel(traitMap, calculator, character);
  }

  private IValueModel<Integer> getEssenceModel() {
    return new EssenceExperienceModel(traitMap, calculator);
  }

  @Override
  public int getMiscGain() {
    int total = 0;
    for (HeroModelExperienceCalculator experienceCalculator : PointModelFetcher.fetch(character).getExperienceCalculators()) {
      total += experienceCalculator.calculateGain();
    }
    return total;
  }

  private IValueModel<Integer> getMiscModel() {
    return new MiscellaneousExperienceModel(character);
  }

  private IValueModel<Integer> getSpellModel() {
    return new SpellExperienceModel(character, calculator, traitMap);
  }

  @Override
  public int getTotalCosts() {
    int experienceCosts = 0;
    for (IValueModel<Integer> model : getAllModels()) {
      experienceCosts += model.getValue();
    }
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
    StringBuffer stringBuffer = new StringBuffer();
    for (IValueModel<Integer> model : getAllModels()) {
      stringBuffer.append(model.getCategoryId());
      stringBuffer.append(": ");
      stringBuffer.append(model.getValue());
    }
    stringBuffer.append("Overall: ");
    stringBuffer.append(getTotalCosts());
    return stringBuffer.toString();
  }
}