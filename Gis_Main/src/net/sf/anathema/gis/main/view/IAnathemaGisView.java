package net.sf.anathema.gis.main.view;

import gis.gisterm.map.IGisView;
import de.disy.gis.gisterm.map.IMapModel;

public interface IAnathemaGisView {

  public IGisView initGisView(IMapModel mapModel);
}