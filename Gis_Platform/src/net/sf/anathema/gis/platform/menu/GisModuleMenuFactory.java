package net.sf.anathema.gis.platform.menu;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.view.IMenuBar;
import net.sf.anathema.framework.view.menu.IMenu;
import net.sf.anathema.gis.data.IGisDataDirectory;
import net.sf.anathema.gis.data.impl.preparation.raster.LargeXeriarRasterTiler;
import net.sf.anathema.gis.data.preparation.raster.RasterTileAction;
import net.sf.anathema.lib.resources.IResources;

public class GisModuleMenuFactory {

  private final IGisDataDirectory gisDataDirectory;

  public GisModuleMenuFactory(IGisDataDirectory gisDataDirectory) {
    this.gisDataDirectory = gisDataDirectory;
  }

  public void createMapMenu(IResources resources, IAnathemaModel model, IMenuBar menubar) {
    IMenu menu = menubar.addMenu(resources.getString("Gis.Menu.Name"));
    menu.addMenuItem(ShowGisAction.createMenuAction(resources, model, gisDataDirectory));
    menu.addMenuItem(new AddSketchLayerAction(resources, model));
    menu.addMenuItem(new RasterTileAction("Import Large Xeriar Map", new LargeXeriarRasterTiler(gisDataDirectory)));
  }
}