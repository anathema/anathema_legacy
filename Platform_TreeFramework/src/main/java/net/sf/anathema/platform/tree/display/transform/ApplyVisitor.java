package net.sf.anathema.platform.tree.display.transform;

import net.sf.anathema.framework.ui.Coordinate;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class ApplyVisitor implements TransformOperationVisitor {
  private Coordinate current;

  public ApplyVisitor(Coordinate original) {
    this.current = original;
  }

  @Override
  public void visitTranslation(Translation translation) {
    current = new Coordinate(current.x + translation.x, current.y + translation.y);
  }

  @Override
  public void visitScale(Scale scale) {
    current = new Coordinate(current.x * scale.scale, current.y * scale.scale);
  }

  @Override
  public void visitRotation(Rotation rotation) {
    double newX = current.x * cos(rotation.angle) + current.y * sin(rotation.angle);
    double newY = current.y * cos(rotation.angle) + current.x * sin(rotation.angle);
    current = new Coordinate(newX, newY);
  }

  public Coordinate getTransformedCoordinate() {
    return current;
  }
}
