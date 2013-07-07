package net.sf.anathema.character.main.library.virtueflaw.model;

import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.main.model.traits.TraitChangeFlavor;
import net.sf.anathema.character.main.model.traits.TraitModel;
import net.sf.anathema.character.main.model.traits.TraitModelFetcher;
import net.sf.anathema.character.model.UnspecifiedChangeListener;
import net.sf.anathema.hero.change.ChangeAnnouncer;
import net.sf.anathema.hero.change.ChangeFlavor;
import net.sf.anathema.hero.change.FlavoredChangeListener;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.InitializationContext;
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
  public void initialize(InitializationContext context, Hero hero) {
    super.initialize(context, hero);
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