package net.sf.anathema.gis.main.impl.model;

import net.sf.anathema.framework.presenter.itemmanagement.PrintNameAdjuster;
import net.sf.anathema.gis.data.IGisDataDirectory;
import net.sf.anathema.gis.main.model.IGisModel;
import net.sf.anathema.gis.main.model.layerfactory.IStandardLayerFactory;
import net.sf.anathema.lib.control.change.IChangeListener;
import de.disy.gis.gisterm.map.BasicMapModel;
import de.disy.gis.gisterm.map.IMapModel;

public class GisModel implements IGisModel {

  private final IMapModel mapModel = new BasicMapModel();
  private final StandardLayerFactory standardLayerFactory;

  public GisModel(IGisDataDirectory gisDataDirectory) {
    standardLayerFactory = new StandardLayerFactory(gisDataDirectory);
  }

  public IMapModel getMapModel() {
    return mapModel;
  }

  public IStandardLayerFactory getStandardLayerFactory() {
    return standardLayerFactory;
  }

  public void setPrintNameAdjuster(PrintNameAdjuster adjuster) {
    // nothing to do
  }

  public void addDirtyListener(IChangeListener changeListener) {
    // nothing to do
  }

  public boolean isDirty() {
    return true;
  }

  public void setClean() {
    // TODO Auto-generated method stub
  }

  public void removeDirtyListener(IChangeListener changeListener) {
    // nothing to do
  }
}