package net.sf.anathema.character.equipment.impl.item.model.gson;

public class FilenameCleaner {
  public String clean(String input) {
    return input.replaceAll("\\W", "_");
  }
}
