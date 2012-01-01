package net.sf.anathema.character.impl.model.charm;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.model.advance.IExperiencePointConfiguration;
import net.sf.anathema.character.model.charm.ComboEditingRules;
import net.sf.anathema.character.model.charm.ComboLearnTime;
import net.sf.anathema.character.model.charm.ICombo;


public class ExperienceComboEditingSupport implements ComboEditingRules {
  private final IExperiencePointConfiguration experiencePoints;
  private ICombo editCombo;
  private ComboLearnTime learnTime;
  private ICombo originalCombo;

  public ExperienceComboEditingSupport(IExperiencePointConfiguration experiencePoints, ICombo editCombo, ComboLearnTime learnTime) {
    this.experiencePoints = experiencePoints;
    this.editCombo = editCombo;
    this.learnTime = learnTime;
  }

  public boolean isAllowedToRemove(ICharm charm) {
    boolean hasSomeXp = experiencePoints.getTotalExperiencePoints() != 0;
    if (isCurrentlyEditing() &&
            (!learnTime.isLearnedOnCreation(originalCombo) || hasSomeXp) &&
            originalCombo.contains(charm))
      return false;
    return true;
  }

  public boolean canFinalizeWithXP() {
    boolean hasNoXp = experiencePoints.getTotalExperiencePoints() == 0;
    if (!isCurrentlyEditing() || hasNoXp) {
      return false;
    }
    ICombo testCombo = new Combo();
    testCombo.getValuesFrom(editCombo);
    testCombo.removeCharms(originalCombo.getCharms());
    return testCombo.getCharms().length > 0;
  }

  private boolean isCurrentlyEditing() {
    return originalCombo != null;
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
}