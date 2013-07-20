package net.sf.anathema.platform.tree.view;

import net.sf.anathema.framework.ui.Coordinate;
import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.platform.tree.view.draw.GraphicsElement;
import net.sf.anathema.platform.tree.view.draw.InteractiveGraphicsElement;
import net.sf.anathema.platform.tree.view.interaction.Executor;
import net.sf.anathema.platform.tree.view.interaction.SpecialControlTrigger;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;

public interface PolygonPanel {
  double RECOMMENDED_DEFAULT_SCALE = 0.75d;

  void refresh();

  SpecialControlTrigger addSpecialControl();

  void add(InteractiveGraphicsElement element);

  void add(GraphicsElement element);

  void scale(double scale);

  void scaleToPoint(double scale, int x, int y);

  void translate(int x, int y);

  void translateRelativeToScale(int x, int y);

  void resetTransformation();

  void changeCursor(Coordinate screenCoordinates);

  void clear();

  Executor onElementAtPoint(Coordinate screenCoordinates);

  void centerOn(int x, int y);

  void addMouseListener(MouseListener listener);

  void addMouseMotionListener(MouseMotionListener listener);

  void addMouseWheelListener(MouseWheelListener listener);

  void setToolTipText(String toolTip);

  void setBackground(RGBColor color);

  void showMoveCursor();
}