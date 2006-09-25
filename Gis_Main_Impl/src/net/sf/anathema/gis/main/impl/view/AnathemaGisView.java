package net.sf.anathema.gis.main.impl.view;

import gis.gisterm.map.DisplayToolbar;
import gis.gisterm.map.GisView;
import gis.gisterm.map.IGisView;

import java.awt.BorderLayout;
import java.text.NumberFormat;

import javax.swing.JComponent;
import javax.swing.JPanel;

import net.sf.anathema.gis.main.view.IAnathemaGisView;
import net.sf.anathema.gis.main.view.ICoordinateView;
import net.sf.anathema.lib.gui.IView;
import de.disy.gis.gisterm.customization.GisTermCustomizations;
import de.disy.gis.gisterm.map.IMapModel;

public class AnathemaGisView implements IAnathemaGisView, IView {

  private final JPanel content = new JPanel(new BorderLayout());
  private IGisView gisView;

  public IGisView initGisView(IMapModel mapModel) {
    gisView = new GisView(mapModel, new NullMapViewConfiguration(), null);
    content.add(createDisplayToolbar(), BorderLayout.NORTH);
    content.add(gisView.getContent(), BorderLayout.CENTER);
    return gisView;
  }

  private DisplayToolbar createDisplayToolbar() {
    DisplayToolbar displayToolbar = new DisplayToolbar(new GisTermCustomizations());
    displayToolbar.connectWith(gisView);
    return displayToolbar;
  }

  public JComponent getComponent() {
    return content;
  }

  public ICoordinateView addCoordinateView(NumberFormat numberFormat) {
    CoordinateView coordView = new CoordinateView(numberFormat);
    content.add(coordView.getComponent(), BorderLayout.SOUTH);
    return coordView;
  }
}