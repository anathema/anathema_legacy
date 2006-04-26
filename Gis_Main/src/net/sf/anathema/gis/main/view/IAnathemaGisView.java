package net.sf.anathema.gis.main.view;

import gis.gisterm.map.IGisView;

import java.text.NumberFormat;

import de.disy.gis.gisterm.map.IMapModel;

public interface IAnathemaGisView {

  public IGisView initGisView(IMapModel mapModel);
  
  public ICoordinateView addCoordinateView(NumberFormat numberFormat);
}