package net.sf.anathema.character.impl.model.advance.models;

import net.sf.anathema.character.impl.model.advance.IPointCostCalculator;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.model.charm.ICombo;
import net.sf.anathema.character.presenter.overview.IValueModel;

public class ComboExperienceModel implements IValueModel<Integer> {
  private final ICharacterStatistics statistics;
  private final IPointCostCalculator calculator;

  public ComboExperienceModel(ICharacterStatistics statistics, IPointCostCalculator calculator) {
    this.statistics = statistics;
    this.calculator = calculator;
  }

  public Integer getValue() {
    return getComboCosts();
  }

  public String getId() {
    return "Combos"; //$NON-NLS-1$
  }

  private int getComboCosts() {
    int experienceCosts = 0;
    for (ICombo combo : statistics.getCombos().getExperienceLearnedCombos()) {
      experienceCosts += calculator.getComboCosts(combo.getCharms());
    }
    return experienceCosts;
  }
}
