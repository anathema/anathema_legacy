package net.sf.anathema.character.main.framework;

import com.google.common.collect.Iterables;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SimpleCharacterTemplateCacheTest {

  @Test
  public void returnsNoTemplatesWhenNoneAreRegistered() throws Exception {
    SimpleCharacterTemplateCache cache = new SimpleCharacterTemplateCache();
    assertThat(Iterables.size(cache), is(0));
  }
}
