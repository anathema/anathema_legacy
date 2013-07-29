package net.sf.anathema.platform.tree.display.transform;

import net.sf.anathema.framework.ui.Coordinate;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ApplyVisitorTest {
  AgnosticTransform transform = new AgnosticTransform();
  Coordinate original = new Coordinate(10, 20);

  @Test
  public void translatesPoint() throws Exception {
    transform.add(new Translation(2, 3));
    ApplyVisitor visitor = new ApplyVisitor(original);
    transform.visitOperations(visitor);
    assertThat(visitor.getTransformedCoordinate(), is(new Coordinate(12, 23)));
  }

  @Test
  public void scalesPoint() throws Exception {
    transform.add(new Scale(7));
    ApplyVisitor visitor = new ApplyVisitor(original);
    transform.visitOperations(visitor);
    assertThat(visitor.getTransformedCoordinate(), is(new Coordinate(70, 140)));
  }

  @Test
  public void rotatesPointAroundOrigin() throws Exception {
    transform.add(new Rotation(Math.PI));
    ApplyVisitor visitor = new ApplyVisitor(original);
    transform.visitOperations(visitor);
    assertThat(visitor.getTransformedCoordinate(), is(new Coordinate(-10, -20)));
  }
}