package net.sf.anathema.gis.platform.menu;

import javax.swing.JMenu;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.lib.resources.IResources;

public class GisModuleMenuFactory {

  public JMenu createMapMenu(IResources resources, IAnathemaModel model) {
    JMenu menu = new JMenu(resources.getString("Gis.Menu.Name")); //$NON-NLS-1$
    menu.add(ShowGisAction.createMenuAction(resources, model));
    menu.add(new AddSketchLayerAction(resources, model));
    return menu;
  }
}
