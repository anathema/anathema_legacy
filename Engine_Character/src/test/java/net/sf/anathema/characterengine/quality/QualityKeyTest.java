package net.sf.anathema.characterengine.quality;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class QualityKeyTest {
  QualityKey firstKey = new QualityKey(new Type("type"), new Name("name"));

  @Test
  public void equalsKeysWithSameNameAndType() throws Exception {
    QualityKey secondKey = new QualityKey(new Type("type"), new Name("name"));
    assertThat(firstKey.equals(secondKey), is(true));
  }

  @Test
  public void hasSameHashCodeAsKeyWithSameNameAndType() throws Exception {
    QualityKey secondKey = new QualityKey(new Type("type"), new Name("name"));
    assertThat(firstKey.hashCode(), is(secondKey.hashCode()));
  }

  @Test
  public void operatesOnClosureWithType() throws Exception {
    TypeClosure closure = mock(TypeClosure.class);
    firstKey.withTypeDo(closure);
    verify(closure).execute(new Type("type"));
  }

  @Test
  public void operatesOnClosureWithName() throws Exception {
    NameClosure closure = mock(NameClosure.class);
    firstKey.withNameDo(closure);
    verify(closure).execute(new Name("name"));
  }
}