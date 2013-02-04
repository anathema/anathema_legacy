package net.sf.anathema.character.equipment.impl.module;

import net.sf.anathema.character.equipment.creation.presenter.stats.properties.EquipmentUI;
import net.sf.anathema.character.equipment.impl.item.model.EquipmentDatabaseManagement;
import net.sf.anathema.character.equipment.impl.item.view.EquipmentDatabaseView;
import net.sf.anathema.character.equipment.item.EquipmentDatabasePresenter;
import net.sf.anathema.character.equipment.item.model.IEquipmentDatabase;
import net.sf.anathema.character.equipment.item.model.IEquipmentDatabaseManagement;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.view.perspective.Container;
import net.sf.anathema.framework.view.perspective.Perspective;
import net.sf.anathema.framework.view.perspective.PerspectiveAutoCollector;
import net.sf.anathema.framework.view.perspective.PerspectiveToggle;
import net.sf.anathema.initialization.reflections.ReflectionObjectFactory;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.resources.IResources;

import static net.sf.anathema.character.equipment.item.model.EquipmentStatisticsType.CloseCombat;

@PerspectiveAutoCollector
@Weight(weight = 5000)
public class EquipmentPerspective implements Perspective {

  @Override
  public void configureToggle(PerspectiveToggle toggle) {
    toggle.setIcon("EquipmentPerspective.png");
    toggle.setTooltip("EquipmentDatabase.NewAction.Name");
  }

  @Override
  public void initContent(Container container, IAnathemaModel model, IResources resources, ReflectionObjectFactory objectFactory) {
    IEquipmentDatabaseManagement databaseManagement = createDatabaseManagement(model, resources);
    EquipmentDatabaseView view = new EquipmentDatabaseView();
    new EquipmentDatabasePresenter(resources, databaseManagement, view).initPresentation();
    container.setSwingContent(view.getComponent());
  }

  private IEquipmentDatabaseManagement createDatabaseManagement(IAnathemaModel model, IResources resources) {
    EquipmentDatabaseActionProperties properties = new EquipmentDatabaseActionProperties(resources, model);
    IEquipmentDatabase database = properties.createItemData(model.getRepository());
    return new EquipmentDatabaseManagement(database);
  }
}
