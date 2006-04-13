package net.sf.anathema.character.impl.model.advance.models;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.impl.model.advance.IPointCostCalculator;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;
import net.sf.anathema.character.presenter.overview.IValueModel;

public class SpellExperienceModel implements IValueModel<Integer> {
  private final ICharacterStatistics statistics;
  private final IPointCostCalculator calculator;
  private final IBasicCharacterData basicCharacter;
  private final ICoreTraitConfiguration traitConfiguration;

  public SpellExperienceModel(
      ICharacterStatistics statistics,
      IPointCostCalculator calculator,
      IBasicCharacterData basicCharacter,
      ICoreTraitConfiguration traitConfiguration) {
    this.statistics = statistics;
    this.calculator = calculator;
    this.basicCharacter = basicCharacter;
    this.traitConfiguration = traitConfiguration;
  }

  public Integer getValue() {
    return getSpellCosts();
  }

  public String getId() {
    return "Spells"; //$NON-NLS-1$
  }

  private int getSpellCosts() {
    int experienceCosts = 0;
    for (ISpell spell : statistics.getSpells().getLearnedSpells(true)) {
      if (!statistics.getSpells().isLearnedOnCreation(spell)) {
        experienceCosts += calculator.getSpellCosts(
            spell,
            basicCharacter,
            traitConfiguration,
            statistics.getCharacterTemplate().getMagicTemplate().getFavoringTraitType());
      }
    }
    return experienceCosts;
  }
}