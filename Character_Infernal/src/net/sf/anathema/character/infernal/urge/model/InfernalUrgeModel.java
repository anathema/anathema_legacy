package net.sf.anathema.character.infernal.urge.model;

import net.sf.anathema.character.generic.additionaltemplate.AdditionalModelType;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.infernal.urge.InfernalUrgeTemplate;
import net.sf.anathema.character.library.virtueflaw.model.IVirtueFlaw;
import net.sf.anathema.character.library.virtueflaw.model.VirtueFlawModel;
import net.sf.anathema.lib.control.GlobalChangeAdapter;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;

import java.util.ArrayList;
import java.util.List;

public class InfernalUrgeModel extends VirtueFlawModel implements IInfernalUrgeModel {
  private InfernalUrge infernalUrge;
  private InfernalUrgeTemplate template;

  public InfernalUrgeModel(InfernalUrgeTemplate template, ICharacterModelContext context) {
    super(context, template);
    this.template = template;
    infernalUrge = new InfernalUrge(context);
  }

  @Override
  public IVirtueFlaw getVirtueFlaw() {
    return infernalUrge;
  }

  @Override
  public ITraitType[] getFlawVirtueTypes() {
    List<ITraitType> flawVirtues = new ArrayList<ITraitType>();
    for (VirtueType virtueType : VirtueType.values()) {
      if (getContext().getTraitCollection().getTrait(virtueType).getCurrentValue() > 2) {
        flawVirtues.add(virtueType);
      }
    }
    return flawVirtues.toArray(new ITraitType[flawVirtues.size()]);
  }

  @Override
  public AdditionalModelType getAdditionalModelType() {
    return AdditionalModelType.Advantages;
  }

  @Override
  public String getTemplateId() {
    return template.getId();
  }

  @Override
  public void addChangeListener(IChangeListener listener) {
    super.addChangeListener(listener);
    getDescription().addTextChangedListener(new GlobalChangeAdapter<String>(listener));
  }

  @Override
  public ITextualDescription getDescription() {
    return infernalUrge.getDescription();
  }

  @Override
  public void setDescription(String urge) {
    getDescription().setText(urge);
  }
}