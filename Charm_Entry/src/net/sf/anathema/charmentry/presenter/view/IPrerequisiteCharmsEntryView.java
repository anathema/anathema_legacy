package net.sf.anathema.charmentry.presenter.view;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.lib.gui.dialog.core.IPageContent;
import net.sf.anathema.lib.workflow.container.ISelectionContainerView;

import javax.swing.JToggleButton;
import javax.swing.ListCellRenderer;

public interface IPrerequisiteCharmsEntryView extends IPageContent {

  ISelectionContainerView<ICharm> addPrerequisiteCharmView(ListCellRenderer renderer);

  JToggleButton addToggleButton(String excellencyString);

}
