package net.sf.anathema.hero.magic.model;

import net.sf.anathema.hero.magic.advance.creation.MagicCreationCostEvaluator;
import net.sf.anathema.hero.magic.advance.creation.MagicLearner;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

public interface MagicModel extends HeroModel, PrintMagicProvider {

  Identifier ID = new SimpleIdentifier("Magic");

  void addPrintProvider(PrintMagicProvider provider);

  void addLearnProvider(MagicLearner provider);

  MagicCreationCostEvaluator getMagicCostEvaluator();
}
