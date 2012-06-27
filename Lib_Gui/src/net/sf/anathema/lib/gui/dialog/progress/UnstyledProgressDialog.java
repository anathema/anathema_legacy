package net.sf.anathema.lib.gui.dialog.progress;

import com.google.common.base.Preconditions;
import net.sf.anathema.lib.gui.swing.GuiUtilities;
import net.sf.anathema.lib.progress.IObservableCancelable;
import net.sf.anathema.lib.progress.IProgressMonitor;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;

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
    JPanel panel = new JPanel(new GridBagLayout());
    panel.add(getContainerContent(), new GridBagConstraints());
    panel.setOpaque(false);
    newDialog.getContentPane().add(panel);
    newDialog.pack();
    if (parentComponent instanceof Container) {
      coverParentContainer(newDialog);
      makeMilky(newDialog);
    } else {
      GuiUtilities.centerToParent(newDialog);
      makeTransparent(newDialog);
    }
    newDialog.setResizable(false);
    newDialog.setModal(true);
    return newDialog;
  }

  private void makeMilky(JDialog newDialog) {
    Color background = new Color(255, 255, 255, 130);
    configureBackground(newDialog, background);
  }

  private void coverParentContainer(JDialog newDialog) {
    Insets insets = ((Container) parentComponent).getInsets();
    newDialog.setSize(parentComponent.getWidth() - insets.left - insets.right,
            parentComponent.getHeight() - insets.top - insets.bottom);
    Point location = newDialog.getLocation();
    newDialog.setLocation(parentComponent.getLocationOnScreen().x + location.x + insets.left,
            parentComponent.getLocationOnScreen().x + location.y + insets.top);
  }

  private void makeTransparent(JDialog newDialog) {
    Color background = new Color(0, 0, 0, 0);
    configureBackground(newDialog, background);
  }

  private void configureBackground(JDialog newDialog, Color background) {
    newDialog.getContentPane().setBackground(background);
    newDialog.setBackground(background);
    newDialog.getRootPane().setOpaque(false);
  }
}