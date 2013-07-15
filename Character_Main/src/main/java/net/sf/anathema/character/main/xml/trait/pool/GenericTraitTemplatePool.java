package net.sf.anathema.character.main.xml.trait.pool;

import net.sf.anathema.character.main.traits.ITraitTemplate;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.character.main.xml.trait.GenericTraitTemplate;
import net.sf.anathema.character.main.xml.trait.IClonableTraitTemplate;
import net.sf.anathema.lib.exception.UnreachableCodeReachedException;
import net.sf.anathema.lib.lang.clone.ICloneable;

import java.util.HashMap;
import java.util.Map;

public class GenericTraitTemplatePool implements ICloneable<GenericTraitTemplatePool> {

  private GenericTraitTemplate defaultTraitTemplate;
  // This is volatile instead of final to allow clone to be implemented
  private volatile Map<TraitType, IClonableTraitTemplate> specialTraitTemplates = new HashMap<>();

  public ITraitTemplate getTemplate(TraitType traitType) {
    ITraitTemplate template = specialTraitTemplates.get(traitType);
    if (template != null) {
      return template;
    }
    return defaultTraitTemplate;
  }

  public GenericTraitTemplate getDefaultTemplate() {
    return defaultTraitTemplate;
  }

  public void setDefaultTemplate(GenericTraitTemplate template) {
    this.defaultTraitTemplate = template;
  }

  public void setSpecialTemplate(TraitType traitType, IClonableTraitTemplate template) {
    specialTraitTemplates.put(traitType, template);
  }

  @Override
  public GenericTraitTemplatePool clone() {
    GenericTraitTemplatePool clone;
    try {
      clone = (GenericTraitTemplatePool) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new UnreachableCodeReachedException(e);
    }
    clone.specialTraitTemplates = new HashMap<>(4 * specialTraitTemplates.size() / 3 + 1);

    if (defaultTraitTemplate != null) {
      clone.defaultTraitTemplate = defaultTraitTemplate.clone();
    }
    for (TraitType type : specialTraitTemplates.keySet()) {
      clone.specialTraitTemplates.put(type, specialTraitTemplates.get(type).clone());
    }
    return clone;
  }
}
