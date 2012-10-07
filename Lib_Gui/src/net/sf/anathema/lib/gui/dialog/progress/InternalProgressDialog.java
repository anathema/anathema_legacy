package net.sf.anathema.lib.gui.dialog.progress;

import com.google.common.base.Preconditions;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.gui.dialog.DialogMessages;
import net.sf.anathema.lib.gui.layout.ButtonPanelBuilder;
import net.sf.anathema.lib.gui.swing.GuiUtilities;
import net.sf.anathema.lib.progress.IObservableCancelable;
import net.sf.anathema.lib.progress.IProgressMonitor;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class InternalProgressDialog extends AbstractProgressDialog implements IProgressMonitor, IObservableCancelable, IProgressComponent {

  private final SmartAction cancelAction;
  private boolean cancelable = false;
  private final Component parentComponent;
  private final String title;

  public InternalProgressDialog(Component parentComponent, String title, InternalProgressDialogModel model) {
    super(model, new ProgressMonitorComponent());
    Preconditions.checkNotNull(model);
    this.parentComponent = parentComponent;
    this.title = title;
    cancelAction = new SmartAction(DialogMessages.CANCEL) {
      @Override
      protected void execute(Component parent) {
        performCancel();
      }
    };
  }

  private void performCancel() {
    if (cancelable) {
      setCanceled(true);
      cancelAction.setEnabled(false);
    }
    yield();
  }

  public void setCancelable(boolean cancelable) {
    this.cancelable = cancelable;
    cancelAction.setEnabled(cancelable);
  }

  @Override
  protected JDialog createDialog() {
    JButton cancelButton = new JButton(cancelAction);
    ButtonPanelBuilder builder = new ButtonPanelBuilder();
    builder.add(cancelButton);

    JDialog newDialog = GuiUtilities.createDialog(parentComponent, title);
    newDialog.getContentPane().setLayout(new BorderLayout(2, 2));
    newDialog.getContentPane().add(getContainerContent(), BorderLayout.CENTER);

    newDialog.getContentPane().add(builder.createPanel(), BorderLayout.SOUTH);
    newDialog.pack();

    newDialog.setSize(Math.max(newDialog.getWidth(), 450), newDialog.getHeight());

    newDialog.setResizable(false);
    newDialog.setModal(true);
    newDialog.getRootPane().setDefaultButton(cancelButton);
    newDialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

    newDialog.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        performCancel();
      }

      @Override
      public void windowClosed(WindowEvent e) {
        synchronized (InternalProgressDialog.this) {
          setDialogClosed(true);
        }
      }
    });
    GuiUtilities.centerToParent(newDialog);
    return newDialog;
  }


}