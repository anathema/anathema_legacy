package net.sf.anathema.character.equipment.item.model.gson;

import com.google.common.collect.Lists;
import net.sf.anathema.character.equipment.item.model.IEquipmentDatabase;
import net.sf.anathema.character.equipment.module.EquipmentDatabaseItemTypeConfiguration;
import net.sf.anathema.equipment.core.IEquipmentTemplate;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.itemdata.model.ItemData;
import net.sf.anathema.framework.presenter.itemmanagement.PrintNameAdjuster;
import net.sf.anathema.framework.repository.ChangeManagement;
import net.sf.anathema.framework.repository.NullChangeManagement;
import net.sf.anathema.framework.repository.Repository;
import net.sf.anathema.lib.control.ChangeListener;
import org.apache.commons.io.FilenameUtils;
import org.jmock.example.announcer.Announcer;

import java.nio.file.Path;
import java.util.List;

public class GsonEquipmentDatabase implements IEquipmentDatabase, ItemData {
  public static final String DATABASE_FOLDER = "equipment";

  public static GsonEquipmentDatabase CreateFrom(IApplicationModel anathemaModel) {
    Repository repository = anathemaModel.getRepository();
    IItemType itemType = new EquipmentDatabaseItemTypeConfiguration().getItemType();
    return new GsonEquipmentDatabase(new EquipmentRepositoryAccess(repository, itemType));
  }

  private final Announcer<ChangeListener> availableTemplatesChangeControl = Announcer.to(ChangeListener.class);
  private final EquipmentGson gson = new EquipmentGson();
  private final EquipmentAccess access;

  public GsonEquipmentDatabase(EquipmentAccess access) {
    this.access = access;
  }

  @Override
  public String[] getAllAvailableTemplateIds() {
    List<String> ids = Lists.newArrayList();
    for (Path file : access.listAllFiles()) {
      String id = FilenameUtils.getBaseName(file.getFileName().toString());
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
  public void saveTemplate(IEquipmentTemplate template) {
    save(template);
    availableTemplatesChangeControl.announce().changeOccurred();
  }

  public void saveTemplateNoOverwrite(IEquipmentTemplate template) {
    if (loadTemplate(template.getName()) == null) {
      saveTemplate(template);
    }
  }

  @Override
  public void addAvailableTemplateChangeListener(ChangeListener listener) {
    availableTemplatesChangeControl.addListener(listener);
  }

  @Override
  public void deleteTemplate(String editTemplateId) {
    delete(editTemplateId);
    availableTemplatesChangeControl.announce().changeOccurred();
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

  @Override
  public void setPrintNameAdjuster(PrintNameAdjuster adjuster) {
    // nothing to do;
  }

  @Override
  public ChangeManagement getChangeManagement() {
    return new NullChangeManagement();
  }
}