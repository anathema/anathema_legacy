package net.sf.anathema.character.infernal.traits;

import com.google.common.base.Functions;
import net.sf.anathema.character.generic.caste.ICasteCollection;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.abilities.GroupedTraitType;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedCasteTraitTypeGroup;
import net.sf.anathema.character.generic.traits.types.YoziType;
import net.sf.anathema.character.impl.model.traits.RegisteredTrait;
import net.sf.anathema.character.impl.model.traits.TraitRegistrar;
import net.sf.anathema.character.infernal.caste.InfernalCaste;
import net.sf.anathema.character.library.trait.favorable.IIncrementChecker;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.sf.anathema.character.generic.caste.ICasteType.NULL_CASTE_TYPE;

@RegisteredTrait
public class YoziRegistrar implements TraitRegistrar {

  private static final Map<YoziType, ICasteType> castesForYozi = new HashMap<YoziType, ICasteType>() {{
    put(YoziType.Malfeas, InfernalCaste.Slayer);
    put(YoziType.Cecelyne, InfernalCaste.Malefactor);
    put(YoziType.SheWhoLivesInHerName, InfernalCaste.Defiler);
    put(YoziType.Adorjan, InfernalCaste.Scourge);
    put(YoziType.EbonDragon, InfernalCaste.Fiend);
  }};

  @Override
  public void addTraits(ICharacterTemplate template, ICoreTraitConfiguration configuration) {
    ICasteCollection casteCollection = template.getCasteCollection();
    GroupedTraitType[] yoziGroups = groupYozi(casteCollection);
    IIdentifiedCasteTraitTypeGroup[] yoziTraitGroups = new YoziTypeGroupFactory().createTraitGroups(casteCollection, yoziGroups);
    IIncrementChecker incrementChecker = YoziFavoredIncrementChecker.create(configuration);
    configuration.addFavorableTraits(yoziTraitGroups, incrementChecker);
  }

  private GroupedTraitType[] groupYozi(ICasteCollection casteCollection) {
    List<GroupedTraitType> groupedTraitTypes = new ArrayList<>();
    for (YoziType yoziType : YoziType.values()) {
      GroupedTraitType traitType = createTraitType(yoziType, casteCollection);
      groupedTraitTypes.add(traitType);
    }
    return groupedTraitTypes.toArray(new GroupedTraitType[groupedTraitTypes.size()]);
  }

  private GroupedTraitType createTraitType(YoziType yoziType, ICasteCollection casteCollection) {
    ICasteType caste = Functions.forMap(castesForYozi, NULL_CASTE_TYPE).apply(yoziType);
    boolean knowsYoziCaste = casteCollection.containsCasteType(caste.getId());
    if (caste == NULL_CASTE_TYPE || !knowsYoziCaste) {
      return new GroupedTraitType(yoziType, yoziType.getId(), Collections.<String>emptyList());
    }
    return new GroupedTraitType(yoziType, yoziType.getId(), Collections.singletonList(caste.getId()));
  }
}