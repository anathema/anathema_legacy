package net.sf.anathema.gis.platform.menu;

import java.io.File;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.presenter.menu.IAnathemaMenu;
import net.sf.anathema.framework.view.IMenuBar;
import net.sf.anathema.gis.data.impl.GisDataDirectory;
import net.sf.anathema.lib.resources.IResources;

public class GisMenu implements IAnathemaMenu {

  public void add(IResources resources, IAnathemaModel model, IMenuBar menubar) {
    GisDataDirectory gisDataDirectory = new GisDataDirectory();
    gisDataDirectory.setDirectory(new File("./gisdata/"));
    new GisModuleMenuFactory(gisDataDirectory).createMapMenu(resources, model, menubar);
  }
}