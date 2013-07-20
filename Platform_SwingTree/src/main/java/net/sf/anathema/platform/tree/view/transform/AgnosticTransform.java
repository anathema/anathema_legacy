package net.sf.anathema.platform.tree.view.transform;

import net.sf.anathema.platform.tree.view.draw.TransformOperation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AgnosticTransform implements Iterable<TransformOperation> {

  private List<TransformOperation> operations = new ArrayList<>();

  public void add(TransformOperation operation) {
    operations.add(operation);
  }

  @Override
  public Iterator<TransformOperation> iterator() {
    return operations.iterator();
  }

  public void setToIdentity() {
    operations.clear();
  }

  public AgnosticTransform createCopy() {
    AgnosticTransform copy = new AgnosticTransform();
    for (TransformOperation operation : operations) {
       copy.add(operation);
    }
    return copy;
  }
}
