package net.sf.anathema.character.generic.framework.xml.trait;

import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.framework.xml.trait.pool.GenericTraitTemplatePool;
import net.sf.anathema.character.generic.impl.traits.ITraitTemplateFactory;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.lib.lang.clone.ICloneable;

public class GenericTraitTemplateFactory implements ITraitTemplateFactory, ICloneable<GenericTraitTemplateFactory> {

  private GenericTraitTemplatePool backgroundPool;
  private GenericTraitTemplatePool abilitiesPool;
  private GenericTraitTemplatePool attributesPool;
  private GenericTraitTemplatePool virtuesPool;
  private GenericTraitTemplate essenceTemplate;
  private GenericTraitTemplate willpowerTemplate;

  public ITraitTemplate createBackgroundTemplate(IBackgroundTemplate template) {
    return backgroundPool.getTemplate(template);
  }

  public ITraitTemplate createWillpowerTemplate() {
    return willpowerTemplate;
  }

  public ITraitTemplate createEssenceTemplate() {
    return essenceTemplate;
  }

  public ITraitTemplate createVirtueTemplate(VirtueType type) {
    return virtuesPool.getTemplate(type);
  }

  public ITraitTemplate createAttributeTemplate(AttributeType type) {
    return attributesPool.getTemplate(type);
  }

  public ITraitTemplate createAbilityTemplate(AbilityType type) {
    return abilitiesPool.getTemplate(type);
  }

  public void setBackgroundPool(GenericTraitTemplatePool backgroundPool) {
    this.backgroundPool = backgroundPool;
  }

  public void setAbilitiesPool(GenericTraitTemplatePool abilitiesPool) {
    this.abilitiesPool = abilitiesPool;
  }

  public void setAttributesPool(GenericTraitTemplatePool attributesPool) {
    this.attributesPool = attributesPool;
  }

  public void setVirtuesPool(GenericTraitTemplatePool virtuesPool) {
    this.virtuesPool = virtuesPool;
  }

  public void setEssenceTemplate(GenericTraitTemplate essenceTemplate) {
    this.essenceTemplate = essenceTemplate;
  }

  public void setWillpowerTemplate(GenericTraitTemplate willpowerTemplate) {
    this.willpowerTemplate = willpowerTemplate;
  }

  @Override
  public GenericTraitTemplateFactory clone() {
    GenericTraitTemplateFactory clone = new GenericTraitTemplateFactory();
    clone.abilitiesPool = abilitiesPool == null ? null : abilitiesPool.clone();
    clone.attributesPool = attributesPool == null ? null : attributesPool.clone();
    clone.backgroundPool = backgroundPool == null ? null : backgroundPool.clone();
    clone.virtuesPool = virtuesPool == null ? null : virtuesPool.clone();
    clone.essenceTemplate = essenceTemplate == null ? null : essenceTemplate.clone();
    clone.willpowerTemplate = willpowerTemplate == null ? null : willpowerTemplate.clone();
    return clone;
  }
}