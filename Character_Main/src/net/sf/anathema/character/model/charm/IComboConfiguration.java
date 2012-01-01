package net.sf.anathema.character.model.charm;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.lib.control.change.IChangeListener;

public interface IComboConfiguration {

  void addCharmToCombo(ICharm charm);

  void addComboModelListener(IChangeListener listener);

  void removeCharmsFromCombo(ICharm[] charms);

  void finalizeCombo();

  void finalizeComboXP(String xpMessage);

  ICombo getEditCombo();

  void addComboConfigurationListener(IComboConfigurationListener listener);

  ICombo[] getCurrentCombos();

  boolean isComboLegal(ICharm charm);

  void deleteCombo(ICombo combo);

  void clearCombo();

  void beginComboEdit(ICombo combo);

  boolean isLearnedOnCreation(ICombo combo);

  ICombo[] getCreationCombos();

  ICombo[] getExperienceLearnedCombos();

  void finalizeCombo(boolean experienced);

  void setCrossPrerequisiteTypeComboAllowed(boolean allowed);

  boolean isAllowedToRemove(ICharm charm);

  boolean canFinalizeWithXP();
}