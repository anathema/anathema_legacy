package net.sf.anathema.hero.sheet.pdf.content.stats;

import net.sf.anathema.hero.sheet.pdf.encoder.boxes.StatsModifierFactory;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

public interface StatsModel extends HeroModel {

  Identifier ID = new SimpleIdentifier("Stats");

  void addModifierFactory(StatsModifierFactory factory);

  Iterable<StatsModifierFactory> getModifierFactories();
}
