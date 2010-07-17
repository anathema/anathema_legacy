package net.sf.anathema.character.generic.impl.magic.persistence;

import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_CHARM;

import java.util.Collection;

import net.sf.anathema.character.generic.impl.magic.Charm;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.ComboRulesBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.IdStringBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.prerequisite.AttributeRequirementBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.prerequisite.CharmPrerequisiteBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.prerequisite.TraitPrerequisitesBuilder;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class CharmSetBuilder extends AbstractCharmSetBuilder {

  private final ICharmBuilder builder = new CharmBuilder(
      new IdStringBuilder(),
      new TraitPrerequisitesBuilder(),
      new AttributeRequirementBuilder(),
      new ComboRulesBuilder(),
      new CharmPrerequisiteBuilder());

  @Override
  protected void buildCharms(Collection<Charm> allCharms, Element charmListElement) throws PersistenceException {
    for (Element charmElementObject : ElementUtilities.elements(charmListElement, TAG_CHARM)) {
      createCharm(allCharms, builder, charmElementObject);
    }
  }
}