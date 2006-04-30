package net.sf.anathema.gis.platform;

import java.io.File;

import net.disy.commons.swing.filechooser.SmartFileChooser;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.module.AbstractAnathemaModule;
import net.sf.anathema.framework.module.AbstractItemTypeConfiguration;
import net.sf.anathema.framework.resources.IAnathemaResources;
import net.sf.anathema.framework.view.IAnathemaView;
import net.sf.anathema.gis.data.impl.GisDataDirectory;
import net.sf.anathema.gis.main.impl.model.AnathemaLayerPopupFactory;
import net.sf.anathema.gis.platform.menu.GisModuleMenuFactory;
import net.sf.anathema.gis.platform.util.DefaultFileChooserProvider;
import net.sf.anathema.lib.logging.Logger;
import net.sf.anathema.lib.resources.IResources;
import de.disy.gis.gisterm.pro.map.layer.LayerPanel;

public class GisModule extends AbstractAnathemaModule {

  private final Logger logger = Logger.getLogger(GisModule.class);
  private final AbstractItemTypeConfiguration gisItemConfiguration = new GisItemTypeConfiguration();
  private final GisDataDirectory gisDataDirectory = new GisDataDirectory();

  public GisModule() {
    gisDataDirectory.setDirectory(new File("./gisdata/"));
    addItemTypeConfiguration(gisItemConfiguration);
  }

  @Override
  public void initModel(IAnathemaModel model) {
    super.initModel(model);
    customizeGisTermFramework();
  }

  private void customizeGisTermFramework() {
    LayerPanel.popupFactory = new AnathemaLayerPopupFactory();
  }

  @Override
  public void initAnathemaResources(IAnathemaResources resources) {
    super.initAnathemaResources(resources);
    // resources.addStringResourceHandler(createStringProvider("Gis", resources.getLocale())); //$NON-NLS-1$
  }

  @Override
  public void initPresentation(IResources resources, IAnathemaView view) {
    super.initPresentation(resources, view);
    SmartFileChooser.getInstance().setFileChooserProvider(new DefaultFileChooserProvider());
    view.getMenuBar().addMenu(new GisModuleMenuFactory(gisDataDirectory).createMapMenu(resources, getAnathemaModel()));
  }
}