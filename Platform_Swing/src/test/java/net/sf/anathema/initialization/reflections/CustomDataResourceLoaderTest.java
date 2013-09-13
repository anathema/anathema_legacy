package net.sf.anathema.initialization.reflections;

import net.sf.anathema.initialization.repository.CustomDataResourceLoader;
import net.sf.anathema.initialization.repository.RepositoryLocationResolver;
import net.sf.anathema.framework.environment.resources.ResourceFile;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CustomDataResourceLoaderTest {

  @Rule
  public TemporaryFolder folder = new TemporaryFolder();
  private RepositoryLocationResolver resolver = mock(RepositoryLocationResolver.class);
  private CustomDataResourceLoader loader;
  private File customFolder;

  @Before
  public void connectResolverWithFolder() throws Exception {
    when(resolver.resolve()).thenReturn(folder.getRoot().getAbsolutePath());
    customFolder = folder.newFolder("custom");
    loader = new CustomDataResourceLoader(resolver);
  }

  @Test
  public void returnsNothingIfThereIsNoMatchingFile() throws Exception {
    Set<ResourceFile> result = loader.getResourcesMatching("NOMATCH");
    assertThat(result.isEmpty(), is(true));
  }

  @Test
  public void returnsNamedFile() throws Exception {
    createFile("MATCH");
    Set<ResourceFile> result = loader.getResourcesMatching("MATCH");
    assertThat(result.size(), is(1));
  }

  @Test
  public void returnsFileMatchingPattern() throws Exception {
    createFile("matching.properties");
    createFile("mismatching.xml");
    Set<ResourceFile> result = loader.getResourcesMatching(".*\\.properties");
    assertThat(result.size(), is(1));
  }

  @Test
  public void doesNotFailOnMoreComplexPatterns() throws Exception {
    createFile("mismatching.properties");
    createFile("Charms_Solar_SecondEdition_Matching.xml");
    Set<ResourceFile> result = loader.getResourcesMatching("Charms_(.*?)_(.*?)(?:_.*)?\\.xml");
    assertThat(result.size(), is(1));
  }

  @SuppressWarnings("ResultOfMethodCallIgnored")
  private void createFile(String name) throws IOException {
    File file = new File(customFolder, name);
    file.createNewFile();
  }
}
