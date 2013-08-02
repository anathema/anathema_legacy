package net.sf.anathema.platform.tree.display.transform;

import net.sf.anathema.framework.ui.Coordinate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;

import static java.lang.Math.pow;

public class AgnosticTransform {

  public double a1 = 1;
  public double b1 = 0;
  public double c1 = 0;
  public double a2 = 0;
  public double b2 = 1;
  public double c2 = 0;
  public double a3 = 0;
  public double b3 = 0;
  public double c3 = 1;

  public void add(TransformOperation operation) {
    modifyMatrix(operation);
  }

  public void preconcatenate(TransformOperation... newOperations) {
    ArrayList<TransformOperation> inverse = new ArrayList<>();
    for (TransformOperation operation : newOperations) {
      inverse.add(0, operation);
    }
    for (TransformOperation transformOperation : inverse) {
      modifyMatrix(transformOperation);
    }
  }

  public boolean isScaleBetween(double maxZoomOutScale, double maxZoomInScale) {
    double minimumDeterminant = pow(maxZoomOutScale, 2);
    double maximumDeterminant = pow(maxZoomInScale, 2);
    double actualDeterminant = getDeterminant();
    return minimumDeterminant <= actualDeterminant && actualDeterminant <= maximumDeterminant;
  }

  public double getScale() {
    return Math.sqrt(getDeterminant());
  }

  private double getDeterminant() {
    return a1 * b2 - b1 * a2;
  }

  public AgnosticTransform invert() {
    double determinant = getDeterminant();
    AgnosticTransform inverse = new AgnosticTransform();
    inverse.a1 = b2 / determinant;
    inverse.a2 = -a2 / determinant;
    inverse.b1 = -b1 / determinant;
    inverse.b2 = a1 / determinant;
    inverse.c1 = (b1 * c2 - b2 * c1) / determinant;
    inverse.c2 = (a2 * c1 - a1 * c2) / determinant;
    return inverse;
  }

  public Coordinate apply(Coordinate input) {
    double newX = input.x * a1 + input.y * b1 + c1;
    double newY = input.x * a2 + input.y * b2 + c2;
    return new Coordinate(newX, newY);
  }

  public void setToIdentity() {
    a1 = 1;
    b1 = 0;
    c1 = 0;
    a2 = 0;
    b2 = 1;
    c2 = 0;
    a3 = 0;
    b3 = 0;
    c3 = 1;
  }

  public AgnosticTransform createCopy() {
    AgnosticTransform copy = new AgnosticTransform();
    copy.a1 = a1;
    copy.a2 = a2;
    copy.b1 = b1;
    copy.b2 = b2;
    copy.c1 = c1;
    copy.c2 = c2;
    return copy;
  }

  @Override
  public boolean equals(Object obj) {
    return EqualsBuilder.reflectionEquals(this, obj);
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this);
  }

  @Override
  public String toString() {
    return "[" + a1 + "\t" + b1 + "\t" + c1 + "]\n" +
            "[" + a2 + "\t" + b2 + "\t" + c2 + "]\n" +
            "[" + a3 + "\t" + b3 + "\t" + c3 + "]\n";
  }

  private void modifyMatrix(TransformOperation operation) {
    operation.accept(new TransformOperationVisitor() {
      @Override
      public void visitTranslation(Translation translation) {
        c1 += translation.x;
        c2 += translation.y;
      }

      @Override
      public void visitScale(Scale scale) {
        a1 *= scale.scale;
        b2 *= scale.scale;
      }

      @Override
      public void visitRotation(Rotation rotation) {
        a1 *= Math.cos(rotation.angle);
        b1 *= Math.sin(rotation.angle);
        a2 *= Math.cos(rotation.angle);
        b2 *= -Math.sin(rotation.angle);
      }
    });
  }
}