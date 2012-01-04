package net.sf.anathema.character.abyssal.reporting.content;

import net.sf.anathema.character.abyssal.resonance.AbyssalResonanceTemplate;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.library.virtueflaw.model.IVirtueFlaw;
import net.sf.anathema.character.library.virtueflaw.presenter.IVirtueFlawModel;
import net.sf.anathema.character.reporting.pdf.content.AbstractSubBoxContent;
import net.sf.anathema.lib.resources.IResources;

public class Abyssal2ndResonanceContent extends AbstractSubBoxContent {

  private IGenericCharacter character;

  public Abyssal2ndResonanceContent(IResources resources, IGenericCharacter character) {
    super(resources);
    this.character = character;
  }

  @Override
  public String getHeaderKey() {
    return "GreatCurse.Abyssal"; //$NON-NLS-1$
  }

  private IVirtueFlaw getResonance() {
    return ((IVirtueFlawModel) character.getAdditionalModel(AbyssalResonanceTemplate.ID)).getVirtueFlaw();
  }

  public int getLimitValue() {
    return getResonance().getLimitTrait().getCurrentValue();
  }

  public boolean isComplete() {
    return getResonance().isFlawComplete();
  }

  public String getFlawedVirtueLabel() {
    return getString("Sheet.GreatCurse.FlawedVirtue") + ": ";   //$NON-NLS-1$ //$NON-NLS-2$
  }

  public String getFlawedVirtue() {
    return getResonance().getRoot().getId();
  }

  public String getResonanceReference() {
    return getString("Sheet.GreatCurse.ResonanceReference");  //$NON-NLS-1$
  }
}
