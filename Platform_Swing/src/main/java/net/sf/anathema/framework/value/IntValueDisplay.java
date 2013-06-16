package net.sf.anathema.framework.value;

import net.miginfocom.swing.MigLayout;
import net.sf.anathema.lib.control.IIntValueChangedListener;
import org.jmock.example.announcer.Announcer;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.withoutInsets;

public class IntValueDisplay implements IIntValueDisplay {

  public static IIntValueDisplay createMarkerDisplay(int maxValue, TwoUpperBounds bounds, IntValueDisplayGraphics graphics) {
    return new IntValueDisplay(maxValue, new RectangleMarkerPanel(), bounds, graphics);
  }

  private int currentValue;
  private int naturalMaximum;
  private int modifiedMaximum;
  private final Announcer<IIntValueChangedListener> valueControl = Announcer.to(IIntValueChangedListener.class);
  private final TwoUpperBounds bounds;
  private final AbstractMarkerPanel panel;
  private final Icon capExceededImage;
  private final Icon activeImage;
  private final Icon passiveImage;
  private final Icon blockedImage;
  private final List<JLabel> imageList = new ArrayList<>();
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

  private IntValueDisplay(int maxValue, AbstractMarkerPanel panel, TwoUpperBounds bounds, IntValueDisplayGraphics graphics) {
    this.activeImage = graphics.getActiveIcon();
    this.passiveImage = graphics.getPassiveIcon();
    this.blockedImage = graphics.getBlockedIcon();
    this.panel = panel;
    this.naturalMaximum = maxValue;
    this.modifiedMaximum = maxValue;
    this.currentValue = 0;
    this.bounds = bounds;
    panel.setLayout(new MigLayout(withoutInsets().wrapAfter(maxValue + maxValue / 5).gridGap("2", "0")));
    initializeLabels(maxValue);
    panel.addMouseListener(mouseListener);
    panel.addMouseMotionListener(mouseListener);
    ImageIcon active = (ImageIcon) activeImage;
    ImageIcon passive = (ImageIcon) passiveImage;
    this.capExceededImage = new ImageIcon(createImage(active.getImage(), passive.getImage()));

    getComponent().addHierarchyListener(new HierarchyListener() {
      @Override
      public void hierarchyChanged(HierarchyEvent arg0) {
        if ((arg0.getChangeFlags() & HierarchyEvent.SHOWING_CHANGED) != 0 && getComponent().isShowing()) refresh();
      }
    });
    refresh();
  }

  private Image createImage(Image active, Image passive) {
    BufferedImage activeTop = new BufferedImage(active.getWidth(null), active.getHeight(null), BufferedImage.TYPE_4BYTE_ABGR);
    BufferedImage passiveTop = new BufferedImage(active.getWidth(null), active.getHeight(null), BufferedImage.TYPE_4BYTE_ABGR);
    activeTop.getGraphics().drawImage(active, 0, 0, null);
    passiveTop.getGraphics().drawImage(passive, 0, 0, null);
    int width = activeTop.getWidth();
    int[] imgData = new int[width];
    int[] maskData = new int[width];
    for (int y = 0; y < activeTop.getHeight(); y++) {
      activeTop.getRGB(0, y, width, 1, imgData, 0, 1);
      passiveTop.getRGB(0, y, width, 1, maskData, 0, 1);
      for (int x = 0; x < width; x++) {
        int color = imgData[x] & 0x00FFFFFF;
        int mask = (maskData[x] & 0x00FF0000) << 8;
        color |= mask;
        imgData[x] = color;
      }
      activeTop.setRGB(0, y, width, 1, imgData, 0, 1);
    }
    return activeTop;
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

  @Override
  public void addIntValueChangedListener(IIntValueChangedListener listener) {
    valueControl.addListener(listener);
  }

  @Override
  public void removeIntValueChangedListener(IIntValueChangedListener listener) {
    valueControl.removeListener(listener);
  }

  private void updateIcons(double xPosition) {
    if (xPosition < (double) activeImage.getIconWidth() / 3.0) {
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

  private void refresh() {
    int natural = bounds.getOriginalBound();
    int modified = bounds.getModifiedBound();
    if (bounds.haveBoundsChanged(naturalMaximum, modifiedMaximum)) {
      naturalMaximum = natural;
      modifiedMaximum = modified;
      setValue(currentValue);
    }
  }

  @Override
  public void setValue(int value) {
    currentValue = value;
    for (int imageIndex = 0; imageIndex < value; imageIndex++) {
      imageList.get(imageIndex).setIcon(imageIndex + 1 > naturalMaximum ? capExceededImage : activeImage);
    }
    for (int imageIndex = value; imageIndex < getMaximumValue(); imageIndex++) {
      imageList.get(imageIndex).setIcon(imageIndex + 1 > modifiedMaximum ? blockedImage : passiveImage);
    }
    fireValueChangedEvent(value);
  }

  @Override
  public JComponent getComponent() {
    return panel;
  }

  private void updateMarkerRectangle(int width) {
    panel.resizeMarkerRectangle(width);
  }

  private void fireValueChangedEvent(int value) {
    valueControl.announce().valueChanged(value);
  }

  @Override
  public void setMaximum(int maximalValue) {
    initializeLabels(maximalValue);
  }
}