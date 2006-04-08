package net.sf.anathema.gis.main.demo;

import de.disy.gis.gisterm.file.extension.IGisFileTypeProvider;
import de.disy.gis.gisterm.file.extension.NullGisFileTypeProvider;
import de.disy.gisterm.pro.map.popupmenu.IMenuItemFactoryRegistry;
import de.disy.gisterm.pro.map.view.configuration.IMapViewConfiguration;
import de.disy.lib.image.ImageResolution;

public class NullMapViewConfiguration implements IMapViewConfiguration {

  private IMenuItemFactoryRegistry menuItemFactory;

  public boolean isDemo() {
    return false;
  }

  public IMenuItemFactoryRegistry getContextMenuItemFactoryRegistry() {
    return menuItemFactory;
  }

  public ImageResolution getScreenResolution() {
    return ImageResolution.SCREEN;
  }

  public boolean isRestrictedPanVisualization() {
    return false;
  }

  public IGisFileTypeProvider getGisFileProvider() {
    return NullGisFileTypeProvider.INSTANCE;
  }
}