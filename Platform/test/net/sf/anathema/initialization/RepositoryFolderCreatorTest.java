package net.sf.anathema.initialization;

import java.io.File;

import net.sf.anathema.framework.repository.RepositoryException;
import net.sf.anathema.initialization.repository.IStringResolver;
import net.sf.anathema.initialization.repository.RepositoryFolderCreator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RepositoryFolderCreatorTest {

  private DummyFileSystemAbstraction dummyFileSystemAbstraction;

  @Before
  public void setUp() {
    dummyFileSystemAbstraction = new DummyFileSystemAbstraction();
  }

  @Test
  public void testExistingFolder() throws Exception {
    dummyFileSystemAbstraction.addExistingFile(new File("folder")); //$NON-NLS-1$
    File repositoryFolder = createRepositoryFolder("folder"); //$NON-NLS-1$
    Assert.assertFalse(dummyFileSystemAbstraction.wasCreated(repositoryFolder));
    Assert.assertEquals(repositoryFolder, new File("folder")); //$NON-NLS-1$
  }

  @Test
  public void testNonExistingFoldersWillBeCreated() throws Exception {
    File repositoryFolder = createRepositoryFolder("nonExistingFolder"); //$NON-NLS-1$
    Assert.assertEquals(repositoryFolder, new File("nonExistingFolder")); //$NON-NLS-1$
    Assert.assertTrue(dummyFileSystemAbstraction.wasCreated(new File("nonExistingFolder"))); //$NON-NLS-1$
  }

  @Test(expected = RepositoryException.class)
  public void testWriteProtectedFile() throws Exception {
    final String writeProtectedFilePath = "writeProtected"; //$NON-NLS-1$
    dummyFileSystemAbstraction.addWriteProtectedFile(new File(writeProtectedFilePath));
    createRepositoryFolder(writeProtectedFilePath);
  }

  @Test(expected = RepositoryException.class)
  public void testReadProtectedFile() throws Exception {
    final String readProtectedFilePath = "readProtected"; //$NON-NLS-1$
    dummyFileSystemAbstraction.addReadProtectedFile(new File(readProtectedFilePath));
    createRepositoryFolder(readProtectedFilePath);
  }

  private File createRepositoryFolder(final String repositoryPath) throws RepositoryException {
    return new RepositoryFolderCreator(dummyFileSystemAbstraction, new IStringResolver() {
      public String resolve() {
        return repositoryPath;
      }
    }).createRepositoryFolder();
  }
}