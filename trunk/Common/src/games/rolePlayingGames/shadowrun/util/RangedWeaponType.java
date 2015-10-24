package games.rolePlayingGames.shadowrun.util;

import javax.xml.bind.annotation.XmlEnum;

/**
 * Shadowrun ranged weapon types.
 * 
 * @author Andrew
 */
@XmlEnum
public enum RangedWeaponType {

	/**
	 * Taser.
	 */
	TASER,
	/**
	 * Hold-out pistol.
	 */
	HOLDOUT,
	/**
	 * Light pistol.
	 */
	LGT_PISTOL,
	/**
	 * Heavy pistol.
	 */
	HVY_PISTOL,
	/**
	 * Machine pistol.
	 */
	MP,
	/**
	 * SMG.
	 */
	SMG,
	/**
	 * Assault rifle.
	 */
	AR,
	/**
	 * Flechette shotgun.
	 */
	FLECHETTE_SHOTGUN,
	/**
	 * Slug shotgun.
	 */
	SLUG_SHOTGUN,
	/**
	 * Sniper rifle.
	 */
	SNIPER,
	/**
	 * Light machine gun.
	 */
	LMG,
	/**
	 * Medium or heavy machine gun.
	 */
	HMG,
	/**
	 * Assault cannon.
	 */
	CANNON,
	/**
	 * Grenade launcher.
	 */
	GL,
	/**
	 * Missile or rocket launcher.
	 */
	MISSILE,
	/**
	 * Bow.
	 */
	BOW,
	/**
	 * Light crossbow.
	 */
	LGT_CROSSBOW,
	/**
	 * Medium crossbow.
	 */
	MED_CROSSBOW,
	/**
	 * Heavy crossbow.
	 */
	HVY_CROSSBOW,
	/**
	 * Throwing knife.
	 */
	THROWING_KNIFE,
	/**
	 * SHURIKEN.
	 */
	SHURIKEN,
	/**
	 * Standard grenade.
	 */
	STD_GRENADE,
	/**
	 * Aerodynamic grenade.
	 */
	AERO_GRENADE;

	/**
	 * Determine if the given ranged weapon type is a heavy weapon or not.
	 * 
	 * @param iRangedWeaponType
	 *            the given ranged weapon type.
	 * @return true if a heavy weapon, false otherwise.
	 */
	public static boolean isHeavyWeapon(final RangedWeaponType iRangedWeaponType) {
		if (LMG.equals(iRangedWeaponType) || HMG.equals(iRangedWeaponType)
				|| CANNON.equals(iRangedWeaponType)
				|| GL.equals(iRangedWeaponType)
				|| MISSILE.equals(iRangedWeaponType)) {
			return true;
		}

		return false;
	}

	/**
	 * Determine if the given ranged weapon type is a weapon that suffers from
	 * scattering or not.
	 * 
	 * @param iRangedWeaponType
	 *            the given ranged weapon type.
	 * @return true if scatter is involved, false otherwise.
	 */
	public static boolean isScatteringWeapon(
			final RangedWeaponType iRangedWeaponType) {
		if (GL.equals(iRangedWeaponType) || MISSILE.equals(iRangedWeaponType)
				|| STD_GRENADE.equals(iRangedWeaponType)
				|| AERO_GRENADE.equals(iRangedWeaponType)) {
			return true;
		}

		return false;
	}
}
