package net.sf.anathema.gis.main.model;

import net.sf.anathema.gis.main.model.layerfactory.IStandardLayerFactory;
import de.disy.gis.gisterm.map.IMapModel;

public interface IGisModel {

  public IMapModel getMapModel();
  
  public IStandardLayerFactory getStandardLayerFactory();
}