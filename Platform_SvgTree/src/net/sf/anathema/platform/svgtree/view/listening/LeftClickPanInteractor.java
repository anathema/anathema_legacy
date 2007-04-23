package net.sf.anathema.platform.svgtree.view.listening;

import java.awt.Cursor;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;

import net.sf.anathema.platform.svgtree.presenter.view.IAnathemaCanvas;
import net.sf.anathema.platform.svgtree.presenter.view.ISvgTreeViewProperties;
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
  private boolean panningEnabled;
  private boolean cursorEnabled;
  private final ISvgTreeViewProperties properties;
  private final SvgTreeListening listening;

  public LeftClickPanInteractor(
      BoundsCalculator calculator,
      IAnathemaCanvas canvas,
      ISvgTreeViewProperties properties,
      SvgTreeListening listening) {
    this.calculator = calculator;
    this.canvas = canvas;
    this.properties = properties;
    this.listening = listening;
  }

  @Override
  public boolean endInteraction() {
    return finished;
  }

  @Override
  public boolean startInteraction(InputEvent event) {
    if (!panningEnabled || !(event instanceof MouseEvent)) {
      return false;
    }
    int mods = event.getModifiers();
    return (event.getID() == MouseEvent.MOUSE_PRESSED) && (mods & InputEvent.BUTTON1_MASK) != 0;
  }

  @Override
  public void mousePressed(MouseEvent e) {
    if (!panningEnabled || !finished) {
      return;
    }
    finished = false;
    xStart = e.getX();
    yStart = e.getY();
    JGVTComponent c = (JGVTComponent) e.getSource();
    previousCursor = c.getCursor();
    canvas.setCursorInternal(properties.getDragCursor());
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    if (cursorEnabled) {
      listening.resetCursor();
      togglePanning();
      return;
    }
    if (!panningEnabled || finished) {
      return;
    }
    finished = true;
    JGVTComponent c = (JGVTComponent) e.getSource();
    AffineTransform at = createTranslateTransform(e);
    AffineTransform rt = (AffineTransform) c.getRenderingTransform().clone();
    rt.preConcatenate(at);
    c.setRenderingTransform(rt);
    canvas.setCursorInternal(previousCursor);
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

  private void togglePanning() {
    this.panningEnabled = true;
    this.cursorEnabled = false;
  }

  public void toggleCursorControls() {
    this.cursorEnabled = true;
    this.panningEnabled = false;
    canvas.setCursorInternal(properties.getForbiddenCursor());
  }

  public void disable() {
    this.cursorEnabled = false;
    this.panningEnabled = false;
  }
}
