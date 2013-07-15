package net.sf.anathema.initialization.reflections;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ExternalResourceFileTest {

  @Rule
  public TemporaryFolder folder = new TemporaryFolder();

  @Test
  public void returnsFilePathAsUnix() throws Exception {
    File file = new File(folder.newFolder("custom"), "X");
    ExternalResourceFile resourceFile = new ExternalResourceFile(file);
    assertThat(resourceFile.getFileName().endsWith("custom/X"), is(true));
  }


  @Test
  public void returnsFileUrl() throws Exception {
    File file = folder.newFile("X");
    ExternalResourceFile resourceFile = new ExternalResourceFile(file);
    assertThat(resourceFile.getURL(), is(file.toURI().toURL()));
  }
}
