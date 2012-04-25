package net.sf.anathema.framework.resources;

import net.sf.anathema.lib.resources.DefaultStringProvider;
import net.sf.anathema.lib.resources.IAnathemaImageProvider;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.resources.MultiSourceStringProvider;
import net.sf.anathema.lib.resources.ResourceFile;

import javax.swing.Icon;
import java.awt.Image;
import java.util.Locale;

public class AnathemaResources implements IResources {

  private final IAnathemaImageProvider imageProvider = new ImageProvider("icons"); //$NON-NLS-1$
  private final MultiSourceStringProvider stringHandler = new MultiSourceStringProvider();
  private final StringProviderFactory factory = new StringProviderFactory(getLocale());

  public AnathemaResources() {
    stringHandler.add(new DefaultStringProvider("Literal")); //$NON-NLS-1$
  }

  public void addResourceBundle(ResourceFile resource) {
    stringHandler.add(factory.create(resource));
  }

  @Override
  public boolean supportsKey(String key) {
    return stringHandler.supportsKey(key);
  }

  @Override
  public String getString(String key, Object... arguments) {
    return stringHandler.getString(key, arguments);
  }

  @Override
  public Image getImage(Class<?> requestor, String relativePath) {
    return imageProvider.getImage(requestor, relativePath);
  }

  @Override
  public Icon getImageIcon(Class<?> requestor, String relativePath) {
    return imageProvider.getImageIcon(requestor, relativePath);
  }

  private Locale getLocale() {
    return Locale.getDefault();
  }
}