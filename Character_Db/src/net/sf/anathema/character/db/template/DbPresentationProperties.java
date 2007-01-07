package net.sf.anathema.character.db.template;

import java.awt.Color;

import net.sf.anathema.character.generic.impl.template.presentation.AbstractPresentationProperties;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.template.presentation.ICharmPresentationProperties;

public final class DbPresentationProperties extends AbstractPresentationProperties {

  private final ICharmPresentationProperties charmPresentationProperties = new DbCharmPresentationProperties();

  public DbPresentationProperties(ITemplateType templateType) {
    super(templateType);
  }

  public Color getColor() {
    return new Color(139, 0, 0);
  }

  @Override
  public String getSmallCasteIconResource(String casteId, String editionId) {
    return getCharacterTypeId() + "Button" + casteId + editionId + "16.png"; //$NON-NLS-1$//$NON-NLS-2$
  }

  @Override
  public String getCasteLabelResource() {
    return getCharacterTypeId() + ".Caste.Label"; //$NON-NLS-1$;
  }

  @Override
  public String getNewActionResource() {
    return "CharacterGenerator.Templates.Dragon-Blooded." + getTemplateType().getSubType().getId(); //$NON-NLS-1$
  }

  public ICharmPresentationProperties getCharmPresentationProperties() {
    return charmPresentationProperties;
  }
}