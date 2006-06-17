package net.sf.anathema.acceptance.fixture.character.template;

import net.sf.anathema.character.generic.template.essence.FactorizedTrait;
import net.sf.anathema.character.generic.template.essence.FactorizedTraitSumCalculator;
import net.sf.anathema.character.generic.template.essence.IEssenceTemplate;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.test.character.dummy.DummyGenericTrait;

public class CheckEssenceTemplateFixture extends AbstractTemplateColumnFixture {

  public int willpower;
  public int essence;
  public int compassion;
  public int conviction;
  public int temperance;
  public int valor;

  private IEssenceTemplate getEssenceTemplate() {
    return getTemplate().getEssenceTemplate();
  }

  private IGenericTrait[] createVirtueTraits() {
    return new IGenericTrait[] {
        new DummyGenericTrait(VirtueType.Compassion, compassion),
        new DummyGenericTrait(VirtueType.Conviction, conviction),
        new DummyGenericTrait(VirtueType.Temperance, temperance),
        new DummyGenericTrait(VirtueType.Valor, valor) };
  }

  private IGenericTrait createWillpowerTrait() {
    return new DummyGenericTrait(OtherTraitType.Willpower, willpower);
  }

  private IGenericTrait createEssenceTrait() {
    return new DummyGenericTrait(OtherTraitType.Essence, essence);
  }

  public int personalPool() {
    FactorizedTrait[] personalTraits = getEssenceTemplate().getPersonalTraits(
        createWillpowerTrait(),
        createVirtueTraits(),
        createEssenceTrait());
    return new FactorizedTraitSumCalculator().calculateSum(personalTraits);
  }

  public int peripheralPool() {
    FactorizedTrait[] peripheralTraits = getEssenceTemplate().getPeripheralTraits(
        createWillpowerTrait(),
        createVirtueTraits(),
        createEssenceTrait());
    return new FactorizedTraitSumCalculator().calculateSum(peripheralTraits);
  }
}