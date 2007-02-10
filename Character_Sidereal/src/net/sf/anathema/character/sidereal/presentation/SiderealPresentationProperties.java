package net.sf.anathema.character.sidereal.presentation;

import java.awt.Color;

import net.sf.anathema.character.generic.impl.template.presentation.AbstractPresentationProperties;
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.platform.svgtree.document.ITreePresentationProperties;

public class SiderealPresentationProperties extends AbstractPresentationProperties {

  private final ITreePresentationProperties charmPresentationProperties = new SiderealCharmPresentationProperties();

  public SiderealPresentationProperties() {
    super(new TemplateType(CharacterType.SIDEREAL));
  }

  public Color getColor() {
    return new Color(147, 112, 219);
  }

  public ITreePresentationProperties getCharmPresentationProperties() {
    return charmPresentationProperties;
  }
}