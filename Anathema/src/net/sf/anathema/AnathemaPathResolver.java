package net.sf.anathema;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.java.plugin.registry.Identity;
import org.java.plugin.registry.Library;
import org.java.plugin.standard.StandardPathResolver;

public class AnathemaPathResolver extends StandardPathResolver {

  private static final String THIRDPARTY_FOLDER = "${ANATHEMA_LIB}"; //$NON-NLS-1$

  @Override
  public URL resolvePath(final Identity identity, final String path) {
    if (!(identity instanceof Library)) {
      return super.resolvePath(identity, path);
    }
    Library library = (Library) identity;
    String libraryPath = library.getPath();
    if (!libraryPath.startsWith(THIRDPARTY_FOLDER)) {
      return super.resolvePath(identity, path);
    }
    String libraryFile = libraryPath.replace(THIRDPARTY_FOLDER, "lib"); //$NON-NLS-1$
    try {
      return new File("./" + libraryFile).getCanonicalFile().toURI().toURL(); //$NON-NLS-1$
    }
    catch (MalformedURLException e) {
      throw new IllegalArgumentException("Couldn't resolve URL: " + libraryFile, e); //$NON-NLS-1$
    }
    catch (IOException e) {
      throw new IllegalArgumentException("Couldn't build canonical path for file: " + libraryFile, e); //$NON-NLS-1$
    }
  }
}