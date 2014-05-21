package net.sf.anathema.character.magic.parser.dto.special;

public class SpecialCharmDto {
  public String charmId;
  public RepurchaseDto repurchase;
  public OxBodyTechniqueDto oxBodyTechnique;
  public PainToleranceDto painTolerance;
  public TraitCapModifierDto traitCapModifier;
  public MultiEffectDto multiEffect;
  public TranscendenceDto transcendence;
  public UpgradableDto upgradable;
  public SubEffectDto subEffect;

  public boolean isSpecial() {
    return repurchase != null || oxBodyTechnique != null || painTolerance != null || traitCapModifier != null || multiEffect != null ||
           transcendence != null || upgradable != null || subEffect != null;
  }
}
