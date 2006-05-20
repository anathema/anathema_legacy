package net.sf.anathema.charmtree.listening;

import java.awt.Cursor;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.charmtree.batik.AnathemaCanvas;
import net.sf.anathema.charmtree.batik.BoundsCalculator;
import net.sf.anathema.charmtree.batik.IBoundsCalculator;
import net.sf.anathema.charmtree.presenter.view.IAnathemaCanvas;
import net.sf.anathema.charmtree.presenter.view.ICharmSelectionListener;
import net.sf.anathema.charmtree.presenter.view.ICharmTreeViewProperties;
import net.sf.anathema.charmtree.provider.svg.ISVGCascadeXMLConstants;

import org.apache.batik.swing.gvt.Interactor;
import org.w3c.dom.NodeList;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.MouseEvent;
import org.w3c.dom.svg.SVGDocument;
import org.w3c.dom.svg.SVGGElement;

public class CharmTreeListening {

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

  private EventListener cursorTooltipInitListener = new EventListener() {
    public void handleEvent(Event evt) {
      if (evt instanceof MouseEvent) {
        SVGGElement group = (SVGGElement) evt.getCurrentTarget();
        String charmId = group.getAttribute(ISVGCascadeXMLConstants.ATTRIB_ID);
        boolean isCharmSelected = properties.isCharmSelected(charmId);
        boolean isCharmUnlearnable = properties.isCharmUnlearnable(charmId);
        boolean isCharmLearnable = properties.isCharmLearnable(charmId);
        setCursorForCharm(isCharmSelected, isCharmLearnable, isCharmUnlearnable);
        setCanvasTooltip(charmId);
      }
    }
  };

  private final EventListener selectionInvokingListener = new EventListener() {
    public void handleEvent(Event evt) {
      if (evt instanceof MouseEvent && ((MouseEvent) evt).getButton() == 0) {
        SVGGElement group = (SVGGElement) evt.getCurrentTarget();
        String charmId = group.getAttribute(ISVGCascadeXMLConstants.ATTRIB_ID);
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
    if (document == null) {
      return;
    }
    NodeList groupElementsList = document.getElementById(ISVGCascadeXMLConstants.VALUE_CASCADE_ID)
        .getElementsByTagName(ISVGCascadeXMLConstants.TAG_G);
    for (int index = 0; index < groupElementsList.getLength(); index++) {
      SVGGElement groupElement = (SVGGElement) groupElementsList.item(index);
      if (groupElement.hasAttribute("isCharm")) {
        groupElement.removeEventListener("click", selectionInvokingListener, false); //$NON-NLS-1$
        groupElement.removeEventListener("mousemove", cursorTooltipInitListener, false); //$NON-NLS-1$
        groupElement.removeEventListener("mouseout", canvasResettingListener, false); //$NON-NLS-1$
      }
    }
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
    if (document == null) {
      return;
    }
    NodeList groupElementsList = document.getElementById(ISVGCascadeXMLConstants.VALUE_CASCADE_ID)
        .getElementsByTagName(ISVGCascadeXMLConstants.TAG_G);
    for (int index = 0; index < groupElementsList.getLength(); index++) {
      SVGGElement groupElement = (SVGGElement) groupElementsList.item(index);
      if (groupElement.hasAttribute("isCharm")) {
        groupElement.addEventListener("click", selectionInvokingListener, false); //$NON-NLS-1$
        groupElement.addEventListener("mousemove", cursorTooltipInitListener, false); //$NON-NLS-1$
        groupElement.addEventListener("mouseout", canvasResettingListener, false); //$NON-NLS-1$
      }
    }
  }

  private void setCanvasTooltip(String charmId) {
    canvas.setToolTipText(properties.getToolTip(charmId));
  }

  private void setCursorForCharm(boolean isCharmSelected, boolean isCharmLearnable, boolean isCharmUnlearnable) {
    if (!isCharmSelected) {
      currentCursor = !isCharmLearnable ? properties.getDefaultCursor() : properties.getAddCursor();
    }
    else {
      currentCursor = !isCharmUnlearnable ? properties.getDefaultCursor() : properties.getRemoveCursor();
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