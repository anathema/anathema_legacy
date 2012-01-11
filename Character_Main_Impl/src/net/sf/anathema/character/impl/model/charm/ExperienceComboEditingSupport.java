package net.sf.anathema.character.impl.model.charm;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.model.advance.IExperiencePointConfiguration;
import net.sf.anathema.character.model.charm.ComboEditingRules;
import net.sf.anathema.character.model.charm.ComboLearnTime;
import net.sf.anathema.character.model.charm.ICombo;


public class ExperienceComboEditingSupport implements ComboEditingRules {
  private final ICharacterStatistics statistics;
  private final IExperiencePointConfiguration experiencePoints;
  private ICombo editCombo;
  private ComboLearnTime learnTime;
  private ICombo originalCombo;

  public ExperienceComboEditingSupport(ICharacterStatistics statistics,
                                       IExperiencePointConfiguration experiencePoints, ICombo editCombo, ComboLearnTime learnTime) {
    this.experiencePoints = experiencePoints;
    this.statistics = statistics;
    this.editCombo = editCombo;
    this.learnTime = learnTime;
  }

  public boolean isAllowedToRemove(ICharm charm) {
    return !(isEditingACombo() &&
            (!learnTime.isLearnedOnCreation(originalCombo) || isExperienced()) &&
            originalCombo.contains(charm));
  }
  
  public boolean canFinalize()
  {
	  return !learnTime.isLearnedOnCreation(originalCombo) || !isExperienced();
  }

  public boolean canFinalizeWithXP() {
    return isExperienced() && isEditingACombo() && haveCharmsBeenAddedToCombo();
  }

  public void startChanging(ICombo combo) {
    this.originalCombo = combo;
  }

  public void commitChanges(String xpMessage) {
    experiencePoints.addEntry(xpMessage, -1);
  }

  public void abortChange() {
    this.originalCombo = null;
  }

  private boolean haveCharmsBeenAddedToCombo() {
    ICombo testCombo = new Combo();
    testCombo.getValuesFrom(editCombo);
    testCombo.removeCharms(originalCombo.getCharms());
    return testCombo.getCharms().length > 0;
  }

  private boolean isEditingACombo() {
    return originalCombo != null;
  }

  private boolean isExperienced() {
    return statistics.isExperienced();
  }
}