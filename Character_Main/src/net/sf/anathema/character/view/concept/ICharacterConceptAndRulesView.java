package net.sf.anathema.character.view.concept;

import net.sf.anathema.framework.presenter.view.IInitializableContentView;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

import javax.swing.ListCellRenderer;

public interface ICharacterConceptAndRulesView extends IInitializableContentView<ICharacterConceptAndRulesViewProperties> {

  <V> IObjectSelectionView<V> addObjectSelectionView(String labelText, V[] objects, ListCellRenderer renderer, boolean editable);

  ITextView addLabelTextView(String labelText);

  void addRulesLabel(String labelText);

  Tool addTool();
}