package net.sf.anathema.initialization.repository.test;

import java.io.File;

import net.sf.anathema.framework.repository.RepositoryException;
import net.sf.anathema.initialization.repository.IStringResolver;
import net.sf.anathema.initialization.repository.RepositoryFolderCreator;
import net.sf.anathema.lib.testing.BasicTestCase;
import net.sf.anathema.lib.testing.ExceptionConvertingBlock;

public class RepositoryFolderCreatorTest extends BasicTestCase {

  private DummyFileSystemAbstraction dummyFileSystemAbstraction;

  @Override
  protected void setUp() {
    dummyFileSystemAbstraction = new DummyFileSystemAbstraction();
  }

  public void testExistingFolder() throws Exception {
    dummyFileSystemAbstraction.addExistingFile(new File("folder")); //$NON-NLS-1$
    File repositoryFolder = createRepositoryFolder("folder"); //$NON-NLS-1$
    assertFalse(dummyFileSystemAbstraction.wasCreated(repositoryFolder));
    assertEquals(repositoryFolder, new File("folder")); //$NON-NLS-1$
  }

  public void testNonExistingFoldersWillBeCreated() throws Exception {
    File repositoryFolder = createRepositoryFolder("nonExistingFolder"); //$NON-NLS-1$
    assertEquals(repositoryFolder, new File("nonExistingFolder")); //$NON-NLS-1$
    assertTrue(dummyFileSystemAbstraction.wasCreated(new File("nonExistingFolder"))); //$NON-NLS-1$
  }

  public void testWriteProtectedFile() throws Exception {
    final String writeProtectedFilePath = "writeProtected"; //$NON-NLS-1$
    dummyFileSystemAbstraction.addWriteProtectedFile(new File(writeProtectedFilePath));
    assertThrowsException(RepositoryException.class, new ExceptionConvertingBlock() {
      @Override
      public void executeExceptionThrowing() throws Exception {
        createRepositoryFolder(writeProtectedFilePath);
      }
    });
  }

  public void testReadProtectedFile() throws Exception {
    final String readProtectedFilePath = "readProtected"; //$NON-NLS-1$
    dummyFileSystemAbstraction.addReadProtectedFile(new File(readProtectedFilePath));
    assertThrowsException(RepositoryException.class, new ExceptionConvertingBlock() {
      @Override
      public void executeExceptionThrowing() throws Exception {
        createRepositoryFolder(readProtectedFilePath);
      }
    });
  }

  private File createRepositoryFolder(final String repositoryPath) throws RepositoryException {
    return new RepositoryFolderCreator(dummyFileSystemAbstraction, new IStringResolver() {
      public String resolve() {
        return repositoryPath;
      }
    }).createRepositoryFolder();
  }
}