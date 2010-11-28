package net.sf.anathema.gis.main.presenter;

import gis.gisterm.gcore.DisplayCoordEvent;
import gis.gisterm.gcore.DisplayCoordListener;
import gis.gisterm.gcore.GenericLayer;
import gis.gisterm.gcore.WorldBox;
import gis.gisterm.map.IGisView;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import net.sf.anathema.gis.main.model.IGisModel;
import net.sf.anathema.gis.main.model.layerfactory.LayerCreationException;
import net.sf.anathema.gis.main.view.IAnathemaGisView;
import net.sf.anathema.gis.main.view.ICoordinateView;
import net.sf.anathema.lib.gui.IPresenter;
import net.sf.anathema.lib.logging.Logger;
import net.sf.anathema.lib.resources.IResources;
import de.disy.gis.gisterm.map.IMapModel;
import de.disy.gis.gisterm.map.theme.LayerTheme;

public class GisPresenter implements IPresenter {

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
      GenericLayer xeriarLayer = model.getStandardLayerFactory().createXeriarRasterLayer();
      if (xeriarLayer != null) {
        mapModel.getThemeModel().addTheme(new LayerTheme(xeriarLayer));
      }
    }
    catch (LayerCreationException e) {
      logger.warn("Error creating XeriarLayer", e); //$NON-NLS-1$
    }
    IGisView gisView = view.initGisView(mapModel);
    final ICoordinateView coordinateView = view.addCoordinateView(createNumberFormat());
    gisView.getDisplay().addDisplayCoordListener(new DisplayCoordListener() {
      public void coordActionPerformed(DisplayCoordEvent event) {
        coordinateView.setCoordinate(event.getX(), event.getY());
      }
    });
    gisView.getMapModel().getViewProperties().setSuggestedWorldBox(
        new WorldBox(PresentationUtilities.ENVELOPE_BLESSED_ISLE));
    gisView.getLocatorView().getMapModel().getViewProperties().setSuggestedWorldBox(
        new WorldBox(PresentationUtilities.ENVELOPE_CREATION));
  }

  private NumberFormat createNumberFormat() {
    NumberFormat format = new DecimalFormat();
    format.setGroupingUsed(false);
    format.setMinimumFractionDigits(1);
    format.setMaximumFractionDigits(1);
    return format;
  }
}