/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.disy.commons.core.io;

import net.sf.anathema.lib.exception.UnreachableCodeReachedException;

import java.io.File;
import java.io.IOException;

public class FileUtilities {

  protected FileUtilities() {
    throw new UnreachableCodeReachedException();
  }

  public static void deleteFileOrDirectory(final File file) throws IOException {
    if (!file.exists()) {
      return;
    }
    if (file.isDirectory()) {
      final File[] files = file.listFiles();
      for (final File file2 : files) {
        deleteFileOrDirectory(file2);
      }
    }
    if (!file.delete()) {
      throw new IOException("delete failed for file '" + file.getAbsolutePath() + "'"); //$NON-NLS-1$ //$NON-NLS-2$
    }
  }
}