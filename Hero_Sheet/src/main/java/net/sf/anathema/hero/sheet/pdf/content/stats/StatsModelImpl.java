package net.sf.anathema.hero.sheet.pdf.content.stats;

import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.change.ChangeAnnouncer;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.StatsModifierFactory;
import net.sf.anathema.lib.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class StatsModelImpl implements StatsModel {

  private List<StatsModifierFactory> factories = new ArrayList<>();

  @Override
  public void addModifierFactory(StatsModifierFactory factory) {
    factories.add(factory);
  }

  @Override
  public Iterable<StatsModifierFactory> getModifierFactories() {
    return factories;
  }

  @Override
  public Identifier getId() {
    return ID;
  }

  @Override
  public void initialize(HeroEnvironment environment, Hero hero) {
  }

  @Override
  public void initializeListening(ChangeAnnouncer announcer) {
  }
}
