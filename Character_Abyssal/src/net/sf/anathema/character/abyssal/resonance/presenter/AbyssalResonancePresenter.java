package net.sf.anathema.character.abyssal.resonance.presenter;

import net.sf.anathema.character.library.virtueflaw.model.IVirtueFlaw;
import net.sf.anathema.character.library.virtueflaw.presenter.IVirtueFlawModel;
import net.sf.anathema.character.library.virtueflaw.presenter.IVirtueFlawView;
import net.sf.anathema.character.library.virtueflaw.presenter.VirtueFlawPresenter;
import net.sf.anathema.lib.resources.IResources;

public class AbyssalResonancePresenter extends VirtueFlawPresenter {

  private final IVirtueFlawModel model;

  public AbyssalResonancePresenter(IResources resources, IVirtueFlawView virtueFlawView, IVirtueFlawModel model) {
    super(resources, virtueFlawView, model);
    this.model = model;
  }
  
  @Override
  protected void initBasicPresentation() {
    IVirtueFlaw virtueFlaw = model.getVirtueFlaw();
    initRootPresentation(virtueFlaw, "Resonance.FlawedVirtue.Name");
  }
}