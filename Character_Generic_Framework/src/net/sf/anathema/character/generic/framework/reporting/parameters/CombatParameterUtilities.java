package net.sf.anathema.character.generic.framework.reporting.parameters;

import java.util.Map;

import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.framework.reporting.ICharacterReportConstants;
import net.sf.anathema.character.generic.impl.CombatStatisticsConfiguration;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;

public class CombatParameterUtilities {

  public final static String[] combatStatsParameterNames = new String[] {
      ICharacterReportConstants.INTITIATIVE,
      ICharacterReportConstants.DODGE,
      ICharacterReportConstants.KNOCKDOWN,
      ICharacterReportConstants.STUN_THRESHOLD,
      ICharacterReportConstants.STUN_DURATION,
      ICharacterReportConstants.MINIMUM_DAMAGE };

  public static void addCombatStatsParameterClasses(Map<String, String> parameterClassesByName) {
    for (String name : combatStatsParameterNames) {
      parameterClassesByName.put(name, Integer.class.getName());
    }
  }

  public static void fillInCombatStats(
      final IGenericTraitCollection collection,
      final IExaltedRuleSet rules,
      boolean isExalted,
      Map<Object, Object> parameters) {
    CombatStatisticsConfiguration configuration = new CombatStatisticsConfiguration(collection, rules, isExalted);
    parameters.put(ICharacterReportConstants.INTITIATIVE, configuration.getBaseInitiative());
    parameters.put(ICharacterReportConstants.DODGE, configuration.getDodgePool());
    parameters.put(ICharacterReportConstants.KNOCKDOWN, configuration.getKnockdownResistance());
    parameters.put(ICharacterReportConstants.STUN_THRESHOLD, configuration.getStunThreshold());
    parameters.put(ICharacterReportConstants.STUN_DURATION, configuration.getStunDuration());
    parameters.put(ICharacterReportConstants.MINIMUM_DAMAGE, configuration.getMinimumDamage());
  }
}