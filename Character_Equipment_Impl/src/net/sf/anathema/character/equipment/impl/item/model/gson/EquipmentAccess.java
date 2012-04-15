package net.sf.anathema.character.equipment.impl.item.model.gson;

import java.io.File;
import java.util.Collection;

public interface EquipmentAccess {
  Collection<File> listAllFiles();

  void delete(String id);

  boolean exists(String templateId);

  String read(String id);

  void write(String id, String json);
}
