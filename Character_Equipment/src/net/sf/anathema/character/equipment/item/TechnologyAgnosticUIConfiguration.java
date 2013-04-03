package net.sf.anathema.character.equipment.item;

@SuppressWarnings("UnusedDeclaration")
public interface TechnologyAgnosticUIConfiguration<T> {
  String NO_ICON = null;
  String NO_LABEL= null;
  String NO_TOOLTIP= null;

  String getIconsRelativePath(T value);

  String getLabel(T value);

  String getToolTipText(T value);
}