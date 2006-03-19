package net.sf.anathema.lib.gui.container;

import java.awt.Component;
import java.awt.Graphics;

import javax.swing.JSplitPane;

/**
 * LazilyDividingSplitPane merkt sich einen Aufruf von
 * setDividerLocation(double) bis die paint Routine das erste Mal
 * aufgerufen worden ist. Das Standardverhalten ist, einen Aufruf von
 * setDividerLocation zu ignorieren, wenn das SplitPane nicht
 * dargestellt wird.
 */
public class LazilyDividingSplitPane extends JSplitPane {
  private boolean isPainted = false;
  private boolean hasProportionalLocation = false;
  private double proportionalLocation;

  public LazilyDividingSplitPane() {
    super();
  }

  public LazilyDividingSplitPane(int newOrientation) {
    super(newOrientation);
  }

  public LazilyDividingSplitPane(int newOrientation, boolean newContinuousLayout) {
    super(newOrientation, newContinuousLayout);
  }

  public LazilyDividingSplitPane(
    int newOrientation,
    boolean newContinuousLayout,
    Component newLeftComponent,
    Component newRightComponent) {
    super(newOrientation, newContinuousLayout, newLeftComponent, newRightComponent);
  }

  public LazilyDividingSplitPane(
    int newOrientation,
    Component newLeftComponent,
    Component newRightComponent) {
    super(newOrientation, newLeftComponent, newRightComponent);
  }

  @Override
  public void setDividerLocation(double proportionalLocation) {
    if (!isPainted) {
      hasProportionalLocation = true;
      this.proportionalLocation = proportionalLocation;
    }
    else
      super.setDividerLocation(proportionalLocation);
  }

  @Override
  public void paint(Graphics g) {
    if (!isPainted) {
      if (hasProportionalLocation) {
        super.setDividerLocation(proportionalLocation);
      }
      isPainted = true;
    }
    super.paint(g);
  }
}