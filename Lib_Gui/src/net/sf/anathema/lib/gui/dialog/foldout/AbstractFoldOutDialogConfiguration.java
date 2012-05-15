package net.sf.anathema.lib.gui.dialog.foldout;

import net.sf.anathema.lib.gui.action.ActionConfiguration;
import net.sf.anathema.lib.gui.action.IActionConfiguration;
import net.sf.anathema.lib.gui.dialog.DialogMessages;
import net.sf.anathema.lib.gui.dialog.userdialog.DefaultDialogConfiguration;
import net.sf.anathema.lib.gui.dialog.userdialog.buttons.IDialogButtonConfiguration;
import net.sf.anathema.lib.gui.dialog.userdialog.page.IDialogPage;

public abstract class AbstractFoldOutDialogConfiguration<P extends IDialogPage> extends DefaultDialogConfiguration<P> implements
        IFoldOutDialogConfiguration<P> {

  private final IFoldOutPage foldOutPage;

  public AbstractFoldOutDialogConfiguration(P dialogPage, IFoldOutPage foldOutPage, IDialogButtonConfiguration buttonConfiguration) {
    super(dialogPage, buttonConfiguration);
    this.foldOutPage = foldOutPage;
  }

  @Override
  public IActionConfiguration getFoldOutButtonConfiguration() {
    String label = DialogMessages.getString("FoldOutDialog.Button.showDetails.text"); //$NON-NLS-1$
    return new ActionConfiguration(label);
  }

  @Override
  public IActionConfiguration getFoldInButtonConfiguration() {
    String label = DialogMessages.getString("FoldOutDialog.Button.hideDetails.text"); //$NON-NLS-1$
    return new ActionConfiguration(label);
  }

  @Override
  public IFoldOutPage getFoldOutPage() {
    return foldOutPage;
  }

  @Override
  public boolean isInitiallyFoldedOut() {
    return false;
  }
}