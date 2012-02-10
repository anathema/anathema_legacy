package net.sf.anathema.character.library.intvalue;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.sf.anathema.character.library.trait.IModifiableCapTrait;
import net.sf.anathema.framework.value.AbstractMarkerPanel;
import net.sf.anathema.framework.value.IIntValueDisplay;
import net.sf.anathema.framework.value.NoMarkerPanel;
import net.sf.anathema.framework.value.RectangleMarkerPanel;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;
import net.sf.anathema.lib.control.intvalue.IntValueControl;

public class IntValueDisplay implements IIntValueDisplay {

  public static IIntValueDisplay createMarkerDisplay(Icon blockedIcon, Icon passiveIcon, Icon activeIcon, IModifiableCapTrait trait, int maxValue) {
    return new IntValueDisplay(blockedIcon, passiveIcon, activeIcon, trait, maxValue, new RectangleMarkerPanel());
  }

  public static IIntValueDisplay createMarkerLessDisplay(Icon blockedIcon, Icon passiveIcon, Icon activeIcon, IModifiableCapTrait trait, int maxValue) {
    return new IntValueDisplay(blockedIcon, passiveIcon, activeIcon, trait, maxValue, new NoMarkerPanel());
  }

  private int currentValue;
  private int naturalMaximum;
  private int modifiedMaximum;
  private final IntValueControl valueControl = new IntValueControl();
  private final IModifiableCapTrait trait;
  private final AbstractMarkerPanel panel;
  private final Icon capExceededImage;
  private final Icon activeImage;
  private final Icon passiveImage;
  private final Icon blockedImage;
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

  private IntValueDisplay(Icon blockedIcon, Icon passiveIcon, Icon activeIcon, IModifiableCapTrait trait, int maxValue, AbstractMarkerPanel panel) {
    this.activeImage = activeIcon;
    this.passiveImage = passiveIcon;
    this.blockedImage = blockedIcon;
    this.trait = trait;
    this.panel = panel;
    this.naturalMaximum = maxValue;
    this.modifiedMaximum = maxValue;
    this.currentValue = 0;
    panel.setLayout(new GridDialogLayout(maxValue + maxValue / 5, false, 2, 0));
    initializeLabels(maxValue);
    panel.addMouseListener(mouseListener);
    panel.addMouseMotionListener(mouseListener);
    
    ImageIcon active = (ImageIcon)activeImage;
    ImageIcon passive = (ImageIcon)passiveImage;
    capExceededImage = new ImageIcon(createImage(active.getImage(), passive.getImage()));
    
    getComponent().addHierarchyListener(new HierarchyListener()
    {
		@Override
		public void hierarchyChanged(HierarchyEvent arg0) {
			if ((arg0.getChangeFlags() & HierarchyEvent.SHOWING_CHANGED) != 0 &&
					getComponent().isShowing())
				refresh();
		}
    });
    refresh();
  }
  
  private Image createImage(Image active, Image passive)
  {
	  BufferedImage activeTop = new BufferedImage(active.getWidth(null), active.getHeight(null), BufferedImage.TYPE_4BYTE_ABGR);
	  BufferedImage passiveTop = new BufferedImage(active.getWidth(null), active.getHeight(null), BufferedImage.TYPE_4BYTE_ABGR);
	  activeTop.getGraphics().drawImage(active, 0, 0, null);
	  passiveTop.getGraphics().drawImage(passive, 0, 0, null);
	  final int width = activeTop.getWidth();
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

  public void addIntValueChangedListener(IIntValueChangedListener listener) {
    valueControl.addIntValueChangeListener(listener);
  }

  public void removeIntValueChangedListener(IIntValueChangedListener listener) {
    valueControl.removeIntValueChangeListener(listener);
  }

  private void updateIcons(double xPosition) {
    if (xPosition < (double)activeImage.getIconWidth() / 3.0) {
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
  
  private void refresh()
  {
	  if (trait != null)
	  {
		  int natural = trait.getUnmodifiedMaximalValue();
		  int modified = trait.getModifiedMaximalValue();
		  if (natural != naturalMaximum ||
			  modified != modifiedMaximum)
		  {
			  naturalMaximum = natural;
			  modifiedMaximum = modified;
			  setValue(currentValue);
		  }
	  }
  }
  
  public void setValue(int value)
  {
	currentValue = value;
    for (int imageIndex = 0; imageIndex < value; imageIndex++) {
      imageList.get(imageIndex).setIcon(imageIndex + 1 > naturalMaximum ? capExceededImage : activeImage);
    }
    for (int imageIndex = value; imageIndex < getMaximumValue(); imageIndex++) {
      imageList.get(imageIndex).setIcon(imageIndex + 1 > modifiedMaximum ? blockedImage : passiveImage);
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