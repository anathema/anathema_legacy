package net.sf.anathema.character.main.framework;

import net.sf.anathema.lib.resources.ResourceFile;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CharacterTemplateResourceCacheTest {

  @Test
  public void returnsNoTemplatesWhenNoneAreRegistered() throws Exception {
    Map<String, List<ResourceFile>> hashMap = new HashMap<>();
    CharacterTemplateResourceCache cache = new CharacterTemplateResourceCache(hashMap);
    ResourceFile[] templates = cache.getTemplateResourcesForType("Solar");
    assertThat(templates.length, is(0));
  }
}
