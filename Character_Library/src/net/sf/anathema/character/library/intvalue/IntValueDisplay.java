package net.sf.anathema.character.library.intvalue;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.sf.anathema.framework.value.AbstractMarkerPanel;
import net.sf.anathema.framework.value.IIntValueDisplay;
import net.sf.anathema.framework.value.NoMarkerPanel;
import net.sf.anathema.framework.value.RectangleMarkerPanel;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;
import net.sf.anathema.lib.control.intvalue.IntValueControl;

public class IntValueDisplay implements IIntValueDisplay {

  public static IIntValueDisplay createMarkerDisplay(Icon passiveIcon, Icon activeIcon, int maxValue) {
    return new IntValueDisplay(passiveIcon, activeIcon, maxValue, new RectangleMarkerPanel());
  }

  public static IIntValueDisplay createMarkerLessDisplay(Icon passiveIcon, Icon activeIcon, int maxValue) {
    return new IntValueDisplay(passiveIcon, activeIcon, maxValue, new NoMarkerPanel());
  }

  private final IntValueControl valueControl = new IntValueControl();
  private final AbstractMarkerPanel panel;
  private final Icon activeImage;
  private final Icon passiveImage;
  private final List<JLabel> imageList = new ArrayList<JLabel>();
  private final MouseInputListener mouseListener = new MouseInputAdapter() {
    @Override
    public void mouseClicked(MouseEvent e) {
      updateIcons(e.getPoint().getX());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
      updateIcons(e.getPoint().getX());
      updateMarkerRectangle(e.getX());
    }

    @Override
    public void mousePressed(MouseEvent e) {
      updateMarkerRectangle(e.getX());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
      updateMarkerRectangle(0);
    }
  };

  private IntValueDisplay(Icon passiveIcon, Icon activeIcon, int maxValue, AbstractMarkerPanel panel) {
    this.activeImage = activeIcon;
    this.passiveImage = passiveIcon;
    this.panel = panel;
    panel.setLayout(new GridDialogLayout(maxValue + maxValue / 5, false, 2, 0));
    initializeLabels(maxValue);
    panel.addMouseListener(mouseListener);
    panel.addMouseMotionListener(mouseListener);
  }

  private void initializeLabels(int maximumValue) {
    int previousMaximumValue = imageList.size();
    for (int imageIndex = previousMaximumValue; imageIndex < maximumValue; imageIndex++) {
      JLabel imageLabel = new JLabel(passiveImage);
      panel.add(imageLabel);
      imageList.add(imageLabel);
      if ((imageIndex + 1) % 5 == 0 && imageIndex + 1 < maximumValue) {
        JLabel label = new JLabel();
        Dimension labelDimension = new Dimension(passiveImage.getIconWidth() / 2, passiveImage.getIconHeight());
        label.setMinimumSize(labelDimension);
        label.setPreferredSize(labelDimension);
        label.setMaximumSize(labelDimension);
        panel.add(label);
      }
    }
    for (int index = previousMaximumValue; index > maximumValue; index--) {
      JLabel label = imageList.remove(index - 1);
      panel.remove(label);
    }
    panel.revalidate();
  }

  public void addIntValueChangedListener(IIntValueChangedListener listener) {
    valueControl.addIntValueChangeListener(listener);
  }

  public void removeIntValueChangedListener(IIntValueChangedListener listener) {
    valueControl.removeIntValueChangeListener(listener);
  }

  private void updateIcons(double xPosition) {
    if (xPosition < activeImage.getIconWidth() / 3) {
      fireValueChangedEvent(0);
      return;
    }
    if (xPosition > imageList.get(getMaximumValue() - 1).getX()) {
      fireValueChangedEvent(getMaximumValue());
      return;
    }
    for (int imageIndex = 0; imageIndex < getMaximumValue(); imageIndex++) {
      if (xPosition < imageList.get(imageIndex).getX()) {
        fireValueChangedEvent(imageIndex);
        return;
      }
    }
  }

  private int getMaximumValue() {
    return imageList.size();
  }

  public void setValue(int value) {
    for (int imageIndex = 0; imageIndex < value; imageIndex++) {
      imageList.get(imageIndex).setIcon(activeImage);
    }
    for (int imageIndex = value; imageIndex < getMaximumValue(); imageIndex++) {
      imageList.get(imageIndex).setIcon(passiveImage);
    }
    fireValueChangedEvent(value);
  }

  public JComponent getComponent() {
    return panel;
  }

  private void updateMarkerRectangle(int width) {
    panel.resizeMarkerRectangle(width);
  }

  private void fireValueChangedEvent(int value) {
    valueControl.fireValueChangedEvent(value);
  }

  public void setMaximum(int maximalValue) {
    initializeLabels(maximalValue);
  }
}