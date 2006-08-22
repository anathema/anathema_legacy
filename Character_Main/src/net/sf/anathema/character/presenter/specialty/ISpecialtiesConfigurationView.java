package net.sf.anathema.character.presenter.specialty;

import javax.swing.Icon;
import javax.swing.ListCellRenderer;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.view.ISpecialtyView;
import net.sf.anathema.character.view.basic.IButtonControlledComboEditView;

public interface ISpecialtiesConfigurationView {

  public ISpecialtyView addSpecialtyView(
      String abilityName,
      String specialtyName,
      Icon deleteIcon,
      int value,
      int maxValue);

  public IButtonControlledComboEditView<ITraitType> addSpecialtySelectionView(
      String labelText,
      ITraitType[] traitTypes,
      ListCellRenderer renderer,
      Icon addIcon);
}