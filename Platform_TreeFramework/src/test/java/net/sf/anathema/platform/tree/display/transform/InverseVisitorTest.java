package net.sf.anathema.platform.tree.display.transform;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class InverseVisitorTest {
  AgnosticTransform original = new AgnosticTransform();
  AgnosticTransform expected = new AgnosticTransform();

  @Test
  public void invertsTranslation() throws Exception {
    original.add(new Translation(2, 3));
    expected.add(new Translation(-2, -3));
    assertInverseEqualsExpectation();
  }

  @Test
  public void invertsScale() throws Exception {
    original.add(new Scale(2));
    expected.add(new Scale(0.5));
    assertInverseEqualsExpectation();
  }

  @Test
  public void invertsRotation() throws Exception {
    original.add(new Rotation(45));
    expected.add(new Rotation(-45));
    assertInverseEqualsExpectation();
  }

  @Test
  public void inverseMatrixPerformsOperationsInInvertedOrder() throws Exception {
    original.add(new Rotation(45));
    original.add(new Scale(5));
    expected.add(new Scale(0.2));
    expected.add(new Rotation(-45));
    assertInverseEqualsExpectation();
  }

  private void assertInverseEqualsExpectation() {
    InverseVisitor visitor = new InverseVisitor();
    original.visitOperations(visitor);
    assertThat(visitor.getInverse(), is(expected));
  }
}