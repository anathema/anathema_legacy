package net.sf.anathema.character.model.charm;

import net.sf.anathema.character.generic.magic.ICharm;

public interface IComboConfiguration {

  public void addCharmToCombo(ICharm charm);

  public void addComboModelListener(IComboModelListener listener);

  public void removeCharmsFromCombo(ICharm[] charms);

  public void finalizeCombo();

  public ICombo getEditCombo();

  public void addComboConfigurationListener(IComboConfigurationListener listener);

  public ICombo[] getCurrentCombos();

  public boolean isComboLegal(ICharm charm);

  public void deleteCombo(ICombo combo);

  public void clearCombo();

  public void beginComboEdit(ICombo combo);

  public boolean isLearnedOnCreation(ICombo combo);

  public ICombo[] getCreationCombos();

  public ICombo[] getExperienceLearnedCombos();

  public void finalizeCombo(boolean experienced);

  public void setCrossPrerequisiteTypeComboAllowed(boolean allowed);
}