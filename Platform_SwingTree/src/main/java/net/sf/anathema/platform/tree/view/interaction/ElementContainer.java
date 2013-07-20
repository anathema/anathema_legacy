package net.sf.anathema.platform.tree.view.interaction;

import net.sf.anathema.framework.ui.Coordinate;
import net.sf.anathema.platform.tree.view.draw.GraphicsElement;
import net.sf.anathema.platform.tree.view.draw.InteractiveGraphicsElement;

import java.util.Iterator;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class ElementContainer implements Iterable<GraphicsElement> {
  private List<GraphicsElement> elements = newArrayList();
  private List<InteractiveGraphicsElement> interactiveElements = newArrayList();

  public void add(InteractiveGraphicsElement element) {
    interactiveElements.add(element);
    add((GraphicsElement) element);
  }

  public void add(GraphicsElement element) {
    elements.add(element);
  }

  public Executor onElementAtPoint(Coordinate point) {
    for (InteractiveGraphicsElement element : interactiveElements)
      if (element.contains(point)) {
        return new ElementExecutor(element);
      }
    return new DefaultExecutor();
  }

  @Override
  public Iterator<GraphicsElement> iterator() {
    return elements.iterator();
  }

  public void clear() {
    elements.clear();
    interactiveElements.clear();
  }
}