package net.sf.anathema.gis.main.impl.view;

import gis.gisterm.GisTermUtilities;
import gis.gisterm.map.GisView;
import gis.gisterm.map.IGisView;

import java.awt.BorderLayout;
import java.awt.Component;
import java.text.NumberFormat;

import javax.swing.JPanel;

import net.sf.anathema.gis.main.view.IAnathemaGisView;
import net.sf.anathema.gis.main.view.ICoordinateView;
import de.disy.gis.gisterm.map.IMapModel;

public class AnathemaGisView implements IAnathemaGisView {

  private final JPanel content = new JPanel(new BorderLayout());
  private IGisView gisView;

  public IGisView initGisView(IMapModel mapModel) {
    gisView = new GisView(mapModel, new NullMapViewConfiguration(), null);
    content.add(GisTermUtilities.createMinimalDisplayToolBar(gisView), BorderLayout.NORTH);
    content.add(gisView.getContent(), BorderLayout.CENTER);
    return gisView;
  }

  public Component getContent() {
    return content;
  }

  public ICoordinateView addCoordinateView(NumberFormat numberFormat) {
    CoordinateView coordView = new CoordinateView(numberFormat);
    content.add(coordView.getComponent(), BorderLayout.SOUTH);
    return coordView;
  }
}