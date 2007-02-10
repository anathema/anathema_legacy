package net.sf.anathema.platform.svgtree.view.batik;

import net.sf.anathema.platform.svgtree.presenter.view.IAnathemaCanvas;
import net.sf.anathema.platform.svgtree.presenter.view.IDocumentLoadedListener;

import org.w3c.dom.events.EventListener;

public class EventListenerRegistratingLoadListener implements IDocumentLoadedListener {

  private final EventListener listener;
  private final IAnathemaCanvas canvas;
  private final String eventType;

  public EventListenerRegistratingLoadListener(String eventType, EventListener listener, IAnathemaCanvas canvas) {
    this.eventType = eventType;
    this.listener = listener;
    this.canvas = canvas;
  }

  public void documentLoaded() {
    canvas.addEventListener(eventType, listener, false);
  }
}