/*
 * This file is part of aion-unique <aion-unique.org>.
 *
 *  aion-unique is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  aion-unique is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with aion-unique.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.aionemu.gameserver.world.zone;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * @author ATracer
 *
 */
@XmlType(name = "ZoneName")
@XmlEnum
public enum ZoneName
{
	//Poeta
	DEFORESTED_AREA,
	WORG_RUN,
	DAMINU_FOREST,
	MELPONEHS_CAMPSITE,
	AGERS_FARM,
	AKARIOS_VILLAGE,
	TIMOLIA_MINE,
	KABARAH_STRIP_MINE,
	FEIRAS_DOCK,
	AKARIOS_PLAINS,
	KALESS_FARM,
	CLIONA_LAKE,
	NYMPHS_POND,
	AGARIC_SPORE_ROAD,
	ITEMUSE_Q1006,
	Q1123,
	//Ishalgen
	DUBARO_VINE_CANYON,
	ANTUROON_SENTRY_POST,
	SAP_FARM,
	ISHALGEN_PRISON_CAMP,
	ODELLA_PLANTATION,
	ALDELLE_HILL,
	MUNIHELE_FOREST,
	NEGIS_DOCK,
	THE_FORSAKEN_HOLLOW,
	ANTUROON_COAST,
	ISHALGEN_SENTRY_POST,
	LAKE_TUNAPRE,
	ALDELLE_VILLAGE,
	EYVINDR_ANCHORAGE,
	KARDS_CAMPSITE,
	ALDELLE_BASIN,
	GUHEITUNS_TENT,
	ANTUROON_CROSSING,
	DARU_SPRING,
	HATATAS_HIDEOUT,
	// sanctum
	COLISEUM,
	OUTER_DOCK,
	DIONYSIA_TAVERN,
	//COLISEUM, 2 coliseum maps
	LIBRARY_OF_THE_SAGES,
	SANCTUM_GATE,
	LYCEUM,
	HALL_OF_PROSPERITY,
	DIVINE_ROAD,
	PROTECTORS_HALL,
	AIRSHIP_DOCK,
	ELYOS_SQUARE,
	STR_SZ_LC1_SUB_H,
	ARTISANS_HALL,
	SKY_GARDEN,
	TEMPEST_SHIPYARD,
	SKY_CANAL,
	EXALTED_PATH,
	//verteron
	ZUMION_CANYON,
	VERTERON_CITADEL,
	CANTAS_COAST,
	GEOLUS_CAMPSITE,
	TURSIN_OUTPOST,
	TURSIN_TOTEM_POLE,
	KRAKAS_DEN,
	BELBUAS_FARM,
	ODIUM_REFINING_CAULDRON,
	TALUNS_NEST,
	ALTAMIA_RIDGE,
	TURSIN_OUTPOST_ENTRANCE,
	FRILLNECK_WOODS,
	PILGRIMS_RESPITE,
	ALTAR_OF_THE_CRIMSON_DRAGON,
	MIRAJUS_HOLY_GROUND,
	NORTHERN_TOLBAS_FOREST,
	ZUMION_RUINS,
	TOLBAS_VILLAGE,
	ARDUS_SHRINE,
	ROAD_TO_ELTNEN,
	DUKAKI_SETTLEMENT,
	CONTAMINATED_SWAMP,
	DUKAKI_MINE,
	ELLUN_RIVER_MOUTH,
	VERTERON_OBSERVATORY,
	MYSTERIOUS_SHIPWRECK,
	VERTERON_SWAMP,
	SOUTHERN_TOLBAS_FOREST,
	TURSIN_GARRISON,
	Q1012,
	//eltnen
	PUTRID_MIRE,
	DRAKE_HABITAT,
	ELTNEN_OBSERVATORY,
	ERACUS_TEMPLE_CAVERN,
	EASTERN_ERACUS_DESERT,
	MYSTIC_SPRING_OF_LAQUEPIN,
	KAIDAN_MINE,
	MABANGTAHS_HIDEOUT,
	PRELLUS_WASTELAND,
	REFUGEE_CAMP,
	KURIULLU_PASS,
	KYOLA_TEMPLE,
	MANDURI_VILLAGE,
	GEROCH_CALDERA,
	TIGRIC_VILLAGE,
	ELTNEN_FORTRESS,
	MYSTIC_SPRING_OF_ANATHE,
	SATALOCAS_TOMB,
	MANDURI_FOREST,
	MANDURI_STORES,
	ROAD_TO_HEIRON,
	LEPHARIST_CITADEL,
	RUBEREIN_WASTES,
	ERACUS_TEMPLE_CAVERN_ENTRANCE,
	LANKUSIS_PLATEAU,
	OUTPOST_RUINS,
	KURIULLU_MOUNTAIN_OUTPOST,
	WESTERN_ERACUS_DESERT,
	KLAW_NEST,
	GOLDEN_BOUGH_GARRISON,
	KYOLA_TEMPLE_ENTRANCE,
	KAIDAN_HEADQUARTERS,
	CALDERA_OUTPOST,
	DESERT_SCOUT_CAMP,
	ELTNEN_FORTRESS_ENTRANCE,
	AGAIRON_VILLAGE,
	MYSTIC_SPRING_OF_AGAIRON,
	KURIULLU_MOUNTAIN,
	ROAD_TO_VERTERON,
	EIRON_DESERT,
	KLAW_DEN,
	//heiron
	INDRATU_BARRACKS,
	MANOR_CEMETERY,
	NUTE_WARRENS,
	BERONES_RUINED_HOUSE,
	NEW_HEIRON_GATE,
	POISON_EXTRACTION_LAB,
	PRETOR_EXPERIMENT_LAB,
	KISHAR_VILLAGE,
	PATEMA_GEYSER,
	JEIAPARAN_VILLAGE,
	KISHAR_OBSERVATION_POST,
	STENON_BAY,
	TIGRIC_VILLAGE_HEIRON, // same name eltnen
	DRAGONS_BLOOD_CANYON,
	ORTON_FARM,
	DRAKE_FARM,
	CURSED_ANCIENT_TEMPLE,
	PATEMA_RUINS,
	LEPHARIST_RESEARCH_CENTER,
	PUITONEN_BOGS,
	RESEARCH_CENTER_OVERWATCH,
	HEIRON_PASS,
	KLAWTANS_WALLOW,
	GLOOMY_MIRE,
	ISLAND_OF_ETERNITY,
	CONTESTED_EXPANSE,
	MEDEUS_ALTAR,
	BLACK_TEARS_SWAMP,
	DEATHSONG_FOREST,
	VAIZELS_PEAK,
	DESTROYED_GUARD_TOWER,
	ROTRON_EXPERIMENT_LAB,
	ARBOLUS_HAVEN,
	MEDEUS_MANOR_WEST,
	MEDEUS_MANOR_EAST,
	ROAD_TO_ELTNEN_HEIRON, // same name eltnen
	NOLANTIS_RUINS,
	MANDURI_VALLEY,
	THE_STORM_CIRCLE,
	KAKUNAS_NEST,
	CHANGARNERKS_CAMPSITE,
	MUDTHORN_EXPERIMENT_LAB,
	DECAYED_GROVE,
	RUINED_DRAKE_TEMPLE,
	POYA_JUNGLE,
	HEIRONOPOLIS,
	MONITOR_FARM,
	HEIRON_OBSERVATORY,
	KUNPAPA_OUTPOST,
	SENEAS_CAMPSITE,
	GRAY_FOG_MARSHES;
}
