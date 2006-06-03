package net.sf.anathema.character.generic.impl.magic.persistence.writer;

import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_AMOUNT;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_DURATION;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_UNIT;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_DURATION;

import net.sf.anathema.character.generic.magic.ICharmData;
import net.sf.anathema.character.generic.magic.charms.duration.IDurationVisitor;
import net.sf.anathema.character.generic.magic.charms.duration.QualifiedAmountDuration;
import net.sf.anathema.character.generic.magic.charms.duration.SimpleDuration;

import org.dom4j.Element;

public class DurationWriter {

  public void write(ICharmData charm, Element charmElement) {
    final Element durationElement = charmElement.addElement(TAG_DURATION);
    charm.getDuration().accept(new IDurationVisitor() {
      public void visitSimpleDuration(SimpleDuration duration) {
        durationElement.addAttribute(ATTRIB_DURATION, duration.getText());
      }

      public void visitQualifiedAmountDuration(QualifiedAmountDuration visitedDuration) {
        durationElement.addAttribute(ATTRIB_AMOUNT, visitedDuration.getAmount());
        durationElement.addAttribute(ATTRIB_UNIT, visitedDuration.getUnit());
      }
    });
  }
}