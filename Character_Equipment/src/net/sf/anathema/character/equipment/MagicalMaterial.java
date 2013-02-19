package net.sf.anathema.character.equipment;

import net.sf.anathema.character.generic.equipment.ArtifactAttuneType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.generic.type.ICharacterTypeVisitor;
import net.sf.anathema.lib.util.Identified;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static net.sf.anathema.character.generic.equipment.ArtifactAttuneType.FullyAttuned;
import static net.sf.anathema.character.generic.equipment.ArtifactAttuneType.PartiallyAttuned;
import static net.sf.anathema.character.generic.equipment.ArtifactAttuneType.Unattuned;
import static net.sf.anathema.character.generic.equipment.ArtifactAttuneType.UnharmoniouslyAttuned;

public enum MagicalMaterial implements Identified {
  Orichalcum, Jade, Moonsilver, Starmetal, Soulsteel, Adamant,
  VitriolOrichalcum, VitriolJade, VitriolMoonsilver, VitriolStarmetal, VitriolSoulsteel, VitriolAdamant;

  @Override
  public String getId() {
    return name();
  }

  public static ArtifactAttuneType[] getAttunementTypes(final ICharacterType type,
                                                        final MagicalMaterial material) {
    final ArtifactAttuneType[][] types = new ArtifactAttuneType[1][];

    type.accept(new ICharacterTypeVisitor() {
      @Override
      public void visitAbyssal() {
        types[0] = getSingleMaterialAttunement(type, material);
      }

      @Override
      public void visitDB() {
        types[0] = getSingleMaterialAttunement(type, material);
      }

      @Override
      public void visitLunar() {
        types[0] = getSingleMaterialAttunement(type, material);
      }

      @Override
      public void visitInfernal() {
        types[0] = getDefaultAttunementOptions(isVitriolTainted(material));
      }

      @Override
      public void visitMortal() {
        types[0] = getNullAttunementTypes();
      }

      @Override
      public void visitSidereal() {
        types[0] = getSingleMaterialAttunement(type, material);
      }

      @Override
      public void visitSolar() {
        types[0] = getSingleMaterialAttunement(type, material);
      }

      @Override
      public void visitGhost() {
        if (material == MagicalMaterial.Orichalcum ||
                material == MagicalMaterial.Moonsilver ||
                material == MagicalMaterial.Starmetal)
          types[0] = new ArtifactAttuneType[]
                  {Unattuned, ArtifactAttuneType.ExpensivePartiallyAttuned};
        else
          types[0] = new ArtifactAttuneType[]
                  {Unattuned, PartiallyAttuned};
      }

      @Override
      public void visitSpirit() {
        types[0] = getNullAttunementTypes();
      }
    });

    return types[0];
  }

  private static boolean isVitriolTainted(MagicalMaterial material) {
    List<MagicalMaterial> vitriolTaintedMaterials = newArrayList(VitriolAdamant, VitriolJade, VitriolMoonsilver, VitriolOrichalcum, VitriolSoulsteel, VitriolStarmetal);
    return vitriolTaintedMaterials.contains(material);
  }
  
  private static ArtifactAttuneType[] getNullAttunementTypes() {
	  return new ArtifactAttuneType[] { Unattuned };
  }

  private static ArtifactAttuneType[] getSingleMaterialAttunement(ICharacterType type, MagicalMaterial material) {
    boolean resonatesWithMaterial = material == getDefault(type);
    return getDefaultAttunementOptions(resonatesWithMaterial);
  }

  private static ArtifactAttuneType[] getDefaultAttunementOptions(boolean resonatesWithMaterial) {
    if (resonatesWithMaterial) {
      return new ArtifactAttuneType[]{Unattuned, FullyAttuned};
    }
    return new ArtifactAttuneType[]{Unattuned, PartiallyAttuned, UnharmoniouslyAttuned};
  }

  public static MagicalMaterial getDefault(ICharacterType characterType) {
    final MagicalMaterial[] material = new MagicalMaterial[1];

    characterType.accept(new ICharacterTypeVisitor() {

      @Override
      public void visitSolar() {
        material[0] = Orichalcum;
      }

      @Override
      public void visitSidereal() {
        material[0] = Starmetal;
      }

      @Override
      public void visitMortal() {
        // nothing to do
      }

      @Override
      public void visitSpirit() {
        // nothing to do
      }

      @Override
      public void visitGhost() {
        // nothing to do
      }

      @Override
      public void visitLunar() {
        material[0] = Moonsilver;
      }

      @Override
      public void visitDB() {
        material[0] = Jade;
      }

      @Override
      public void visitInfernal() {
        material[0] = VitriolOrichalcum;
      }

      @Override
      public void visitAbyssal() {
        material[0] = Soulsteel;
      }
    });
    return material[0];
  }
}