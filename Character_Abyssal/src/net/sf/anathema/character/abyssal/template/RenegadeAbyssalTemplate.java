package net.sf.anathema.character.abyssal.template;

import net.sf.anathema.character.abyssal.template.creation.RenegadeAbyssalCreationPoints;
import net.sf.anathema.character.generic.additionalrules.IAdditionalRules;
import net.sf.anathema.character.generic.impl.magic.persistence.ICharmCache;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.generic.template.creation.ICreationPoints;
import net.sf.anathema.character.generic.template.presentation.IPresentationProperties;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public class RenegadeAbyssalTemplate extends AbstractAbyssalTemplate {

  public static final IIdentificate RENEGADE_SUBTYPE = new Identificate("RenegadeAbyssal"); //$NON-NLS-1$
  public static final ITemplateType TEMPLATE_TYPE = new TemplateType(CharacterType.ABYSSAL, RENEGADE_SUBTYPE);
  private final ICreationPoints creationPoints = new RenegadeAbyssalCreationPoints();
  private final AbyssalPresentationProperties abyssalPresentationProperties;

  public RenegadeAbyssalTemplate(ICharmCache charmProvider, IAdditionalRules additionalRules) {
    super(charmProvider, additionalRules);
    abyssalPresentationProperties = new AbyssalPresentationProperties(
        "CharacterGenerator.NewCharacter.Abyssal.Renegade.Name"); //$NON-NLS-1$
  }

  public ITemplateType getTemplateType() {
    return TEMPLATE_TYPE;
  }

  public final IPresentationProperties getPresentationProperties() {
    return abyssalPresentationProperties;
  }

  public ICreationPoints getCreationPoints() {
    return creationPoints;
  }
}