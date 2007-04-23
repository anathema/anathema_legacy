package net.sf.anathema.platform.svgtree.view.listening;

import java.awt.Cursor;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;

import net.sf.anathema.platform.svgtree.presenter.view.IAnathemaCanvas;
import net.sf.anathema.platform.svgtree.view.batik.BoundsCalculator;

import org.apache.batik.swing.gvt.InteractorAdapter;
import org.apache.batik.swing.gvt.JGVTComponent;

public class LeftClickPanInteractor extends InteractorAdapter {
  protected boolean finished = true;
  protected int xStart;
  protected int yStart;
  protected Cursor previousCursor;

  private final IAnathemaCanvas canvas;
  private final BoundsCalculator calculator;
  private boolean enabled;
  private final Cursor dragCursor;

  public LeftClickPanInteractor(BoundsCalculator calculator, IAnathemaCanvas canvas, Cursor dragCursor) {
    this.calculator = calculator;
    this.canvas = canvas;
    this.dragCursor = dragCursor;
  }

  @Override
  public boolean endInteraction() {
    return finished;
  }

  @Override
  public boolean startInteraction(InputEvent event) {
    if (!enabled || !(event instanceof MouseEvent)) {
      return false;
    }
    int mods = event.getModifiers();
    return event.getID() == MouseEvent.MOUSE_PRESSED && (mods & InputEvent.BUTTON1_MASK) != 0;
  }

  @Override
  public void mousePressed(MouseEvent e) {
    if (!enabled || !finished) {
      return;
    }
    finished = false;
    xStart = e.getX();
    yStart = e.getY();
    JGVTComponent c = (JGVTComponent) e.getSource();
    previousCursor = c.getCursor();
    canvas.setCursorInternal(dragCursor);
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    if (!enabled || finished) {
      return;
    }
    finished = true;
    JGVTComponent c = (JGVTComponent) e.getSource();
    AffineTransform at = createTranslateTransform(e);
    AffineTransform rt = (AffineTransform) c.getRenderingTransform().clone();
    rt.preConcatenate(at);
    c.setRenderingTransform(rt);
    if (c.getCursor() == dragCursor) {
      canvas.setCursorInternal(previousCursor);
    }
    calculator.reset();
  }

  @Override
  public void mouseDragged(MouseEvent e) {
    JGVTComponent component = (JGVTComponent) e.getSource();
    AffineTransform transform = createTranslateTransform(e);
    component.setPaintingTransform(transform);
  }

  private AffineTransform createTranslateTransform(MouseEvent e) {
    AffineTransform transform = AffineTransform.getTranslateInstance(e.getX() - xStart, e.getY() - yStart);
    return transform;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }
}
