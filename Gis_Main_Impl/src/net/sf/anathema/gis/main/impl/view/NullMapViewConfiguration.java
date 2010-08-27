package net.sf.anathema.gis.main.impl.view;

import de.disy.gis.gisterm.file.extension.IGisFileTypeProvider;
import de.disy.gis.gisterm.file.extension.NullGisFileTypeProvider;
import de.disy.gisterm.pro.map.popupmenu.IMenuItemFactoryRegistry;
import de.disy.gisterm.pro.map.view.configuration.IMapViewConfiguration;
import de.disy.lib.image.ImageResolution;

public class NullMapViewConfiguration implements IMapViewConfiguration {

  private IMenuItemFactoryRegistry menuItemFactory;

  @Override
  public boolean isDemo() {
    return false;
  }

  @Override
  public IMenuItemFactoryRegistry getContextMenuItemFactoryRegistry() {
    return menuItemFactory;
  }

  @Override
  public ImageResolution getScreenResolution() {
    return ImageResolution.SCREEN;
  }

  @Override
  public boolean isRestrictedPanVisualization() {
    return false;
  }

  @Override
  public IGisFileTypeProvider getGisFileProvider() {
    return NullGisFileTypeProvider.INSTANCE;
  }
}