package net.sf.anathema.character.sidereal.presentation;

import net.sf.anathema.character.generic.framework.xml.presentation.GenericPresentationTemplate;
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.impl.persistence.SecondEdition;
import net.sf.anathema.character.sidereal.caste.SiderealCaste;
import net.sf.anathema.character.sidereal.colleges.presenter.IAstrologicalHouse;
import net.sf.anathema.lib.resources.AbstractUI;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.*;

public class SiderealCasteUI extends AbstractUI {

  public SiderealCasteUI(IResources resources) {
    super(resources);
  }

  public Icon getCasteIcon(IAstrologicalHouse house) {
    SiderealCaste caste = SiderealCaste.valueOf(house.getId());
    GenericPresentationTemplate template = new GenericPresentationTemplate();
    template.setParentTemplate(new TemplateType(CharacterType.SIDEREAL));
    return getIcon(template.getSmallCasteIconResource(caste.getId(), SecondEdition.SECOND_EDITION));
  }
}