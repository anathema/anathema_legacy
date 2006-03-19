package net.sf.anathema.development.reporting.encoder.voidstate.subreports.ability;

import java.awt.Point;

import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.development.reporting.encoder.AbstractCharacterSheetPageEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.traits.VoidStateAbilityEncoder;

import org.dom4j.Element;

public class VoidstateAbilityPageEncoder extends AbstractCharacterSheetPageEncoder {

  private final CharacterType characterType;
  private final VoidStateAbilityEncoder encoder;

  public VoidstateAbilityPageEncoder(CharacterType characterType, VoidStateAbilityEncoder encoder) {
    this.characterType = characterType;
    this.encoder = encoder;
  }

  public int encodeBand(Element bandElement) {
    ICharacterTemplate template = getDefaultTemplate(characterType);
    return encoder.encodeAbilites(bandElement, template, new Point(0, 0));
  }

  public String getGroupName() {
    return "VoidStateAbilities";
  }
}