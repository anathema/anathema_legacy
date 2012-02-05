package net.sf.anathema.character.equipment;

import net.sf.anathema.character.generic.equipment.ArtifactAttuneType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.generic.type.ICharacterTypeVisitor;
import net.sf.anathema.lib.util.IIdentificate;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static net.sf.anathema.character.generic.equipment.ArtifactAttuneType.FullyAttuned;
import static net.sf.anathema.character.generic.equipment.ArtifactAttuneType.PartiallyAttuned;
import static net.sf.anathema.character.generic.equipment.ArtifactAttuneType.Unattuned;
import static net.sf.anathema.character.generic.equipment.ArtifactAttuneType.UnharmoniouslyAttuned;

public enum MagicalMaterial implements IIdentificate {
  Orichalcum, Jade, Moonsilver, Starmetal, Soulsteel, Adamant,
  VitriolOrichalcum, VitriolJade, VitriolMoonsilver, VitriolStarmetal, VitriolSoulsteel, VitriolAdamant;

  public String getId() {
    return name();
  }

  public static ArtifactAttuneType[] getAttunementTypes(final ICharacterType type,
                                                        final MagicalMaterial material) {
    final ArtifactAttuneType[][] types = new ArtifactAttuneType[1][];

    type.accept(new ICharacterTypeVisitor() {
      @Override
      public void visitAbyssal(ICharacterType visitedType) {
        types[0] = getSingleMaterialAttunement(type, material);
      }

      @Override
      public void visitDB(ICharacterType visitedType) {
        types[0] = getSingleMaterialAttunement(type, material);
      }

      @Override
      public void visitDragonKing(ICharacterType type) {
        types[0] = null;
      }

      @Override
      public void visitLunar(ICharacterType type) {
        types[0] = getSingleMaterialAttunement(type, material);
      }

      @Override
      public void visitInfernal(ICharacterType type) {
        types[0] = getDefaultAttunementOptions(isVitriolTainted(material));
      }

      @Override
      public void visitMortal(ICharacterType visitedType) {
        types[0] = null;
      }

      @Override
      public void visitSidereal(ICharacterType visitedType) {
        types[0] = getSingleMaterialAttunement(type, material);
      }

      @Override
      public void visitSolar(ICharacterType visitedType) {
        types[0] = getSingleMaterialAttunement(type, material);
      }

      @Override
      public void visitGhost(ICharacterType type) {
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
      public void visitSpirit(ICharacterType type) {
        types[0] = null;
      }
    });

    return types[0];
  }

  private static boolean isVitriolTainted(MagicalMaterial material) {
    List<MagicalMaterial> vitriolTaintedMaterials = newArrayList(VitriolAdamant, VitriolJade, VitriolMoonsilver, VitriolOrichalcum, VitriolSoulsteel, VitriolStarmetal);
    return vitriolTaintedMaterials.contains(material);
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

      public void visitSolar(ICharacterType visitedType) {
        material[0] = Orichalcum;
      }

      public void visitSidereal(ICharacterType visitedType) {
        material[0] = Starmetal;
      }

      public void visitMortal(ICharacterType visitedType) {
        // nothing to do
      }

      public void visitSpirit(ICharacterType visitedType) {
        // nothing to do
      }

      public void visitGhost(ICharacterType visitedType) {
        // nothing to do
      }

      public void visitLunar(ICharacterType type) {
        material[0] = Moonsilver;
      }

      public void visitDragonKing(ICharacterType type) {
        material[0] = Orichalcum;
      }

      public void visitDB(ICharacterType visitedType) {
        material[0] = Jade;
      }

      public void visitInfernal(ICharacterType visitedType) {
        material[0] = VitriolOrichalcum;
      }

      public void visitAbyssal(ICharacterType visitedType) {
        material[0] = Soulsteel;
      }
    });
    return material[0];
  }
}