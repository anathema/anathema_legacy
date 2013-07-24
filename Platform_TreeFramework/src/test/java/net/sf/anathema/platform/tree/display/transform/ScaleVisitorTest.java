package net.sf.anathema.platform.tree.display.transform;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ScaleVisitorTest {
  private final AgnosticTransform transform = new AgnosticTransform();
  private final ScaleVisitor visitor = new ScaleVisitor();

  @Test
  public void originalTransformHasScale1() throws Exception {
    double scale = calculateScale();
    assertThat(scale, is(1.0));
  }

  @Test
  public void findsScaleForSingleScaleOperation() throws Exception {
    transform.add(new Scale(2));
    double scale = calculateScale();
    assertThat(scale, is(2.0));
  }

  @Test
  public void findsScaleForPreconcatenatedScaleOperation() throws Exception {
    AgnosticTransform newTransform = new AgnosticTransform();
    newTransform.add(new Scale(4));
    transform.add(new PreConcatenate(newTransform));
    double scale = calculateScale();
    assertThat(scale, is(4.0));
  }

  private double calculateScale() {
    for (TransformOperation transformOperation : transform) {
      transformOperation.accept(visitor);
    }
    return visitor.getScale();
  }

}
