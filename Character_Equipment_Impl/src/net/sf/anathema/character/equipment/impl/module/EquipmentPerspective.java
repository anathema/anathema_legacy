package net.sf.anathema.character.equipment.impl.module;

import net.sf.anathema.character.equipment.impl.item.model.EquipmentDatabaseManagement;
import net.sf.anathema.character.equipment.item.view.swing.SwingEquipmentDatabaseView;
import net.sf.anathema.character.equipment.item.EquipmentDatabasePresenter;
import net.sf.anathema.character.equipment.item.model.IEquipmentDatabase;
import net.sf.anathema.character.equipment.item.model.IEquipmentDatabaseManagement;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.view.perspective.Container;
import net.sf.anathema.framework.view.perspective.Perspective;
import net.sf.anathema.framework.view.perspective.PerspectiveAutoCollector;
import net.sf.anathema.framework.view.perspective.PerspectiveToggle;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.resources.IResources;

@PerspectiveAutoCollector
@Weight(weight = 5000)
public class EquipmentPerspective implements Perspective {

  @Override
  public void configureToggle(PerspectiveToggle toggle) {
    toggle.setIcon("EquipmentPerspective.png");
    toggle.setTooltip("EquipmentDatabase.Perspective.Name");
  }

  @Override
  public void initContent(Container container, IApplicationModel applicationModel, IResources resources) {
    IEquipmentDatabaseManagement databaseManagement = createDatabaseManagement(applicationModel, resources);
    SwingEquipmentDatabaseView view = new SwingEquipmentDatabaseView();
    new EquipmentDatabasePresenter(resources, databaseManagement, view).initPresentation();
    container.setSwingContent(view.perspectivePane.getComponent());
  }

  private IEquipmentDatabaseManagement createDatabaseManagement(IApplicationModel model, IResources resources) {
    EquipmentDatabaseActionProperties properties = new EquipmentDatabaseActionProperties(resources, model);
    IEquipmentDatabase database = properties.createItemData(model.getRepository());
    return new EquipmentDatabaseManagement(database);
  }
}
