package net.sf.anathema.character.equipment.impl.item.model.gson;

import com.google.common.collect.Lists;
import net.sf.anathema.character.equipment.item.model.ICollectionFactory;
import net.sf.anathema.character.equipment.item.model.IEquipmentDatabase;
import net.sf.anathema.character.equipment.template.IEquipmentTemplate;
import net.sf.anathema.framework.itemdata.model.NonPersistableItemData;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

public class GsonEquipmentDatabase extends NonPersistableItemData implements IEquipmentDatabase {
  public static final String DATABASE_FOLDER = "equipment"; //$NON-NLS-1$
  private final ChangeControl availableTemplatesChangeControl = new ChangeControl();
  private final ICollectionFactory collectionFactory = new GsonCollectionFactory();
  private final EquipmentGson gson = new EquipmentGson();
  private File databaseDirectory;

  public GsonEquipmentDatabase(File databaseDirectory) {
    this.databaseDirectory = databaseDirectory;
  }

  @Override
  public String[] getAllAvailableTemplateIds() {
    List<String> ids = Lists.newArrayList();
    for (File file : getAllItemFiles()) {
      ids.add(FilenameUtils.getBaseName(file.getName()));
    }
    return ids.toArray(new String[ids.size()]);
  }

  @Override
  public IEquipmentTemplate loadTemplate(String templateId) {
    if (!exists(templateId)) {
      return null;
    }
    return load(templateId);
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
    if (exists(editTemplateId)) {
      File templateFile = getTemplateFile(editTemplateId);
      templateFile.delete();
    }
  }

  private Collection<File> getAllItemFiles() {
    return FileUtils.listFiles(databaseDirectory, new String[]{"item"}, false);
  }

  private boolean exists(String templateId) {
    return getTemplateFile(templateId).exists();
  }

  private IEquipmentTemplate load(String templateId) {
    try {
      File file = getTemplateFile(templateId);
      String json = FileUtils.readFileToString(file);
      return gson.fromJson(json);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private void save(IEquipmentTemplate template) {
    try {
      String json = gson.toJson(template);
      FileUtils.writeStringToFile(getTemplateFile(template.getName()), json);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private File getTemplateFile(String editTemplateId) {
    String cleaned = new FilenameCleaner().clean(editTemplateId);
    return new File(databaseDirectory, cleaned + ".item");
  }
}