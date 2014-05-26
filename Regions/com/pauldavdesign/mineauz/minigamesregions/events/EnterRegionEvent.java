package com.pauldavdesign.mineauz.minigamesregions.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.pauldavdesign.mineauz.minigames.MinigamePlayer;
import com.pauldavdesign.mineauz.minigamesregions.Region;

public class EnterRegionEvent extends Event{
	private static final HandlerList handlers = new HandlerList();
	
	private MinigamePlayer player;
	private Region region;
	
	public EnterRegionEvent(MinigamePlayer player, Region region){
		this.player = player;
		this.region = region;
	}
	
	public MinigamePlayer getMinigamePlayer(){
		return player;
	}
	
	public Region getRegion(){
		return region;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
 
    public static HandlerList getHandlerList() {
        return handlers;
    }

}
