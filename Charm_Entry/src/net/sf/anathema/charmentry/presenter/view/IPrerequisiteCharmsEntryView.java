package net.sf.anathema.charmentry.presenter.view;

import javax.swing.JToggleButton;
import javax.swing.ListCellRenderer;

import net.disy.commons.swing.dialog.core.IPageContent;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.lib.workflow.container.ISelectionContainerView;

public interface IPrerequisiteCharmsEntryView extends IPageContent {

  public ISelectionContainerView<ICharm> addPrerequisiteCharmView(ListCellRenderer renderer);

  public JToggleButton addToggleButton(String excellencyString);

}
