package net.sf.anathema.magic.description.persistence;

public interface MagicDescriptionDataBase {
  void saveDescription(String charmId, String description);

  String loadDescription(String charmId);
}
