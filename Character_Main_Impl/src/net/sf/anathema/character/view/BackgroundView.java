package net.sf.anathema.character.view;

import net.sf.anathema.character.library.intvalue.IRemovableTraitView;
import net.sf.anathema.framework.presenter.view.IButtonControlledComboEditView;
import net.sf.anathema.framework.presenter.view.IInitializableContentView;
import net.sf.anathema.framework.presenter.view.ITextFieldComboBoxEditor;

import javax.swing.Icon;
import javax.swing.ListCellRenderer;

public interface BackgroundView extends IInitializableContentView<BackgroundViewProperties> {
  IButtonControlledComboEditView<Object> addBackgroundSelectionView(String labelText, ListCellRenderer renderer,
                                                                    ITextFieldComboBoxEditor backgroundEditor,
                                                                    Icon addIcon);

  IRemovableTraitView< ? > addBackgroundView(Icon deleteIcon, String labelText, int creationValue, int maxValue);
}
