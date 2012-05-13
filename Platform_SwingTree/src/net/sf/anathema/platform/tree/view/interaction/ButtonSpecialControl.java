package net.sf.anathema.platform.tree.view.interaction;

import javax.swing.JButton;
import javax.swing.JComponent;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

public class ButtonSpecialControl implements SpecialControl {

  private Rectangle originalBounds = new Rectangle(200, 200, 180, 15);
  private final JComponent control;

  public ButtonSpecialControl(String title) {
    this.control = new JButton(title);
  }

  @Override
  public void transformThrough(AffineTransform transform) {
    Shape transformedShape = transform.createTransformedShape(originalBounds);
    Rectangle transformedShapeBounds = transformedShape.getBounds();
    control.setBounds(transformedShapeBounds);
  }

  @Override
  public void addTo(JComponent parent) {
    parent.add(control);
  }

  @Override
  public void remove(JComponent parent) {
    parent.remove(control);
  }

  @Override
  public void transformOriginalCoordinates(AffineTransform transform) {
    this.originalBounds = transform.createTransformedShape(originalBounds).getBounds();
    control.setBounds(originalBounds);
  }
}