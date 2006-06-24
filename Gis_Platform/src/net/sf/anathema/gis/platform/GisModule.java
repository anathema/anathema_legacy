package net.sf.anathema.gis.platform;

import java.io.File;

import net.disy.commons.swing.filechooser.SmartFileChooser;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.module.AbstractAnathemaModule;
import net.sf.anathema.framework.view.IAnathemaView;
import net.sf.anathema.gis.data.impl.GisDataDirectory;
import net.sf.anathema.gis.main.impl.model.AnathemaLayerPopupFactory;
import net.sf.anathema.gis.platform.menu.GisModuleMenuFactory;
import net.sf.anathema.gis.platform.util.DefaultFileChooserProvider;
import net.sf.anathema.lib.resources.IResources;
import de.disy.gis.gisterm.pro.map.layer.LayerPanel;

public class GisModule extends AbstractAnathemaModule {

  private final GisDataDirectory gisDataDirectory = new GisDataDirectory();

  public GisModule() {
    gisDataDirectory.setDirectory(new File("./gisdata/"));
    customizeGisTermFramework();
  }

  private void customizeGisTermFramework() {
    LayerPanel.popupFactory = new AnathemaLayerPopupFactory();
  }

  @Override
  public void initPresentation(IResources resources, IAnathemaModel model, IAnathemaView view) {
    super.initPresentation(resources, model, view);
    SmartFileChooser.getInstance().setFileChooserProvider(new DefaultFileChooserProvider());
    view.getMenuBar().addMenu(new GisModuleMenuFactory(gisDataDirectory).createMapMenu(resources, getAnathemaModel()));
  }
}