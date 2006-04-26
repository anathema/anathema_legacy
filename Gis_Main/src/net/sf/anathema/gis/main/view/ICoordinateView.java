package net.sf.anathema.gis.main.view;

import net.sf.anathema.lib.gui.IView;

public interface ICoordinateView extends IView {
  
  public void setCoordinate(double x, double y);
}