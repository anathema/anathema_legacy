package net.sf.anathema.gis;

import net.disy.commons.swing.filechooser.SmartFileChooser;
import net.sf.anathema.gis.main.impl.model.AnathemaLayerPopupFactory;
import net.sf.anathema.gis.platform.util.DefaultFileChooserProvider;

import org.java.plugin.Plugin;

import de.disy.gis.gisterm.pro.map.layer.LayerPanel;

public class GisPlugin extends Plugin {

  @Override
  protected void doStart() throws Exception {
    LayerPanel.popupFactory = new AnathemaLayerPopupFactory();
    SmartFileChooser.getInstance().setFileChooserProvider(new DefaultFileChooserProvider());
  }

  @Override
  protected void doStop() throws Exception {
    // nothing to do;
  }
}