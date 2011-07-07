package net.sf.anathema.character.view;

import javax.swing.Icon;
import javax.swing.ListCellRenderer;

import net.sf.anathema.character.library.intvalue.IRemovableTraitView;
import net.sf.anathema.character.library.trait.IModifiableCapTrait;
import net.sf.anathema.framework.presenter.view.IButtonControlledComboEditView;
import net.sf.anathema.framework.presenter.view.IInitializableContentView;
import net.sf.anathema.framework.presenter.view.ITextFieldComboBoxEditor;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.lib.workflow.labelledvalue.IValueView;

public interface IBasicAdvantageView extends IInitializableContentView<IAdvantageViewProperties> {

  public IIntValueView addVirtue(String labelText, int value, int maxValue);

  public IIntValueView addWillpower(String labelText, int value, int maxValue);

  public IButtonControlledComboEditView<Object> addBackgroundSelectionView(
	      String labelText,
	      ListCellRenderer renderer,
	      ITextFieldComboBoxEditor backgroundEditor,
	      Icon addIcon);

  public IRemovableTraitView< ? > addBackgroundView(Icon deleteIcon, String labelText, int creationValue, int maxValue);

  public abstract IIntValueView addEssenceView(String labelText, int value, int maxValue, IModifiableCapTrait trait);

  public IValueView<String> addPoolView(String labelText, String value);

  public void setBackgroundPanelEnabled(boolean enabled);
}