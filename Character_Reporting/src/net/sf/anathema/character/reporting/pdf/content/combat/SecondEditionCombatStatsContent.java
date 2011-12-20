package net.sf.anathema.character.reporting.pdf.content.combat;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.equipment.IEquipmentModifiers;
import net.sf.anathema.character.generic.impl.CharacterUtilties;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.reporting.pdf.content.general.QualifiedText;
import net.sf.anathema.lib.resources.IResources;

import static net.sf.anathema.character.reporting.pdf.content.general.TextType.Comment;
import static net.sf.anathema.character.reporting.pdf.content.general.TextType.Normal;

public class SecondEditionCombatStatsContent extends AbstractCombatStatsContent {

  private IGenericCharacter character;

  protected SecondEditionCombatStatsContent(IGenericCharacter character, IResources resources) {
    super(resources);
    this.character = character;
  }

  public String getJoinLabel() {
    return getString("Sheet.Combat.JoinBattle"); //$NON-NLS-1$
  }

  public String getDodgeLabel() {
    return getString("Sheet.Combat.DodgeDV"); //$NON-NLS-1$
  }

  public String getKnockdownLabel() {
    return getString("Sheet.Combat.Knockdown"); //$NON-NLS-1$
  }

  public String getStunningLabel() {
    return getString("Sheet.Combat.Stunning"); //$NON-NLS-1$
  }

  public String getThresholdPoolLabel() {
    return getString("Sheet.Combat.ThresholdPool"); //$NON-NLS-1$
  }

  public int getJoinBattle() {
    return CharacterUtilties.getJoinBattle(getTraitCollection(), getEquipment());
  }

  public int getDodgeDv() {
    return CharacterUtilties.getDodgeDv(getCharacterType(), getTraitCollection(), getEquipment());
  }

  public int getKnockdownThreshold() {
    return CharacterUtilties.getKnockdownThreshold(getTraitCollection(), getEquipment());
  }

  public int getKnockdownPool() {
    return CharacterUtilties.getKnockdownPool(character, getTraitCollection(), getEquipment());
  }

  public int getStunningThreshold() {
    return CharacterUtilties.getStunningThreshold(getTraitCollection(), getEquipment());
  }

  public int getStunningPool() {
    return CharacterUtilties.getStunningPool(getTraitCollection(), getEquipment());
  }

  private ICharacterType getCharacterType() {
    return character.getTemplate().getTemplateType().getCharacterType();
  }

  private IEquipmentModifiers getEquipment() {
    return character.getEquipmentModifiers();
  }

  private IGenericTraitCollection getTraitCollection() {
    return character.getTraitCollection();
  }

  public String[] getAttacks() {
    return new String[] { getString("Sheet.Combat.AttackList.DeclareAttack"), //$NON-NLS-1$
                          getString("Sheet.Combat.AttackList.DeclareDefence"), //$NON-NLS-1$
                          getString("Sheet.Combat.AttackList.AttackRoll"), //$NON-NLS-1$
                          getString("Sheet.Combat.AttackList.AttackReroll"), //$NON-NLS-1$
                          getString("Sheet.Combat.AttackList.SubstractPenalties"), //$NON-NLS-1$
                          getString("Sheet.Combat.AttackList.DefenseReroll"), //$NON-NLS-1$
                          getString("Sheet.Combat.AttackList.CalculateRawDamage"), //$NON-NLS-1$
                          getString("Sheet.Combat.AttackList.RollDamage"), //$NON-NLS-1$
                          getString("Sheet.Combat.AttackList.Counterattack"), //$NON-NLS-1$
                          getString("Sheet.Combat.AttackList.ApplyDamage") //$NON-NLS-1$

    };
  }

  public QualifiedText[] getKnockdownAndStunningTexts() {
    return new QualifiedText[] { new QualifiedText(getString("Sheet.Combat.Knockdown.Header") + "\n", Normal), //$NON-NLS-1$ //$NON-NLS-2$
                                 new QualifiedText(getString("Sheet.Combat.Knockdown.Second.Comment") + "\n\n", Comment), //$NON-NLS-1$ //$NON-NLS-2$
                                 new QualifiedText(getString("Sheet.Combat.Stunning.Header") + "\n", Normal), //$NON-NLS-1$ //$NON-NLS-2$
                                 new QualifiedText(getString("Sheet.Combat.Stunning.Second.Comment"), Comment) //$NON-NLS-1$

    };
  }

  public String getAttackHeader() {
    return getString("Sheet.Combat.OrderAttackEvents");  //$NON-NLS-1$
  }

  public String getAttackComment() {
    return getString("Sheet.Combat.Comment.Rules");
  }

  public CombatAction[] getCombatActions() {
    String nameHeader = getResources().getString("Sheet.Combat.CommonActions.Action"); //$NON-NLS-1$
    String speedHeader = getResources().getString("Sheet.Combat.CommonActions.Speed"); //$NON-NLS-1$
    String dvHeader = getResources().getString("Sheet.Combat.CommonActions.DV"); //$NON-NLS-1$
    CombatAction headerData = new CombatAction(nameHeader, speedHeader, dvHeader);
    CombatAction emptyData = new CombatAction(" ", " ", " ");
    return new CombatAction[] { headerData, emptyData, getCombatAction("JoinBattle"), //$NON-NLS-1$
                                getCombatAction("ReadyWeapon"), //$NON-NLS-1$
                                getCombatAction("PhysicalAttack"), //$NON-NLS-1$
                                getCombatAction("CoordinateAttack"), //$NON-NLS-1$
                                getCombatAction("Aim"), //$NON-NLS-1$
                                getCombatAction("Guard"), //$NON-NLS-1$
                                getCombatAction("Move"), //$NON-NLS-1$
                                getCombatAction("Dash"), //$NON-NLS-1$
                                getCombatAction("Misc"), //$NON-NLS-1$
                                getCombatAction("Jump"), //$NON-NLS-1$
                                getCombatAction("Rise"), //$NON-NLS-1$
                                getCombatAction("Inactive") //$NON-NLS-1$

    };
  }

  private CombatAction getCombatAction(String actionId) {
    String name = getString("Sheet.Combat.CommonActions." + actionId + ".Name");  //$NON-NLS-1$ //$NON-NLS-2$
    String speed = getString("Sheet.Combat.CommonActions." + actionId + ".Speed"); //$NON-NLS-1$ //$NON-NLS-2$
    String dv = getString("Sheet.Combat.CommonActions." + actionId + ".DV"); //$NON-NLS-1$ //$NON-NLS-2$
    return new CombatAction(name, speed, dv);
  }

  public String getActionHeader() {
    return  getResources().getString("Sheet.Combat.CommonActions.Header"); //$NON-NLS-1$
  }
}
