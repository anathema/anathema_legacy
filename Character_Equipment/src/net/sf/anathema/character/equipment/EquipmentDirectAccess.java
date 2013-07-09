package net.sf.anathema.character.equipment;

import net.sf.anathema.character.equipment.item.model.gson.EquipmentAccess;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.io.PathUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;

public class EquipmentDirectAccess implements EquipmentAccess {
  private final Path baseDirectory;

  public EquipmentDirectAccess(Path baseDirectory) {
    this.baseDirectory = baseDirectory;
  }

  @Override
  public Collection<Path> listAllFiles() {
    return PathUtils.listAll(baseDirectory, "*.item");
  }

  @Override
  public void delete(String id) {
    throw new PersistenceException("Use Repository Access instead");
  }

  @Override
  public boolean exists(String templateId) {
    return Files.exists(getTemplateFile(templateId));
  }

  @Override
  public String read(String id) {
    try {
      Path templateFile = getTemplateFile(id);
      return PathUtils.readFileToString(templateFile);
    } catch (IOException e) {
      throw new PersistenceException(e);
    }
  }

  @Override
  public void write(String id, String content) {
    throw new PersistenceException("Use Repository Access instead");
  }

  private Path getTemplateFile(String id) {
    return baseDirectory.resolve(id + ".item");
  }
}