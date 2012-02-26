package net.sf.anathema.character.sidereal.presentation;

import net.sf.anathema.character.generic.impl.template.presentation.AbstractPresentationProperties;
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.platform.svgtree.document.visualizer.ITreePresentationProperties;

public class SiderealPresentationProperties extends AbstractPresentationProperties {

  private final SiderealCharmPresentationProperties charmPresentationProperties = new SiderealCharmPresentationProperties();

  public SiderealPresentationProperties() {
    super(new TemplateType(CharacterType.SIDEREAL));
  }

  @Override
  public ITreePresentationProperties getCharmPresentationProperties() {
    return charmPresentationProperties;
  }
}