package net.sf.anathema.lib.gui.dialog.progress;

import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.lib.gui.dialog.widgets.AutoWrappingLabel;
import net.sf.anathema.lib.gui.message.LargeIconMessageTypeUi;
import net.sf.anathema.lib.message.MessageType;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Dimension;

public class ProgressMonitorComponent implements ProgressContainer {
  private static final String TASK_LABEL_ELLIPSIS = "..."; //$NON-NLS-1$

  private final ProgressMonitorBar progressBar;
  private final AutoWrappingLabel taskNameLabel;
  private final JLabel subTaskNameLabel;

  public ProgressMonitorComponent() {
    taskNameLabel = new AutoWrappingLabel();
    taskNameLabel.setOpaque(false);

    subTaskNameLabel = new JLabel("", SwingConstants.LEFT); //$NON-NLS-1$
    subTaskNameLabel.setPreferredSize(new Dimension(subTaskNameLabel.getPreferredSize().width, 25));

    progressBar = new ProgressMonitorBar();
    progressBar.setBorderPainted(true);

  }

  @Override
  public JComponent getContent() {
    Icon icon = new LargeIconMessageTypeUi().getIcon(MessageType.INFORMATION);
    JPanel topPanel = new JPanel(new BorderLayout(10, 8));
    topPanel.add(new JLabel(icon), BorderLayout.WEST);
    topPanel.add(taskNameLabel.getContent(), BorderLayout.CENTER);
    topPanel.setBorder(new EmptyBorder(8, 0, 0, 0));

    JPanel mainPanel = new JPanel(new MigLayout(new LC().fill().insets("5", "7", "0", "7").wrapAfter(1)));
    mainPanel.add(topPanel, new CC().growX());
    mainPanel.add(progressBar, new CC().growX());
    mainPanel.add(subTaskNameLabel, new CC().growX());
    return mainPanel;
  }

  @Override
  public final void beginTaskWithUnknownTotalWork(String name) {
    beginTask(name, UNKNOWN);
  }

  @Override
  public void beginTask(String name, final int totalWork) {
    final String taskName = addTaskLabelEllipsis(name);
    subTask(""); //$NON-NLS-1$
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        progressBar.beginTask(taskName, totalWork);
        taskNameLabel.setText(taskName);
      }
    });
  }

  public static String addTaskLabelEllipsis(String name) {
    if (name.length() == 0) {
      return name;
    }
    if (name.endsWith(TASK_LABEL_ELLIPSIS)) {
      return name;
    }
    return name + TASK_LABEL_ELLIPSIS;
  }

  @Override
  public void done() {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        progressBar.done();
      }
    });
  }

  @Override
  public void subTask(final String name) {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        subTaskNameLabel.setText(name);
      }
    });
  }

  @Override
  public void worked(int work) {
    progressBar.worked(work);
  }

  @Override
  public void setCanceled(boolean canceled) {
    throw new UnsupportedOperationException();
  }
}
