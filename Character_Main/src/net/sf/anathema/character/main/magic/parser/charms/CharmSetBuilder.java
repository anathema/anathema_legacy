package net.sf.anathema.character.main.magic.parser.charms;

import net.sf.anathema.character.main.magic.model.charm.CharmImpl;
import net.sf.anathema.character.main.magic.parser.combos.ComboRulesBuilder;
import net.sf.anathema.character.main.magic.parser.charms.prerequisite.AttributeRequirementBuilder;
import net.sf.anathema.character.main.magic.parser.charms.prerequisite.CharmPrerequisiteBuilder;
import net.sf.anathema.character.main.magic.parser.charms.prerequisite.TraitPrerequisitesBuilder;
import net.sf.anathema.character.main.magic.parser.charms.special.SpecialCharmBuilder;
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
  protected void buildCharms(Collection<CharmImpl> allCharms, List<ISpecialCharm> specialCharms, Element charmListElement) throws PersistenceException {
    for (Element charmElementObject : ElementUtilities.elements(charmListElement, TAG_CHARM)) {
      createCharm(allCharms, specialCharms, builder, charmElementObject);
    }
  }
}