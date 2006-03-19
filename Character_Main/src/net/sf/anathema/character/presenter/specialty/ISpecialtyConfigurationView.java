package net.sf.anathema.character.presenter.specialty;

import javax.swing.Icon;
import javax.swing.ListCellRenderer;

import net.sf.anathema.character.view.ISpecialtyView;
import net.sf.anathema.character.view.basic.IButtonControlledComboEditView;

public interface ISpecialtyConfigurationView {

  public ISpecialtyView addSpecialtyView(
      String abilityName,
      String specialtyName,
      Icon deleteIcon,
      int value,
      int maxValue);

  public IButtonControlledComboEditView addSpecialtySelectionView(
      String labelText,
      Object[] abilities,
      ListCellRenderer renderer,
      Icon addIcon);
}