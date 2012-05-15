/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.sf.anathema.lib.gui.dialog.progress;

import net.disy.commons.core.progress.IProgressMonitor;
import net.disy.commons.swing.component.IComponentContainer;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.message.LargeIconMessageTypeUi;
import net.sf.anathema.lib.gui.dialog.widgets.AutoWrappingLabel;
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

public class ProgressMonitorComponent implements IProgressMonitor, IComponentContainer {
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
    final Icon icon = new LargeIconMessageTypeUi().getIcon(MessageType.INFORMATION);
    final JPanel topPanel = new JPanel(new BorderLayout(10, 8));
    topPanel.add(new JLabel(icon), BorderLayout.WEST);
    topPanel.add(taskNameLabel.getContent(), BorderLayout.CENTER);
    topPanel.setBorder(new EmptyBorder(8, 0, 0, 0));

    final JPanel mainPanel = new JPanel(new GridDialogLayout(1, false));
    mainPanel.add(topPanel, GridDialogLayoutData.FILL_HORIZONTAL);
    mainPanel.add(progressBar, GridDialogLayoutData.FILL_HORIZONTAL);
    mainPanel.add(subTaskNameLabel, GridDialogLayoutData.FILL_HORIZONTAL);
    mainPanel.setBorder(new EmptyBorder(5, 7, 0, 7));
    return mainPanel;
  }

  @Override
  public final void beginTaskWithUnknownTotalWork(final String name) {
    beginTask(name, UNKNOWN);
  }

  @Override
  public void beginTask(final String name, final int totalWork) {
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

  public static String addTaskLabelEllipsis(final String name) {
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
  public void worked(final int work) {
    progressBar.worked(work);
  }

  @Override
  public void setCanceled(final boolean canceled) {
    throw new UnsupportedOperationException();
  }
}
