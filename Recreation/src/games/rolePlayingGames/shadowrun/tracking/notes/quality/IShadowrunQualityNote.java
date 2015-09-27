package games.rolePlayingGames.shadowrun.tracking.notes.quality;

import games.rolePlayingGames.tracking.note.status.trait.ITraitNote;

/**
 * A quality in Shadowrun.
 * 
 * @author Andrew
 */
public interface IShadowrunQualityNote extends ITraitNote {

	/**
	 * @return true if the quality is related to combat.
	 */
	boolean isCombatQuality();

	/**
	 * @return quality type.
	 */
	QualityType getQualityType();

}
