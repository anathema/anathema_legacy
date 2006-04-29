package net.sf.anathema.character.generic.impl.magic.persistence.test;

import net.sf.anathema.character.generic.impl.magic.Charm;
import net.sf.anathema.character.generic.impl.magic.CostList;
import net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants;
import net.sf.anathema.character.generic.impl.magic.MagicSource;
import net.sf.anathema.character.generic.impl.magic.PermanentCostList;
import net.sf.anathema.character.generic.impl.magic.charm.type.CharmTypeModel;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.HeaderStringBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.IHeaderStringBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.prerequisite.CharmPrerequisiteList;
import net.sf.anathema.character.generic.impl.magic.persistence.prerequisite.SelectiveCharmGroupTemplate;
import net.sf.anathema.character.generic.magic.charms.ComboRestrictions;
import net.sf.anathema.character.generic.magic.charms.Duration;
import net.sf.anathema.character.generic.magic.charms.ICharmAttributeRequirement;
import net.sf.anathema.character.generic.magic.charms.type.CharmType;
import net.sf.anathema.character.generic.magic.general.IMagicSource;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.traits.types.ValuedTraitType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.lib.exception.PersistenceException;

import org.dom4j.Element;

public class GenericCharmBuilder {

  private final IHeaderStringBuilder idBuilder = new HeaderStringBuilder(ICharmXMLConstants.ATTRIB_ID);

  public Charm buildCharm(Element charmElement, AbilityType type) throws PersistenceException {
    String id = idBuilder.build(charmElement) + type.getId();
    String groupdId = type.getId();
    return getCharm(id, groupdId, type, CharmType.ExtraAction, null);
  }

  private Charm getCharm(String id, String groupdId, AbilityType traitType, CharmType type, String parentId) {
    final String[] parentArray = parentId == null ? new String[0] : new String[] { parentId };
    CharmPrerequisiteList list = new CharmPrerequisiteList(
        new IGenericTrait[] { new ValuedTraitType(traitType, 1) },
        new ValuedTraitType(OtherTraitType.Essence, 1),
        parentArray,
        new SelectiveCharmGroupTemplate[0],
        new ICharmAttributeRequirement[0]);
    CharmTypeModel model = new CharmTypeModel();
    model.setCharmType(type);
    final Charm charm = new Charm(
        CharacterType.SOLAR,
        id,
        groupdId,
        list,
        new CostList(null, null, null),
        new PermanentCostList(null, null, null, null),
        new ComboRestrictions(),
        Duration.INSTANT_DURATION,
        model,
        new IMagicSource[] { MagicSource.CUSTOM_SOURCE });
    return charm;
  }
}