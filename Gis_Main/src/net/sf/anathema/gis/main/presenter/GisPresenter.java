package net.sf.anathema.gis.main.presenter;

import gis.gisterm.map.IGisView;
import net.sf.anathema.gis.main.model.IGisModel;
import net.sf.anathema.gis.main.model.layerfactory.LayerCreationException;
import net.sf.anathema.gis.main.view.IAnathemaGisView;
import net.sf.anathema.lib.logging.Logger;
import net.sf.anathema.lib.resources.IResources;
import de.disy.gis.gisterm.map.IMapModel;
import de.disy.gis.gisterm.map.theme.LayerTheme;

public class GisPresenter {

  private static final Logger logger = Logger.getLogger(GisPresenter.class);
  private final IGisModel model;
  private final IAnathemaGisView view;
  private final IResources resources;

  public GisPresenter(IGisModel model, IAnathemaGisView view, IResources resources) {
    this.model = model;
    this.view = view;
    this.resources = resources;
  }

  public void initPresentation() {
    IMapModel mapModel = model.getMapModel();
    try {
      mapModel.getThemeModel().addTheme(new LayerTheme(model.getStandardLayerFactory().createXeriarRasterLayer()));
    }
    catch (LayerCreationException e) {
      logger.warn("Error creating XeriarLayer", e);
    }
    IGisView gisView = view.initGisView(mapModel);
  }
}