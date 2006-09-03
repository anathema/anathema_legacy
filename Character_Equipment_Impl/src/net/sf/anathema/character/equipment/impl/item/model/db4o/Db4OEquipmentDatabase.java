package net.sf.anathema.character.equipment.impl.item.model.db4o;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import net.sf.anathema.character.equipment.item.model.ICollectionFactory;
import net.sf.anathema.character.equipment.item.model.IEquipmentDatabase;
import net.sf.anathema.character.equipment.template.IEquipmentTemplate;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.exception.PersistenceException;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;

public class Db4OEquipmentDatabase implements IEquipmentDatabase {

  private ICollectionFactory collectionFactory;
  private final ChangeControl availableTemplatesChangeControl = new ChangeControl();
  private final ObjectContainer container;

  public Db4OEquipmentDatabase(File databaseFile) {
    container = EquipmentDatabaseConnectionManager.createConnection(databaseFile);
    collectionFactory = new Db4OCollectionFactory(container);
  }

  public String[] getAllAvailableTemplateIds() {
    final Set<String> idSet = new HashSet<String>();
    container.query(new Predicate<IEquipmentTemplate>() {
      @Override
      public boolean match(IEquipmentTemplate candidate) {
        idSet.add(candidate.getName());
        return false;
      }
    });
    return idSet.toArray(new String[idSet.size()]);
  }

  public IEquipmentTemplate loadTemplate(final String templateId) {
    ObjectSet<IEquipmentTemplate> results = container.query(new Predicate<IEquipmentTemplate>() {
      @Override
      public boolean match(IEquipmentTemplate candidate) {
        return candidate.getName().equals(templateId);
      }
    });
    return results.next();
  }

  public ICollectionFactory getCollectionFactory() {
    return collectionFactory;
  }

  public void saveTemplate(IEquipmentTemplate template) throws PersistenceException {
    container.set(template);
    container.commit();
    availableTemplatesChangeControl.fireChangedEvent();
  }

  public void addAvailableTemplateChangeListener(IChangeListener listener) {
    availableTemplatesChangeControl.addChangeListener(listener);
  }

  public void deleteTemplate(String editTemplateId) {
    IEquipmentTemplate oldTemplate = loadTemplate(editTemplateId);
    container.delete(oldTemplate);
    container.commit();
    availableTemplatesChangeControl.fireChangedEvent();
  }

  public void updateTemplate(String editTemplateId, IEquipmentTemplate saveTemplate) {
    IEquipmentTemplate oldTemplate = loadTemplate(editTemplateId);
    container.delete(oldTemplate);
    container.commit();
    container.set(saveTemplate);
    container.commit();
    availableTemplatesChangeControl.fireChangedEvent();
  }
}