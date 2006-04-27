package net.sf.anathema.character.generic.impl.magic.persistence.builder;

import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_PRIMARY_STEP;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_SECONDARY_STEP;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_TYPE;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_CHARMTYPE;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_SPECIAL;

import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.generic.impl.magic.charm.type.CharmTypeModel;
import net.sf.anathema.character.generic.impl.magic.charm.type.ReflexiveSpecialsModel;
import net.sf.anathema.character.generic.magic.charms.CharmException;
import net.sf.anathema.character.generic.magic.charms.ICharmTypeVisitor;
import net.sf.anathema.character.generic.magic.charms.type.CharmType;
import net.sf.anathema.character.generic.magic.charms.type.ICharmTypeModel;
import net.sf.anathema.character.generic.magic.charms.type.ITypeSpecialsModel;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class CharmTypeBuilder {

  public ICharmTypeModel build(Element rulesElement) throws CharmException {
    CharmType charmType;
    final Element typeElement = rulesElement.element(TAG_CHARMTYPE);
    try {
      charmType = CharmType.valueOf(typeElement.attributeValue(ATTRIB_TYPE));
    }
    catch (IllegalArgumentException e) {
      throw new CharmException("Bad type in charm. (Type unreadable)"); //$NON-NLS-1$
    }
    catch (NullPointerException e) {
      throw new CharmException("Bad type in charm. (Element required)"); //$NON-NLS-1$
    }

    final CharmTypeModel charmTypeModel = new CharmTypeModel();
    charmTypeModel.setCharmType(charmType);
    charmType.accept(new ICharmTypeVisitor() {
      public void visitExtraAction(CharmType visitedType) {
        // Nothing to do
      }

      public void visitReflexive(CharmType visitedType) {
        charmTypeModel.setSpecialModel(buildReflexiveModel(typeElement));
      }

      public void visitSimple(CharmType visitedType) {
        // TODO Auto-generated method stub
      }

      public void visitSpecial(CharmType visitedType) {
        // Nothing to do
      }

      public void visitSupplemental(CharmType visitedType) {
        // Nothing to do
      }
    });
    return charmTypeModel;
  }

  private ITypeSpecialsModel buildReflexiveModel(Element typeElement) {
    Element element = typeElement.element(TAG_SPECIAL);
    try {
      int primaryStep = ElementUtilities.getRequiredIntAttrib(element, ATTRIB_PRIMARY_STEP);
      String secondStepString = element.attributeValue(ATTRIB_SECONDARY_STEP);
      if (StringUtilities.isNullOrEmpty(secondStepString)) {
        return new ReflexiveSpecialsModel(primaryStep, null);
      }
      try {
        Integer secondStep = Integer.valueOf(secondStepString);
        return new ReflexiveSpecialsModel(primaryStep, secondStep);
      }
      catch (NumberFormatException e) {
        return null;
      }
    }
    catch (PersistenceException e) {
      return null;
    }
  }
}