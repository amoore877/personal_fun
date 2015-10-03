package games.rolePlayingGames.shadowrun.util;

import java.util.HashMap;

/**
 * Range table in Shadowrun.
 * 
 * @author Andrew
 */
public final class RangeTable {

	/**
	 * The table mapping.
	 */
	private static final HashMap<RangedWeaponType, HashMap<FiringRangeType, String>> rangeTable = new HashMap<RangedWeaponType, HashMap<FiringRangeType, String>>();

	static {
		// taser
		final HashMap<FiringRangeType, String> taser = new HashMap<FiringRangeType, String>();
		taser.put(FiringRangeType.SHORT, "0-5");
		taser.put(FiringRangeType.MEDIUM, "6-10");
		taser.put(FiringRangeType.LONG, "11-15");
		taser.put(FiringRangeType.EXTREME, "16-20");
		rangeTable.put(RangedWeaponType.TASER, taser);

		// hold out
		final HashMap<FiringRangeType, String> holdOut = new HashMap<FiringRangeType, String>();
		holdOut.put(FiringRangeType.SHORT, "0-5");
		holdOut.put(FiringRangeType.MEDIUM, "6-15");
		holdOut.put(FiringRangeType.LONG, "16-30");
		holdOut.put(FiringRangeType.EXTREME, "31-50");
		rangeTable.put(RangedWeaponType.HOLDOUT, holdOut);

		// light pistol
		final HashMap<FiringRangeType, String> lightPistol = new HashMap<FiringRangeType, String>();
		lightPistol.put(FiringRangeType.SHORT, "0-5");
		lightPistol.put(FiringRangeType.MEDIUM, "6-15");
		lightPistol.put(FiringRangeType.LONG, "16-30");
		lightPistol.put(FiringRangeType.EXTREME, "31-50");
		rangeTable.put(RangedWeaponType.LGT_PISTOL, lightPistol);

		// heavy pistol
		final HashMap<FiringRangeType, String> heavyPistol = new HashMap<FiringRangeType, String>();
		heavyPistol.put(FiringRangeType.SHORT, "0-5");
		heavyPistol.put(FiringRangeType.MEDIUM, "6-20");
		heavyPistol.put(FiringRangeType.LONG, "21-40");
		heavyPistol.put(FiringRangeType.EXTREME, "41-60");
		rangeTable.put(RangedWeaponType.HVY_PISTOL, heavyPistol);

		// machine pistol
		final HashMap<FiringRangeType, String> machinePistol = new HashMap<FiringRangeType, String>();
		machinePistol.put(FiringRangeType.SHORT, "0-5");
		machinePistol.put(FiringRangeType.MEDIUM, "6-15");
		machinePistol.put(FiringRangeType.LONG, "16-30");
		machinePistol.put(FiringRangeType.EXTREME, "31-50");
		rangeTable.put(RangedWeaponType.MP, machinePistol);

		// SMG
		final HashMap<FiringRangeType, String> smg = new HashMap<FiringRangeType, String>();
		smg.put(FiringRangeType.SHORT, "0-10");
		smg.put(FiringRangeType.MEDIUM, "11-40");
		smg.put(FiringRangeType.LONG, "41-80");
		smg.put(FiringRangeType.EXTREME, "81-150");
		rangeTable.put(RangedWeaponType.SMG, smg);

		// assault rifle
		final HashMap<FiringRangeType, String> assaultRifle = new HashMap<FiringRangeType, String>();
		assaultRifle.put(FiringRangeType.SHORT, "0-25");
		assaultRifle.put(FiringRangeType.MEDIUM, "26-150");
		assaultRifle.put(FiringRangeType.LONG, "151-350");
		assaultRifle.put(FiringRangeType.EXTREME, "351-550");
		rangeTable.put(RangedWeaponType.AR, assaultRifle);

		// shotgun, flechette
		final HashMap<FiringRangeType, String> flechetteShotgun = new HashMap<FiringRangeType, String>();
		flechetteShotgun.put(FiringRangeType.SHORT, "0-15");
		flechetteShotgun.put(FiringRangeType.MEDIUM, "16-30");
		flechetteShotgun.put(FiringRangeType.LONG, "31-45");
		flechetteShotgun.put(FiringRangeType.EXTREME, "46-60");
		rangeTable.put(RangedWeaponType.FLECHETTE_SHOTGUN, flechetteShotgun);

		// shotgun, slug
		final HashMap<FiringRangeType, String> slugShotgun = new HashMap<FiringRangeType, String>();
		slugShotgun.put(FiringRangeType.SHORT, "0-10");
		slugShotgun.put(FiringRangeType.MEDIUM, "11-40");
		slugShotgun.put(FiringRangeType.LONG, "41-80");
		slugShotgun.put(FiringRangeType.EXTREME, "81-150");
		rangeTable.put(RangedWeaponType.SLUG_SHOTGUN, slugShotgun);

		// sniper rifle
		final HashMap<FiringRangeType, String> sniper = new HashMap<FiringRangeType, String>();
		sniper.put(FiringRangeType.SHORT, "0-50");
		sniper.put(FiringRangeType.MEDIUM, "51-350");
		sniper.put(FiringRangeType.LONG, "351-800");
		sniper.put(FiringRangeType.EXTREME, "801-1500");
		rangeTable.put(RangedWeaponType.SNIPER, sniper);

		// LMG
		final HashMap<FiringRangeType, String> lmg = new HashMap<FiringRangeType, String>();
		lmg.put(FiringRangeType.SHORT, "0-25");
		lmg.put(FiringRangeType.MEDIUM, "26-200");
		lmg.put(FiringRangeType.LONG, "201-400");
		lmg.put(FiringRangeType.EXTREME, "401-800");
		rangeTable.put(RangedWeaponType.LMG, lmg);

		// HMG
		final HashMap<FiringRangeType, String> hmg = new HashMap<FiringRangeType, String>();
		hmg.put(FiringRangeType.SHORT, "0-40");
		hmg.put(FiringRangeType.MEDIUM, "41-250");
		hmg.put(FiringRangeType.LONG, "251-750");
		hmg.put(FiringRangeType.EXTREME, "751-1200");
		rangeTable.put(RangedWeaponType.HMG, hmg);

		// assault cannon
		final HashMap<FiringRangeType, String> cannon = new HashMap<FiringRangeType, String>();
		cannon.put(FiringRangeType.SHORT, "0-50");
		cannon.put(FiringRangeType.MEDIUM, "51-300");
		cannon.put(FiringRangeType.LONG, "301-750");
		cannon.put(FiringRangeType.EXTREME, "751-1500");
		rangeTable.put(RangedWeaponType.CANNON, cannon);

		// grenade launcher
		final HashMap<FiringRangeType, String> grenadeLauncher = new HashMap<FiringRangeType, String>();
		grenadeLauncher.put(FiringRangeType.SHORT, "5-50");
		grenadeLauncher.put(FiringRangeType.MEDIUM, "51-100");
		grenadeLauncher.put(FiringRangeType.LONG, "101-150");
		grenadeLauncher.put(FiringRangeType.EXTREME, "151-500");
		rangeTable.put(RangedWeaponType.GL, grenadeLauncher);

		// missile launcher
		final HashMap<FiringRangeType, String> missileLauncher = new HashMap<FiringRangeType, String>();
		missileLauncher.put(FiringRangeType.SHORT, "20-70");
		missileLauncher.put(FiringRangeType.MEDIUM, "71-150");
		missileLauncher.put(FiringRangeType.LONG, "151-450");
		missileLauncher.put(FiringRangeType.EXTREME, "451-1500");
		rangeTable.put(RangedWeaponType.MISSILE, missileLauncher);

		// bow
		final HashMap<FiringRangeType, String> bow = new HashMap<FiringRangeType, String>();
		bow.put(FiringRangeType.SHORT, "0-STR");
		bow.put(FiringRangeType.MEDIUM, "To STRx10");
		bow.put(FiringRangeType.LONG, "To STRx30");
		bow.put(FiringRangeType.EXTREME, "To STRx60");
		rangeTable.put(RangedWeaponType.BOW, bow);

		// light crossbow
		final HashMap<FiringRangeType, String> lightCrossbow = new HashMap<FiringRangeType, String>();
		lightCrossbow.put(FiringRangeType.SHORT, "0-6");
		lightCrossbow.put(FiringRangeType.MEDIUM, "7-24");
		lightCrossbow.put(FiringRangeType.LONG, "25-60");
		lightCrossbow.put(FiringRangeType.EXTREME, "61-120");
		rangeTable.put(RangedWeaponType.LGT_CROSSBOW, lightCrossbow);

		// med crossbow
		final HashMap<FiringRangeType, String> medCrossbow = new HashMap<FiringRangeType, String>();
		medCrossbow.put(FiringRangeType.SHORT, "0-9");
		medCrossbow.put(FiringRangeType.MEDIUM, "10-36");
		medCrossbow.put(FiringRangeType.LONG, "37-90");
		medCrossbow.put(FiringRangeType.EXTREME, "91-150");
		rangeTable.put(RangedWeaponType.MED_CROSSBOW, medCrossbow);

		// hvy crossbow
		final HashMap<FiringRangeType, String> hvyCrossbow = new HashMap<FiringRangeType, String>();
		hvyCrossbow.put(FiringRangeType.SHORT, "0-15");
		hvyCrossbow.put(FiringRangeType.MEDIUM, "16-45");
		hvyCrossbow.put(FiringRangeType.LONG, "46-120");
		hvyCrossbow.put(FiringRangeType.EXTREME, "121-180");
		rangeTable.put(RangedWeaponType.HVY_CROSSBOW, hvyCrossbow);

		// throwing knife
		final HashMap<FiringRangeType, String> throwingKnife = new HashMap<FiringRangeType, String>();
		throwingKnife.put(FiringRangeType.SHORT, "0-STR");
		throwingKnife.put(FiringRangeType.MEDIUM, "To STRx2");
		throwingKnife.put(FiringRangeType.LONG, "To STRx3");
		throwingKnife.put(FiringRangeType.EXTREME, "To STRx5");
		rangeTable.put(RangedWeaponType.THROWING_KNIFE, throwingKnife);

		// shuriken
		final HashMap<FiringRangeType, String> shuriken = new HashMap<FiringRangeType, String>();
		shuriken.put(FiringRangeType.SHORT, "0-STR");
		shuriken.put(FiringRangeType.MEDIUM, "To STRx2");
		shuriken.put(FiringRangeType.LONG, "To STRx5");
		shuriken.put(FiringRangeType.EXTREME, "To STRx7");
		rangeTable.put(RangedWeaponType.SHURIKEN, shuriken);

		// standard grenade
		final HashMap<FiringRangeType, String> stdGrenade = new HashMap<FiringRangeType, String>();
		stdGrenade.put(FiringRangeType.SHORT, "0-STRx2");
		stdGrenade.put(FiringRangeType.MEDIUM, "To STRx4");
		stdGrenade.put(FiringRangeType.LONG, "To STRx6");
		stdGrenade.put(FiringRangeType.EXTREME, "To STRx10");
		rangeTable.put(RangedWeaponType.STD_GRENADE, stdGrenade);

		// aerodynamic grenade
		final HashMap<FiringRangeType, String> aeroGrenade = new HashMap<FiringRangeType, String>();
		aeroGrenade.put(FiringRangeType.SHORT, "0-STRx2");
		aeroGrenade.put(FiringRangeType.MEDIUM, "To STRx4");
		aeroGrenade.put(FiringRangeType.LONG, "To STRx8");
		aeroGrenade.put(FiringRangeType.EXTREME, "To STRx15");
		rangeTable.put(RangedWeaponType.AERO_GRENADE, aeroGrenade);
	}

	private RangeTable() {
	}

	/**
	 * Get the ranges of a specific ranged weapon type.
	 */
	@SuppressWarnings("unchecked")
	public static HashMap<FiringRangeType, String> getAllRangesForWeapon(
			final RangedWeaponType iRangedWeaponType) {
		return (HashMap<FiringRangeType, String>) (rangeTable
				.get(iRangedWeaponType).clone());
	}
}
