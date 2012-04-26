package net.sf.anathema.initialization.reflections;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import net.sf.anathema.initialization.repository.RepositoryFolderCreator;
import net.sf.anathema.initialization.repository.RepositoryLocationResolver;
import net.sf.anathema.initialization.repository.IOFileSystemAbstraction;
import net.sf.anathema.initialization.repository.IStringResolver;
import net.sf.anathema.framework.repository.RepositoryException;
import net.sf.anathema.lib.resources.ResourceFile;

import net.disy.commons.core.message.Message;
import net.disy.commons.swing.dialog.message.MessageDialogFactory;

import java.io.File;
import java.io.IOException;
import java.io.FilenameFilter;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CustomDataResourceLoader implements ResourceLoader {
  private final File customFolder;

  public CustomDataResourceLoader(RepositoryLocationResolver resolver) {
		this.customFolder = getCustomFolder(resolver.resolve(), "custom");
	}
	
	private File getCustomFolder(String repositoryFolderName, String customFolderName) {
    try {
			final File repositoryFolder = new File( repositoryFolderName );
      IOFileSystemAbstraction fileSystem = new IOFileSystemAbstraction();
      new RepositoryFolderCreator(fileSystem, new IStringResolver() {
        @Override
        public String resolve() {
          try {
            return repositoryFolder.getCanonicalPath();
          } catch (IOException e) {
            throw new RepositoryException("Could not resolve path of repository");
          }
        }
      }).createRepositoryFolder();
			return createCustomFolder(new File(repositoryFolder, customFolderName));
    } catch (RepositoryException e) {
      Throwable cause = e.getCause();
      if (cause == null) {
        cause = e;
      }
      MessageDialogFactory.showMessageDialog(null,
              new Message("Could not load the repository: " + cause.getMessage(), cause)); //$NON-NLS-1$
      return null;
    }
  }
	
  private File createCustomFolder( final File customFolder ) throws RepositoryException {
    IOFileSystemAbstraction fileSystem = new IOFileSystemAbstraction();
    IStringResolver pathResolver = new IStringResolver() {
      @Override
      public String resolve() {
        try {
          return customFolder.getCanonicalPath();
        } catch (IOException e) {
          throw new RepositoryException("Could not resolve path of repository");
        }
			}
		};
    File file = new File(pathResolver.resolve());
    if (!fileSystem.exists(file)) {
      try {
        fileSystem.createFolder(file);
      }
      catch (IOException e) {
        throw new RepositoryException(e);
      }
    }
    if (!fileSystem.canRead(file) || !fileSystem.canWrite(file)) {
      throw new RepositoryException("Read/Write error on repository custom folder at " + file.getAbsolutePath()); //$NON-NLS-1$
    }
    return file;
  }

  @Override
  public Set<ResourceFile> getResourcesMatching(final String namePattern) {
    Collection<File> customFiles = Lists.newArrayList(customFolder.listFiles(new FilenameFilter() {
      @Override
      public boolean accept(File dir, String name) {
        return name.matches(namePattern);
      }
    }));
    HashSet<ResourceFile> resourceFiles = Sets.newHashSet();
    for (File file : customFiles) {
      resourceFiles.add(new ExternalResourceFile(file));
    }
    return resourceFiles;
  }
}