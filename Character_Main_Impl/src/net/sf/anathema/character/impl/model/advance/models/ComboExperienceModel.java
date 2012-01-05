package net.sf.anathema.character.impl.model.advance.models;

import net.sf.anathema.character.impl.model.advance.IPointCostCalculator;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.model.charm.ICombo;

public class ComboExperienceModel extends AbstractIntegerValueModel {
  private final ICharacterStatistics statistics;
  private final IPointCostCalculator calculator;

  public ComboExperienceModel(ICharacterStatistics statistics, IPointCostCalculator calculator) {
    super("Experience", "Combos"); //$NON-NLS-1$ //$NON-NLS-2$
    this.statistics = statistics;
    this.calculator = calculator;
  }

  public Integer getValue() {
    return getComboCosts();
  }

  private int getComboCosts() {
    int experienceCosts = 0;
    for (ICombo combo : statistics.getCombos().getExperienceLearnedCombos()) {
      experienceCosts += calculator.getComboCosts(combo.getCharms());
    }
    return experienceCosts;
  }

  @Override
  public boolean equals(Object obj) {
    return super.equals(obj);
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }
}
