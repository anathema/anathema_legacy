package net.sf.anathema.hero.change;

public interface ChangeAnnouncer {

  void addListener(FlavoredChangeListener listener);

  void announceChangeOf(ChangeFlavor flavor);
}
