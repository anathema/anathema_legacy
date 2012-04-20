package net.sf.anathema.character.equipment.impl.item.model.gson;

import com.google.common.collect.Lists;
import net.sf.anathema.character.equipment.item.model.ICollectionFactory;
import net.sf.anathema.character.equipment.item.model.IEquipmentDatabase;
import net.sf.anathema.character.equipment.template.IEquipmentTemplate;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.item.IItemTypeRegistry;
import net.sf.anathema.framework.itemdata.model.NonPersistableItemData;
import net.sf.anathema.framework.repository.IRepository;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.util.List;

import static net.sf.anathema.character.equipment.impl.module.EquipmentDatabaseItemTypeConfiguration.EQUIPMENT_DATABASE_ITEM_TYPE_ID;

public class GsonEquipmentDatabase extends NonPersistableItemData implements IEquipmentDatabase {
  public static final String DATABASE_FOLDER = "equipment"; //$NON-NLS-1$

  public static GsonEquipmentDatabase CreateFrom(IAnathemaModel anathemaModel) {
    IRepository repository = anathemaModel.getRepository();
    IItemType itemType = getItemType(anathemaModel);
    return new GsonEquipmentDatabase(new EquipmentRepositoryAccess(repository, itemType));
  }

  private static IItemType getItemType(IAnathemaModel anathemaModel) {
    IItemTypeRegistry registry = anathemaModel.getItemTypeRegistry();
    return registry.getById(EQUIPMENT_DATABASE_ITEM_TYPE_ID);
  }

  private final ChangeControl availableTemplatesChangeControl = new ChangeControl();

  private final ICollectionFactory collectionFactory = new GsonCollectionFactory();
  private final EquipmentGson gson = new EquipmentGson();
  private final EquipmentAccess access;

  public GsonEquipmentDatabase(EquipmentAccess access) {
    this.access = access;
  }

  @Override
  public String[] getAllAvailableTemplateIds() {
    List<String> ids = Lists.newArrayList();
    for (File file : access.listAllFiles()) {
      String id = FilenameUtils.getBaseName(file.getName());
      ids.add(loadExistingTemplate(id).getName());
    }
    return ids.toArray(new String[ids.size()]);
  }

  @Override
  public IEquipmentTemplate loadTemplate(String templateId) {
    String id = FilenameCleaner.clean(templateId);
    if (!access.exists(id)) {
      return null;
    }
    return loadExistingTemplate(id);
  }


  @Override
  public ICollectionFactory getCollectionFactory() {
    return collectionFactory;
  }

  @Override
  public void saveTemplate(IEquipmentTemplate template) {
    save(template);
    availableTemplatesChangeControl.fireChangedEvent();
  }

  public void saveTemplateNoOverwrite(IEquipmentTemplate template) {
    if (loadTemplate(template.getName()) == null) {
      saveTemplate(template);
    }
  }

  @Override
  public void addAvailableTemplateChangeListener(IChangeListener listener) {
    availableTemplatesChangeControl.addChangeListener(listener);
  }

  @Override
  public void deleteTemplate(String editTemplateId) {
    delete(editTemplateId);
    availableTemplatesChangeControl.fireChangedEvent();
  }


  @Override
  public void updateTemplate(String editTemplateId, IEquipmentTemplate saveTemplate) {
    delete(editTemplateId);
    saveTemplate(saveTemplate);
  }

  private void delete(String editTemplateId) {
    String id = FilenameCleaner.clean(editTemplateId);
    if (access.exists(id)) {
      access.delete(id);
    }
  }

  private IEquipmentTemplate loadExistingTemplate(String templateId) {
    String id = FilenameCleaner.clean(templateId);
    String json = access.read(id);
    return gson.fromJson(json);
  }

  private void save(IEquipmentTemplate template) {
    String id = FilenameCleaner.clean(template.getName());
    String json = gson.toJson(template);
    access.write(id, json);
  }

  public boolean isEmpty() {
    return getAllAvailableTemplateIds().length == 0;
  }
}