package net.sf.anathema.hero.platform;

import net.sf.anathema.character.framework.type.CharacterTypes;
import net.sf.anathema.framework.item.RepositoryConfiguration;
import net.sf.anathema.framework.repository.IRepositoryFileResolver;
import net.sf.anathema.framework.repository.access.printname.SimpleRepositoryId;
import net.sf.anathema.hero.dummy.DummyMundaneCharacterType;
import net.sf.anathema.hero.framework.perspective.model.CharacterReference;
import org.apache.commons.io.FileUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.mockito.Mockito;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JsonCharacterReferenceScannerTest {

  @Rule
  public TemporaryFolder folder = new TemporaryFolder();

  @SuppressWarnings("ResultOfMethodCallIgnored")
  @Test
  public void closesFileAfterScanning() throws Exception {
    File testFile = folder.newFile();
    FileUtils.writeStringToFile(testFile, "{repositoryId: x, printName: y}");
    CharacterTypes types = mock(CharacterTypes.class);
    when(types.findById(anyString())).thenReturn(new DummyMundaneCharacterType());
    IRepositoryFileResolver resolver = prepareResolverToReturnFile(testFile);
    CharacterReference reference = new CharacterReference(new SimpleRepositoryId("x"), "y");
    new JsonCharacterReferenceScanner(types, resolver).getCasteType(reference);
    testFile.delete();
    assertThat(testFile.exists(), is(false));
  }

  private IRepositoryFileResolver prepareResolverToReturnFile(File testFile) {
    IRepositoryFileResolver resolver = mock(IRepositoryFileResolver.class);
    when(resolver.getMainFile(Mockito.any(RepositoryConfiguration.class), Mockito.any(String.class))).thenReturn(testFile);
    return resolver;
  }
}
