package net.sf.anathema.charmtree.listening;

import java.awt.Cursor;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.charmtree.batik.AnathemaCanvas;
import net.sf.anathema.charmtree.batik.BoundsCalculator;
import net.sf.anathema.charmtree.batik.EventListenerRegistratingLoadListener;
import net.sf.anathema.charmtree.batik.IBoundsCalculator;
import net.sf.anathema.charmtree.presenter.view.IAnathemaCanvas;
import net.sf.anathema.charmtree.presenter.view.ICharmSelectionListener;
import net.sf.anathema.charmtree.presenter.view.ICharmTreeView;
import net.sf.anathema.charmtree.presenter.view.ICharmTreeViewProperties;
import net.sf.anathema.charmtree.provider.svg.ISVGCascadeXMLConstants;

import org.apache.batik.dom.svg.SVGOMDocument;
import org.apache.batik.swing.gvt.Interactor;
import org.w3c.dom.NodeList;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;
import org.w3c.dom.events.MouseEvent;
import org.w3c.dom.svg.SVGDocument;
import org.w3c.dom.svg.SVGGElement;
import org.w3c.dom.svg.SVGLocatable;
import org.w3c.dom.svg.SVGTSpanElement;

public class CharmTreeListening {

  private boolean overCharmLearnable;
  private boolean overCharmSelected;
  private boolean overCharmUnlearnable;
  private ICharmTreeViewProperties properties;
  private Cursor currentCursor;
  private final IAnathemaCanvas canvas;
  private final BoundsCalculator boundsCalculator = new BoundsCalculator();
  private final ArrayList<ICharmSelectionListener> charmListenerList = new ArrayList<ICharmSelectionListener>();

  private final EventListener canvasResettingListener = new EventListener() {
    public void handleEvent(Event evt) {
      canvas.setToolTipText(null);
      canvas.setCursorInternal(Cursor.getDefaultCursor());
    }
  };

  private ICharmSelectionListener charmSelectionListener = new ICharmSelectionListener() {
    public void charmSelected(String charmId) {
      overCharmSelected = properties.isCharmSelected(charmId);
      overCharmUnlearnable = properties.isCharmUnlearnable(charmId);
      overCharmLearnable = properties.isCharmLearnable(charmId);
      setCursorForCharm();
      setCanvasTooltip(charmId);
    }
  };

  private EventListener cursorTooltipInitListener = new EventListener() {
    public void handleEvent(Event evt) {
      if (evt instanceof MouseEvent) {
        MouseEvent mouseEvent = (MouseEvent) evt;
        NodeList groupElementsList = canvas.getElementById(ISVGCascadeXMLConstants.VALUE_CASCADE_ID)
            .getElementsByTagName(ISVGCascadeXMLConstants.TAG_G);
        for (int index = 0; index < groupElementsList.getLength(); index++) {
          SVGGElement groupElement = (SVGGElement) groupElementsList.item(index);
          if (boundsCalculator.getBounds(groupElement).contains(mouseEvent.getClientX(), mouseEvent.getClientY())) {
            String charmId = groupElement.getAttribute(ISVGCascadeXMLConstants.ATTRIB_ID);
            if (StringUtilities.isNullOrEmpty(charmId)) {
              continue;
            }
            overCharmSelected = properties.isCharmSelected(charmId);
            overCharmUnlearnable = properties.isCharmUnlearnable(charmId);
            overCharmLearnable = properties.isCharmLearnable(charmId);
            setCursorForCharm();
            setCanvasTooltip(charmId);
            return;
          }
        }
        canvas.setCursorInternal(Cursor.getDefaultCursor());
        canvas.setToolTipText(null);
      }
    }
  };

  private final EventListener selectionInvokingListener = new EventListener() {
    public void handleEvent(Event evt) {
      if (evt instanceof MouseEvent && ((MouseEvent) evt).getButton() == 0) {
        MouseEvent mouseEvent = (MouseEvent) evt;
        String charmId = null;
        EventTarget eventTarget = evt.getTarget();
        if (eventTarget instanceof SVGTSpanElement) {
          charmId = ((SVGTSpanElement) eventTarget).getParentNode().getParentNode().getAttributes().getNamedItem(
              ISVGCascadeXMLConstants.ATTRIB_ID).getNodeValue();
        }
        else if (eventTarget instanceof SVGLocatable) {
          NodeList groupElementsList = canvas.getElementById(ISVGCascadeXMLConstants.VALUE_CASCADE_ID)
              .getElementsByTagName(ISVGCascadeXMLConstants.TAG_G);
          for (int index = 0; index < groupElementsList.getLength(); index++) {
            SVGGElement groupElement = (SVGGElement) groupElementsList.item(index);
            if (boundsCalculator.getBounds(groupElement).contains(mouseEvent.getClientX(), mouseEvent.getClientY())) {
              charmId = groupElement.getAttribute(ISVGCascadeXMLConstants.ATTRIB_ID);
            }
          }
        }
        if (StringUtilities.isNullOrEmpty(charmId)) {
          return;
        }
        fireCharmSelectionEvent(charmId);
      }
    }
  };

  public CharmTreeListening(final AnathemaCanvas canvas) {
    this.canvas = canvas;
    canvas.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
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

  public synchronized void addCharmSelectionListener(ICharmSelectionListener listener) {
    charmListenerList.add(listener);
  }

  public void destructDocumentListening(final SVGDocument document) {
    boundsCalculator.reset();
    if (document == null || !(document instanceof SVGOMDocument)) {
      return;
    }
    SVGOMDocument omDocument = (SVGOMDocument) document;
    omDocument.removeEventListener("mousemove", cursorTooltipInitListener, false); //$NON-NLS-1$
    omDocument.removeEventListener("mouseout", canvasResettingListener, false); //$NON-NLS-1$
    omDocument.removeEventListener("click", selectionInvokingListener, false); //$NON-NLS-1$
  }

  private synchronized void fireCharmSelectionEvent(String charmId) {
    for (ICharmSelectionListener listener : charmListenerList) {
      listener.charmSelected(charmId);
    }
  }

  public Rectangle getGroupBounds(String groupId) {
    SVGGElement svgElement = (SVGGElement) canvas.getElementById(groupId);
    return boundsCalculator.getBounds(svgElement);
  }

  public void initDocumentListening(SVGDocument document) {
    if (document == null || !(document instanceof SVGOMDocument)) {
      return;
    }
    final SVGOMDocument omDocument = (SVGOMDocument) document;
    omDocument.addEventListener("click", selectionInvokingListener, false); //$NON-NLS-1$
  }

  public void initOverallCharmTreeListening(ICharmTreeView charmTreeView) {
    addCharmSelectionListener(charmSelectionListener);
    charmTreeView.addDocumentLoadedListener(new EventListenerRegistratingLoadListener(
        "mousemove", cursorTooltipInitListener, canvas)); //$NON-NLS-1$
    charmTreeView.addDocumentLoadedListener(new EventListenerRegistratingLoadListener(
        "mouseout", canvasResettingListener, canvas)); //$NON-NLS-1$
  }

  private void setCanvasTooltip(String charmId) {
    canvas.setToolTipText(properties.getToolTip(charmId));
  }

  private void setCursorForCharm() {
    if (!overCharmSelected) {
      currentCursor = !overCharmLearnable ? properties.getDefaultCursor() : properties.getAddCursor();
    }
    else {
      currentCursor = !overCharmUnlearnable ? properties.getDefaultCursor() : properties.getRemoveCursor();
    }
    canvas.setCursorInternal(currentCursor);
  }

  public void setProperties(ICharmTreeViewProperties viewProperties) {
    this.properties = viewProperties;
  }

  public IBoundsCalculator getBoundsCalculator() {
    return boundsCalculator;
  }
}