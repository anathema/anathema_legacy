package net.sf.anathema.character.equipment.impl.module;

import net.sf.anathema.character.equipment.creation.presenter.stats.properties.EquipmentUI;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.itemdata.model.IItemData;
import net.sf.anathema.framework.module.IDatabaseActionProperties;
import net.sf.anathema.framework.repository.AnathemaDataItem;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.view.IItemView;
import net.sf.anathema.framework.view.perspective.Container;
import net.sf.anathema.framework.view.perspective.Perspective;
import net.sf.anathema.framework.view.perspective.PerspectiveAutoCollector;
import net.sf.anathema.framework.view.perspective.PerspectiveToggle;
import net.sf.anathema.initialization.reflections.ReflectionObjectFactory;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.Identifier;

import static net.sf.anathema.character.equipment.item.model.EquipmentStatisticsType.CloseCombat;

@PerspectiveAutoCollector
@Weight(weight = 6000)
public class EquipmentPerspective implements Perspective {

  @Override
  public void configureToggle(PerspectiveToggle toggle) {
    toggle.setIcon(EquipmentUI.getIconName(CloseCombat));
    toggle.setTooltip("EquipmentDatabase.NewAction.Name");
  }

  @Override
  public void initContent(Container container, IAnathemaModel model, IResources resources, ReflectionObjectFactory objectFactory) {
    IDatabaseActionProperties properties = new EquipmentDatabaseActionProperties(resources, model);
    IItemType itemType = model.getItemTypeRegistry().getById(properties.getItemTypeId());
    IItemData database = properties.createItemData(model.getRepository());
    IItem anathemaItem = new AnathemaDataItem(itemType, new Identifier(properties.getItemId()), database);
    IItemView view = model.getViewFactoryRegistry().get(itemType).createView(anathemaItem);
    container.setSwingContent(view.getComponent());
  }
}
