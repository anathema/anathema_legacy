package net.sf.anathema.gis.main.model;

import net.sf.anathema.framework.itemdata.model.IItemData;
import net.sf.anathema.gis.main.model.layerfactory.IStandardLayerFactory;
import de.disy.gis.gisterm.map.IMapModel;

public interface IGisModel extends IItemData {

  public IMapModel getMapModel();

  public IStandardLayerFactory getStandardLayerFactory();
}