package net.sf.anathema.character.view.concept;

import javax.swing.ListCellRenderer;
import javax.swing.text.JTextComponent;

import net.sf.anathema.framework.presenter.view.ITabView;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

public interface ICharacterConceptAndRulesView extends ITabView<ICharacterConceptAndRulesViewProperties> {

  public IObjectSelectionView addConceptObjectSelectionView(
      String labelText,
      Object[] objects,
      ListCellRenderer renderer,
      boolean editable);

  public ITextView addLabelTextView(String labelText);

  public void setEnabled(boolean enabled);

  public JTextComponent addWillpowerConditionView(String headerLabelText);

  public void addRulesLabel(String labelText);
}