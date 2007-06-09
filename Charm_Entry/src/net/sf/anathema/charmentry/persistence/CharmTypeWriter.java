package net.sf.anathema.charmentry.persistence;

import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_DEFENSE;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_PRIMARY_STEP;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_SECONDARY_STEP;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_SPEED;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_TURN_TYPE;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_TYPE;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_CHARMTYPE;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_SPECIAL;

import net.sf.anathema.character.generic.magic.ICharmData;
import net.sf.anathema.character.generic.magic.charms.ICharmTypeVisitor;
import net.sf.anathema.character.generic.magic.charms.type.CharmType;
import net.sf.anathema.character.generic.magic.charms.type.ICharmTypeModel;
import net.sf.anathema.character.generic.magic.charms.type.IReflexiveSpecialsModel;
import net.sf.anathema.character.generic.magic.charms.type.ISimpleSpecialsModel;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class CharmTypeWriter {

  public void write(ICharmData charm, Element charmElement) {
    final Element typeElement = charmElement.addElement(TAG_CHARMTYPE);
    final ICharmTypeModel charmTypeModel = charm.getCharmTypeModel();
    typeElement.addAttribute(ATTRIB_TYPE, charmTypeModel.getCharmType().getId());
    if (!charmTypeModel.hasSpecialsModel()) {
      return;
    }
    charmTypeModel.getCharmType().accept(new ICharmTypeVisitor() {
      public void visitExtraAction(CharmType visitedType) {
        // Nothing to do
      }

      public void visitReflexive(CharmType visitedType) {
        IReflexiveSpecialsModel model = (IReflexiveSpecialsModel) charmTypeModel.getSpecialsModel();
        if (model == null) {
          return;
        }
        Element specialElement = typeElement.addElement(TAG_SPECIAL);
        ElementUtilities.addAttribute(specialElement, ATTRIB_PRIMARY_STEP, model.getPrimaryStep());
        if (model.isSplitEnabled()) {
          ElementUtilities.addAttribute(specialElement, ATTRIB_SECONDARY_STEP, model.getSecondaryStep());
        }
      }

      public void visitSimple(CharmType visitedType) {
        ISimpleSpecialsModel model = (ISimpleSpecialsModel) charmTypeModel.getSpecialsModel();
        if (model == null) {
          return;
        }
        Element specialElement = typeElement.addElement(TAG_SPECIAL);
        ElementUtilities.addAttribute(specialElement, ATTRIB_SPEED, model.getSpeed());
        specialElement.addAttribute(ATTRIB_TURN_TYPE, model.getTurnType().name());
        ElementUtilities.addAttribute(specialElement, ATTRIB_DEFENSE, model.getDefenseModifier());
      }

      public void visitSpecial(CharmType visitedType) {
        // Nothing to do
      }

      public void visitPermanent(CharmType visitedType) {
        // Nothing to do
      }

      public void visitSupplemental(CharmType visitedType) {
        // Nothing to do
      }
    });
  }
}