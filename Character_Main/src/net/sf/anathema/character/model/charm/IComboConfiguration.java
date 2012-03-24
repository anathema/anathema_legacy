package net.sf.anathema.character.model.charm;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.lib.control.change.IChangeListener;

public interface IComboConfiguration extends ComboLearnTime {

  void addCharmToCombo(ICharm charm, boolean experienced);

  void addComboModelListener(IChangeListener listener);

  void removeCharmsFromCombo(ICharm[] charms);

  void finalizeCombo();

  ICombo getEditCombo();

  void addComboConfigurationListener(IComboConfigurationListener listener);

  ICombo[] getCurrentCombos();

  boolean isComboLegal(ICharm charm);

  void deleteCombo(ICombo combo);

  void clearCombo();

  @Override
  public boolean isLearnedOnCreation(ICombo combo);

  void beginComboEdit(ICombo combo);

  ICombo[] getCreationCombos();

  ICombo[] getExperienceLearnedCombos();

  void finalizeCombo(boolean experienced);

  void setCrossPrerequisiteTypeComboAllowed(boolean allowed);
}