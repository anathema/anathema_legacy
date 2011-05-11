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

public class SvgTreeListening {

  private final ISvgTreeViewProperties properties;
  private final IAnathemaCanvas canvas;
  private final IBoundsCalculator boundsCalculator;
  private final GenericControl<INodeSelectionListener> control = new GenericControl<INodeSelectionListener>();
  private final LeftClickPanInteractor leftClickPanner;
  private String selectionId;

  private final EventListener nodeExitListener = new EventListener() {
    public void handleEvent(Event event) {
      canvas.setToolTipText(null);
      String nodeId = ((SVGGElement) event.getCurrentTarget()).getId();
      if (((MouseEvent) event).getButton() == 0) {
        if (selectionId == null) {
          selectionId=nodeId;
        }
        canvas.setCursorInternal(properties.getForbiddenCursor());
        leftClickPanner.toggleCursorControls();
      }
      else if (selectionId == null || selectionId.equals(nodeId)) {
        leftClickPanner.togglePanning();
        selectionId = null;
        resetCursor();
      }
    }
  };

  private final EventListener cursorTooltipInitListener = new EventListener() {
    public void handleEvent(Event event) {
      leftClickPanner.disable();
      String nodeId = ((SVGGElement) event.getCurrentTarget()).getId();
      if (selectionId == null || selectionId.equals(nodeId)) {
        selectionId = nodeId;
        setCursor(nodeId);
        setCanvasTooltip(nodeId);
      }
    }
  };

  private final EventListener selectionInvokingListener = new EventListener() {
    public void handleEvent(Event event) {
      MouseEvent mouseEvent = (MouseEvent) event;
      String nodeId = ((SVGGElement) event.getCurrentTarget()).getId();
      boolean primaryButton = mouseEvent.getButton() == 0;
      if (primaryButton && (selectionId == null || selectionId.equals(nodeId))) {
        fireNodeSelectionEvent(nodeId);
      }
      selectionId = null;
      setCursor(nodeId);
      setCanvasTooltip(nodeId);
      event.stopPropagation();
    }
  };

  private final EventListener controlListener = new EventListener() {
    @Override
    public void handleEvent(Event evt) {
      leftClickPanner.disable();
      canvas.setCursorInternal(properties.getControlCursor());
    }
  };
  private EventListener cursorListener = new EventListener() {
    @Override
    public void handleEvent(Event event) {
      selectionId = null;
      resetCursor();
    }
  };

  @SuppressWarnings("unchecked")
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
    List<Interactor> interactors = canvas.getInteractors();
    interactors.add(new RightClickMagnifyInteractor(boundsCalculator, canvas, this, properties.getZoomCursor()));
    interactors.add(new DoubleRightClickResetTransformInteractor(boundsCalculator));
    this.leftClickPanner = new LeftClickPanInteractor(boundsCalculator, canvas, properties);
    interactors.add(leftClickPanner);
    canvas.addMouseListener(leftClickPanner);
    canvas.addMouseListener(new RightClickPanAdapter(boundsCalculator));
    canvas.addMouseWheelListener(new MouseWheelMagnifyListener(boundsCalculator));
    canvas.setCursorInternal(properties.getDefaultCursor());
  }

  public void addNodeSelectionListener(final INodeSelectionListener listener) {
    control.addListener(listener);
  }

  public void destructDocumentListening(final SVGDocument document) {
    if (document == null) {
      return;
    }
    document.getRootElement().removeEventListener(SVGConstants.SVG_MOUSEUP_EVENT_TYPE, cursorListener, false);
    for (SVGGElement groupElement : canvas.getNodeElements()) {
      groupElement.removeEventListener(SVGConstants.SVG_MOUSEUP_EVENT_TYPE, selectionInvokingListener, false);
      groupElement.removeEventListener(SVGConstants.SVG_MOUSEOVER_EVENT_TYPE, cursorTooltipInitListener, false);
      groupElement.removeEventListener(SVGConstants.SVG_MOUSEOUT_EVENT_TYPE, nodeExitListener, false);
    }
    for (final SVGGElement groupElement : canvas.getControlElements()) {
      groupElement.removeEventListener(SVGConstants.SVG_MOUSEMOVE_EVENT_TYPE, controlListener, false);
      groupElement.removeEventListener(SVGConstants.SVG_MOUSEOUT_EVENT_TYPE, nodeExitListener, false);
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
    document.getRootElement().addEventListener(SVGConstants.SVG_MOUSEUP_EVENT_TYPE, cursorListener, false);
    for (SVGGElement groupElement : canvas.getNodeElements()) {
      groupElement.addEventListener(SVGConstants.SVG_MOUSEUP_EVENT_TYPE, selectionInvokingListener, false);
      groupElement.addEventListener(SVGConstants.SVG_MOUSEOVER_EVENT_TYPE, cursorTooltipInitListener, false);
      groupElement.addEventListener(SVGConstants.SVG_MOUSEOUT_EVENT_TYPE, nodeExitListener, false);
    }
    for (final SVGGElement groupElement : canvas.getControlElements()) {
      groupElement.addEventListener(SVGConstants.SVG_MOUSEMOVE_EVENT_TYPE, controlListener, false);
      groupElement.addEventListener(SVGConstants.SVG_MOUSEOUT_EVENT_TYPE, nodeExitListener, false);
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
    canvas.setCursorInternal(properties.getDefaultCursor());
  }
}