package net.sf.anathema.platform.tree.display.transform;

import org.apache.commons.lang3.builder.EqualsBuilder;

import java.util.ArrayList;
import java.util.List;

public class AgnosticTransform {

  private List<TransformOperation> operations = new ArrayList<>();

  public void add(TransformOperation operation) {
    operations.add(operation);
  }

  public void preconcatenate(TransformOperation... newOperations) {
    ArrayList<TransformOperation> inverse = new ArrayList<>();
    for (TransformOperation operation : newOperations) {
      inverse.add(0, operation);

    }
    for (TransformOperation transformOperation : inverse) {
      operations.add(0, transformOperation);
    }
  }

  public void visitOperations(TransformOperationVisitor visitor) {
    for (TransformOperation operation : operations) {
      operation.accept(visitor);
    }
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


  @Override
  public boolean equals(Object obj) {
    return EqualsBuilder.reflectionEquals(this, obj);
  }

  @Override
  public int hashCode() {
    return operations.hashCode();
  }
}