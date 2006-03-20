package net.sf.anathema.character.generic.framework.reporting.parameters;

import java.util.Map;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.framework.reporting.ICharacterReportConstants;
import net.sf.anathema.character.generic.framework.reporting.datasource.BackgroundsDataSource;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.framework.reporting.IReportDataSource;
import net.sf.anathema.lib.resources.IResources;

public class AdvantageParameterUtilities {

  public final static void addVirtueParameterClasses(Map<String, String> parameterClassesByName) {
    for (VirtueType virtueType : VirtueType.values()) {
      addTraitValueParameterClass(parameterClassesByName, virtueType);
    }
  }

  public static void addEssenceParameterClass(Map<String, String> parameterClassesByName) {
    addTraitValueParameterClass(parameterClassesByName, OtherTraitType.Essence);
  }

  public static void addEssencePoolParameterClasses(Map<String, String> parameterClassesByName) {
    parameterClassesByName.put(ICharacterReportConstants.ESSENCE_PERSONAL_POOL, String.class.getName());
    parameterClassesByName.put(ICharacterReportConstants.ESSENCE_PERIPHERAL_POOL, String.class.getName());
  }

  public static void addWillpowerParameterClasses(Map<String, String> parameterClassesByName) {
    addTraitValueParameterClass(parameterClassesByName, OtherTraitType.Willpower);
  }

  private static void addTraitValueParameterClass(Map<String, String> parameterClassesByName, ITraitType trait) {
    parameterClassesByName.put(trait.getId(), Integer.class.getName());
  }

  public static void addBackgroundParameterClasses(Map<String, String> parameterClassesByName) {
    parameterClassesByName.put(ICharacterReportConstants.BACKGROUND_DATA_SOURCE, IReportDataSource.class.getName());
  }

  public static final void fillInVirtues(IGenericCharacter character, Map<Object, Object> parameters) {
    for (VirtueType virtueType : VirtueType.values()) {
      parameters.put(virtueType.getId(), new Integer(character.getTrait(virtueType).getCurrentValue()));
    }
  }

  public static void fillInWillpower(IGenericCharacter abstraction, Map<Object, Object> parameters) {
    ITraitType traitType = OtherTraitType.Willpower;
    parameters.put(traitType.getId(), new Integer(abstraction.getTrait(traitType).getCurrentValue()));
  }

  public static void fillInEssence(IGenericCharacter abstraction, Map<Object, Object> parameters) {
    ITraitType traitType = OtherTraitType.Essence;
    parameters.put(traitType.getId(), new Integer(abstraction.getTrait(traitType).getCurrentValue()));
  }

  public static void fillInEssencePools(IGenericCharacter character, Map<Object, Object> parameters) {
    parameters.put(ICharacterReportConstants.ESSENCE_PERIPHERAL_POOL, character.getPeripheralPool());
    parameters.put(ICharacterReportConstants.ESSENCE_PERSONAL_POOL, character.getPersonalPool());
  }

  public static void fillInBackgrounds(IGenericCharacter character, Map<Object, Object> parameters, IResources resources) {
    parameters.put(ICharacterReportConstants.BACKGROUND_DATA_SOURCE, new BackgroundsDataSource(character, resources));
  }
}