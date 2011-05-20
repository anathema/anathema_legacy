package net.sf.anathema.character.sidereal.paradox.model;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.additionaltemplate.AdditionalModelType;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.library.virtueflaw.model.IVirtueFlaw;
import net.sf.anathema.character.library.virtueflaw.model.VirtueFlawModel;
import net.sf.anathema.lib.control.change.IChangeListener;

public class SiderealParadoxModel extends VirtueFlawModel
{
  SiderealParadox virtueFlaw;
	
  public SiderealParadoxModel(final ICharacterModelContext context, IAdditionalTemplate additionalTemplate) {
    super(context, additionalTemplate);
    virtueFlaw = new SiderealParadox(context);
  }
  
  @Override
  public IVirtueFlaw getVirtueFlaw()
  {
	  return virtueFlaw;
  }
  
  @Override
  public AdditionalModelType getAdditionalModelType() {
	    return AdditionalModelType.Miscellaneous;
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