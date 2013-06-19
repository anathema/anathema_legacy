package net.sf.anathema.character.intimacies.presenter;

import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryListener;
import net.sf.anathema.hero.change.ChangeAnnouncer;
import net.sf.anathema.hero.change.FlavoredChangeListener;
import net.sf.anathema.hero.intimacies.model.IntimaciesModel;
import net.sf.anathema.hero.intimacies.model.Intimacy;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.InitializationContext;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class DemoIntimaciesModel implements IntimaciesModel {

  private List<Intimacy> entries = new ArrayList<>();

  @Override
  public void addModelChangeListener(IChangeListener listener) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean isCharacterExperienced() {
    return false;
  }

  @Override
  public int getCompletionValue() {
    throw new UnsupportedOperationException();
  }

  @Override
  public int getFreeIntimacies() {
    throw new UnsupportedOperationException();
  }

  @Override
  public int getIntimaciesLimit() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setCurrentName(String name) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void addChangeListener(FlavoredChangeListener listener) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void addModelChangeListener(IRemovableEntryListener<Intimacy> listener) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Intimacy commitSelection() {
    throw new UnsupportedOperationException();
  }

  @Override
  public List<Intimacy> getEntries() {
    return entries;
  }

  @Override
  public void removeEntry(Intimacy entry) {
    throw new UnsupportedOperationException();
  }

  public void addEntry(Intimacy intimacy) {
    entries.add(intimacy);
  }

  @Override
  public Identifier getId() {
    return IntimaciesModel.ID;
  }

  @Override
  public void initialize(InitializationContext context, Hero hero) {
    // nothing to do
  }

  @Override
  public void initializeListening(ChangeAnnouncer announcer) {
    // nothing to do
  }
}