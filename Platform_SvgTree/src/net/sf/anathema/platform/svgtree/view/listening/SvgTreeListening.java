package net.sf.anathema.platform.svgtree.view.listening;

import java.awt.Cursor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.control.IClosure;
import net.sf.anathema.platform.svgtree.document.svg.ISVGCascadeXMLConstants;
import net.sf.anathema.platform.svgtree.presenter.view.IAnathemaCanvas;
import net.sf.anathema.platform.svgtree.presenter.view.INodeSelectionListener;
import net.sf.anathema.platform.svgtree.presenter.view.ISvgTreeViewProperties;
import net.sf.anathema.platform.svgtree.view.batik.AnathemaCanvas;
import net.sf.anathema.platform.svgtree.view.batik.BoundsCalculator;
import net.sf.anathema.platform.svgtree.view.batik.IBoundsCalculator;

import org.apache.batik.swing.gvt.Interactor;
import org.apache.batik.util.SVGConstants;
import org.w3c.dom.NodeList;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.MouseEvent;
import org.w3c.dom.svg.SVGDocument;
import org.w3c.dom.svg.SVGGElement;

public class SvgTreeListening {

  private ISvgTreeViewProperties properties;
  private final IAnathemaCanvas canvas;
  private final BoundsCalculator boundsCalculator = new BoundsCalculator();
  private final GenericControl<INodeSelectionListener> control = new GenericControl<INodeSelectionListener>();

  private final EventListener canvasResettingListener = new EventListener() {
    public void handleEvent(Event evt) {
      canvas.setToolTipText(null);
      canvas.setCursorInternal(Cursor.getDefaultCursor());
    }
  };

  private final EventListener cursorTooltipInitListener = new EventListener() {
    public void handleEvent(Event evt) {
      if (evt instanceof MouseEvent) {
        SVGGElement group = (SVGGElement) evt.getCurrentTarget();
        String nodeId = group.getId();
        setCursor(nodeId);
        setCanvasTooltip(nodeId);
      }
    }
  };

  private final EventListener selectionInvokingListener = new EventListener() {
    public void handleEvent(Event evt) {
      if (evt instanceof MouseEvent && ((MouseEvent) evt).getButton() == 0 && ((MouseEvent) evt).getDetail() == 1) {
        SVGGElement group = (SVGGElement) evt.getCurrentTarget();
        String nodeId = group.getId();
        fireNodeSelectionEvent(nodeId);
        setCursor(nodeId);
      }
    }
  };

  public SvgTreeListening(final AnathemaCanvas canvas) {
    this.canvas = canvas;
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
    interactors.add(new RightClickMagnifyInteractor(boundsCalculator));
    interactors.add(new RightClickPanInteractor(boundsCalculator));
    interactors.add(new DoubleRightClickResetTransformInteractor(boundsCalculator));
  }

  public void addNodeSelectionListener(final INodeSelectionListener listener) {
    control.addListener(listener);
  }

  public void destructDocumentListening(final SVGDocument document) {
    boundsCalculator.reset();
    if (document == null) {
      return;
    }
    NodeList groupElementsList = document.getElementById(ISVGCascadeXMLConstants.VALUE_CASCADE_ID)
        .getElementsByTagName(SVGConstants.SVG_G_TAG);
    for (int index = 0; index < groupElementsList.getLength(); index++) {
      SVGGElement groupElement = (SVGGElement) groupElementsList.item(index);
      if (groupElement.hasAttribute(ISVGCascadeXMLConstants.ATTRIB_IS_LISTENER_REQUIRED)) {
        groupElement.removeEventListener("click", selectionInvokingListener, false); //$NON-NLS-1$
        groupElement.removeEventListener("mousemove", cursorTooltipInitListener, false); //$NON-NLS-1$
        groupElement.removeEventListener("mouseout", canvasResettingListener, false); //$NON-NLS-1$
      }
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
    NodeList groupElementsList = document.getElementById(ISVGCascadeXMLConstants.VALUE_CASCADE_ID)
        .getElementsByTagName(SVGConstants.SVG_G_TAG);
    for (int index = 0; index < groupElementsList.getLength(); index++) {
      SVGGElement groupElement = (SVGGElement) groupElementsList.item(index);
      if (groupElement.hasAttribute(ISVGCascadeXMLConstants.ATTRIB_IS_LISTENER_REQUIRED)) {
        groupElement.addEventListener("click", selectionInvokingListener, false); //$NON-NLS-1$
        groupElement.addEventListener("mousemove", cursorTooltipInitListener, false); //$NON-NLS-1$
        groupElement.addEventListener("mouseout", canvasResettingListener, false); //$NON-NLS-1$
      }
    }
  }

  private void setCanvasTooltip(final String node) {
    canvas.setToolTipText(properties.getToolTip(node));
  }

  public void setProperties(final ISvgTreeViewProperties viewProperties) {
    this.properties = viewProperties;
  }

  public IBoundsCalculator getBoundsCalculator() {
    return boundsCalculator;
  }

  private void setCursor(final String nodeId) {
    boolean isSelected = properties.isNodeSelected(nodeId);
    boolean isDeselectable = properties.isNodeDeselectable(nodeId);
    boolean isSelectable = properties.isNodeSelectable(nodeId);
    setCursorForNode(isSelected, isSelectable, isDeselectable);
    setCanvasTooltip(nodeId);
  }

  private void setCursorForNode(boolean isSelected, boolean isSelectable, boolean isDeselectable) {
    Cursor currentCursor;
    if (!isSelected) {
      currentCursor = !isSelectable ? properties.getDefaultCursor() : properties.getAddCursor();
    }
    else {
      currentCursor = !isDeselectable ? properties.getDefaultCursor() : properties.getRemoveCursor();
    }
    canvas.setCursorInternal(currentCursor);
  }
}