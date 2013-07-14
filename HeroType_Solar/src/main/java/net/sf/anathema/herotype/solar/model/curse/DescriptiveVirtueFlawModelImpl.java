package net.sf.anathema.herotype.solar.model.curse;

import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.character.main.traits.types.VirtueType;
import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.change.ChangeAnnouncer;
import net.sf.anathema.hero.model.change.ChangeFlavor;
import net.sf.anathema.hero.model.change.FlavoredChangeListener;
import net.sf.anathema.hero.model.change.UnspecifiedChangeListener;
import net.sf.anathema.hero.traits.TraitChangeFlavor;
import net.sf.anathema.hero.traits.TraitModel;
import net.sf.anathema.hero.traits.TraitModelFetcher;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.control.GlobalChangeAdapter;
import net.sf.anathema.lib.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class DescriptiveVirtueFlawModelImpl extends AbstractVirtueFlawModel implements DescriptiveVirtueFlawModel {

  private DescriptiveVirtueFlaw virtueFlaw;
  private TraitModel traitModel;

  @Override
  public final Identifier getId() {
    return ID;
  }

  @Override
  public void initialize(HeroEnvironment environment, Hero hero) {
    super.initialize(environment, hero);
    this.traitModel = TraitModelFetcher.fetch(hero);
    virtueFlaw = new DescriptiveVirtueFlawImpl(hero);
  }

  @Override
  public void initializeListening(ChangeAnnouncer announcer) {
    addChangeListener(new UnspecifiedChangeListener(announcer));
    announcer.addListener(new FlavoredChangeListener() {
      @Override
      public void changeOccurred(ChangeFlavor flavor) {
        if (TraitChangeFlavor.changes(flavor, VirtueType.values())) {
          TraitType rootType = getVirtueFlaw().getRoot();
          if (rootType != null && traitModel.getTrait(rootType).getCurrentValue() < 3) {
            getVirtueFlaw().setRoot(null);
          }
        }
      }
    });
  }

  @Override
  public void addChangeListener(ChangeListener listener) {
    super.addChangeListener(listener);
    GlobalChangeAdapter<String> changeAdapter = new GlobalChangeAdapter<>(listener);
    virtueFlaw.getDescription().addTextChangedListener(changeAdapter);
    virtueFlaw.getLimitBreak().addTextChangedListener(changeAdapter);
  }

  @Override
  public DescriptiveVirtueFlaw getVirtueFlaw() {
    return virtueFlaw;
  }

  @Override
  public TraitType[] getFlawVirtueTypes() {
    List<TraitType> flawVirtues = new ArrayList<>();
    for (VirtueType virtueType : VirtueType.values()) {
      if (traitModel.getTrait(virtueType).getCurrentValue() > 2) {
        flawVirtues.add(virtueType);
      }
    }
    return flawVirtues.toArray(new TraitType[flawVirtues.size()]);
  }
}