package net.sf.anathema.character.equipment.item.model.gson;

import java.nio.file.Path;
import java.util.Collection;

public interface EquipmentAccess {
  Collection<Path> listAllFiles();

  void delete(String id);

  boolean exists(String templateId);

  String read(String id);

  void write(String id, String json);
}