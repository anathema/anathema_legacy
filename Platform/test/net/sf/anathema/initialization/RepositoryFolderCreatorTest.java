package net.sf.anathema.initialization;

import net.sf.anathema.framework.repository.RepositoryException;
import net.sf.anathema.initialization.repository.IStringResolver;
import net.sf.anathema.initialization.repository.RepositoryFolderCreator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class RepositoryFolderCreatorTest {

  private DummyFileSystemAbstraction dummyFileSystemAbstraction;

  @Before
  public void setUp() {
    dummyFileSystemAbstraction = new DummyFileSystemAbstraction();
  }

  @Test
  public void testExistingFolder() throws Exception {
    dummyFileSystemAbstraction.addExistingFile(new File("folder"));
    File repositoryFolder = createRepositoryFolder("folder");
    Assert.assertFalse(dummyFileSystemAbstraction.wasCreated(repositoryFolder));
    Assert.assertEquals(repositoryFolder, new File("folder"));
  }

  @Test
  public void testNonExistingFoldersWillBeCreated() throws Exception {
    File repositoryFolder = createRepositoryFolder("nonExistingFolder");
    Assert.assertEquals(repositoryFolder, new File("nonExistingFolder"));
    Assert.assertTrue(dummyFileSystemAbstraction.wasCreated(new File("nonExistingFolder")));
  }

  @Test(expected = RepositoryException.class)
  public void testWriteProtectedFile() throws Exception {
    final String writeProtectedFilePath = "writeProtected";
    dummyFileSystemAbstraction.addWriteProtectedFile(new File(writeProtectedFilePath));
    createRepositoryFolder(writeProtectedFilePath);
  }

  @Test(expected = RepositoryException.class)
  public void testReadProtectedFile() throws Exception {
    final String readProtectedFilePath = "readProtected";
    dummyFileSystemAbstraction.addReadProtectedFile(new File(readProtectedFilePath));
    createRepositoryFolder(readProtectedFilePath);
  }

  private File createRepositoryFolder(final String repositoryPath) throws RepositoryException {
    return new RepositoryFolderCreator(dummyFileSystemAbstraction, new IStringResolver() {
      @Override
      public String resolve() {
        return repositoryPath;
      }
    }).createRepositoryFolder();
  }
}