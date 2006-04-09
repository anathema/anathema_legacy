package net.sf.anathema.gis.main.model.layerfactory;

import gis.gisterm.gcore.GenericLayer;

public interface IStandardLayerFactory {

  public GenericLayer createXeriarRasterLayer() throws LayerCreationException;
}