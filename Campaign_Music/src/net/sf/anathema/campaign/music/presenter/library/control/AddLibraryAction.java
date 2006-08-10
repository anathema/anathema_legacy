package net.sf.anathema.campaign.music.presenter.library.control;

import java.awt.Component;

import net.disy.commons.swing.action.SmartAction;
import net.sf.anathema.campaign.music.model.libary.ILibraryControl;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.lib.resources.IResources;

public class AddLibraryAction extends SmartAction {

  private final ILibraryControl libraryModel;
  private final String unknownLibraryBase;

  public AddLibraryAction(ILibraryControl libraryModel, IResources resources) {
    super(new BasicUi(resources).getAddIcon());
    setToolTipText(resources.getString("Music.Actions.AddLibrary.Tooltip")); //$NON-NLS-1$
    unknownLibraryBase = resources.getString("Music.Libraries.NewLibrary.UnnamedBaseString"); //$NON-NLS-1$
    this.libraryModel = libraryModel;
  }

  @Override
  protected void execute(Component parentComponent) {
    libraryModel.addNewUnnamedLibrary(unknownLibraryBase);
  }
}