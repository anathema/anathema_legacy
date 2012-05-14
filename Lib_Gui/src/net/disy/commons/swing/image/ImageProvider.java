/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.disy.commons.swing.image;

import net.disy.commons.core.io.IOUtilities;
import net.disy.commons.core.util.Ensure;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

public class ImageProvider implements IImageProvider {

  private final String rootPath;

  /**
   * class for loading images out of a central path
   * @param rootPath central path where all images are located
   */
  public ImageProvider(final String rootPath) {
    Ensure.ensureNotNull("RootPath is null.", rootPath); //$NON-NLS-1$
    this.rootPath = rootPath;
  }

  /**
   * gets an image relative to root path
   * @param relativePath
   * @return Image or null, if image cannot be loaded and hasDummyImage is false
   */
  @Override
  public Image getImage(final String relativePath) {
    return getImage(relativePath, false);
  }

  /**
   * @param relativePath
   * @param isAnimated
   * @return Image or null, if image cannot be loaded and hasDummyImage is false
   */
  private Image getImage(final String relativePath, final boolean isAnimated) {
    final InputStream inputStream = getInputStream(relativePath);
    if (inputStream == null) {
      return null;
    }
    try {
      return loadImage(isAnimated, inputStream);
    }
    finally {
      IOUtilities.close(inputStream);
    }
  }

  private Image loadImage(final boolean isAnimated, final InputStream inputStream) {
    try {
      if (isAnimated) {
        return ImageLoader.getImageWithoutCaching(inputStream);
      }
      return ImageLoader.getMemoryImageWithoutCaching(inputStream);
    }
    catch (final IOException e) {
      throw new ImageLoadingException("Cannot open image: " + e.getMessage()); //$NON-NLS-1$
    }
  }

  private InputStream getInputStream(final String relativePath) {
    Ensure.ensureNotNull("RelativePath to image is null.", relativePath); //$NON-NLS-1$
    final String resourceName = rootPath + "/" + relativePath; //$NON-NLS-1$
    final InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourceName);
    if (inputStream == null) {
      throw new ImageLoadingException("Cannot find image resource: " + resourceName); //$NON-NLS-1$
    }
    return inputStream;
  }

  @Override
  public Icon getImageIcon(final String relativePath) {
    final Image image = getImage(relativePath);
    return image == null ? null : new ImageIcon(image);
  }

}