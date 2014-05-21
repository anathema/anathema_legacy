package net.sf.anathema.character.magic.parser.charms;

import net.sf.anathema.character.magic.charm.CharmImpl;
import net.sf.anathema.character.magic.parser.charms.prerequisite.AttributePrerequisiteBuilder;
import net.sf.anathema.character.magic.parser.charms.prerequisite.CharmPrerequisiteBuilder;
import net.sf.anathema.character.magic.parser.charms.prerequisite.TraitPrerequisitesBuilder;
import net.sf.anathema.character.magic.parser.charms.special.ReflectionSpecialCharmParser;
import net.sf.anathema.character.magic.parser.combos.ComboRulesBuilder;
import net.sf.anathema.character.magic.parser.dto.special.SpecialCharmDto;
import net.sf.anathema.character.framework.type.CharacterTypes;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;
import org.dom4j.Element;

import java.util.Collection;
import java.util.List;

import static net.sf.anathema.character.magic.charm.ICharmXMLConstants.TAG_CHARM;

public class CharmSetBuilder extends AbstractCharmSetBuilder {

  private final ICharmBuilder builder;

  public CharmSetBuilder(CharacterTypes characterTypes, ReflectionSpecialCharmParser specialCharmParser) {
    this.builder =
            new CharmBuilder(new IdStringBuilder(), new TraitPrerequisitesBuilder(), new AttributePrerequisiteBuilder(), new ComboRulesBuilder(),
                    new CharmPrerequisiteBuilder(), characterTypes, specialCharmParser);
  }

  @Override
  protected void buildCharms(Collection<CharmImpl> allCharms, List<SpecialCharmDto> specialCharms, Element charmListElement) throws
          PersistenceException {
    for (Element charmElementObject : ElementUtilities.elements(charmListElement, TAG_CHARM)) {
      createCharm(allCharms, specialCharms, builder, charmElementObject);
    }
  }
}