package net.sf.anathema.lib.gui.dialog.progress;

import com.google.common.base.Preconditions;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.lib.gui.swing.GuiUtilities;
import net.sf.anathema.lib.progress.IObservableCancelable;
import net.sf.anathema.lib.progress.IProgressMonitor;
import org.jdesktop.swingx.JXLabel;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Insets;

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
    JDialog newDialog = GuiUtilities.createRawDialogForParentComponent(parentComponent);
    newDialog.setTitle(title);
    newDialog.setUndecorated(true);
    createContent(newDialog);
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

  private void createContent(JDialog newDialog) {
    JPanel panel = new JPanel(new MigLayout(new LC().fill().wrapAfter(1), new AC().align("center"),
            new AC().align("center").grow().fill()));
    JComponent containerContent = getContainerContent();
    panel.add(containerContent, new CC().pos("0.5al", "0.5al"));
    JXLabel textDisplay = new JXLabel(title);
    textDisplay.setLineWrap(true);
    textDisplay.setFont(textDisplay.getFont().deriveFont(Font.BOLD).deriveFont(30f));
    textDisplay.setTextAlignment(JXLabel.TextAlignment.CENTER);
    panel.add(textDisplay, new CC().dockSouth().gapBottom("20"));
    panel.setOpaque(false);
    newDialog.getContentPane().add(panel);
  }

  private void makeMilky(JDialog newDialog) {
    Color background = new Color(255, 255, 255, 130);
    configureBackground(newDialog, background);
  }

  private void coverParentContainer(JDialog newDialog) {
    Insets insets = ((Container) parentComponent).getInsets();
    newDialog.setSize(parentComponent.getWidth() - insets.left - insets.right,
            parentComponent.getHeight() - insets.top - insets.bottom);
    newDialog.setLocation(parentComponent.getX() + insets.left, parentComponent.getY() + insets.top);
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