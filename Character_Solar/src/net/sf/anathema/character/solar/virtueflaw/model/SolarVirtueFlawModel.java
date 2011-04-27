package net.sf.anathema.character.solar.virtueflaw.model;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.VirtueChangeListener;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.library.virtueflaw.model.VirtueFlawModel;
import net.sf.anathema.character.solar.virtueflaw.presenter.ISolarVirtueFlawModel;
import net.sf.anathema.lib.control.change.GlobalChangeAdapter;
import net.sf.anathema.lib.control.change.IChangeListener;

public class SolarVirtueFlawModel extends VirtueFlawModel implements ISolarVirtueFlawModel {

  private final ISolarVirtueFlaw virtueFlaw;
  private IGenericTraitCollection traitCollection;

  public SolarVirtueFlawModel(final ICharacterModelContext context, IAdditionalTemplate additionalTemplate) {
    super(context, additionalTemplate);
    this.traitCollection = context.getTraitCollection();
    virtueFlaw = new SolarVirtueFlaw(context);
    addVirtueChangeListener(new VirtueChangeListener() {
      @Override
      public void configuredChangeOccured() {
        ITraitType rootType = getVirtueFlaw().getRoot();
        if (rootType != null && context.getTraitCollection().getTrait(rootType).getCurrentValue() < 3) {
          getVirtueFlaw().setRoot(null);
        }
      }
    });
  }
  
  @Override
  public void addChangeListener(IChangeListener listener) {
    super.addChangeListener(listener);
    GlobalChangeAdapter<String> changeAdapter = new GlobalChangeAdapter<String>(listener);
    virtueFlaw.getDescription().addTextChangedListener(changeAdapter);
    virtueFlaw.getLimitBreak().addTextChangedListener(changeAdapter);
  }

  @Override
  public ISolarVirtueFlaw getVirtueFlaw() {
    return virtueFlaw;
  }

  public ITraitType[] getFlawVirtueTypes() {
    List<ITraitType> flawVirtues = new ArrayList<ITraitType>();
    for (VirtueType virtueType : VirtueType.values()) {
      if (traitCollection.getTrait(virtueType).getCurrentValue() > 2) {
        flawVirtues.add(virtueType);
      }
    }
    return flawVirtues.toArray(new ITraitType[0]);
  }
}