package net.sf.anathema.character.view;

import javax.swing.ComboBoxEditor;
import javax.swing.Icon;
import javax.swing.ListCellRenderer;

import net.sf.anathema.character.library.intvalue.IRemovableTraitView;
import net.sf.anathema.framework.presenter.view.IButtonControlledObjectSelectionView;
import net.sf.anathema.framework.presenter.view.ITabView;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.lib.workflow.labelledvalue.IValueView;

public interface IBasicAdvantageView extends ITabView<IAdvantageViewProperties> {

  public IIntValueView addVirtue(String labelText, int value, int maxValue);

  public IIntValueView addWillpower(String labelText, int value, int maxValue);

  public IButtonControlledObjectSelectionView addBackgroundSelectionView(
      String labelText,
      ComboBoxEditor editor,
      ListCellRenderer renderer,
      Icon addIcon);

  public IRemovableTraitView addBackgroundView(Icon deleteIcon, String labelText, int creationValue, int maxValue);

  public abstract IIntValueView addEssenceView(String labelText, int value, int maxValue);

  public IValueView<String> addPoolView(String labelText, String value);

  public void setBackgroundPanelEnabled(boolean enabled);
}