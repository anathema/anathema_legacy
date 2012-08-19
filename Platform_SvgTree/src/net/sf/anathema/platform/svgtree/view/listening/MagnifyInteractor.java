package net.sf.anathema.platform.svgtree.view.listening;

import net.sf.anathema.platform.svgtree.presenter.view.IAnathemaCanvas;
import org.apache.batik.swing.gvt.InteractorAdapter;
import org.apache.batik.swing.gvt.JGVTComponent;

import java.awt.Cursor;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;

public class MagnifyInteractor extends InteractorAdapter implements MouseWheelListener {

  private static final float MAX_ZOOM_OUT_DETERMINANT = 0.50f;  //   50%
  private static final float MAX_ZOOM_IN_DETERMINANT = 12.00f;  // 1200%
  private static final float PERCENTAGE_INCREMENT = 0.05f;  //    5% used for mousewheel zoom only
  private boolean finished = true;
  private int yStart;
  private int xStart;
  private final Cursor zoomCursor;
  private final IAnathemaCanvas canvas;
  private final SvgTreeListening listening;

  public MagnifyInteractor(IAnathemaCanvas canvas, SvgTreeListening svgTreeListening, Cursor zoomCursor) {
    this.canvas = canvas;
    this.listening = svgTreeListening;
    this.zoomCursor = zoomCursor;
  }

  @Override
  public boolean startInteraction(InputEvent event) {
    int mods = event.getModifiers();
    return event.getID() == MouseEvent.MOUSE_PRESSED && (mods & InputEvent.BUTTON3_MASK) != 0;
  }

  @Override
  public boolean endInteraction() {
    return finished;
  }

  @Override
  public void mousePressed(MouseEvent event) {
    if (finished) {
      finished = false;
      yStart = event.getY();
      xStart = event.getX();
      canvas.setCursorInternal(zoomCursor);
    }
  }

  @Override
  public void mouseReleased(MouseEvent event) {
    finished = true;
    listening.resetCursor();

    JGVTComponent c = (JGVTComponent) event.getSource();
    AffineTransform currentTransform = c.getRenderingTransform();
    AffineTransform zoomTransform = c.getPaintingTransform();
    AffineTransform finalTransform = attemptTransform(currentTransform, zoomTransform);

    if (finalTransform != null) {
      c.setRenderingTransform(finalTransform);
    }
  }

  @Override
  public void mouseDragged(MouseEvent event) {
    int verticalMovement = yStart - event.getY();

    // The 15 used here is a bit of a magic number.
    // We make 15 the minimum value for drag distance.
    // Every 15 units dragged equals 100% zoom.

    verticalMovement = verticalMovement < 0 ? Math.min(-15, verticalMovement) : Math.max(15, verticalMovement);

    double scale = verticalMovement / 15.0;
    scale = scale < 0 ? -1 / scale : scale;

    magnifyView((JGVTComponent) event.getSource(), xStart, yStart, scale, false);
  }

  @Override
  public void mouseWheelMoved(MouseWheelEvent event) {
    int wheelClicks = event.getWheelRotation();
    int percentageTicks = (int) (1 / PERCENTAGE_INCREMENT);

    // max movement is capped at 1/PERCENTAGE_INCREMENTS
    int movement = wheelClicks < 0 ? Math.max(wheelClicks, -percentageTicks) : Math.min(wheelClicks, percentageTicks);

    // ensuring scale does not go to zero or negative
    double scale = Math.max(0.00001, 1 - PERCENTAGE_INCREMENT * movement);

    magnifyView((JGVTComponent) event.getSource(), event.getX(), event.getY(), scale, true);
  }

  private void magnifyView(JGVTComponent c, int x, int y, double scale, boolean renderImmediately) {

    AffineTransform currentTransform = c.getRenderingTransform();
    AffineTransform zoomTransform = createZoomTransform(x, y, scale);
    AffineTransform finalTransform = attemptTransform(currentTransform, zoomTransform);

    if (finalTransform != null) {
      if (renderImmediately) {
        c.setRenderingTransform(finalTransform);
      } else {
        c.setPaintingTransform(zoomTransform);
      }
    }
  }

  private AffineTransform createZoomTransform(int x, int y, double scale) {
    AffineTransform zoom = new AffineTransform();
    zoom.translate(x, y);
    zoom.scale(scale, scale);
    zoom.translate(-x, -y);
    return zoom;
  }

  // returns null if the transform is out of zoom range
  private AffineTransform attemptTransform(AffineTransform current, AffineTransform zoom) {
    AffineTransform result = null;
    if (current != null && zoom != null) {
      AffineTransform clone = (AffineTransform) current.clone();
      clone.preConcatenate(zoom);
      if (MAX_ZOOM_OUT_DETERMINANT < clone.getDeterminant() && clone.getDeterminant() < MAX_ZOOM_IN_DETERMINANT) {
        result = clone;
      }
    }
    return result;
  }
}