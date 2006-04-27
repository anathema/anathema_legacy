package net.sf.anathema.gis.platform.menu;

import javax.swing.JMenu;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.gis.data.IGisDataDirectory;
import net.sf.anathema.gis.data.impl.preparation.raster.LargeXeriarRasterTiler;
import net.sf.anathema.gis.data.preparation.raster.RasterTileAction;
import net.sf.anathema.lib.resources.IResources;

public class GisModuleMenuFactory {
  
  private final IGisDataDirectory gisDataDirectory;

  public GisModuleMenuFactory(IGisDataDirectory gisDataDirectory) {
    this.gisDataDirectory = gisDataDirectory;
  }

  public JMenu createMapMenu(IResources resources, IAnathemaModel model) {
    JMenu menu = new JMenu(resources.getString("Gis.Menu.Name")); //$NON-NLS-1$
    menu.add(ShowGisAction.createMenuAction(resources, model, gisDataDirectory));
    menu.add(new AddSketchLayerAction(resources, model));
    menu.add(new RasterTileAction("Import Large Xeriar Map", new LargeXeriarRasterTiler(gisDataDirectory)));
    return menu;
  }
}