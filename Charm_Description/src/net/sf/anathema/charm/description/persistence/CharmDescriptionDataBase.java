package net.sf.anathema.charm.description.persistence;

public interface CharmDescriptionDataBase {
  void saveDescription(String charmId, String description);

  String loadDescription(String charmId);
}
