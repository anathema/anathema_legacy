package net.sf.anathema.hero.specialties.display.presenter;

import net.sf.anathema.character.generic.framework.ITraitReference;
import net.sf.anathema.character.presenter.ExtensibleTraitView;
import net.sf.anathema.lib.file.RelativePath;

import javax.swing.ListCellRenderer;

public interface SpecialtiesConfigurationView {

  ExtensibleTraitView addSpecialtyView(String abilityName, String specialtyName, RelativePath deleteIcon, int value, int maxValue);

  IButtonControlledComboEditView<ITraitReference> addSpecialtySelectionView(String labelText, ListCellRenderer renderer, RelativePath addIcon);
}