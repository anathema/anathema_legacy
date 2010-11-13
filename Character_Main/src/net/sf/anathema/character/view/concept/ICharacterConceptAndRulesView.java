package net.sf.anathema.character.view.concept;

import javax.swing.AbstractButton;
import javax.swing.ListCellRenderer;

import net.disy.commons.swing.action.SmartAction;
import net.sf.anathema.framework.presenter.view.IInitializableContentView;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

public interface ICharacterConceptAndRulesView extends
    IInitializableContentView<ICharacterConceptAndRulesViewProperties> {

  public <V> IObjectSelectionView<V> addObjectSelectionView(
      String labelText,
      V[] objects,
      ListCellRenderer renderer,
      boolean editable);

  public ITextView addLabelTextView(String labelText);

  public IWillpowerConditionView addWillpowerConditionView(String headerLabelText);

  public void addRulesLabel(String labelText);

  public AbstractButton addAction(SmartAction action, int row);
}