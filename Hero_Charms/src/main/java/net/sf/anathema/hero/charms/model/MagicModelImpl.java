package net.sf.anathema.hero.charms.model;

import net.sf.anathema.character.main.magic.sheet.content.IMagicStats;
import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.hero.charms.advance.creation.MagicCreationCostEvaluator;
import net.sf.anathema.hero.charms.advance.creation.MagicLearner;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.change.ChangeAnnouncer;
import net.sf.anathema.lib.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class MagicModelImpl implements MagicModel {

  private final List<PrintMagicProvider> printMagicProviders = new ArrayList<>();
  private final List<MagicLearner> magicLearners = new ArrayList<>();

  @Override
  public Identifier getId() {
    return ID;
  }

  @Override
  public void initialize(HeroEnvironment environment, Hero hero) {
    // nothing to do
  }

  @Override
  public void initializeListening(ChangeAnnouncer announcer) {
    // nothing to do
  }

  @Override
  public void addPrintProvider(PrintMagicProvider provider) {
    printMagicProviders.add(provider);
  }

  @Override
  public void addLearnProvider(MagicLearner provider) {
    magicLearners.add(provider);
  }

  @Override
  public MagicCreationCostEvaluator getMagicCostEvaluator() {
    return new MagicCreationCostEvaluator(magicLearners);
  }

  @Override
  public void addPrintMagic(List<IMagicStats> printMagic) {
    for (PrintMagicProvider provider : printMagicProviders) {
      provider.addPrintMagic(printMagic);
    }
  }
}
