package au.com.mineauz.minigames.managers.language.langkeys;

import org.jetbrains.annotations.NotNull;

public enum MgMenuLangKey implements LangKey {
    MENU_BLOCKDATA_CLICKBLOCK("menu.blockData.clickBlock"),
    MENU_BLOCKDATA_ERROR_INVALID("menu.blockData.error.invalid"),
    MENU_DEFAULTWINNINGTEAM_NAME("menu.defaultWinningTeam.name"),
    MENU_DELETE_RIGHTCLICK("menu.delete.RightClick"),
    MENU_DELETE_SHIFTRIGHTCLICK("menu.delete.ShiftRightClick"),
    MENU_DISPLAYLOADOUT_ALLOWFALLDAMAGE_NAME("menu.displayLoadout.allowFallDamage.name"),
    MENU_DISPLAYLOADOUT_ALLOWHUNGER_NAME("menu.displayLoadout.allowHunger.name"),
    MENU_DISPLAYLOADOUT_ALLOWOFFHAND_NAME("menu.displayLoadout.allowOffhand.name"),
    MENU_DISPLAYLOADOUT_DELETE("menu.displayLoadout.delete"),
    MENU_DISPLAYLOADOUT_DISPLAYINMENU_NAME("menu.displayLoadout.displayInMenu.name"),
    MENU_DISPLAYLOADOUT_DISPLAYNAME_NAME("menu.displayLoadout.displayName.name"),
    MENU_DISPLAYLOADOUT_EFFECTS_NAME("menu.displayLoadout.effects.name"),
    MENU_DISPLAYLOADOUT_ENTERCHAT("menu.displayLoadout.enterChat"),
    MENU_DISPLAYLOADOUT_LOCKARMOR_NAME("menu.displayLoadout.lockArmor.name"),
    MENU_DISPLAYLOADOUT_LOCKINVENTORY_NAME("menu.displayLoadout.lockInventory.name"),
    MENU_DISPLAYLOADOUT_LOCKTOTEAM_NAME("menu.displayLoadout.lockToTeam.name"),
    MENU_DISPLAYLOADOUT_NOTDELETE("menu.displayLoadout.notDelete"),
    MENU_DISPLAYLOADOUT_SAVE_NAME("menu.displayLoadout.save.name"),
    MENU_DISPLAYLOADOUT_SETTINGS_NAME("menu.displayLoadout.settings.name"),
    MENU_DISPLAYLOADOUT_USEPERMISSIONS_DESCRIPTION("menu.displayLoadout.usePermissions.description"),
    MENU_DISPLAYLOADOUT_USEPERMISSIONS_NAME("menu.displayLoadout.usePermissions.name"),
    MENU_DISPLAYLOADOUT_XPLEVEL_DESCRIPTION("menu.displayLoadout.xpLevel.description"),
    MENU_DISPLAYLOADOUT_XPLEVEL_NAME("menu.displayLoadout.xpLevel.name"),
    MENU_EFFECTS_SAVE_NAME("menu.effects.save.name"),
    MENU_FLAGADD_ENTERCHAT("menu.flagAdd.enterChat"),
    MENU_FLAGADD_NAME("menu.flagAdd.name"),
    MENU_HIERARCHY_ENTERCHAT("menu.hierarchy.enterChat"),
    MENU_LOADOUT_SAVE("menu.displayLoadout.save.success"),
    MENU_MONEYREWARD_ITEM_NAME("menu.moneyReward.item.name"),
    MENU_MONEYREWARD_MENU_NAME("menu.moneyReward.menu.name"),
    MENU_PAGE_BACK("menu.page.back"),
    MENU_PAGE_NEXT("menu.page.next"),
    MENU_PAGE_PREVIOUS("menu.page.previous"),
    MENU_PLAYSOUND_MENU_NAME("menu.playSound.menu.name"),
    MENU_PLAYSOUND_PITCH_NAME("menu.playSound.pitch.name"),
    MENU_PLAYSOUND_PRIVATEPLAYBACK_NAME("menu.playSound.privatePlayback.name"),
    MENU_PLAYSOUND_SOUND_NAME("menu.playSound.sound.name"),
    MENU_PLAYSOUND_VOLUME_NAME("menu.playSound.volume.name"),
    MENU_POTIONADD_ENTERCHAT("menu.potionAdd.enterChat"),
    MENU_POTIONADD_ERROR_SYNTAX("menu.potionAdd.error.syntax"),
    MENU_POTIONADD_NAME("menu.potionAdd.name"), // todo unused
    MENU_STRING_ALLOWNULL("menu.string.allowNull"),
    MENU_STRING_ENTERCHAT("menu.string.enterChat"),
    MENU_TEAMADD_NAME("menu.teamAdd.name"),
    MENU_TEAM_AUTOBALANCE("menu.team.autobalance"),
    MENU_TEAM_DISPLAYNAME("menu.team.displayName"),
    MENU_TEAM_MAXPLAYERS("menu.team.maxPlayers"),
    MENU_TEAM_NAMEVISIBILITY_ALWAYSVISIBLE("menu.team.nameVisibility.alwaysVisible"),
    MENU_TEAM_NAMEVISIBILITY_HIDEOTHERTEAM("menu.team.nameVisibility.hideOtherTeam"),
    MENU_TEAM_NAMEVISIBILITY_HIDEOWNTEAM("menu.team.nameVisibility.hideOwnTeam"),
    MENU_TEAM_NAMEVISIBILITY_NAME("menu.team.nameVisibility.name"),
    MENU_TEAM_NAMEVISIBILITY_NEVERVISIBLE("menu.team.nameVisibility.neverVisible"),
    MENU_WHITELIST_ADDMATERIAL_NAME("menu.whitelist.addMaterial.name"),
    MENU_WHITELIST_BLOCK_NAME("menu.whitelist.block.name"),
    MENU_WHITELIST_ENTERCHAT("menu.whitelist.enterChat"),
    MENU_WHITELIST_ERROR_CONTAINS("menu.whitelist.error.contains"),
    MENU_WHITELIST_MODE("menu.whitelist.mode"),
    MENU_FLAG_REMOVED("menu.flag.removed"),
    MENU_NUMBER_ENTERCHAT("menu.number.enterChat"),
    MENU_LIST_ERROR_INVALID("menu.list.error.invalid"),
    MENU_LIST_ERROR_TOOLONG("menu.list.error.long"),
    MENU_LIST_OPTION("menu.list.options"),
    MENU_LIST_ENTERCHAT("menu.list.enterChat"),
    MENU_LOADOUT_ADD_ENTERCHAT("menu.loadout.add.enterChat"),
    MENU_LOADOUT_ERROR_ALREADYEXISTS("menu.loadout.error.alreadyExists"),
    MENU_STAT_DISPLAYNAME("menu.stat.displayname"),
    MENU_STAT_STORAGEFORMAT("menu.stat.storageFormat"),
    MENU_STAT_EDIT_NAME("menu.stat.edit.name"),
    MENU_REWARD_SELECTTYPE_NAME("menu.reward.selectType.name");

    private final @NotNull String path;

    MgMenuLangKey(@NotNull String path) {
        this.path = path;
    }

    public @NotNull String getPath() {
        return path;
    }
}
