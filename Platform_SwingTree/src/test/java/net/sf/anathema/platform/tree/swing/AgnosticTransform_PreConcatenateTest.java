package net.sf.anathema.platform.tree.swing;

import net.sf.anathema.platform.tree.display.transform.AgnosticTransform;
import net.sf.anathema.platform.tree.display.transform.PreConcatenate;
import net.sf.anathema.platform.tree.display.transform.Scale;
import net.sf.anathema.platform.tree.display.transform.Translation;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AgnosticTransform_PreConcatenateTest {
  AgnosticTransform transform = new AgnosticTransform();
  AgnosticTransform preTransform = new AgnosticTransform();
  AgnosticTransform expected = new AgnosticTransform();

  @Test
  public void preconcatenationOfEmptyMatrixDoesNotChangeIt() throws Exception {
    assertThatTransformEqualsExpected();
  }

  @Test
  public void preconcatenationEqualsAdditionWhenNothingComesAfter() throws Exception {
    preTransform.add(new Scale(4));
    expected.add(new Scale(4));
    assertThatTransformEqualsExpected();
  }

  @Test
  public void preconcatenationEqualsAdditionInOrder() throws Exception {
    preTransform.add(new Scale(4));
    transform.add(new Translation(3, 4));
    expected.add(new Scale(4));
    expected.add(new Translation(3, 4));
    assertThatTransformEqualsExpected();
  }

  @Test
  public void preconcatenationEqualsAdditionInInverseOrder() throws Exception {
    preTransform.add(new Scale(4));
    preTransform.add(new Scale(0.5));
    transform.add(new Translation(3, 4));
    expected.add(new Scale(4));
    expected.add(new Scale(0.5));
    expected.add(new Translation(3, 4));
    assertThatTransformEqualsExpected();
  }

  private void assertThatTransformEqualsExpected() {
    transform.add(new PreConcatenate(preTransform));
    assertThat(SwingTransformer.convert(transform), is(SwingTransformer.convert(expected)));
  }
}
