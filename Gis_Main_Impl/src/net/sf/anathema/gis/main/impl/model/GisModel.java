package net.sf.anathema.gis.main.impl.model;

import java.io.File;

import net.sf.anathema.gis.main.model.IGisModel;
import net.sf.anathema.gis.main.model.layerfactory.IStandardLayerFactory;
import de.disy.gis.gisterm.map.BasicMapModel;
import de.disy.gis.gisterm.map.IMapModel;

public class GisModel implements IGisModel {
  
  private final IMapModel mapModel = new BasicMapModel();
  private final StandardLayerFactory standardLayerFactory;
  
  public GisModel(File repositoryFile) {
    standardLayerFactory  = new StandardLayerFactory(repositoryFile);
  }

  public IMapModel getMapModel() {
    return mapModel;
  }

  public IStandardLayerFactory getStandardLayerFactory() {
    return standardLayerFactory;
  }
}