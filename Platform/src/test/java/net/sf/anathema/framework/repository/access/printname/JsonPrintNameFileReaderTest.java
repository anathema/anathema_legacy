package net.sf.anathema.framework.repository.access.printname;

import net.sf.anathema.framework.item.IItemType;
import org.apache.commons.io.FileUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;

public class JsonPrintNameFileReaderTest {

  @Rule
  public TemporaryFolder folder = new TemporaryFolder();

  @SuppressWarnings("ResultOfMethodCallIgnored")
  @Test
  public void closesFileAfterReading() throws Exception {
    File testFile = folder.newFile();
    FileUtils.writeStringToFile(testFile, "{repositoryId: x, printName: y}");
    IItemType itemType = mock(IItemType.class);
    new JsonPrintNameFileReader().readPrintName(testFile, itemType);
    testFile.delete();
    assertThat(testFile.exists(), is(false));
  }
}
