package net.sf.anathema.hero.model.change;

public interface ChangeAnnouncer {

  void addListener(FlavoredChangeListener listener);

  void announceChangeOf(ChangeFlavor flavor);
}
