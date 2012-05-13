package net.sf.anathema.platform.tree.view.interaction;

import javax.swing.JButton;
import javax.swing.JComponent;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;

public class ButtonSpecialControl implements SpecialControl {

  private Rectangle originalBounds = new Rectangle(0, 0, 0, 15);
  private final JButton control;

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

  @Override
  public void setPosition(int x, int y) {
    originalBounds.setLocation(x, y);
  }

  @Override
  public void setWidth(int width) {
    originalBounds.setSize(width, 15);
  }

  public void whenTriggeredExecute(final Runnable actionListener) {
    control.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        actionListener.run();
      }
    });
  }
}