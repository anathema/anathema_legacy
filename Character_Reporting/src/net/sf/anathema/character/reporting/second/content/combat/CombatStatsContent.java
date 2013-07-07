package net.sf.anathema.character.reporting.second.content.combat;

import net.sf.anathema.character.main.equipment.ICharacterStatsModifiers;
import net.sf.anathema.character.main.util.CharacterUtilities;
import net.sf.anathema.character.main.traits.types.AbilityType;
import net.sf.anathema.character.main.library.trait.specialties.HighestSpecialty;
import net.sf.anathema.character.reporting.pdf.content.combat.AbstractCombatStatsContent;
import net.sf.anathema.character.reporting.pdf.content.combat.CombatAction;
import net.sf.anathema.character.reporting.pdf.content.general.QualifiedText;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.resources.Resources;

import static net.sf.anathema.character.reporting.pdf.content.general.TextType.Comment;
import static net.sf.anathema.character.reporting.pdf.content.general.TextType.Normal;

public class CombatStatsContent extends AbstractCombatStatsContent {

  private final HighestSpecialty dodgeSpecialty;
  private final HighestSpecialty awarenessSpecialty;
  private final ICharacterStatsModifiers modifiers;

  protected CombatStatsContent(Hero hero, Resources resources) {
    super(resources, hero);
    dodgeSpecialty = new HighestSpecialty(hero, AbilityType.Dodge);
    awarenessSpecialty = new HighestSpecialty(hero, AbilityType.Awareness);
    modifiers = StatsModifiers.allStatsModifiers(hero);
  }

  public String getJoinLabel() {
    return getString("Sheet.Combat.JoinBattle");
  }

  public String getDodgeLabel() {
    return getString("Sheet.Combat.DodgeDV");
  }

  public String getJoinBattleSpecialtyLabel() {
    return getString("Sheet.Combat.NormalSpecialty") + awarenessSpecialty;
  }

  public String getDodgeSpecialtyLabel() {
    return getString("Sheet.Combat.NormalSpecialty") + dodgeSpecialty;
  }

  public int getJoinBattle() {
    return CharacterUtilities.getJoinBattle(getTraitCollection(), modifiers);
  }

  public int getJoinBattleWithSpecialty() {
    return CharacterUtilities.getJoinBattleWithSpecialty(getTraitCollection(), modifiers, awarenessSpecialty.getValue());
  }

  public int getDodgeDv() {
    return CharacterUtilities.getDodgeDv(getCharacterType(), getTraitCollection(), modifiers);
  }

  public int getDodgeDvWithSpecialty() {
    return CharacterUtilities.getDodgeDvWithSpecialty(getCharacterType(), getTraitCollection(), modifiers, dodgeSpecialty.getValue());
  }

  public String[] getAttacks() {
    return new String[]{getString("Sheet.Combat.AttackList.DeclareAttack"), getString("Sheet.Combat.AttackList.DeclareDefence"),
            getString("Sheet.Combat.AttackList.AttackRoll"), getString("Sheet.Combat.AttackList.AttackReroll"),
            getString("Sheet.Combat.AttackList.SubstractPenalties"), getString("Sheet.Combat.AttackList.DefenseReroll"),
            getString("Sheet.Combat.AttackList.CalculateRawDamage"), getString("Sheet.Combat.AttackList.RollDamage"),
            getString("Sheet.Combat.AttackList.Counterattack"), getString("Sheet.Combat.AttackList.ApplyDamage")

    };
  }

  public QualifiedText[] getKnockdownAndStunningTexts() {
    return new QualifiedText[]{new QualifiedText(getString("Sheet.Combat.Knockdown.Header") + "\n", Normal),
            new QualifiedText(getString("Sheet.Combat.Knockdown.Second.Comment") + "\n\n", Comment),
            new QualifiedText(getString("Sheet.Combat.Stunning.Header") + "\n", Normal),
            new QualifiedText(getString("Sheet.Combat.Stunning.Second.Comment"), Comment)

    };
  }

  public String getAttackHeader() {
    return getString("Sheet.Combat.OrderAttackEvents");
  }

  public String getAttackComment() {
    return getString("Sheet.Combat.Comment.Rules");
  }

  public CombatAction[] getCombatActions() {
    String nameHeader = getResources().getString("Sheet.Combat.CommonActions.Action");
    String speedHeader = getResources().getString("Sheet.Combat.CommonActions.Speed");
    String dvHeader = getResources().getString("Sheet.Combat.CommonActions.DV");
    CombatAction headerData = new CombatAction(nameHeader, speedHeader, dvHeader);
    CombatAction emptyData = new CombatAction(" ", " ", " ");
    return new CombatAction[]{headerData, emptyData, getCombatAction("JoinBattle"), getCombatAction("ReadyWeapon"), getCombatAction("PhysicalAttack"),
            getCombatAction("CoordinateAttack"), getCombatAction("Aim"), getCombatAction("Guard"), getCombatAction("Move"), getCombatAction("Dash"),
            getCombatAction("Misc"), getCombatAction("Jump"), getCombatAction("Rise"), getCombatAction("Inactive")

    };
  }

  private CombatAction getCombatAction(String actionId) {
    String name = getString("Sheet.Combat.CommonActions." + actionId + ".Name");
    String speed = getString("Sheet.Combat.CommonActions." + actionId + ".Speed");
    String dv = getString("Sheet.Combat.CommonActions." + actionId + ".DV");
    return new CombatAction(name, speed, dv);
  }

  public String getActionHeader() {
    return getResources().getString("Sheet.Combat.CommonActions.Header");
  }
}
