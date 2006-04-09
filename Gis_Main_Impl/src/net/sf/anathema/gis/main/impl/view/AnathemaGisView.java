package net.sf.anathema.gis.main.impl.view;

import gis.gisterm.GisTermUtilities;
import gis.gisterm.map.GisView;
import gis.gisterm.map.IGisView;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JPanel;

import net.sf.anathema.gis.main.view.IAnathemaGisView;
import de.disy.gis.gisterm.map.IMapModel;
import de.disy.gisterm.pro.map.extent.DefaultScaleView;

public class AnathemaGisView implements IAnathemaGisView {

  private final JPanel content = new JPanel(new BorderLayout());
  private IGisView gisView;

  public IGisView initGisView(IMapModel mapModel) {
    gisView = new GisView(mapModel, new NullMapViewConfiguration(), null);
    content.add(GisTermUtilities.createMinimalDisplayToolBar(gisView), BorderLayout.NORTH);
    content.add(gisView.getContent(), BorderLayout.CENTER);
    DefaultScaleView scaleView = new DefaultScaleView();
    scaleView.setModel(gisView);
    content.add(scaleView, BorderLayout.SOUTH);
    return gisView;
  }

  public Component getContent() {
    return content;
  }
}