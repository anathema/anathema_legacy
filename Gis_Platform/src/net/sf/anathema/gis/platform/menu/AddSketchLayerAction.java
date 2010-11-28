package net.sf.anathema.gis.platform.menu;

import java.awt.Component;

import net.disy.commons.swing.action.SmartAction;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.gis.main.model.IGisModel;
import net.sf.anathema.gis.platform.GisItemTypeConfiguration;
import net.sf.anathema.gis.platform.util.SelectiveItemTypeEnabler;
import net.sf.anathema.lib.resources.IResources;
import de.disy.gis.gisterm.map.IMapModel;
import de.disy.gis.gisterm.map.theme.ITheme;
import de.disy.gis.gisterm.map.theme.LayerTheme;

public class AddSketchLayerAction extends SmartAction {

  private final IAnathemaModel anathemaModel;

  public AddSketchLayerAction(IResources resources, IAnathemaModel anathemaModel) {
    super(resources.getString("Gis.Menu.ItemName.AddSketchLayer")); //$NON-NLS-1$
    this.anathemaModel = anathemaModel;
    anathemaModel.getItemManagement().addListener(
        new SelectiveItemTypeEnabler(this, GisItemTypeConfiguration.GIS_ITEM_TYPE_ID));
  }

  @Override
  protected void execute(Component parentComponent) {
    IItem selectedItem = anathemaModel.getItemManagement().getSelectedItem();
    IGisModel gisModel = (IGisModel) selectedItem.getItemData();
    IMapModel mapModel = gisModel.getMapModel();
    ITheme sketchTheme = new LayerTheme(gisModel.getStandardLayerFactory().createSketchLayer());
    mapModel.getThemeModel().addTheme(sketchTheme);
    mapModel.getSelectionModel().setSelectedTheme(sketchTheme);
  }
}