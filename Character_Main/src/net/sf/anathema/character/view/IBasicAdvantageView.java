package net.sf.anathema.character.view;

import net.sf.anathema.character.library.intvalue.IRemovableTraitView;
import net.sf.anathema.character.library.trait.IModifiableCapTrait;
import net.sf.anathema.framework.presenter.view.IButtonControlledComboEditView;
import net.sf.anathema.framework.presenter.view.IInitializableContentView;
import net.sf.anathema.framework.presenter.view.ITextFieldComboBoxEditor;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.lib.workflow.labelledvalue.IValueView;

import javax.swing.Icon;
import javax.swing.ListCellRenderer;

public interface IBasicAdvantageView extends IInitializableContentView<IAdvantageViewProperties> {

  IIntValueView addVirtue(String labelText, int value, int maxValue);

  IIntValueView addWillpower(String labelText, int value, int maxValue);

  IButtonControlledComboEditView<Object> addBackgroundSelectionView(String labelText, ListCellRenderer renderer,
                                                                    ITextFieldComboBoxEditor backgroundEditor, Icon addIcon);

  IRemovableTraitView< ? > addBackgroundView(Icon deleteIcon, String labelText, int creationValue, int maxValue);

  IIntValueView addEssenceView(String labelText, int value, int maxValue, IModifiableCapTrait trait);

  IValueView<String> addPoolView(String labelText, String value);

  void setBackgroundPanelEnabled(boolean enabled);
}