package net.sf.anathema.character.equipment.impl.item.model.db4o;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import net.sf.anathema.character.equipment.item.model.ICollectionFactory;
import net.sf.anathema.character.equipment.item.model.IEquipmentDatabase;
import net.sf.anathema.character.equipment.template.IEquipmentTemplate;
import net.sf.anathema.framework.itemdata.model.NonPersistableItemData;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;

public class Db4OEquipmentDatabase extends NonPersistableItemData implements IEquipmentDatabase {

  public static final String DATABASE_FILE = "Equipment.yap"; //$NON-NLS-1$
  public static final String DATABASE_FOLDER = "equipment"; //$NON-NLS-1$
  private final ICollectionFactory collectionFactory;
  private final ChangeControl availableTemplatesChangeControl = new ChangeControl();
  private final ObjectContainer container;

  public Db4OEquipmentDatabase(File databaseFile) {
    this(EquipmentDatabaseConnectionManager.createConnection(databaseFile));
  }

  /** Required for DB conversion from 1.0 */
  public Db4OEquipmentDatabase(ObjectContainer container) {
    this.container = container;
    collectionFactory = new Db4OCollectionFactory(container);
  }

  public String[] getAllAvailableTemplateIds() {
    final Set<String> idSet = new HashSet<String>();
    queryContainer(new Predicate<IEquipmentTemplate>() {
      @Override
      public boolean match(IEquipmentTemplate candidate) {
        idSet.add(candidate.getName());
        return false;
      }
    });
    return idSet.toArray(new String[idSet.size()]);
  }

  public void queryContainer(Predicate<IEquipmentTemplate> predicate) {
    container.query(predicate);
  }

  public IEquipmentTemplate loadTemplate(final String templateId) {
    ObjectSet<IEquipmentTemplate> results = container.query(new Predicate<IEquipmentTemplate>() {
      @Override
      public boolean match(IEquipmentTemplate candidate) {
        return candidate.getName().equals(templateId);
      }
    });
    if (results.isEmpty()) {
      return null;
    }
    return results.next();
  }

  public ICollectionFactory getCollectionFactory() {
    return collectionFactory;
  }

  public void saveTemplate(IEquipmentTemplate template) {
    container.set(template);
    container.commit();
    availableTemplatesChangeControl.fireChangedEvent();
  }

  public void addAvailableTemplateChangeListener(IChangeListener listener) {
    availableTemplatesChangeControl.addChangeListener(listener);
  }

  public void deleteTemplate(String editTemplateId) {
    delete(editTemplateId);
    availableTemplatesChangeControl.fireChangedEvent();
  }

  private void delete(String editTemplateId) {
    IEquipmentTemplate oldTemplate = loadTemplate(editTemplateId);
    container.delete(oldTemplate);
    container.commit();
  }

  public void updateTemplate(String editTemplateId, IEquipmentTemplate saveTemplate) {
    delete(editTemplateId);
    saveTemplate(saveTemplate);
  }
}