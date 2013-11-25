package net.sf.anathema.hero.framework.perspective.sheet;

import net.sf.anathema.framework.environment.ConfigurableDummyObjectFactory;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControlledFileChooserTest {

  private final FileChooserConfiguration configuration = new FileChooserConfiguration("description", "filter");
  private final ConfigurableDummyObjectFactory factory = new ConfigurableDummyObjectFactory();

  @Test
  public void findsFileViaRegisteredFileChooser() throws Exception {
    Path expectation = Paths.get(".");
    registerFileChooserFor(expectation);
    assertThat(new ControlledFileChooser(factory, configuration).getPrintFile(), is(expectation));
  }

  private void registerFileChooserFor(Path expectation) {
    SingleFileChooser singleFileChooser = mock(SingleFileChooser.class);
    when(singleFileChooser.selectSaveFile(configuration)).thenReturn(expectation);
    factory.add(SingleFileChooser.class, singleFileChooser);
  }
}