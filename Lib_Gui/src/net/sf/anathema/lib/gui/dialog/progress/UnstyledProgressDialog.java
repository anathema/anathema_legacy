package net.sf.anathema.lib.gui.dialog.progress;

import com.google.common.base.Preconditions;
import net.sf.anathema.lib.gui.swing.GuiUtilities;
import net.sf.anathema.lib.progress.IObservableCancelable;
import net.sf.anathema.lib.progress.IProgressMonitor;

import javax.swing.JDialog;
import java.awt.Color;
import java.awt.Component;

public class UnstyledProgressDialog extends AbstractProgressDialog implements IProgressMonitor, IObservableCancelable, IProgressComponent {

  private final Component parentComponent;
  private final String title;

  public UnstyledProgressDialog(Component parentComponent, String title, InternalProgressDialogModel model) {
    super(model, new SimpleProgressMonitorComponent());
    Preconditions.checkNotNull(model);
    this.parentComponent = parentComponent;
    this.title = title;
  }

  @Override
  protected JDialog createDialog() {
    JDialog newDialog = GuiUtilities.createDialog(parentComponent, title);
    newDialog.setUndecorated(true);
    makeTransparent(newDialog);
    newDialog.getContentPane().add(getContainerContent());
    newDialog.pack();
    newDialog.setResizable(false);
    newDialog.setModal(true);
    return newDialog;
  }

  private void makeTransparent(JDialog newDialog) {
    Color background = new Color(0, 0, 0, 0);
    newDialog.getContentPane().setBackground(background);
    newDialog.setBackground(background);
    newDialog.getRootPane().setOpaque(false);
  }
}