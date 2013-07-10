package net.sf.anathema.character.main.framework;

import net.sf.anathema.lib.resources.ResourceFile;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CharacterTemplateResourceCacheTest {

  @Test
  public void returnsNoTemplatesWhenNoneAreRegistered() throws Exception {
    CharacterTemplateResourceCache cache = new CharacterTemplateResourceCache();
    ResourceFile[] templates = cache.getTemplateResourcesForType();
    assertThat(templates.length, is(0));
  }
}
