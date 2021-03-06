/*******************************************************************************
 * @author Reika Kalseki
 * 
 * Copyright 2017
 * 
 * All rights reserved.
 * Distribution of the software in any form is only allowed with
 * explicit, prior permission from the owner.
 ******************************************************************************/
package Reika.LegacyCraft;

import net.minecraft.entity.EntityLiving;
import Reika.DragonAPI.Libraries.ReikaEntityHelper;
import Reika.LegacyCraft.Overrides.Entity.EntityLegacyCreeper;
import Reika.LegacyCraft.Overrides.Entity.EntityLegacyEnderman;
import Reika.LegacyCraft.Overrides.Entity.EntityLegacySkeleton;
import Reika.LegacyCraft.Overrides.Entity.EntityLegacySpider;
import Reika.LegacyCraft.Overrides.Entity.EntityLegacyVillager;
import Reika.LegacyCraft.Overrides.Entity.EntityLegacyZombie;

public enum MobOverrides {

	CREEPER(EntityLegacyCreeper.class, "Creeper", 50),
	SKELETON(EntityLegacySkeleton.class, "Skeleton", 51),
	ZOMBIE(EntityLegacyZombie.class, "Zombie", 54),
	SPIDER(EntityLegacySpider.class, "Spider", 52),
	ENDERMAN(EntityLegacyEnderman.class, "Enderman", 58),
	VILLAGER(EntityLegacyVillager.class, "Villager", 120);

	private final Class mobClass;
	public final String name;
	public final int entityID;

	public static final MobOverrides[] mobList = values();

	private MobOverrides(Class<? extends EntityLiving> c, String n, int id) {
		mobClass = c;
		name = n;
		entityID = id;
	}

	@Deprecated
	public void register() {
		ReikaEntityHelper.overrideEntity(mobClass, name, entityID);
	}

	public static void registerAll() {
		for (int i = 0; i < mobList.length; i++) {
			MobOverrides mob = mobList[i];
			if (mob.isActive()) {
				mob.register();
			}
		}
	}

	public boolean isActive() {
		return LegacyCraft.config.overrideMob(this);
	}

}
