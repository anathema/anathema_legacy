package net.sf.anathema.platform.svgtree.view.listening;

import java.awt.Cursor;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;

import net.sf.anathema.platform.svgtree.presenter.view.IAnathemaCanvas;
import net.sf.anathema.platform.svgtree.view.batik.IBoundsCalculator;

import org.apache.batik.swing.gvt.InteractorAdapter;
import org.apache.batik.swing.gvt.JGVTComponent;

public class MagnifyInteractor extends InteractorAdapter implements MouseWheelListener {
	
  public static final float MAX_ZOOM_OUT_DETERMINANT = 0.5f;  // 50%
  public static final float MAX_ZOOM_IN_DETERMINANT  = 8.0f;  // 800%
	public static final float PERCENTAGE_INCREMENT     = 0.05f; // 5%
  private final IBoundsCalculator calculator;
  private boolean finished = true;
  private int yStart;
  private int xStart;
  private final Cursor zoomCursor;
  private final IAnathemaCanvas canvas;
  private final SvgTreeListening listening;

  public MagnifyInteractor(
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
    int verticalMovement = yStart - event.getY();/*
		
		double scale = Math.max(0.00001, 1 - 0.05 *
					                  Math.copySign(Math.min(5,Math.abs(movement)),
												                  movement));
		double scale = Math.max(0.00001, 1 - 0.05 * event.getWheelRotation());
		System.out.println( "scale = " + scale);*/

		magnify((JGVTComponent)event.getSource(), xStart, yStart, verticalMovement);
  }
	
  @Override
  public void mouseWheelMoved(MouseWheelEvent event) {
		int wheelClicks = event.getWheelRotation();

		magnify((JGVTComponent)event.getSource(), event.getX(), event.getY(), wheelClicks);
  }
	
	private void magnify(JGVTComponent c, int x, int y, int movement) {
		double scale = Math.max(0.00001, 1 - PERCENTAGE_INCREMENT * movement);
		AffineTransform current = c.getRenderingTransform();
    AffineTransform zoom = translate( x, y, scale);
		
		if( testTransform(current, zoom) ) {
			current.preConcatenate(zoom);
			c.setRenderingTransform(current);
		}
	}
	
	private AffineTransform translate(int x, int y, double scale) {
    AffineTransform zoom = new AffineTransform();
		zoom.translate(x, y);
    zoom.scale(scale, scale);
    zoom.translate(-x, -y);
		return zoom;
	}
	
	private boolean testTransform(AffineTransform current, AffineTransform zoom) {
			boolean result = true;
			AffineTransform clone = (AffineTransform)current.clone();
			clone.preConcatenate(zoom);
	    if (clone.getDeterminant() < MAX_ZOOM_OUT_DETERMINANT ||
				  clone.getDeterminant() > MAX_ZOOM_IN_DETERMINANT) {
				result = false;
			}
			return result;
	}

}