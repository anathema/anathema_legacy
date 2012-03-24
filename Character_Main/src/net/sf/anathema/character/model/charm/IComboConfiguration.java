package net.sf.anathema.character.model.charm;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.lib.control.change.IChangeListener;

public interface IComboConfiguration {

  void addCharmToCombo(ICharm charm, boolean experienced);

  void addComboModelListener(IChangeListener listener);

  void removeCharmsFromCombo(ICharm[] charms);

  void finalizeCombo();

  ICombo getEditCombo();

  void addComboConfigurationListener(IComboConfigurationListener listener);

  boolean isComboLegal(ICharm charm);

  void deleteCombo(ICombo combo);

  void clearCombo();

  void beginComboEdit(ICombo combo);

  ICombo[] getAllCombos();

  void setCrossPrerequisiteTypeComboAllowed(boolean allowed);
}