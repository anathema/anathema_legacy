package net.sf.anathema.character.sidereal.flawedfate.model;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.library.virtueflaw.model.VirtueFlawModel;
import net.sf.anathema.lib.control.IChangeListener;

import java.util.ArrayList;
import java.util.List;

public class SiderealFlawedFateModel extends VirtueFlawModel
{
  public SiderealFlawedFateModel(ICharacterModelContext context, IAdditionalTemplate additionalTemplate) {
    super(context, additionalTemplate);
  }
  
  @Override
  public void addChangeListener(IChangeListener listener) {
    super.addChangeListener(listener);
  }

  @Override
  public ITraitType[] getFlawVirtueTypes() {
    List<ITraitType> flawVirtues = new ArrayList<>();
    for (VirtueType virtueType : VirtueType.values()) {
      flawVirtues.add(virtueType);
    }
    return flawVirtues.toArray(new ITraitType[0]);
  }
}