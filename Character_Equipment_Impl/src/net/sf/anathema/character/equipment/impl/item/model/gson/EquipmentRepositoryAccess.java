package net.sf.anathema.character.equipment.impl.item.model.gson;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.repository.IRepository;
import net.sf.anathema.framework.repository.RepositoryStringAccess;

import java.io.File;
import java.util.Collection;

public class EquipmentRepositoryAccess implements EquipmentAccess {

  private final IRepository repository;
  private final IItemType equipmentType;
  private final RepositoryStringAccess repositoryStringAccess;

  public EquipmentRepositoryAccess(IRepository repository, IItemType equipmentType) {
    this.repository = repository;
    this.equipmentType = equipmentType;
    this.repositoryStringAccess = new RepositoryStringAccess(repository, equipmentType);
  }

  @Override
  public Collection<File> listAllFiles() {
    return repository.getRepositoryFileResolver().listAllFiles(equipmentType);
  }

  @Override
  public void delete(String id) {
    repository.getRepositoryFileResolver().getMainFile(equipmentType, id).delete();
  }

  @Override
  public boolean exists(String templateId) {
    return repository.knowsItem(equipmentType, templateId);
  }

  @Override
  public String read(String id) {
    return repositoryStringAccess.read(id);
  }

  @Override
  public void write(String id, String json) {
    repositoryStringAccess.write(id, json);
  }
}