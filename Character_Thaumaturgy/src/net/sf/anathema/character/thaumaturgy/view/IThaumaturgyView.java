package net.sf.anathema.character.thaumaturgy.view;

import javax.swing.Icon;
import javax.swing.ListCellRenderer;

import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;
import net.sf.anathema.character.thaumaturgy.model.ThaumaturgyMagicType;
import net.sf.anathema.framework.presenter.view.IButtonControlledObjectSelectionView;
import net.sf.anathema.framework.presenter.view.ITextFieldComboBoxEditor;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;

public interface IThaumaturgyView {

  public IThaumaturgyMagicView addMagicView(
      String artName,
      String procedureName,
      Icon deleteIcon,
      int value,
      int maxValue);
  
  public void setCurrentMode(ThaumaturgyMagicType type);
  
  public void addModeListener(final String[] values, final String value, final IObjectValueChangedListener<String> listener);
  
  public IButtonControlledObjectSelectionView<String> addArtSelectionView(
	      String labelText,
	      ITextFieldComboBoxEditor editor,
	      ListCellRenderer renderer,
	      Icon addIcon);

  public IProcedureEditView<String> addProcedureSelectionView(
	  IDefaultTrait control,
      ListCellRenderer renderer,
      ITextFieldComboBoxEditor artEditor,
      ITextFieldComboBoxEditor procedureEditor,
      Icon addIcon);
  
  public void clear();
}