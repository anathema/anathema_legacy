package net.sf.anathema.lib.gui.dialog.progress;

import org.jdesktop.swingx.JXBusyLabel;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.RoundRectangle2D;

public class SimpleProgressMonitorComponent implements ProgressContainer {

  private JXBusyLabel label;

  public SimpleProgressMonitorComponent() {
    label = new JXBusyLabel(new Dimension(100, 100));
    label.getBusyPainter().setHighlightColor(new Color(247, 234, 130));
    label.getBusyPainter().setBaseColor(new Color(166, 166, 166));
    label.getBusyPainter().setPoints(8);
    label.getBusyPainter().setTrailLength(4);
    RoundRectangle2D shape = new RoundRectangle2D.Float(0, 0, 30, 10, 10, 10);
    label.getBusyPainter().setPointShape(shape);
    label.setBusy(true);
  }

  @Override
  public JComponent getContent() {
    JPanel mainPanel = new JPanel();
    mainPanel.setOpaque(false);
    mainPanel.add(label);
    mainPanel.setBorder(new EmptyBorder(5, 7, 0, 7));
    return mainPanel;
  }

  @Override
  public final void beginTaskWithUnknownTotalWork(String name) {
    //nothing to do
  }

  @Override
  public void beginTask(String name, final int totalWork) {
    //nothing to do
  }

  @Override
  public void done() {
    //nothing to do
  }

  @Override
  public void subTask(final String name) {
    //nothing to do
  }

  @Override
  public void worked(int work) {
    //nothing to do
  }

  @Override
  public void setCanceled(boolean canceled) {
    //nothing to do
  }
}
