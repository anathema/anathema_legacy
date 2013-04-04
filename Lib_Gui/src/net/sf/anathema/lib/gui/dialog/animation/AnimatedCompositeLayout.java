package net.sf.anathema.lib.gui.dialog.animation;

import com.google.common.base.Preconditions;

import javax.swing.JComponent;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager2;

public class AnimatedCompositeLayout implements LayoutManager2 {

  private final JComponent overlaidComponent;
  private final JComponent baseComponent;

  public AnimatedCompositeLayout(JComponent baseComponent, JComponent overlaidComponent) {
    Preconditions.checkNotNull(baseComponent);
    Preconditions.checkNotNull(overlaidComponent);
    this.baseComponent = baseComponent;
    this.overlaidComponent = overlaidComponent;
  }

  @Override
  public void addLayoutComponent(String name, Component comp) {
    if (comp != baseComponent && comp != overlaidComponent) {
      throw new IllegalArgumentException(
          "No other components may be added to an animated composite panel, tried to add " + comp);
    }
  }

  @Override
  public void addLayoutComponent(Component comp, Object constraints) {
    if (comp != baseComponent && comp != overlaidComponent) {
      throw new IllegalArgumentException(
          "No other components may be added to an animated composite panel, tried to add " + comp);
    }
  }

  @Override
  public void layoutContainer(Container parent) {
    synchronized (parent.getTreeLock()) {
      AnimatedCompositeComponent component = (AnimatedCompositeComponent) parent;
      Insets insets = parent.getInsets();
      Dimension size = parent.getSize();
      baseComponent.setBounds(
          insets.left,
          insets.top,
          size.width - (insets.left + insets.right),
          size.height - (insets.top + insets.bottom));
      int overlayPositionFromBottom = component.isOverlayVisible()
          ? component.getSize().height - component.getOverlayPosition()
          : component.getOverlayPosition();
      overlaidComponent.setBounds(
          insets.left,
          insets.top + size.height - overlayPositionFromBottom,
          size.width - (insets.left + insets.right),
          size.height - (insets.top + insets.bottom));
      //baseComponent.setVisible(!swing.isOverlayVisible());
      overlaidComponent.setVisible(overlayPositionFromBottom > 0);
      baseComponent.setVisible(overlayPositionFromBottom < size.height);
    }
  }

  @Override
  public Dimension minimumLayoutSize(Container parent) {
    synchronized (parent.getTreeLock()) {
      Insets insets = parent.getInsets();
      Dimension size1 = baseComponent.getMinimumSize();
      Dimension size2 = overlaidComponent.getMinimumSize();
      return new Dimension(
          insets.left + insets.right + Math.max(size1.width, size2.width),
          insets.top + insets.bottom + Math.max(size1.height, size2.height));
    }
  }

  @Override
  public Dimension preferredLayoutSize(Container parent) {
    synchronized (parent.getTreeLock()) {
      Insets insets = parent.getInsets();
      Dimension size1 = baseComponent.getPreferredSize();
      Dimension size2 = overlaidComponent.getPreferredSize();
      return new Dimension(
          insets.left + insets.right + Math.max(size1.width, size2.width),
          insets.top + insets.bottom + Math.max(size1.height, size2.height));
    }
  }

  @Override
  public Dimension maximumLayoutSize(Container target) {
    return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
  }

  @Override
  public float getLayoutAlignmentX(Container parent) {
    return 0.5f;
  }

  /**
   * Returns the alignment along the y axis.  This specifies how
   * the swing would like to be aligned relative to other
   * components.  The value should be a number between 0 and 1
   * where 0 represents alignment along the origin, 1 is aligned
   * the furthest away from the origin, 0.5 is centered, etc.
   */
  @Override
  public float getLayoutAlignmentY(Container parent) {
    return 0.5f;
  }

  @Override
  public void invalidateLayout(Container target) {
    //nothing to do
  }

  @Override
  public void removeLayoutComponent(Component comp) {
    //nothing to do
  }
}