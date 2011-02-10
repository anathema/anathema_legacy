package net.sf.anathema.character.abyssal.resonance.model;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.library.virtueflaw.model.VirtueFlawModel;
import net.sf.anathema.lib.control.change.IChangeListener;

public class AbyssalResonanceModel extends VirtueFlawModel {

  public AbyssalResonanceModel(final ICharacterModelContext context, IAdditionalTemplate additionalTemplate) {
    super(context, additionalTemplate);
  }
  
  @Override
  public void addChangeListener(IChangeListener listener) {
    super.addChangeListener(listener);
  }

  public ITraitType[] getFlawVirtueTypes() {
    List<ITraitType> flawVirtues = new ArrayList<ITraitType>();
    for (VirtueType virtueType : VirtueType.values()) {
      flawVirtues.add(virtueType);
    }
    return flawVirtues.toArray(new ITraitType[0]);
  }
}