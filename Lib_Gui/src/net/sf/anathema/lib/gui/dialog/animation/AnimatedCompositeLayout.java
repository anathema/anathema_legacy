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

  public AnimatedCompositeLayout(final JComponent baseComponent, final JComponent overlaidComponent) {
    Preconditions.checkNotNull(baseComponent);
    Preconditions.checkNotNull(overlaidComponent);
    this.baseComponent = baseComponent;
    this.overlaidComponent = overlaidComponent;
  }

  @Override
  public void addLayoutComponent(final String name, final Component comp) {
    if (comp != baseComponent && comp != overlaidComponent) {
      throw new IllegalArgumentException(
          "No other components may be added to an animated composite panel, tried to add " + comp); //$NON-NLS-1$
    }
  }

  @Override
  public void addLayoutComponent(final Component comp, final Object constraints) {
    if (comp != baseComponent && comp != overlaidComponent) {
      throw new IllegalArgumentException(
          "No other components may be added to an animated composite panel, tried to add " + comp); //$NON-NLS-1$
    }
  }

  @Override
  public void layoutContainer(final Container parent) {
    synchronized (parent.getTreeLock()) {
      final AnimatedCompositeComponent component = (AnimatedCompositeComponent) parent;
      final Insets insets = parent.getInsets();
      final Dimension size = parent.getSize();
      baseComponent.setBounds(
          insets.left,
          insets.top,
          size.width - (insets.left + insets.right),
          size.height - (insets.top + insets.bottom));
      final int overlayPositionFromBottom = component.isOverlayVisible()
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
  public Dimension minimumLayoutSize(final Container parent) {
    synchronized (parent.getTreeLock()) {
      final Insets insets = parent.getInsets();
      final Dimension size1 = baseComponent.getMinimumSize();
      final Dimension size2 = overlaidComponent.getMinimumSize();
      return new Dimension(
          insets.left + insets.right + Math.max(size1.width, size2.width),
          insets.top + insets.bottom + Math.max(size1.height, size2.height));
    }
  }

  @Override
  public Dimension preferredLayoutSize(final Container parent) {
    synchronized (parent.getTreeLock()) {
      final Insets insets = parent.getInsets();
      final Dimension size1 = baseComponent.getPreferredSize();
      final Dimension size2 = overlaidComponent.getPreferredSize();
      return new Dimension(
          insets.left + insets.right + Math.max(size1.width, size2.width),
          insets.top + insets.bottom + Math.max(size1.height, size2.height));
    }
  }

  @Override
  public Dimension maximumLayoutSize(final Container target) {
    return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
  }

  @Override
  public float getLayoutAlignmentX(final Container parent) {
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
  public float getLayoutAlignmentY(final Container parent) {
    return 0.5f;
  }

  @Override
  public void invalidateLayout(final Container target) {
    //nothing to do
  }

  @Override
  public void removeLayoutComponent(final Component comp) {
    //nothing to do
  }
}