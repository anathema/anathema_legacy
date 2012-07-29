package net.sf.anathema.characterengine;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class QualityKeyTest {

  @Test
  public void equalsKeysWithSameNameAndType() throws Exception {
    QualityKey firstKey = new QualityKey(new Type("type"), new Name("name"));
    QualityKey secondKey = new QualityKey(new Type("type"), new Name("name"));
    assertThat(firstKey.equals(secondKey), is(true));
  }

  @Test
  public void hasSameHashCodeAsKeyWithSameNameAndType() throws Exception {
    QualityKey firstKey = new QualityKey(new Type("type"), new Name("name"));
    QualityKey secondKey = new QualityKey(new Type("type"), new Name("name"));
    assertThat(firstKey.hashCode(), is(secondKey.hashCode()));
  }
}