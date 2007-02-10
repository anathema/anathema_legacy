package net.sf.anathema.character.abyssal.template;

import java.awt.Color;

import net.sf.anathema.character.generic.impl.template.presentation.AbstractPresentationProperties;
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.generic.template.presentation.ITreePresentationProperties;
import net.sf.anathema.character.generic.type.CharacterType;

public class AbyssalPresentationProperties extends AbstractPresentationProperties {

  private final String newActionResoure;
  private final AbyssalCharmPresentationProperties abyssalCharmPresentationProperties = new AbyssalCharmPresentationProperties();

  public AbyssalPresentationProperties(String newActionResoure) {
    super(new TemplateType(CharacterType.ABYSSAL));
    this.newActionResoure = newActionResoure;
  }

  @Override
  public String getNewActionResource() {
    return newActionResoure;
  }

  public Color getColor() {
    return new Color(169, 169, 169);
  }

  public ITreePresentationProperties getCharmPresentationProperties() {
    return abyssalCharmPresentationProperties;
  }
}