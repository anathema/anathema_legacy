package net.sf.anathema.initialization.reflections;

import com.google.common.collect.Sets;
import net.sf.anathema.framework.environment.resources.ResourceFile;
import org.junit.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AggregatedResourceLoaderTest {

  @Test
  public void isEmptyWithoutDelegate() throws Exception {
    AggregatedResourceLoader loader = new AggregatedResourceLoader();
    Set<ResourceFile> result = loader.getResourcesMatching("X");
    assertThat(result.isEmpty(), is(true));
  }

  @Test
  public void providesResourcesFromDelegate() throws Exception {
    ResourceLoader delegate = mock(ResourceLoader.class);
    ResourceFile element = mock(ResourceFile.class);
    when(delegate.getResourcesMatching("X")).thenReturn(Sets.newHashSet(element));
    AggregatedResourceLoader loader = new AggregatedResourceLoader(delegate);
    Set<ResourceFile> result = loader.getResourcesMatching("X");
    assertThat(result, hasItem(element));
  }
}
