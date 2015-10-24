package games.rolePlayingGames.shadowrun.tracking.trackables.xml.adapter;

import games.rolePlayingGames.shadowrun.tracking.notes.ability.AbstractAbility;
import games.rolePlayingGames.shadowrun.tracking.notes.impl.QualityNote;
import games.rolePlayingGames.shadowrun.tracking.notes.impl.Skill;
import games.rolePlayingGames.shadowrun.tracking.notes.impl.Spell;
import games.rolePlayingGames.shadowrun.tracking.notes.impl.StatusEffectNote;
import games.rolePlayingGames.shadowrun.tracking.trackables.impl.character.NonPlayerCharacter;
import games.rolePlayingGames.shadowrun.tracking.trackables.impl.character.NonPlayerHacker;
import games.rolePlayingGames.shadowrun.tracking.trackables.impl.character.NonPlayerTechnomancer;
import games.rolePlayingGames.shadowrun.tracking.trackables.impl.equipment.Armor;
import games.rolePlayingGames.shadowrun.tracking.trackables.impl.equipment.ChargedMeleeWeapon;
import games.rolePlayingGames.shadowrun.tracking.trackables.impl.equipment.Device;
import games.rolePlayingGames.shadowrun.tracking.trackables.impl.equipment.FirearmWeapon;
import games.rolePlayingGames.shadowrun.tracking.trackables.impl.equipment.MeleeWeapon;
import games.rolePlayingGames.shadowrun.tracking.trackables.impl.equipment.ProjectileWeapon;
import games.rolePlayingGames.shadowrun.tracking.trackables.impl.equipment.ShadowrunBasicItem;
import games.rolePlayingGames.shadowrun.tracking.trackables.impl.equipment.TimedItem;
import games.rolePlayingGames.shadowrun.tracking.trackables.impl.matrix.NonPlayerAgent;
import games.rolePlayingGames.shadowrun.tracking.trackables.impl.matrix.NonPlayerAutoPilotDevice;
import games.rolePlayingGames.shadowrun.tracking.trackables.impl.spirit.NonPlayerSpirit;
import games.rolePlayingGames.shadowrun.tracking.trackables.item.AbstractShadowrunItem;
import games.rolePlayingGames.shadowrun.tracking.trackables.item.equipment.weapon.AbstractAmmoFedWeapon;
import games.rolePlayingGames.shadowrun.tracking.trackables.item.equipment.weapon.AbstractMeleeWeapon;
import games.rolePlayingGames.shadowrun.tracking.trackables.item.equipment.weapon.AbstractShadowrunWeapon;
import games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.equipment.ArmorJAXB;
import games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.equipment.BasicItemJAXB;
import games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.equipment.ChargedMeleeWeaponJAXB;
import games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.equipment.DeviceJAXB;
import games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.equipment.EquipmentJAXB;
import games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.equipment.FirearmWeaponJAXB;
import games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.equipment.MeleeWeaponJAXB;
import games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.equipment.ProjectileWeaponJAXB;
import games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.equipment.RangedWeaponJAXB;
import games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.equipment.TimedItemJAXB;
import games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.equipment.WeaponJAXB;
import games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.living.CharacterJAXB;
import games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.living.HackerJAXB;
import games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.living.LivingBeingJAXB;
import games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.living.SpiritJAXB;
import games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.living.TechnomancerJAXB;
import games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.matrix.AgentJAXB;
import games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.matrix.AutoPilotDeviceJAXB;
import games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.matrix.MatrixBeingJAXB;
import games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.support.AbilityJAXB;
import games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.support.QualityJAXB;
import games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.support.SkillJAXB;
import games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.support.SpellJAXB;
import games.rolePlayingGames.shadowrun.tracking.trackables.xml.jaxb.support.StatusEffectJAXB;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 * Utility class for JAXB class to regular Java class.
 * 
 * @author Andrew
 */
public final class JAXBtoJava {

	/**
	 * Default spirit services count.
	 */
	private static final int DEFAULT_SPIRIT_SERVICES = 4;

	/**
	 * Default projectile weapon spare clips.
	 */
	private static final int DEFAULT_PROJECTILE_WEAPON_SPARE_CLIPS = 4;

	/**
	 * Default firearm spare clips.
	 */
	private static final int DEFAULT_FIREARM_SPARE_CLIPS = 3;

	/**
	 * Default charged melee weapon spare clips.
	 */
	private static final int DEFAULT_CHARGED_MELEE_WEAPON_SPARE_CLIPS = 0;

	/**
	 * Instance.
	 */
	private static JAXBtoJava myInstance = null;

	/**
	 * Armor folder.
	 */
	private static final String ARMOR_FOLDER = "tracking/shadowrun/armor";

	/**
	 * Basic item folder.
	 */
	private static final String BASIC_ITEM_FOLDER = "tracking/shadowrun/basicitem";

	/**
	 * Charged melee weapon folder.
	 */
	private static final String CHARGED_MELEE_WEAPON_FOLDER = "tracking/shadowrun/chargedmeleeweapon";

	/**
	 * Device folder.
	 */
	private static final String DEVICE_FOLDER = "tracking/shadowrun/device";

	/**
	 * Firearm folder.
	 */
	private static final String FIREARM_FOLDER = "tracking/shadowrun/firearm";

	/**
	 * Melee weapon folder.
	 */
	private static final String MELEE_WEAPON_FOLDER = "tracking/shadowrun/meleeweapon";

	/**
	 * Projectile folder.
	 */
	private static final String PROJECTILE_FOLDER = "tracking/shadowrun/projectile";

	/**
	 * Timed item folder.
	 */
	private static final String TIMED_ITEM_FOLDER = "tracking/shadowrun/timeditem";

	/**
	 * Status effect folder.
	 */
	private static final String STATUS_EFFECT_FOLDER = "tracking/shadowrun/statuseffect";

	/**
	 * Quality folder.
	 */
	private static final String QUALITY_FOLDER = "tracking/shadowrun/quality";

	/**
	 * Regular character folder.
	 */
	private static final String CHARACTER_FOLDER = "tracking/shadowrun/character";

	/**
	 * Spirit folder.
	 */
	private static final String SPIRIT_FOLDER = "tracking/shadowrun/spirit";

	/**
	 * Hacker folder.
	 */
	private static final String HACKER_FOLDER = "tracking/shadowrun/hacker";

	/**
	 * Technomancer folder.
	 */
	private static final String TECHNO_FOLDER = "tracking/shadowrun/techno";

	/**
	 * Agent folder.
	 */
	private static final String AGENT_FOLDER = "tracking/shadowrun/agent";

	/**
	 * Autopilot devices folder.
	 */
	private static final String AUTO_PILOT_FOLDER = "tracking/shadowrun/autopilot";

	/**
	 * Equipment map.
	 */
	private final HashMap<String, EquipmentJAXB> myEquipment = new HashMap<String, EquipmentJAXB>();

	/**
	 * Status effect map.
	 */
	private final HashMap<String, StatusEffectJAXB> myStatusEffects = new HashMap<String, StatusEffectJAXB>();

	/**
	 * Quality map.
	 */
	private final HashMap<String, QualityJAXB> myQualities = new HashMap<String, QualityJAXB>();

	/**
	 * Regular character list.
	 */
	private final ArrayList<CharacterJAXB> myCharacters = new ArrayList<CharacterJAXB>();

	/**
	 * Hacker list.
	 */
	private final ArrayList<HackerJAXB> myHackers = new ArrayList<HackerJAXB>();

	/**
	 * Spirit list.
	 */
	private final ArrayList<SpiritJAXB> mySpirits = new ArrayList<SpiritJAXB>();

	/**
	 * Technomancers list.
	 */
	private final ArrayList<TechnomancerJAXB> myTechnomancers = new ArrayList<TechnomancerJAXB>();

	/**
	 * Agents list.
	 */
	private final ArrayList<AgentJAXB> myAgents = new ArrayList<AgentJAXB>();

	/**
	 * Autopiloted devices list.
	 */
	private final ArrayList<AutoPilotDeviceJAXB> myAutoPilots = new ArrayList<AutoPilotDeviceJAXB>();

	/**
	 * Constructor.
	 * 
	 * @throws JAXBException
	 */
	private JAXBtoJava() throws JAXBException {
		parseEquipment();
		parseStatusEffects();
		parseQualities();
		parseLivingBeings();
		parseMatrixBeings();
	}

	/**
	 * @return instance.
	 * @throws JAXBException
	 */
	public static JAXBtoJava getInstance() {
		if (myInstance == null) {
			try {
				myInstance = new JAXBtoJava();
			} catch (final JAXBException e) {
				e.printStackTrace();
			}
		}

		return myInstance;
	}

	/**
	 * Parse qualities.
	 * 
	 * @throws JAXBException
	 */
	private void parseQualities() throws JAXBException {
		final File folder = new File(getClass().getClassLoader()
				.getResource(QUALITY_FOLDER).getFile());
		final JAXBContext context = JAXBContext.newInstance(QualityJAXB.class);
		final Unmarshaller unmarshaller = context.createUnmarshaller();
		for (final File file : folder.listFiles()) {
			final QualityJAXB object = (QualityJAXB) unmarshaller
					.unmarshal(file);

			myQualities.put(object.getId(), object);
		}
	}

	/**
	 * Parse status effects.
	 * 
	 * @throws JAXBException
	 */
	private void parseStatusEffects() throws JAXBException {
		final File folder = new File(getClass().getClassLoader()
				.getResource(STATUS_EFFECT_FOLDER).getFile());
		final JAXBContext context = JAXBContext
				.newInstance(StatusEffectJAXB.class);
		final Unmarshaller unmarshaller = context.createUnmarshaller();
		for (final File file : folder.listFiles()) {
			final StatusEffectJAXB object = (StatusEffectJAXB) unmarshaller
					.unmarshal(file);

			myStatusEffects.put(object.getId(), object);
		}
	}

	/**
	 * Parse matrix beings.
	 * 
	 * @throws JAXBException
	 */
	private void parseMatrixBeings() throws JAXBException {
		parseAgents();
		parseAutoPilots();
	}

	/**
	 * Parse autopiloted devices.
	 * 
	 * @throws JAXBException
	 */
	private void parseAutoPilots() throws JAXBException {
		final File folder = new File(getClass().getClassLoader()
				.getResource(AUTO_PILOT_FOLDER).getFile());
		final JAXBContext context = JAXBContext
				.newInstance(AutoPilotDeviceJAXB.class);
		final Unmarshaller unmarshaller = context.createUnmarshaller();
		for (final File file : folder.listFiles()) {
			final AutoPilotDeviceJAXB object = (AutoPilotDeviceJAXB) unmarshaller
					.unmarshal(file);

			myAutoPilots.add(object);
		}
	}

	/**
	 * Parse agents.
	 * 
	 * @throws JAXBException
	 */
	private void parseAgents() throws JAXBException {
		final File folder = new File(getClass().getClassLoader()
				.getResource(AGENT_FOLDER).getFile());
		final JAXBContext context = JAXBContext.newInstance(AgentJAXB.class);
		final Unmarshaller unmarshaller = context.createUnmarshaller();
		for (final File file : folder.listFiles()) {
			final AgentJAXB object = (AgentJAXB) unmarshaller.unmarshal(file);

			myAgents.add(object);
		}
	}

	/**
	 * Parse living beings.
	 * 
	 * @throws JAXBException
	 */
	private void parseLivingBeings() throws JAXBException {
		parseCharacters();
		parseHackers();
		parseSpirits();
		parseTechnomancers();
	}

	/**
	 * Parse technomancers.
	 * 
	 * @throws JAXBException
	 */
	private void parseTechnomancers() throws JAXBException {
		final File folder = new File(getClass().getClassLoader()
				.getResource(TECHNO_FOLDER).getFile());
		final JAXBContext context = JAXBContext
				.newInstance(TechnomancerJAXB.class);
		final Unmarshaller unmarshaller = context.createUnmarshaller();
		for (final File file : folder.listFiles()) {
			final TechnomancerJAXB object = (TechnomancerJAXB) unmarshaller
					.unmarshal(file);

			myTechnomancers.add(object);
		}
	}

	/**
	 * Parse spirits.
	 * 
	 * @throws JAXBException
	 */
	private void parseSpirits() throws JAXBException {
		final File folder = new File(getClass().getClassLoader()
				.getResource(SPIRIT_FOLDER).getFile());
		final JAXBContext context = JAXBContext.newInstance(SpiritJAXB.class);
		final Unmarshaller unmarshaller = context.createUnmarshaller();
		for (final File file : folder.listFiles()) {
			final SpiritJAXB object = (SpiritJAXB) unmarshaller.unmarshal(file);

			mySpirits.add(object);
		}
	}

	/**
	 * Parse hackers.
	 * 
	 * @throws JAXBException
	 */
	private void parseHackers() throws JAXBException {
		final File folder = new File(getClass().getClassLoader()
				.getResource(HACKER_FOLDER).getFile());
		final JAXBContext context = JAXBContext.newInstance(HackerJAXB.class);
		final Unmarshaller unmarshaller = context.createUnmarshaller();
		for (final File file : folder.listFiles()) {
			final HackerJAXB object = (HackerJAXB) unmarshaller.unmarshal(file);

			myHackers.add(object);
		}
	}

	/**
	 * Parse characters.
	 * 
	 * @throws JAXBException
	 */
	private void parseCharacters() throws JAXBException {
		final File folder = new File(getClass().getClassLoader()
				.getResource(CHARACTER_FOLDER).getFile());
		final JAXBContext context = JAXBContext
				.newInstance(CharacterJAXB.class);
		final Unmarshaller unmarshaller = context.createUnmarshaller();
		for (final File file : folder.listFiles()) {
			final CharacterJAXB object = (CharacterJAXB) unmarshaller
					.unmarshal(file);

			myCharacters.add(object);
		}
	}

	/**
	 * Parse equipment.
	 * 
	 * @throws JAXBException
	 */
	private void parseEquipment() throws JAXBException {
		parseArmors();
		parseBasicItems();
		parseChargedMeleeWeapons();
		parseDevices();
		parseFirearms();
		parseMeleeWeapons();
		parseProjectileWeapons();
		parseTimedItems();
	}

	/**
	 * Parse timed items.
	 * 
	 * @throws JAXBException
	 */
	private void parseTimedItems() throws JAXBException {
		final File folder = new File(getClass().getClassLoader()
				.getResource(TIMED_ITEM_FOLDER).getFile());
		final JAXBContext context = JAXBContext
				.newInstance(TimedItemJAXB.class);
		final Unmarshaller unmarshaller = context.createUnmarshaller();
		for (final File file : folder.listFiles()) {
			final TimedItemJAXB object = (TimedItemJAXB) unmarshaller
					.unmarshal(file);

			myEquipment.put(object.getId(), object);
		}
	}

	/**
	 * Parse projectile weapons.
	 * 
	 * @throws JAXBException
	 */
	private void parseProjectileWeapons() throws JAXBException {
		final File folder = new File(getClass().getClassLoader()
				.getResource(PROJECTILE_FOLDER).getFile());
		final JAXBContext context = JAXBContext
				.newInstance(ProjectileWeaponJAXB.class);
		final Unmarshaller unmarshaller = context.createUnmarshaller();
		for (final File file : folder.listFiles()) {
			final ProjectileWeaponJAXB object = (ProjectileWeaponJAXB) unmarshaller
					.unmarshal(file);

			myEquipment.put(object.getId(), object);
		}
	}

	/**
	 * Parse melee weapons.
	 * 
	 * @throws JAXBException
	 */
	private void parseMeleeWeapons() throws JAXBException {
		final File folder = new File(getClass().getClassLoader()
				.getResource(MELEE_WEAPON_FOLDER).getFile());
		final JAXBContext context = JAXBContext
				.newInstance(MeleeWeaponJAXB.class);
		final Unmarshaller unmarshaller = context.createUnmarshaller();
		for (final File file : folder.listFiles()) {
			final MeleeWeaponJAXB object = (MeleeWeaponJAXB) unmarshaller
					.unmarshal(file);

			myEquipment.put(object.getId(), object);
		}
	}

	/**
	 * Parse firearms.
	 * 
	 * @throws JAXBException
	 */
	private void parseFirearms() throws JAXBException {
		final File folder = new File(getClass().getClassLoader()
				.getResource(FIREARM_FOLDER).getFile());
		final JAXBContext context = JAXBContext
				.newInstance(FirearmWeaponJAXB.class);
		final Unmarshaller unmarshaller = context.createUnmarshaller();
		for (final File file : folder.listFiles()) {
			final FirearmWeaponJAXB object = (FirearmWeaponJAXB) unmarshaller
					.unmarshal(file);

			myEquipment.put(object.getId(), object);
		}
	}

	/**
	 * Parse devices.
	 * 
	 * @throws JAXBException
	 */
	private void parseDevices() throws JAXBException {
		final File folder = new File(getClass().getClassLoader()
				.getResource(DEVICE_FOLDER).getFile());
		final JAXBContext context = JAXBContext.newInstance(DeviceJAXB.class);
		final Unmarshaller unmarshaller = context.createUnmarshaller();
		for (final File file : folder.listFiles()) {
			final DeviceJAXB object = (DeviceJAXB) unmarshaller.unmarshal(file);

			myEquipment.put(object.getId(), object);
		}
	}

	/**
	 * Parse charged melee weapons.
	 * 
	 * @throws JAXBException
	 */
	private void parseChargedMeleeWeapons() throws JAXBException {
		final File folder = new File(getClass().getClassLoader()
				.getResource(CHARGED_MELEE_WEAPON_FOLDER).getFile());
		final JAXBContext context = JAXBContext
				.newInstance(ChargedMeleeWeaponJAXB.class);
		final Unmarshaller unmarshaller = context.createUnmarshaller();
		for (final File file : folder.listFiles()) {
			final ChargedMeleeWeaponJAXB object = (ChargedMeleeWeaponJAXB) unmarshaller
					.unmarshal(file);

			myEquipment.put(object.getId(), object);
		}
	}

	/**
	 * Parse basic items.
	 * 
	 * @throws JAXBException
	 */
	private void parseBasicItems() throws JAXBException {
		final File folder = new File(getClass().getClassLoader()
				.getResource(BASIC_ITEM_FOLDER).getFile());
		final JAXBContext context = JAXBContext
				.newInstance(BasicItemJAXB.class);
		final Unmarshaller unmarshaller = context.createUnmarshaller();
		for (final File file : folder.listFiles()) {
			final BasicItemJAXB object = (BasicItemJAXB) unmarshaller
					.unmarshal(file);

			myEquipment.put(object.getId(), object);
		}
	}

	/**
	 * Parse armors.
	 * 
	 * @throws JAXBException
	 */
	private void parseArmors() throws JAXBException {
		final File folder = new File(getClass().getClassLoader()
				.getResource(ARMOR_FOLDER).getFile());
		final JAXBContext context = JAXBContext.newInstance(ArmorJAXB.class);
		final Unmarshaller unmarshaller = context.createUnmarshaller();
		for (final File file : folder.listFiles()) {
			final ArmorJAXB object = (ArmorJAXB) unmarshaller.unmarshal(file);

			myEquipment.put(object.getId(), object);
		}
	}

	/**
	 * Get some Shadowrun item from the Equipment JAXB.
	 * 
	 * @param iEquipment
	 *            EquipmentJAXB.
	 * @return some item/equipment, or null.
	 */
	public AbstractShadowrunItem getItem(final EquipmentJAXB iEquipment) {
		if (iEquipment instanceof ArmorJAXB) {
			return getArmor((ArmorJAXB) iEquipment);
		} else if (iEquipment instanceof BasicItemJAXB) {
			return getBasicItem((BasicItemJAXB) iEquipment);
		} else if (iEquipment instanceof WeaponJAXB) {
			return getWeapon((WeaponJAXB) iEquipment);
		} else {
			// add any additional extensions of Equipment here
			return null;
		}
	}

	/**
	 * Get some item from the JAXB version ID.
	 * 
	 * @param iID
	 *            JAXB version of item.
	 * @return item. May be null.
	 */
	public AbstractShadowrunItem getItemByID(final String iID) {
		final EquipmentJAXB object = myEquipment.get(iID);
		if (object != null) {
			return getItem(object);
		} else {
			return null;
		}
	}

	/**
	 * Get some Shadowrun items from the Equipment JAXBs. Ignore nulls on
	 * conversion.
	 * 
	 * @param iEquipments
	 *            Equipment JAXBs.
	 * @return some items/equipments.
	 */
	public ArrayList<AbstractShadowrunItem> getItems(
			final ArrayList<EquipmentJAXB> iEquipments) {
		final ArrayList<AbstractShadowrunItem> oResult = new ArrayList<AbstractShadowrunItem>();

		for (final EquipmentJAXB equipment : iEquipments) {
			final AbstractShadowrunItem convertedItem = getItem(equipment);
			if (convertedItem != null) {
				oResult.add(convertedItem);
			}
		}

		return oResult;
	}

	/**
	 * Get some items from the JAXB version IDs. Ignore nulls on conversion.
	 * 
	 * @param iIDs
	 *            JAXB version IDs of items.
	 * @return items.
	 */
	public ArrayList<AbstractShadowrunItem> getItemsByID(
			final ArrayList<String> iIDs) {
		final ArrayList<AbstractShadowrunItem> oResult = new ArrayList<AbstractShadowrunItem>();

		for (final String id : iIDs) {
			final AbstractShadowrunItem convertedItem = getItemByID(id);
			if (convertedItem != null) {
				oResult.add(convertedItem);
			}
		}

		return oResult;
	}

	/**
	 * Get some Shadowrun weapon from the Weapon JAXB.
	 * 
	 * @param iWeapon
	 *            WeaponJAXB.
	 * @return some weapon, or null.
	 */
	public AbstractShadowrunWeapon getWeapon(final WeaponJAXB iWeapon) {
		if (iWeapon instanceof RangedWeaponJAXB) {
			return getRangedWeapon((RangedWeaponJAXB) iWeapon);
		} else if (iWeapon instanceof MeleeWeaponJAXB) {
			return getMeleeWeapon((MeleeWeaponJAXB) iWeapon);
		} else {
			// add any additional extensions of weapon here
			return null;
		}
	}

	/**
	 * Get some Shadowrun melee weapon from the Melee Weapon JAXB.
	 * 
	 * @param iWeapon
	 *            melee weapon JAXB.
	 * @return some melee weapon.
	 */
	public AbstractMeleeWeapon getMeleeWeapon(final MeleeWeaponJAXB iWeapon) {
		if (iWeapon instanceof ChargedMeleeWeaponJAXB) {
			return getChargedMeleeWeapon((ChargedMeleeWeaponJAXB) iWeapon);
		} else {
			// add any additional extensions of melee weapon here
			return new MeleeWeapon(iWeapon.getName(), iWeapon.getBenefits(),
					iWeapon.getAccuracy(), iWeapon.getDamageValue(),
					iWeapon.getArmorPiercing(), iWeapon.getDamageElement(),
					iWeapon.getReach());
		}
	}

	/**
	 * Get some Shadowrun charged melee weapon from the Charged Melee Weapon
	 * JAXB. Use default "spare clips" of 0.
	 * 
	 * @param iWeapon
	 *            charged melee weapon JAXB.
	 * @return some charged melee weapon.
	 */
	public ChargedMeleeWeapon getChargedMeleeWeapon(
			final ChargedMeleeWeaponJAXB iWeapon) {
		return getChargedMeleeWeapon(iWeapon,
				DEFAULT_CHARGED_MELEE_WEAPON_SPARE_CLIPS);
	}

	/**
	 * Get some Shadowrun charged melee weapon from the Charged Melee Weapon
	 * JAXB.
	 * 
	 * @param iWeapon
	 *            charged melee weapon JAXB.
	 * @param iSpareClips
	 *            spare clips to use.
	 * @return some charged melee weapon.
	 */
	public ChargedMeleeWeapon getChargedMeleeWeapon(
			final ChargedMeleeWeaponJAXB iWeapon, final int iSpareClips) {
		return new ChargedMeleeWeapon(iWeapon.getName(), iWeapon.getBenefits(),
				iWeapon.getAccuracy(), iWeapon.getDamageValue(),
				iWeapon.getArmorPiercing(), iWeapon.getDamageElement(),
				iWeapon.getReach(), iWeapon.getClipCapacity(), iSpareClips);
	}

	/**
	 * Get some Shadowrun ammo-fed weapon from the Ranged Weapon JAXB.
	 * 
	 * @param iWeapon
	 *            ranged weapon JAXB.
	 * @return some ammo-fed weapon, or null.
	 */
	public AbstractAmmoFedWeapon getRangedWeapon(final RangedWeaponJAXB iWeapon) {
		if (iWeapon instanceof ProjectileWeaponJAXB) {
			return getProjectileWeapon((ProjectileWeaponJAXB) iWeapon);
		} else if (iWeapon instanceof FirearmWeaponJAXB) {
			return getFirearmWeapon((FirearmWeaponJAXB) iWeapon);
		} else {
			// add any additional extensions of ranged weapon here
			return null;
		}
	}

	/**
	 * Get some Shadowrun firearm from the Firearm Weapon JAXB. Use default
	 * spare clips of 3.
	 * 
	 * @param iWeapon
	 *            firearm weapon JAXB.
	 * @return some firearm weapon.
	 */
	public FirearmWeapon getFirearmWeapon(final FirearmWeaponJAXB iWeapon) {
		return getFirearmWeapon(iWeapon, DEFAULT_FIREARM_SPARE_CLIPS);
	}

	/**
	 * Get some Shadowrun firearm from the Firearm Weapon JAXB.
	 * 
	 * @param iWeapon
	 *            firearm weapon JAXB.
	 * @param iSpareClips
	 *            spare clips.
	 * @return some firearm weapon.
	 */
	public FirearmWeapon getFirearmWeapon(final FirearmWeaponJAXB iWeapon,
			final int iSpareClips) {
		return new FirearmWeapon(iWeapon.getName(), iWeapon.getBenefits(),
				iWeapon.getAccuracy(), iWeapon.getDamageValue(),
				iWeapon.getArmorPiercing(), iWeapon.getDamageElement(),
				iWeapon.getClipCapacity(), iSpareClips,
				iWeapon.getRangedWeaponTypes(), iWeapon.getFiringModes(),
				iWeapon.getRecoilCompensation());
	}

	/**
	 * Get some Shadowrun projectile weapon from the Projectile Weapon JAXB. Use
	 * default "spare clips" of 4.
	 * 
	 * @param iWeapon
	 *            projectile weapon JAXB.
	 * @return some projectile weapon.
	 */
	public ProjectileWeapon getProjectileWeapon(
			final ProjectileWeaponJAXB iWeapon) {
		return getProjectileWeapon(iWeapon,
				DEFAULT_PROJECTILE_WEAPON_SPARE_CLIPS);
	}

	/**
	 * Get some Shadowrun projectile weapon from the Projectile Weapon JAXB.
	 * 
	 * @param iWeapon
	 *            projectile weapon JAXB.
	 * @param iSpareClips
	 *            "spare clips" to use.
	 * @return some projectile weapon.
	 */
	public ProjectileWeapon getProjectileWeapon(
			final ProjectileWeaponJAXB iWeapon, final int iSpareClips) {
		return new ProjectileWeapon(iWeapon.getName(), iWeapon.getBenefits(),
				iWeapon.getAccuracy(), iWeapon.getDamageValue(),
				iWeapon.getArmorPiercing(), iWeapon.getDamageElement(),
				iSpareClips, iWeapon.getRangedWeaponTypes());
	}

	/**
	 * Get an armor class.
	 * 
	 * @param iArmor
	 *            JAXB armor.
	 * @return Java armor.
	 */
	public Armor getArmor(final ArmorJAXB iArmor) {
		return new Armor(iArmor.getName(), iArmor.getArmor(),
				iArmor.getBenefits());
	}

	/**
	 * Get some Shadowrun item from the Basic Item JAXB.
	 * 
	 * @param iItem
	 *            BasicItemJAXB.
	 * @return device, timed item, or basic item.
	 */
	public AbstractShadowrunItem getBasicItem(final BasicItemJAXB iItem) {
		if (iItem instanceof DeviceJAXB) {
			return getDevice((DeviceJAXB) iItem);
		} else if (iItem instanceof TimedItemJAXB) {
			return getTimedItem((TimedItemJAXB) iItem);
		} else {
			// add any additional extensions of BasicItem here
			return new ShadowrunBasicItem(iItem.getName(), iItem.getBody(),
					iItem.getArmor());
		}
	}

	/**
	 * Get some timed item from the JAXB version.
	 * 
	 * @param iTimedItem
	 *            JAXB version of timed item.
	 * @return timed item.
	 */
	public AbstractShadowrunItem getTimedItem(final TimedItemJAXB iTimedItem) {
		return new TimedItem(iTimedItem.getName(), iTimedItem.getBody(),
				iTimedItem.getArmor(), 0, iTimedItem.getEffect());
	}

	/**
	 * Get some device from the JAXB version.
	 * 
	 * @param iDevice
	 *            JAXB version of device.
	 * @return device.
	 */
	public Device getDevice(final DeviceJAXB iDevice) {
		return new Device(iDevice.getName(), iDevice.getBody(),
				iDevice.getArmor(), iDevice.getRating());
	}

	/**
	 * Get some ability from the JAXB version.
	 * 
	 * @param iAbility
	 *            JAXB version of ability.
	 * @return ability, or null.
	 */
	public AbstractAbility getAbility(final AbilityJAXB iAbility) {
		if (iAbility instanceof SkillJAXB) {
			return getSkill((SkillJAXB) iAbility);
		} else if (iAbility instanceof SpellJAXB) {
			return getSpell((SpellJAXB) iAbility);
		} else {
			// add additional extensions of Ability JAXB here
			return null;
		}
	}

	/**
	 * Convert JAXB abilities to Abstract Abilities. Ignore abilities that
	 * cannot be converted.
	 * 
	 * @param iAbilities
	 *            JAXB abilities.
	 * @return list of abilities.
	 */
	public ArrayList<AbstractAbility> getAbilities(
			final ArrayList<? extends AbilityJAXB> iAbilities) {
		final ArrayList<AbstractAbility> oResult = new ArrayList<AbstractAbility>();

		for (final AbilityJAXB ability : iAbilities) {
			final AbstractAbility convertedAbility = getAbility(ability);
			if (convertedAbility != null) {
				oResult.add(convertedAbility);
			}
		}

		return oResult;
	}

	/**
	 * Convert character's JAXB abilities to Abstract Abilities. Ignore
	 * abilities that cannot be converted.
	 * 
	 * @param iAbilities
	 *            JAXB abilities.
	 * @return list of abilities.
	 */
	public <C extends LivingBeingJAXB> ArrayList<AbstractAbility> getAbilities(
			final C iCharacter) {
		final ArrayList<AbstractAbility> oResult = new ArrayList<AbstractAbility>();

		oResult.addAll(getAbilities(iCharacter.getSkills()));
		oResult.addAll(getAbilities(iCharacter.getSpells()));

		return oResult;
	}

	/**
	 * Convert matrix being's JAXB abilities to Abstract Abilities. Ignore
	 * abilities that cannot be converted.
	 * 
	 * @param iAbilities
	 *            JAXB abilities.
	 * @return list of abilities.
	 */
	public <M extends MatrixBeingJAXB> ArrayList<AbstractAbility> getAbilities(
			final M iMatrixBeing) {
		final ArrayList<AbstractAbility> oResult = new ArrayList<AbstractAbility>();

		oResult.addAll(getAbilities(iMatrixBeing.getSkills()));
		oResult.addAll(getAbilities(iMatrixBeing.getSpells()));

		return oResult;
	}

	/**
	 * Get some skill from the JAXB version.
	 * 
	 * @param iSkill
	 *            JAXB version of skill.
	 * @return skill.
	 */
	public Skill getSkill(final SkillJAXB iSkill) {
		return new Skill(iSkill.getFullDesc(), iSkill.getLevel());
	}

	/**
	 * Get some spell from the JAXB version.
	 * 
	 * @param iSpell
	 *            JAXB version of spell.
	 * @return spell.
	 */
	public Spell getSpell(final SpellJAXB iSpell) {
		return new Spell(iSpell.getFullDesc(), iSpell.getBriefDesc());
	}

	/**
	 * Get some quality from the JAXB version.
	 * 
	 * @param iQuality
	 *            JAXB version of quality.
	 * @return quality.
	 */
	public QualityNote getQuality(final QualityJAXB iQuality) {
		return new QualityNote(iQuality.getFullDesc(), iQuality.getBriefDesc(),
				iQuality.isCombatQuality(), iQuality.getQualityType());
	}

	/**
	 * Get some quality from the JAXB version ID.
	 * 
	 * @param iID
	 *            JAXB version of quality.
	 * @return quality. May be null.
	 */
	public QualityNote getQualityByID(final String iID) {
		final QualityJAXB object = myQualities.get(iID);
		if (object != null) {
			return getQuality(object);
		} else {
			return null;
		}
	}

	/**
	 * Get some qualities from the JAXB version. Ignore nulls on conversion.
	 * 
	 * @param iQualities
	 *            JAXB version of qualities.
	 * @return qualities.
	 */
	public ArrayList<QualityNote> getQualities(
			final ArrayList<QualityJAXB> iQualities) {
		final ArrayList<QualityNote> oResult = new ArrayList<QualityNote>();

		for (final QualityJAXB quality : iQualities) {
			final QualityNote convertedQuality = getQuality(quality);
			if (convertedQuality != null) {
				oResult.add(convertedQuality);
			}
		}

		return oResult;
	}

	/**
	 * Get some qualities from the JAXB version IDs. Ignore nulls on conversion.
	 * 
	 * @param iIDs
	 *            JAXB version IDs of qualities.
	 * @return qualities.
	 */
	public ArrayList<QualityNote> getQualitiesByID(final ArrayList<String> iIDs) {
		final ArrayList<QualityNote> oResult = new ArrayList<QualityNote>();

		for (final String id : iIDs) {
			final QualityNote convertedQuality = getQualityByID(id);
			if (convertedQuality != null) {
				oResult.add(convertedQuality);
			}
		}

		return oResult;
	}

	/**
	 * Get some status effect from the JAXB version.
	 * 
	 * @param iStatusEffect
	 *            JAXB version of status effect.
	 * @return status effect.
	 */
	public StatusEffectNote getStatusEffect(final StatusEffectJAXB iStatusEffect) {
		return new StatusEffectNote(iStatusEffect.getFullDesc(),
				iStatusEffect.getBriefDesc(),
				iStatusEffect.isCombatStatusEffect(),
				iStatusEffect.getStatusEffectType());
	}

	/**
	 * Get some status effect from the JAXB version ID.
	 * 
	 * @param iID
	 *            JAXB version of status effect.
	 * @return status effect. May be null.
	 */
	public StatusEffectNote getStatusEffectByID(final String iID) {
		final StatusEffectJAXB object = myStatusEffects.get(iID);
		if (object != null) {
			return getStatusEffect(object);
		} else {
			return null;
		}
	}

	/**
	 * Get some status effects from the JAXB version. Ignore nulls on
	 * conversion.
	 * 
	 * @param iStatusEffects
	 *            JAXB version of status effects.
	 * @return status effects.
	 */
	public ArrayList<StatusEffectNote> getStatusEffects(
			final ArrayList<StatusEffectJAXB> iStatusEffects) {
		final ArrayList<StatusEffectNote> oResult = new ArrayList<StatusEffectNote>();

		for (final StatusEffectJAXB statusEffect : iStatusEffects) {
			final StatusEffectNote convertedStatusEffect = getStatusEffect(statusEffect);
			if (convertedStatusEffect != null) {
				oResult.add(convertedStatusEffect);
			}
		}

		return oResult;
	}

	/**
	 * Get some status effects from the JAXB version IDs. Ignore nulls on
	 * conversion.
	 * 
	 * @param iIDs
	 *            JAXB version IDs of status effects.
	 * @return status effects.
	 */
	public ArrayList<StatusEffectNote> getStatusEffectsByID(
			final ArrayList<String> iIDs) {
		final ArrayList<StatusEffectNote> oResult = new ArrayList<StatusEffectNote>();

		for (final String id : iIDs) {
			final StatusEffectNote convertedStatusEffect = getStatusEffectByID(id);
			if (convertedStatusEffect != null) {
				oResult.add(convertedStatusEffect);
			}
		}

		return oResult;
	}

	/**
	 * Get some agent from the JAXB version.
	 * 
	 * @param iAgent
	 *            JAXB version of agent.
	 * @return agent.
	 */
	public NonPlayerAgent getAgent(final AgentJAXB iAgent) {
		return new NonPlayerAgent(iAgent.getName(), iAgent.getRating(),
				iAgent.getAttack(), iAgent.getSleaze(),
				iAgent.getDataProcessing(), iAgent.getFirewall(),
				getAbilities(iAgent));
	}

	/**
	 * Get some autopilot device from JAXB version.
	 * 
	 * @param iAutoPilotDevice
	 *            JAXB version of autopilot device.
	 * @return autopilot device.
	 */
	public NonPlayerAutoPilotDevice getAutoPilotDevice(
			final AutoPilotDeviceJAXB iAutoPilotDevice) {
		return new NonPlayerAutoPilotDevice(iAutoPilotDevice.getName(),
				iAutoPilotDevice.getBody(), iAutoPilotDevice.getArmor(),
				iAutoPilotDevice.getRating(),
				getItemsByID(iAutoPilotDevice.getInventory()),
				getAbilities(iAutoPilotDevice),
				iAutoPilotDevice.getDataProcessing(),
				iAutoPilotDevice.getFirewall(), iAutoPilotDevice.getPilot());
	}

	/**
	 * Get some NPC spirit from JAXB version. Use default services of 4.
	 * 
	 * @param iSpirit
	 *            JAXB version of spirit.
	 * @return spirit.
	 */
	public NonPlayerSpirit getSpirit(final SpiritJAXB iSpirit) {
		return getSpirit(iSpirit, DEFAULT_SPIRIT_SERVICES);
	}

	/**
	 * Get some NPC spirit from JAXB version.
	 * 
	 * @param iSpirit
	 *            JAXB version of spirit.
	 * @param iServices
	 *            services for the spirit.
	 * @return spirit.
	 */
	public NonPlayerSpirit getSpirit(final SpiritJAXB iSpirit,
			final int iServices) {
		return new NonPlayerSpirit(iSpirit.getName(), iSpirit.getSpecial(),
				iSpirit.getBody(), iSpirit.getWillpower(),
				getStatusEffectsByID(iSpirit.getStatusEffects()),
				getItemsByID(iSpirit.getInventory()),
				getQualitiesByID(iSpirit.getQualities()), iServices,
				iSpirit.getAgility(), iSpirit.getReaction(),
				iSpirit.getStrength(), iSpirit.getLogic(),
				iSpirit.getIntuition(), iSpirit.getCharisma(),
				iSpirit.getInitDice(), getAbilities(iSpirit));
	}

	/**
	 * Get some character from JAXB version.
	 * 
	 * @param iCharacter
	 *            JAXB version of character.
	 * @return character.
	 */
	public NonPlayerCharacter getCharacter(final CharacterJAXB iCharacter) {
		if (iCharacter instanceof HackerJAXB) {
			return getHacker((HackerJAXB) iCharacter);
		} else if (iCharacter instanceof TechnomancerJAXB) {
			return getTechnomancer((TechnomancerJAXB) iCharacter);
		} else {
			// add additional extensions of Character JAXB here
			return new NonPlayerCharacter(iCharacter.getName(),
					iCharacter.getEssence(), iCharacter.getBody(),
					iCharacter.getWillpower(), iCharacter.getSpecial(),
					getStatusEffectsByID(iCharacter.getStatusEffects()),
					getItemsByID(iCharacter.getInventory()),
					getQualitiesByID(iCharacter.getQualities()),
					iCharacter.getAgility(), iCharacter.getReaction(),
					iCharacter.getStrength(), iCharacter.getLogic(),
					iCharacter.getIntuition(), iCharacter.getCharisma(),
					iCharacter.getInitDice(), getAbilities(iCharacter));
		}
	}

	/**
	 * Get some technomancer from JAXB version.
	 * 
	 * @param iTechnomancer
	 *            JAXB version of technomancer.
	 * @return technomancer.
	 */
	public NonPlayerTechnomancer getTechnomancer(
			final TechnomancerJAXB iTechnomancer) {
		return new NonPlayerTechnomancer(iTechnomancer.getName(),
				iTechnomancer.getEssence(), iTechnomancer.getBody(),
				iTechnomancer.getWillpower(), iTechnomancer.getSpecial(),
				getStatusEffectsByID(iTechnomancer.getStatusEffects()),
				getItemsByID(iTechnomancer.getInventory()),
				getQualitiesByID(iTechnomancer.getQualities()),
				iTechnomancer.getAgility(), iTechnomancer.getReaction(),
				iTechnomancer.getStrength(), iTechnomancer.getLogic(),
				iTechnomancer.getIntuition(), iTechnomancer.getCharisma(),
				iTechnomancer.getInitDice(), getAbilities(iTechnomancer));
	}

	/**
	 * Get some hacker from JAXB version.
	 * 
	 * @param iHacker
	 *            JAXB version of hacker.
	 * @return hacker.
	 */
	public NonPlayerHacker getHacker(final HackerJAXB iHacker) {
		return new NonPlayerHacker(iHacker.getName(), iHacker.getEssence(),
				iHacker.getBody(), iHacker.getWillpower(),
				iHacker.getSpecial(),
				getStatusEffectsByID(iHacker.getStatusEffects()),
				getItemsByID(iHacker.getInventory()),
				getQualitiesByID(iHacker.getQualities()), iHacker.getAgility(),
				iHacker.getReaction(), iHacker.getStrength(),
				iHacker.getLogic(), iHacker.getIntuition(),
				iHacker.getCharisma(), iHacker.getInitDice(),
				getAbilities(iHacker), iHacker.getRating(),
				iHacker.getAttack(), iHacker.getSleaze(),
				iHacker.getDataProcessing(), iHacker.getFirewall());
	}

	/**
	 * @return all loaded characters.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<CharacterJAXB> getCharacters() {
		return (ArrayList<CharacterJAXB>) myCharacters.clone();
	}

	/**
	 * @return all loaded hackers.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<HackerJAXB> getHackers() {
		return (ArrayList<HackerJAXB>) myHackers.clone();
	}

	/**
	 * @return all loaded spirits.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<SpiritJAXB> getSpirits() {
		return (ArrayList<SpiritJAXB>) mySpirits.clone();
	}

	/**
	 * @return all loaded technomancers.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<TechnomancerJAXB> getTechnomancers() {
		return (ArrayList<TechnomancerJAXB>) myTechnomancers.clone();
	}

	/**
	 * @return all loaded agents.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<AgentJAXB> getAgents() {
		return (ArrayList<AgentJAXB>) myAgents.clone();
	}

	/**
	 * @return all loaded autopiloted devices.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<AutoPilotDeviceJAXB> getAutoPilots() {
		return (ArrayList<AutoPilotDeviceJAXB>) myAutoPilots.clone();
	}
}
