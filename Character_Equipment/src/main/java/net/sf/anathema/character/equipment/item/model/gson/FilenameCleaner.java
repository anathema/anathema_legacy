package net.sf.anathema.character.equipment.item.model.gson;

public class FilenameCleaner {
  public static String clean(String input) {
    return input.replaceAll("\\W", "_");
  }
}
