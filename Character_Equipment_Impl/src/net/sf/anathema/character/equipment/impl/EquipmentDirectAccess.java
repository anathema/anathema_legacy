package net.sf.anathema.character.equipment.impl;

import net.sf.anathema.character.equipment.impl.item.model.gson.EquipmentAccess;
import net.sf.anathema.lib.exception.PersistenceException;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

public class EquipmentDirectAccess implements EquipmentAccess {
  private final File baseDirectory;

  public EquipmentDirectAccess(File baseDirectory) {
    this.baseDirectory = baseDirectory;
  }

  @Override
  public Collection<File> listAllFiles() {
    return FileUtils.listFiles(baseDirectory, new String[]{"item"}, false);
  }

  @Override
  public void delete(String id) {
    throw new PersistenceException("Use Repository Access instead");
  }

  @Override
  public boolean exists(String templateId) {
    return getTemplateFile(templateId).exists();
  }

  @Override
  public String read(String id) {
    try {
      return FileUtils.readFileToString(getTemplateFile(id));
    } catch (IOException e) {
      throw new PersistenceException(e);
    }
  }

  @Override
  public void write(String id, String content) {
    throw new PersistenceException("Use Repository Access instead");
  }

  private File getTemplateFile(String id) {
    return new File(baseDirectory, id + ".item");
  }
}