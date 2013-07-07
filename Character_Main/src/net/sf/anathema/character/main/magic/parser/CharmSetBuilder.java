package net.sf.anathema.character.main.magic.parser;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.parser.prerequisite.AttributeRequirementBuilder;
import net.sf.anathema.character.main.magic.parser.prerequisite.CharmPrerequisiteBuilder;
import net.sf.anathema.character.main.magic.parser.prerequisite.TraitPrerequisitesBuilder;
import net.sf.anathema.character.main.magic.parser.special.SpecialCharmBuilder;
import net.sf.anathema.character.main.magic.model.charm.special.ISpecialCharm;
import net.sf.anathema.character.main.type.CharacterTypes;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;
import org.dom4j.Element;

import java.util.Collection;
import java.util.List;

import static net.sf.anathema.character.main.magic.model.charm.ICharmXMLConstants.TAG_CHARM;

public class CharmSetBuilder extends AbstractCharmSetBuilder {

  private final ICharmBuilder builder;

  public CharmSetBuilder(CharacterTypes characterTypes, SpecialCharmBuilder specialCharmBuilder) {
    this.builder =
            new CharmBuilder(new IdStringBuilder(), new TraitPrerequisitesBuilder(), new AttributeRequirementBuilder(), new ComboRulesBuilder(),
                    new CharmPrerequisiteBuilder(), characterTypes, specialCharmBuilder);
  }

  @Override
  protected void buildCharms(Collection<Charm> allCharms, List<ISpecialCharm> specialCharms, Element charmListElement) throws PersistenceException {
    for (Element charmElementObject : ElementUtilities.elements(charmListElement, TAG_CHARM)) {
      createCharm(allCharms, specialCharms, builder, charmElementObject);
    }
  }
}