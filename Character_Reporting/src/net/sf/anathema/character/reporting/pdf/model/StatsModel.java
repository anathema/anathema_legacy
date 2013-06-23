package net.sf.anathema.character.reporting.pdf.model;

import net.sf.anathema.character.reporting.pdf.rendering.boxes.StatsModifierFactory;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

public interface StatsModel extends HeroModel {

  Identifier ID = new SimpleIdentifier("Stats");

  void addModifierFactory(StatsModifierFactory factory);

  Iterable<StatsModifierFactory> getModifierFactories();
}
