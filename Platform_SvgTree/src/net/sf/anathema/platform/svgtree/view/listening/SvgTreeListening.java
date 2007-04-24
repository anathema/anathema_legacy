package net.sf.anathema.platform.svgtree.view.listening;

import java.awt.Cursor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.control.IClosure;
import net.sf.anathema.platform.svgtree.presenter.view.IAnathemaCanvas;
import net.sf.anathema.platform.svgtree.presenter.view.INodeSelectionListener;
import net.sf.anathema.platform.svgtree.presenter.view.ISvgTreeViewProperties;
import net.sf.anathema.platform.svgtree.view.batik.AnathemaCanvas;
import net.sf.anathema.platform.svgtree.view.batik.IBoundsCalculator;

import org.apache.batik.swing.gvt.Interactor;
import org.apache.batik.util.SVGConstants;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.MouseEvent;
import org.w3c.dom.svg.SVGDocument;
import org.w3c.dom.svg.SVGGElement;
import org.w3c.dom.svg.SVGTSpanElement;
import org.w3c.dom.svg.SVGTextElement;

public class SvgTreeListening {

  private final ISvgTreeViewProperties properties;
  private final IAnathemaCanvas canvas;
  private final IBoundsCalculator boundsCalculator;
  private final GenericControl<INodeSelectionListener> control = new GenericControl<INodeSelectionListener>();
  private final LeftClickPanInteractor leftClickPanInteractor;
  private String selectionId;

  private final EventListener canvasResettingListener = new EventListener() {
    public void handleEvent(Event event) {
      if (event.getTarget() instanceof SVGTSpanElement || event.getTarget() instanceof SVGTextElement) {
        return;
      }
      canvas.setToolTipText(null);
      if (((MouseEvent) event).getButton() == 0) {
        if (selectionId == null) {
          SVGGElement group = (SVGGElement) event.getCurrentTarget();
          selectionId = group.getId();
        }
        leftClickPanInteractor.toggleCursorControls();
      }
      else {
        leftClickPanInteractor.togglePanning();
        resetCursor();
      }
    }
  };

  private final EventListener cursorTooltipInitListener = new EventListener() {
    public void handleEvent(Event event) {
      SVGGElement group = (SVGGElement) event.getCurrentTarget();
      String nodeId = group.getId();
      if (selectionId == null || nodeId.equals(selectionId)) {
        setCursor(nodeId);
        setCanvasTooltip(nodeId);
        leftClickPanInteractor.disable();
      }
    }
  };

  private final EventListener selectionInvokingListener = new EventListener() {
    public void handleEvent(Event event) {
      MouseEvent mouseEvent = (MouseEvent) event;
      if (mouseEvent.getButton() == 0 && mouseEvent.getDetail() == 1) {
        SVGGElement group = (SVGGElement) event.getCurrentTarget();
        String nodeId = group.getId();
        if (selectionId == null || nodeId.equals(selectionId)) {
          fireNodeSelectionEvent(nodeId);
          setCursor(nodeId);
          setCanvasTooltip(nodeId);
        }
        selectionId = null;
      }
    }
  };

  public SvgTreeListening(
      final AnathemaCanvas canvas,
      IBoundsCalculator calculator,
      ISvgTreeViewProperties viewProperties) {
    this.canvas = canvas;
    this.boundsCalculator = calculator;
    this.properties = viewProperties;
    canvas.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(final KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_LEFT || key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
          boundsCalculator.reset();
        }
      }
    });
    canvas.addMouseWheelListener(new MouseWheelMagnifyListener(boundsCalculator));
    List<Interactor> interactors = canvas.getInteractors();
    interactors.add(new RightClickMagnifyInteractor(boundsCalculator, canvas, this, properties.getZoomCursor()));
    interactors.add(new RightClickPanInteractor(boundsCalculator));
    this.leftClickPanInteractor = new LeftClickPanInteractor(boundsCalculator, canvas, properties, this);
    interactors.add(leftClickPanInteractor);
    interactors.add(new DoubleRightClickResetTransformInteractor(boundsCalculator));
    canvas.setCursorInternal(properties.getDefaultCursor());
    canvas.addMouseListener(leftClickPanInteractor);
  }

  public void addNodeSelectionListener(final INodeSelectionListener listener) {
    control.addListener(listener);
  }

  public void destructDocumentListening(final SVGDocument document) {
    boundsCalculator.reset();
    if (document == null) {
      return;
    }
    for (SVGGElement groupElement : canvas.getNodeElements()) {
      groupElement.removeEventListener(SVGConstants.SVG_MOUSEUP_EVENT_TYPE, selectionInvokingListener, false);
      groupElement.removeEventListener(SVGConstants.SVG_MOUSEMOVE_EVENT_TYPE, cursorTooltipInitListener, false);
      groupElement.removeEventListener(SVGConstants.SVG_MOUSEOUT_EVENT_TYPE, canvasResettingListener, false);
    }
  }

  private void fireNodeSelectionEvent(final String nodeId) {
    control.forAllDo(new IClosure<INodeSelectionListener>() {
      public void execute(final INodeSelectionListener input) {
        input.nodeSelected(nodeId);
      }
    });
  }

  public void initDocumentListening(final SVGDocument document) {
    if (document == null) {
      return;
    }
    for (SVGGElement groupElement : canvas.getNodeElements()) {
      groupElement.addEventListener(SVGConstants.SVG_MOUSEUP_EVENT_TYPE, selectionInvokingListener, false);
      groupElement.addEventListener(SVGConstants.SVG_MOUSEMOVE_EVENT_TYPE, cursorTooltipInitListener, false);
      groupElement.addEventListener(SVGConstants.SVG_MOUSEOUT_EVENT_TYPE, canvasResettingListener, false);
    }
  }

  private void setCanvasTooltip(final String node) {
    canvas.setToolTipText(properties.getToolTip(node));
  }

  private void setCursor(final String nodeId) {
    Cursor cursor = properties.getCursor(nodeId);
    canvas.setCursorInternal(cursor);
  }

  public void resetCursor() {
    this.selectionId = null;
    canvas.setCursorInternal(properties.getDefaultCursor());
  }
}