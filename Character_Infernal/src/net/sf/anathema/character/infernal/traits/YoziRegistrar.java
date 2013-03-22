package net.sf.anathema.character.infernal.traits;

import com.google.common.base.Functions;
import net.sf.anathema.character.generic.caste.ICasteCollection;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.ITraitTemplateFactory;
import net.sf.anathema.character.generic.template.abilities.GroupedTraitType;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedCasteTraitTypeGroup;
import net.sf.anathema.character.generic.traits.types.YoziType;
import net.sf.anathema.character.impl.model.context.CharacterListening;
import net.sf.anathema.character.impl.model.traits.RegisteredTrait;
import net.sf.anathema.character.impl.model.traits.TraitRegistrar;
import net.sf.anathema.character.infernal.caste.InfernalCaste;
import net.sf.anathema.character.library.trait.favorable.FavorableState;
import net.sf.anathema.character.library.trait.favorable.IFavorableStateChangedListener;
import net.sf.anathema.character.library.trait.favorable.IFavorableTrait;
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
  public void addTraits(ICoreTraitConfiguration configuration, ICharacterTemplate template) {
    ICasteCollection casteCollection = template.getCasteCollection();
    GroupedTraitType[] yoziGroups = groupYozi(casteCollection);
    IIdentifiedCasteTraitTypeGroup[] yoziTraitGroups = new YoziTypeGroupFactory().createTraitGroups(casteCollection,
            yoziGroups);
    IIncrementChecker incrementChecker = YoziFavoredIncrementChecker.create(configuration);
    ITraitTemplateFactory traitTemplateFactory = template.getTraitTemplateCollection().getTraitTemplateFactory();
    YoziTemplateFactory factory = new YoziTemplateFactory(traitTemplateFactory);
    configuration.addFavorableTraits(yoziTraitGroups, incrementChecker, factory);
  }

  @Override
  public void initListening(ICoreTraitConfiguration configuration, final CharacterListening listening) {
    for (YoziType yoziType : YoziType.values()) {
      try {
        IFavorableTrait yozi = configuration.getFavorableTrait(yoziType);
        listening.addTraitListening(yozi);
        yozi.getFavorization().addFavorableStateChangedListener(new IFavorableStateChangedListener() {
          @Override
          public void favorableStateChanged(FavorableState state) {
            listening.fireCharacterChanged();
          }
        });
      } catch (UnsupportedOperationException e) {
        break; //template does not support yozis
      }
    }
  }

  private GroupedTraitType[] groupYozi(ICasteCollection casteCollection) {
    List<GroupedTraitType> groupedTraitTypes = new ArrayList<>();
    for (YoziType yoziType : YoziType.values()) {
      GroupedTraitType traitType = createTraitType(yoziType, casteCollection);
      groupedTraitTypes.add(traitType);
    }
    return groupedTraitTypes.toArray(new GroupedTraitType[groupedTraitTypes.size()]);
  }

  @SuppressWarnings("ConstantConditions")
  private GroupedTraitType createTraitType(YoziType yoziType, ICasteCollection casteCollection) {
    ICasteType caste = Functions.forMap(castesForYozi, NULL_CASTE_TYPE).apply(yoziType);
    boolean knowsYoziCaste = casteCollection.containsCasteType(caste.getId());
    if (caste == NULL_CASTE_TYPE || !knowsYoziCaste) {
      return new GroupedTraitType(yoziType, yoziType.getId(), Collections.<String>emptyList());
    }
    return new GroupedTraitType(yoziType, yoziType.getId(), Collections.singletonList(caste.getId()));
  }
}