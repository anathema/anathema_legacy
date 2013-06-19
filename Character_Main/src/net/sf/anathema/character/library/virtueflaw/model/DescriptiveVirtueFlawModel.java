package net.sf.anathema.character.library.virtueflaw.model;

import net.sf.anathema.character.change.ChangeFlavor;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.library.virtueflaw.presenter.IDescriptiveVirtueFlawModel;
import net.sf.anathema.character.main.hero.Hero;
import net.sf.anathema.character.main.hero.change.FlavoredChangeListener;
import net.sf.anathema.character.main.model.traits.TraitChangeFlavor;
import net.sf.anathema.character.main.model.traits.TraitModel;
import net.sf.anathema.character.main.model.traits.TraitModelFetcher;
import net.sf.anathema.lib.control.GlobalChangeAdapter;
import net.sf.anathema.lib.control.IChangeListener;

import java.util.ArrayList;
import java.util.List;

public class DescriptiveVirtueFlawModel extends VirtueFlawModel implements IDescriptiveVirtueFlawModel {

  private final IDescriptiveVirtueFlaw virtueFlaw;
  private TraitModel traitModel;

  public DescriptiveVirtueFlawModel(final Hero hero, IAdditionalTemplate additionalTemplate) {
    super(hero, additionalTemplate);
    this.traitModel = TraitModelFetcher.fetch(hero);
    virtueFlaw = new DescriptiveVirtueFlaw(hero);
    hero.getChangeAnnouncer().addListener(new FlavoredChangeListener() {
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
  public void addChangeListener(IChangeListener listener) {
    super.addChangeListener(listener);
    GlobalChangeAdapter<String> changeAdapter = new GlobalChangeAdapter<>(listener);
    virtueFlaw.getDescription().addTextChangedListener(changeAdapter);
    virtueFlaw.getLimitBreak().addTextChangedListener(changeAdapter);
  }

  @Override
  public IDescriptiveVirtueFlaw getVirtueFlaw() {
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