package net.sf.anathema.charmtree.listening;

import java.awt.Cursor;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;

import net.sf.anathema.charmtree.batik.BoundsCalculator;
import net.sf.anathema.charmtree.presenter.view.IAnathemaCanvas;

import org.apache.batik.swing.gvt.InteractorAdapter;
import org.apache.batik.swing.gvt.JGVTComponent;

public class LeftClickPanInteractor extends InteractorAdapter {
  /**
   * The cursor for panning.
   */
  public final static Cursor PAN_CURSOR = new Cursor(Cursor.MOVE_CURSOR);

  /**
   * Whether the interactor has finished.
   */
  protected boolean finished = true;

  /**
   * The mouse x start position.
   */
  protected int xStart;

  /**
   * The mouse y start position.
   */
  protected int yStart;

  /**
   * The mouse x current position.
   */
  protected int xCurrent;

  /**
   * The mouse y current position.
   */
  protected int yCurrent;

  /**
   * To store the previous cursor.
   */
  protected Cursor previousCursor;

  private final IAnathemaCanvas canvas;
  private final BoundsCalculator calculator;

  public LeftClickPanInteractor(BoundsCalculator calculator, IAnathemaCanvas canvas) {
    this.calculator = calculator;
    this.canvas = canvas;
  }

  @Override
  public boolean endInteraction() {
    return finished;
  }

  @Override
  public boolean startInteraction(InputEvent ie) {
    if (!(ie instanceof MouseEvent)) {
      return false;
    }
    int mods = ie.getModifiers();
    return ie.getID() == MouseEvent.MOUSE_PRESSED && (mods & InputEvent.BUTTON1_MASK) != 0;
  }

  @Override
  public void mousePressed(MouseEvent e) {
    if (!finished) {
      mouseExited(e);
      return;
    }
    finished = false;
    xStart = e.getX();
    yStart = e.getY();
    JGVTComponent c = (JGVTComponent) e.getSource();
    previousCursor = c.getCursor();
    canvas.setCursorInternal(PAN_CURSOR);
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    if (finished) {
      return;
    }
    finished = true;
    JGVTComponent c = (JGVTComponent) e.getSource();
    xCurrent = e.getX();
    yCurrent = e.getY();
    AffineTransform at = AffineTransform.getTranslateInstance(xCurrent - xStart, yCurrent - yStart);
    AffineTransform rt = (AffineTransform) c.getRenderingTransform().clone();
    rt.preConcatenate(at);
    c.setRenderingTransform(rt);
    if (c.getCursor() == PAN_CURSOR) {
      canvas.setCursorInternal(previousCursor);
    }
    calculator.reset();
  }

  @Override
  public void mouseExited(MouseEvent e) {
    finished = true;
    JGVTComponent c = (JGVTComponent) e.getSource();
    c.setPaintingTransform(null);
    if (c.getCursor() == PAN_CURSOR) {
      canvas.setCursorInternal(previousCursor);
    }
    calculator.reset();
  }

  @Override
  public void mouseDragged(MouseEvent e) {
    JGVTComponent component = (JGVTComponent) e.getSource();
    xCurrent = e.getX();
    yCurrent = e.getY();
    AffineTransform transform = AffineTransform.getTranslateInstance(xCurrent - xStart, yCurrent - yStart);
    component.setPaintingTransform(transform);
  }
}
