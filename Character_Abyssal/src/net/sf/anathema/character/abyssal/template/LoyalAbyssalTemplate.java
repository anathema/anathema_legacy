package net.sf.anathema.character.abyssal.template;

import net.sf.anathema.character.abyssal.template.creation.LoyalAbyssalCreationPoints;
import net.sf.anathema.character.generic.additionalrules.IAdditionalRules;
import net.sf.anathema.character.generic.impl.magic.persistence.ICharmCache;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.generic.template.creation.ICreationPoints;
import net.sf.anathema.character.generic.template.presentation.IPresentationProperties;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.lib.exception.PersistenceException;

public class LoyalAbyssalTemplate extends AbstractAbyssalTemplate {

  public static final ITemplateType TEMPLATE_TYPE = new TemplateType(CharacterType.ABYSSAL);
  private final ICreationPoints creationPoints = new LoyalAbyssalCreationPoints();
  private final AbyssalPresentationProperties presentationProperties;

  public LoyalAbyssalTemplate(ICharmCache charmProvider, IAdditionalRules additionalRules)
      throws PersistenceException {
    super(charmProvider, additionalRules);
    presentationProperties = new AbyssalPresentationProperties("CharacterGenerator.NewCharacter.Abyssal.Loyal.Name"); //$NON-NLS-1$;
  }

  public ITemplateType getTemplateType() {
    return TEMPLATE_TYPE;
  }

  public final IPresentationProperties getPresentationProperties() {
    return presentationProperties;
  }

  public ICreationPoints getCreationPoints() {
    return creationPoints;
  }
}