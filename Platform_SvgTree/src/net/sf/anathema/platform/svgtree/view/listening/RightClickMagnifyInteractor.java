package net.sf.anathema.platform.svgtree.view.listening;

import java.awt.Cursor;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;

import net.sf.anathema.platform.svgtree.presenter.view.IAnathemaCanvas;
import net.sf.anathema.platform.svgtree.view.batik.IBoundsCalculator;

import org.apache.batik.swing.gvt.InteractorAdapter;
import org.apache.batik.swing.gvt.JGVTComponent;

public class RightClickMagnifyInteractor extends InteractorAdapter {
	
  public static final float MAX_ZOOM_DETERMINANT = .65f;
  private final IBoundsCalculator calculator;
  private boolean finished = true;
  private int yStart;
  private int xStart;
  private final Cursor zoomCursor;
  private final IAnathemaCanvas canvas;
  private final SvgTreeListening listening;

  public RightClickMagnifyInteractor(
      IBoundsCalculator boundsCalculator,
      IAnathemaCanvas canvas,
      SvgTreeListening svgTreeListening,
      Cursor zoomCursor) {
    this.calculator = boundsCalculator;
    this.canvas = canvas;
    this.listening = svgTreeListening;
    this.zoomCursor = zoomCursor;
  }

  @Override
  public boolean startInteraction(InputEvent ie) {
    int mods = ie.getModifiers();
    return ie.getID() == MouseEvent.MOUSE_PRESSED && (mods & InputEvent.BUTTON3_MASK) != 0;
  }

  @Override
  public boolean endInteraction() {
    return finished;
  }

  @Override
  public void mousePressed(MouseEvent e) {
    JGVTComponent c = (JGVTComponent) e.getSource();
    if (!finished) {
      c.setPaintingTransform(null);
      return;
    }
    finished = false;
    yStart = e.getY();
    xStart = e.getX();
    canvas.setCursorInternal(zoomCursor);
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    finished = true;
    JGVTComponent c = (JGVTComponent) e.getSource();
    AffineTransform pt = c.getPaintingTransform();
    if (pt != null) {
      AffineTransform rt = (AffineTransform) c.getRenderingTransform().clone();
      rt.preConcatenate(pt);
      c.setRenderingTransform(rt);
    }
    listening.resetCursor();
    calculator.reset();
  }

  @Override
  public void mouseDragged(MouseEvent event) {
    JGVTComponent component = (JGVTComponent) event.getSource();
    AffineTransform at = AffineTransform.getTranslateInstance(xStart, yStart);
    int movement = yStart - event.getY();
    if (movement < 0) {
      movement = movement > -5 ? 15 : movement - 10;
    }
    else {
      movement = movement < 5 ? 15 : movement + 10;
    }
    double scale = movement / 15.0;
    scale = scale > 0 ? scale : -1 / scale;

    at.scale(scale, scale);
    at.translate(-xStart, -yStart);
    
    JGVTComponent c = (JGVTComponent) event.getSource();
    AffineTransform rt = (AffineTransform) c.getRenderingTransform().clone();
    rt.preConcatenate(at);
    if (rt.getDeterminant() < MAX_ZOOM_DETERMINANT)
  	  return;
    component.setPaintingTransform(at);
  }
}