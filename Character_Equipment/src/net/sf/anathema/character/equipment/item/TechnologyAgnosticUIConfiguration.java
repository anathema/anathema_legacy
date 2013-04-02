package net.sf.anathema.character.equipment.item;

public interface TechnologyAgnosticUIConfiguration<T> {
  String getIconsRelativePath(T value);

  String getLabel(T value);

  String getToolTipText(T value);
}