package net.sf.anathema.character.abyssal.template;

import java.awt.Color;

import net.sf.anathema.character.generic.impl.template.presentation.AbstractPresentationProperties;
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.platform.svgtree.document.visualizer.ITreePresentationProperties;

public class Abyssal2ndPresentationProperties extends AbstractPresentationProperties {

  private final String newActionResource;
  private final Abyssal2ndCharmPresentationProperties abyssal2ndCharmPresentationProperties = new Abyssal2ndCharmPresentationProperties();

  public Abyssal2ndPresentationProperties(String newActionResource) {
    super(new TemplateType(CharacterType.ABYSSAL));
    this.newActionResource = newActionResource;
  }

  @Override
  public String getNewActionResource() {
    return newActionResource;
  }

  public Color getColor() {
    return new Color(169, 169, 169);
  }

  public ITreePresentationProperties getCharmPresentationProperties() {
    return abyssal2ndCharmPresentationProperties;
  }
}