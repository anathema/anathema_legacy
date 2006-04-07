package net.sf.anathema.character.view;

import javax.swing.ComboBoxEditor;
import javax.swing.Icon;
import javax.swing.ListCellRenderer;

import net.sf.anathema.character.library.intvalue.IRemovableTraitView;
import net.sf.anathema.framework.presenter.view.IObjectSelectionView;
import net.sf.anathema.framework.presenter.view.ITabView;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.lib.workflow.labelledvalue.ILabelledValueView;

public interface IBasicAdvantageView extends ITabView<IAdvantageViewProperties> {

  public IIntValueView addVirtue(String labelText, int value, int maxValue);

  public IIntValueView addWillpower(String labelText, int value, int maxValue);

  public IObjectSelectionView addBackgroundSelectionView(
      String labelText,
      Object[] selectionObjects,
      ComboBoxEditor editor,
      ListCellRenderer renderer,
      Icon addIcon);

  public IRemovableTraitView addBackgroundView(Icon deleteIcon, String labelText, int creationValue, int maxValue);

  public abstract IIntValueView addEssenceView(String labelText, int value, int maxValue);

  public ILabelledValueView<String> addPoolView(String labelText, String value);

  public void setBackgroundPanelEnabled(boolean enabled);
}