package net.sf.anathema.campaign.music.presenter.selection;

import java.awt.Component;

import net.disy.commons.swing.action.SmartAction;
import net.sf.anathema.campaign.music.model.selection.IMusicSelectionModel;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.lib.resources.IResources;

public class NewSelectionAction extends SmartAction {

  private static final long serialVersionUID = -8213743358757844742L;
  private final IMusicSelectionModel selectionModel;
  private final String unnamedSelectionBase;

  public NewSelectionAction(IResources resources, IMusicSelectionModel selectionModel) {
    super(new BasicUi(resources).getAddIcon());
    this.selectionModel = selectionModel;
    setToolTipText(resources.getString("Music.Actions.NewSelection.Tooltip")); //$NON-NLS-1$
    unnamedSelectionBase = resources.getString("Music.Selections.NewSelection.UnnamedBaseString"); //$NON-NLS-1$
  }

  @Override
  protected void execute(Component parentComponent) {
    selectionModel.addNewSelection(unnamedSelectionBase);
  }
}