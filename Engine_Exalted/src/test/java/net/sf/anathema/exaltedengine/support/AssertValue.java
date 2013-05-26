package net.sf.anathema.exaltedengine.support;

import net.sf.anathema.characterengine.persona.QualityClosure;
import net.sf.anathema.characterengine.quality.Quality;
import net.sf.anathema.exaltedengine.numericquality.NumericValue;
import net.sf.anathema.exaltedengine.numericquality.QualityWithValue;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import static org.hamcrest.MatcherAssert.assertThat;

public class AssertValue implements QualityClosure {

  private final int value;

  public AssertValue(int value) {
    this.value = value;
  }

  @Override
  public void execute(Quality quality) {
    QualityWithValue qualityWithValue = (QualityWithValue) quality;
    assertThat(qualityWithValue, hasValue(value));
  }

  private Matcher<QualityWithValue> hasValue(final int value) {
    return new TypeSafeMatcher<QualityWithValue>() {
      @Override
      protected boolean matchesSafely(QualityWithValue quality) {
        return quality.hasValue(new NumericValue(value));
      }

      @Override
      public void describeTo(Description description) {
        description.appendText("a quality with value ").appendValue(value);
      }
    };
  }
}
