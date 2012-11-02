package net.sf.anathema.character.abyssal.resonance.model;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.library.virtueflaw.model.VirtueFlawModel;
import net.sf.anathema.lib.control.IChangeListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AbyssalResonanceModel extends VirtueFlawModel
{
  private final AbyssalResonance virtueFlaw;
	
  public AbyssalResonanceModel(ICharacterModelContext context, IAdditionalTemplate additionalTemplate) {
    super(context, additionalTemplate);
    virtueFlaw = new AbyssalResonance(context);
  }
  
  @Override
  public AbyssalResonance getVirtueFlaw() {
    return virtueFlaw;
  }
  
  @Override
  public void addChangeListener(IChangeListener listener) {
    super.addChangeListener(listener);
  }

  @Override
  public ITraitType[] getFlawVirtueTypes() {
    List<ITraitType> flawVirtues = new ArrayList<>();
    Collections.addAll(flawVirtues, VirtueType.values());
    return flawVirtues.toArray(new ITraitType[flawVirtues.size()]);
  }
}