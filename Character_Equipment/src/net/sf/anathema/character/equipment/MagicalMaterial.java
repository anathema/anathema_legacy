package net.sf.anathema.character.equipment;

import net.sf.anathema.character.generic.equipment.ArtifactAttuneType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.generic.type.ICharacterTypeVisitor;
import net.sf.anathema.lib.util.IIdentificate;

public enum MagicalMaterial implements IIdentificate {
  Orichalcum, Jade, Moonsilver, Starmetal, Soulsteel, Adamant;

  public String getId() {
    return name();
  }
  
  public static ArtifactAttuneType[] getAttunementTypes(final ICharacterType type, 
		  final MagicalMaterial material)
  {
	  final ArtifactAttuneType[][] types = new ArtifactAttuneType[1][];
	  
	  type.accept(new ICharacterTypeVisitor()
	  {
		@Override
		public void visitAbyssal(ICharacterType visitedType)
		{
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
				    { ArtifactAttuneType.Unattuned, ArtifactAttuneType.ExpensivePartiallyAttuned };
			else
				types[0] = new ArtifactAttuneType[]
				    { ArtifactAttuneType.Unattuned, ArtifactAttuneType.PartiallyAttuned };
		}

		@Override
		public void visitSpirit(ICharacterType type) {
			types[0] = null;
		}
	  });
	  
	  return types[0];
  }
  
  private static ArtifactAttuneType[] getSingleMaterialAttunement(ICharacterType type, MagicalMaterial material)
  {
	  ArtifactAttuneType[] attunement;
	  if (material == getDefault(type))
		  attunement = new ArtifactAttuneType[] { ArtifactAttuneType.Unattuned, ArtifactAttuneType.FullyAttuned };
	  else
		  attunement = new ArtifactAttuneType[] { ArtifactAttuneType.Unattuned,
			  ArtifactAttuneType.PartiallyAttuned, ArtifactAttuneType.UnharmoniouslyAttuned };
	  return attunement;
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
      
      public void visitGhost(ICharacterType visitedType)
      {
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

      public void visitAbyssal(ICharacterType visitedType) {
        material[0] = Soulsteel;
      }
    });
    return material[0];
  }
}