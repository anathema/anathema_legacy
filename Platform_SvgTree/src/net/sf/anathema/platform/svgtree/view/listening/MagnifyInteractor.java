package net.sf.anathema.platform.svgtree.view.listening;

import java.awt.Cursor;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;

import net.sf.anathema.platform.svgtree.presenter.view.IAnathemaCanvas;

import org.apache.batik.swing.gvt.InteractorAdapter;
import org.apache.batik.swing.gvt.JGVTComponent;

public class MagnifyInteractor extends InteractorAdapter implements MouseWheelListener {
  
  private static final float MAX_ZOOM_OUT_DETERMINANT =  0.50f;  //   50%
  private static final float MAX_ZOOM_IN_DETERMINANT  = 12.00f;  // 1200%
  private static final float PERCENTAGE_INCREMENT     =  0.05f;  //    5%
  private boolean finished = true;
  private int yStart;
  private int xStart;
  private final Cursor zoomCursor;
  private final IAnathemaCanvas canvas;
  private final SvgTreeListening listening;

  public MagnifyInteractor(
      IAnathemaCanvas canvas,
      SvgTreeListening svgTreeListening,
      Cursor zoomCursor) {
    this.canvas     = canvas;
    this.listening  = svgTreeListening;
    this.zoomCursor = zoomCursor;
  }

  @Override
  public boolean startInteraction(InputEvent event) {
    int mods = event.getModifiers();
    return event.getID() == MouseEvent.MOUSE_PRESSED
           && (mods & InputEvent.BUTTON3_MASK) != 0;
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
    } else {
      JGVTComponent c = (JGVTComponent)event.getSource();
      c.setPaintingTransform(null);
      System.out.println( "******** IS THIS EVER CALLED??? ********");
    }
  }
  
  @Override
  public void mouseReleased(MouseEvent event) {
    finished = true;
    
    JGVTComponent c = (JGVTComponent)event.getSource();
    AffineTransform current = (AffineTransform)c.getRenderingTransform().clone();
    AffineTransform zoom = c.getPaintingTransform();
    if( zoom != null ) {
      //if( isZoomInRange(current, zoom) ) {
      current.preConcatenate(zoom);
      c.setRenderingTransform(current);
      //}
    }
    listening.resetCursor();
  }

  @Override
  public void mouseDragged(MouseEvent event) {
    int verticalMovement = event.getY() - yStart;
    
    // The 10 used here is a bit of a magic number.
    // It was selected based on testing, to allow for a smooth zoom
    // Every 10 pixels of drag equals a PERCENTAGE_INCREMENT zoom, up to 100% per action.
    verticalMovement /= 10;
    
    // minimum of 1 increment of movement allowed.
    verticalMovement = verticalMovement < 0 ? Math.min(-1, verticalMovement) : Math.max(1, verticalMovement);
    
    // Force movement to conform to between 1 and 1/PERCENTAGE_INCREMENTS increments
/*    int percentageTicks = (int)(1/PERCENTAGE_INCREMENT);
    int movement  = Math.max(Math.abs(verticalMovement), 1 );
        movement  = Math.min(Math.abs(verticalMovement), percentageTicks );
        movement *= verticalMovement < 0 ? -1 : 1;  // preserve direction after abs()
  */  
    magnify((JGVTComponent)event.getSource(), xStart, yStart, verticalMovement, false);
  }
  
  @Override
  public void mouseWheelMoved(MouseWheelEvent event) {
    int wheelClicks = event.getWheelRotation();
    
    magnify((JGVTComponent)event.getSource(), event.getX(), event.getY(), wheelClicks, true);
  }
  
  private void magnify(JGVTComponent c, int x, int y, int movement, boolean renderImmediately) {
    int percentageTicks = (int)(1/PERCENTAGE_INCREMENT);
    movement = movement < 0 ? Math.max(-percentageTicks, movement) : Math.min(percentageTicks, movement);
    double scale = Math.max(0.00001, 1 - PERCENTAGE_INCREMENT * movement);
    AffineTransform current = (AffineTransform)c.getRenderingTransform().clone();
    AffineTransform zoom = translate(x, y, scale);
    System.out.println( "movement = " + movement);
    System.out.println( "scale = " + scale);
    System.out.println( "getProgressivePaint() = " + c.getProgressivePaint());
    System.out.println( "getDoubleBufferedRendering() = " + c.getDoubleBufferedRendering());
    if( isZoomInRange(current, zoom) ) {
      current.preConcatenate(zoom);
      if(renderImmediately) {
        c.setRenderingTransform(current);
      } else {
        c.setPaintingTransform(zoom);
      }
    }
  }
  
  private AffineTransform translate(int x, int y, double scale) {
    AffineTransform zoom = new AffineTransform();
    zoom.translate(x, y);
    zoom.scale(scale, scale);
    zoom.translate(-x, -y);
    return zoom;
  }
  
  private boolean isZoomInRange(AffineTransform current, AffineTransform zoom) {
    boolean result = false;
    if(current != null && zoom != null) {
      AffineTransform clone = (AffineTransform)current.clone();
      clone.preConcatenate(zoom);
      if(   clone.getDeterminant() > MAX_ZOOM_OUT_DETERMINANT
         && clone.getDeterminant() < MAX_ZOOM_IN_DETERMINANT  ) {
        result = true;
      }
    }
    return result;
  }
}