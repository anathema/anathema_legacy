package net.sf.anathema.character.generic.impl.bootjob;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TemplateTransformerTest {

  private TemplateTransformer transformer = new TemplateTransformer();

  @Test
  public void returnsOriginalIfAllElseFails() throws Exception {
    assertTransformationResultsIn("anyString", "anyString");
  }

  @Test
  public void onlyReplacesTemplateSubtype() throws Exception {
    String input = "<ExaltedCharacter repositoryId=\"RevisedLoyalAbyssal\"/>";
    assertTransformationResultsIn(input, input);
  }

  private void assertTransformationResultsIn(String input, String expectation) {
    String result = transformer.transform(input);
    assertThat(result, is(expectation));
  }
}
