package au.com.mineauz.minigamesregions;

import java.util.Map;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.SetMultimap;

import au.com.mineauz.minigames.MinigamePlayer;
import au.com.mineauz.minigames.MinigameUtils;
import au.com.mineauz.minigames.Minigames;
import au.com.mineauz.minigames.display.IDisplayObject;
import au.com.mineauz.minigames.minigame.Minigame;

public class RegionDisplayManager {
	private Map<Player, Map<Region, IDisplayObject>> regionDisplays;
	private Map<Player, Map<Node, IDisplayObject>> nodeDisplays;
	
	private SetMultimap<Object, Player> activeWatchers;
	private Map<Object, ArmorStand> nameDisplay;
	
	public RegionDisplayManager() {
		regionDisplays = Maps.newHashMap();
		nodeDisplays = Maps.newHashMap();
		
		activeWatchers = HashMultimap.create();
		nameDisplay = Maps.newIdentityHashMap();
	}
	
	private void showInfo(Region region, Player player) {
		activeWatchers.put(region, player);
		
		ArmorStand stand = nameDisplay.get(region);
		if (stand == null) {
			Location midPoint = region.getFirstPoint().clone().add(region.getSecondPoint()).add(1,1,1).multiply(0.5).subtract(0, 1.4, 0);
			stand = region.getFirstPoint().getWorld().spawn(midPoint, ArmorStand.class);
			stand.setGravity(false);
			stand.setSmall(true);
			stand.setVisible(false);
			stand.setCustomNameVisible(true);
			
			nameDisplay.put(region, stand);
		}
		
		StringBuilder info = new StringBuilder();
		info.append(ChatColor.BLUE);
		info.append("Region: ");
		info.append(ChatColor.WHITE);
		info.append(region.getName());
		
		// TODO: Add more info
		stand.setCustomName(info.toString());
	}
	
	private void showInfo(Node node, Player player) {
		activeWatchers.put(node, player);
		
		ArmorStand stand = nameDisplay.get(node);
		if (stand == null) {
			stand = node.getLocation().getWorld().spawn(node.getLocation().clone().subtract(0, 0.75, 0), ArmorStand.class);
			stand.setGravity(false);
			stand.setSmall(true);
			stand.setVisible(false);
			stand.setCustomNameVisible(true);
			
			nameDisplay.put(node, stand);
		}
		
		StringBuilder info = new StringBuilder();
		info.append(ChatColor.RED);
		info.append("Node: ");
		info.append(ChatColor.WHITE);
		info.append(node.getName());
		
		// TODO: Add more info
		stand.setCustomName(info.toString());
	}
	
	private void hideInfo(Object object, Player player) {
		activeWatchers.remove(object, player);
		if (activeWatchers.get(object).isEmpty()) {
			ArmorStand stand = nameDisplay.remove(object);
			stand.remove();
		}
	}
	
	public void show(Region region, MinigamePlayer player) {
		Map<Region, IDisplayObject> regions = regionDisplays.get(player.getPlayer());
		if (regions == null) {
			regions = Maps.newIdentityHashMap();
			regionDisplays.put(player.getPlayer(), regions);
		}
		
		Location[] corners = MinigameUtils.getMinMaxSelection(region.getFirstPoint(), region.getSecondPoint());
		
		IDisplayObject display = Minigames.plugin.display.displayCuboid(player.getPlayer(), corners[0], corners[1].add(1, 1, 1));
		display.show();
		regions.put(region, display);
		
		showInfo(region, player.getPlayer());
	}
	
	public void show(Node node, MinigamePlayer player) {
		Map<Node, IDisplayObject> nodes = nodeDisplays.get(player.getPlayer());
		if (nodes == null) {
			nodes = Maps.newIdentityHashMap();
			nodeDisplays.put(player.getPlayer(), nodes);
		}
		
		IDisplayObject display = Minigames.plugin.display.displayPoint(player.getPlayer(), node.getLocation(), true);
		display.show();
		nodes.put(node, display);
		
		showInfo(node, player.getPlayer());
	}
	
	public void hide(Region region, MinigamePlayer player) {
		Map<Region, IDisplayObject> regions = regionDisplays.get(player.getPlayer());
		if (regions == null) {
			return;
		}
		
		IDisplayObject display = regions.remove(region);
		if (display != null) {
			display.remove();
		}
		
		hideInfo(region, player.getPlayer());
	}
	
	public void hide(Node node, MinigamePlayer player) {
		Map<Node, IDisplayObject> nodes = nodeDisplays.get(player.getPlayer());
		if (nodes == null) {
			return;
		}
		
		IDisplayObject display = nodes.remove(node);
		if (display != null) {
			display.remove();
		}
		
		hideInfo(node, player.getPlayer());
	}
	
	public void showAll(Minigame minigame, MinigamePlayer player) {
		RegionModule module = RegionModule.getMinigameModule(minigame);
		for (Region region : module.getRegions()) {
			show(region, player);
		}
		
		for (Node node : module.getNodes()) {
			show(node, player);
		}
	}
	
	public void hideAll(Minigame minigame, MinigamePlayer player) {
		RegionModule module = RegionModule.getMinigameModule(minigame);
		for (Region region : module.getRegions()) {
			hide(region, player);
		}
		
		for (Node node : module.getNodes()) {
			hide(node, player);
		}
	}
	
	public void hideAll(Player player) {
		Map<Region, IDisplayObject> regions = regionDisplays.remove(player);
		if (regions != null) {
			for (IDisplayObject display : regions.values()) {
				display.remove();
			}
			
			for (Region region : regions.keySet()) {
				hideInfo(region, player);
			}
		}
		
		Map<Node, IDisplayObject> nodes = nodeDisplays.remove(player);
		if (nodes != null) {
			for (IDisplayObject display : nodes.values()) {
				display.remove();
			}
			
			for (Node node : nodes.keySet()) {
				hideInfo(node, player);
			}
		}
	}
	
	public void shutdown() {
		for (ArmorStand stand : nameDisplay.values()) {
			stand.remove();
		}
	}
}