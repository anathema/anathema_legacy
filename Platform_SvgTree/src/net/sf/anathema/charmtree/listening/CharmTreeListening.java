package net.sf.anathema.charmtree.listening;

import java.awt.Cursor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import net.sf.anathema.charmtree.batik.AnathemaCanvas;
import net.sf.anathema.charmtree.batik.BoundsCalculator;
import net.sf.anathema.charmtree.batik.IBoundsCalculator;
import net.sf.anathema.charmtree.presenter.view.IAnathemaCanvas;
import net.sf.anathema.charmtree.presenter.view.ICharmSelectionListener;
import net.sf.anathema.charmtree.presenter.view.ICharmTreeViewProperties;
import net.sf.anathema.charmtree.provider.svg.ISVGCascadeXMLConstants;
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.control.IClosure;

import org.apache.batik.swing.gvt.Interactor;
import org.apache.batik.util.SVGConstants;
import org.w3c.dom.NodeList;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.MouseEvent;
import org.w3c.dom.svg.SVGDocument;
import org.w3c.dom.svg.SVGGElement;

public class CharmTreeListening {

  private ICharmTreeViewProperties properties;
  private final IAnathemaCanvas canvas;
  private final BoundsCalculator boundsCalculator = new BoundsCalculator();
  private final GenericControl<ICharmSelectionListener> control = new GenericControl<ICharmSelectionListener>();

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
        String charmId = group.getId();
        setCursor(charmId);
        setCanvasTooltip(charmId);
      }
    }
  };

  private final EventListener selectionInvokingListener = new EventListener() {
    public void handleEvent(Event evt) {
      if (evt instanceof MouseEvent && ((MouseEvent) evt).getButton() == 0 && ((MouseEvent) evt).getDetail() == 1) {
        SVGGElement group = (SVGGElement) evt.getCurrentTarget();
        String charmId = group.getId();
        fireCharmSelectionEvent(charmId);
        setCursor(charmId);
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

  public void addCharmSelectionListener(ICharmSelectionListener listener) {
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
      if (groupElement.hasAttribute(ISVGCascadeXMLConstants.ATTRIB_IS_CHARM)) {
        groupElement.removeEventListener("click", selectionInvokingListener, false); //$NON-NLS-1$
        groupElement.removeEventListener("mousemove", cursorTooltipInitListener, false); //$NON-NLS-1$
        groupElement.removeEventListener("mouseout", canvasResettingListener, false); //$NON-NLS-1$
      }
    }
  }

  private void fireCharmSelectionEvent(final String charmId) {
    control.forAllDo(new IClosure<ICharmSelectionListener>() {
      public void execute(ICharmSelectionListener input) {
        input.charmSelected(charmId);
      }
    });
  }

  public void initDocumentListening(SVGDocument document) {
    if (document == null) {
      return;
    }
    NodeList groupElementsList = document.getElementById(ISVGCascadeXMLConstants.VALUE_CASCADE_ID)
        .getElementsByTagName(SVGConstants.SVG_G_TAG);
    for (int index = 0; index < groupElementsList.getLength(); index++) {
      SVGGElement groupElement = (SVGGElement) groupElementsList.item(index);
      if (groupElement.hasAttribute(ISVGCascadeXMLConstants.ATTRIB_IS_CHARM)) {
        groupElement.addEventListener("click", selectionInvokingListener, false); //$NON-NLS-1$
        groupElement.addEventListener("mousemove", cursorTooltipInitListener, false); //$NON-NLS-1$
        groupElement.addEventListener("mouseout", canvasResettingListener, false); //$NON-NLS-1$
      }
    }
  }

  private void setCanvasTooltip(String charmId) {
    canvas.setToolTipText(properties.getToolTip(charmId));
  }

  public void setProperties(ICharmTreeViewProperties viewProperties) {
    this.properties = viewProperties;
  }

  public IBoundsCalculator getBoundsCalculator() {
    return boundsCalculator;
  }

  private void setCursor(String charmId) {
    boolean isCharmSelected = properties.isCharmSelected(charmId);
    boolean isCharmUnlearnable = properties.isCharmUnlearnable(charmId);
    boolean isCharmLearnable = properties.isCharmLearnable(charmId);
    setCursorForCharm(isCharmSelected, isCharmLearnable, isCharmUnlearnable);
    setCanvasTooltip(charmId);
  }

  private void setCursorForCharm(boolean isCharmSelected, boolean isCharmLearnable, boolean isCharmUnlearnable) {
    Cursor currentCursor;
    if (!isCharmSelected) {
      currentCursor = !isCharmLearnable ? properties.getDefaultCursor() : properties.getAddCursor();
    }
    else {
      currentCursor = !isCharmUnlearnable ? properties.getDefaultCursor() : properties.getRemoveCursor();
    }
    canvas.setCursorInternal(currentCursor);
  }
}