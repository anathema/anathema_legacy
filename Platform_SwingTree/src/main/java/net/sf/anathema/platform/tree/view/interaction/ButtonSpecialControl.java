package net.sf.anathema.platform.tree.view.interaction;

import net.sf.anathema.platform.tree.display.transform.AgnosticTransform;
import net.sf.anathema.platform.tree.view.transform.SwingTransformer;

import javax.swing.JComponent;
import javax.swing.JToggleButton;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonSpecialControl implements SpecialControl {

  private Rectangle originalBounds = new Rectangle(0, 0, 0, 15);
  private final JToggleButton button;
  private final SpecialContentMap specialContent;

  public ButtonSpecialControl(String title, SpecialContentMap specialContent) {
    this.specialContent = specialContent;
    this.button = new JToggleButton(title);
  }

  @Override
  public void transformThrough(AgnosticTransform transform) {
    Shape transformedShape = SwingTransformer.convert(transform).createTransformedShape(originalBounds);
    Rectangle transformedShapeBounds = transformedShape.getBounds();
    button.setBounds(transformedShapeBounds);
  }

  @Override
  public void addTo(JComponent parent) {
    parent.add(button);
  }

  @Override
  public void remove(JComponent parent) {
    parent.remove(button);
  }

  @Override
  public void transformOriginalCoordinates(AgnosticTransform transform) {
    Shape transformedShape = SwingTransformer.convert(transform).createTransformedShape(originalBounds);
    this.originalBounds = transformedShape.getBounds();
    button.setBounds(originalBounds);
  }

  @Override
  public void setPosition(int x, int y) {
    originalBounds.setLocation(x, y);
  }

  @Override
  public void setWidth(int width) {
    originalBounds.setSize(width, 15);
  }

  public void whenTriggeredShow(final net.sf.anathema.platform.tree.display.SpecialControl specialControl) {
    final PopupSpecialCharmContainer container = new PopupSpecialCharmContainer(button, specialContent);
    specialControl.showIn(container);
    button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (button.isSelected()) {
          container.display();
        }
        else{
          container.hide();
        }
      }
    });
  }
}