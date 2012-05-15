package net.sf.anathema.character.presenter.specialty;

import net.sf.anathema.character.generic.framework.ITraitReference;
import net.sf.anathema.character.view.ISpecialtyView;
import net.sf.anathema.framework.presenter.view.IButtonControlledComboEditView;

import javax.swing.Icon;
import javax.swing.ListCellRenderer;

public interface ISpecialtiesConfigurationView {

  ISpecialtyView addSpecialtyView(String abilityName, String specialtyName, Icon deleteIcon, int value, int maxValue);

  IButtonControlledComboEditView<ITraitReference> addSpecialtySelectionView(String labelText, ListCellRenderer renderer, Icon addIcon);
}