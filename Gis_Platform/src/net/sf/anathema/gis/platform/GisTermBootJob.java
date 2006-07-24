package net.sf.anathema.gis.platform;

import de.disy.gis.gisterm.pro.map.layer.LayerPanel;
import net.disy.commons.swing.filechooser.SmartFileChooser;
import net.sf.anathema.framework.IAnathemaBootJob;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.view.IAnathemaView;
import net.sf.anathema.gis.main.impl.model.AnathemaLayerPopupFactory;
import net.sf.anathema.gis.platform.util.DefaultFileChooserProvider;
import net.sf.anathema.lib.resources.IResources;

public class GisTermBootJob implements IAnathemaBootJob {

  // TODO In start-Methode von Plugin-Klasse verlagern
  public void run(IResources resources, IAnathemaModel model, IAnathemaView view) {
    LayerPanel.popupFactory = new AnathemaLayerPopupFactory();
    SmartFileChooser.getInstance().setFileChooserProvider(new DefaultFileChooserProvider());
  }
}