package net.sf.anathema.character.generic.framework.xml.trait.pool;

import java.util.HashMap;
import java.util.Map;

import net.disy.commons.core.exception.UnreachableCodeReachedException;
import net.sf.anathema.character.generic.framework.xml.trait.GenericTraitTemplate;
import net.sf.anathema.character.generic.framework.xml.trait.IClonableTraitTemplate;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.lib.lang.clone.ICloneable;

public class GenericTraitTemplatePool implements ICloneable<GenericTraitTemplatePool> {

  private GenericTraitTemplate defaultTraitTemplate;
  // This is volatile instead of final to allow clone to be implemented
  private volatile Map<ITraitType, IClonableTraitTemplate> specialTraitTemplates = new HashMap<ITraitType, IClonableTraitTemplate>();

  public ITraitTemplate getTemplate(ITraitType traitType) {
    ITraitTemplate template = specialTraitTemplates.get(traitType);
    if (template != null) {
      return template;
    }
    return defaultTraitTemplate;
  }
  
  public GenericTraitTemplate getDefaultTemplate()
  {
	  return defaultTraitTemplate;
  }

  public void setDefaultTemplate(GenericTraitTemplate template) {
    this.defaultTraitTemplate = template;
  }

  public void setSpecialTemplate(ITraitType traitType, IClonableTraitTemplate template) {
    specialTraitTemplates.put(traitType, template);
  }

  @Override
  public GenericTraitTemplatePool clone() {
    GenericTraitTemplatePool clone;
    try {
      clone = (GenericTraitTemplatePool)super.clone();
    } catch (CloneNotSupportedException e) {
      throw new UnreachableCodeReachedException(e);
    }
    clone.specialTraitTemplates = new HashMap<ITraitType, IClonableTraitTemplate>(4 * specialTraitTemplates.size() / 3 + 1);
    
    if (defaultTraitTemplate != null) {
      clone.defaultTraitTemplate = defaultTraitTemplate.clone();
    }
    for (ITraitType type : specialTraitTemplates.keySet()) {
      clone.specialTraitTemplates.put(type, specialTraitTemplates.get(type).clone());
    }
    return clone;
  }
}
