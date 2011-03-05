package net.sf.anathema.character.generic.impl.exalt;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.impl.template.AbstractCharacterTemplate;
import net.sf.anathema.character.generic.impl.template.points.UnsupportedCreationPoints;
import net.sf.anathema.character.generic.impl.traits.ExaltTraitTemplateFactory;
import net.sf.anathema.character.generic.impl.traits.TraitTemplateCollection;
import net.sf.anathema.character.generic.template.ITraitTemplateCollection;
import net.sf.anathema.character.generic.template.IUnsupportedTemplate;
import net.sf.anathema.character.generic.template.creation.IBonusPointCosts;
import net.sf.anathema.character.generic.template.creation.ICreationPoints;
import net.sf.anathema.character.generic.template.essence.IEssenceTemplate;
import net.sf.anathema.character.generic.template.experience.IExperiencePointCosts;
import net.sf.anathema.character.generic.template.magic.ISpellMagicTemplate;
import net.sf.anathema.lib.data.Range;
import net.sf.anathema.lib.exception.NotYetImplementedException;

public abstract class AbstractUnsupportedExaltTemplate extends AbstractCharacterTemplate implements
    IUnsupportedTemplate {

  private final ICreationPoints creationPoints = new UnsupportedCreationPoints();
  private final ITraitTemplateCollection traitTemplateCollection = new TraitTemplateCollection(
      new ExaltTraitTemplateFactory());

  public IBonusPointCosts getBonusPointCosts() {
    throw new NotYetImplementedException();
  }

  public ISpellMagicTemplate getSpellMagic() {
    throw new NotYetImplementedException();
  }

  public IEssenceTemplate getEssenceTemplate() {
    throw new NotYetImplementedException();
  }

  public Range getEssenceRange() {
    throw new NotYetImplementedException();
  }

  public IExperiencePointCosts getExperienceCost() {
    throw new NotYetImplementedException();
  }

  public ICreationPoints getCreationPoints() {
    return creationPoints;
  }

  public ITraitTemplateCollection getTraitTemplateCollection() {
    return traitTemplateCollection;
  }

  @Override
  public ICasteType[] getAllCasteTypes() {
    return new ICasteType[0];
  }
  
  public boolean isLegacy()
  {
	  return false;
  }
}