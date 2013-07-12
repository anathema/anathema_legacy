package net.sf.anathema.hero.magic.model;

import net.sf.anathema.hero.magic.advance.MagicCostEvaluator;
import net.sf.anathema.character.main.magic.model.magic.IMagicStats;
import net.sf.anathema.hero.change.ChangeAnnouncer;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.InitializationContext;
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
  public void initialize(InitializationContext context, Hero hero) {
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
  public MagicCostEvaluator getMagicCostEvaluator() {
    return new MagicCostEvaluator(magicLearners);
  }

  @Override
  public void addPrintMagic(List<IMagicStats> printMagic) {
    for (PrintMagicProvider provider : printMagicProviders) {
      provider.addPrintMagic(printMagic);
    }
  }
}
