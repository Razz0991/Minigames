package au.com.mineauz.minigamesregions;

import au.com.mineauz.minigames.minigame.Minigame;
import au.com.mineauz.minigames.objects.MinigamePlayer;
import au.com.mineauz.minigames.script.ScriptReference;
import au.com.mineauz.minigames.script.ScriptValue;
import au.com.mineauz.minigames.script.ScriptWrapper;
import au.com.mineauz.minigamesregions.actions.ActionInterface;
import au.com.mineauz.minigamesregions.conditions.ACondition;
import au.com.mineauz.minigamesregions.executors.NodeExecutor;
import au.com.mineauz.minigamesregions.triggers.Trigger;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Node implements ExecutableScriptObject {
    private final String name;
    private final Minigame minigame;
    private final List<NodeExecutor> executors = new ArrayList<>();
    private Location loc;
    private boolean enabled = true;

    public Node(@NotNull String name, @NotNull Minigame minigame, @NotNull Location loc) {
        this.name = name;
        this.minigame = minigame;
        this.loc = loc;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return loc.clone();
    }

    public void setLocation(Location loc) {
        this.loc = loc.clone();
    }

    public int addExecutor(Trigger trigger) {
        executors.add(new NodeExecutor(trigger));
        return executors.size();
    }

    public int addExecutor(NodeExecutor exec) {
        executors.add(exec);
        return executors.size();
    }

    public List<NodeExecutor> getExecutors() {
        return executors;
    }

    public void removeExecutor(int id) {
        if (executors.size() <= id) {
            executors.remove(id - 1);
        }
    }

    public void removeExecutor(NodeExecutor executor) {
        executors.remove(executor);
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void execute(@NotNull Trigger trigger, @Nullable MinigamePlayer mgPlayer) {
        if (mgPlayer == null || mgPlayer.getMinigame() == null) return;
        if (mgPlayer.getMinigame() != null && mgPlayer.getMinigame().isSpectator(mgPlayer)) return;
        List<NodeExecutor> toExecute = new ArrayList<>();
        for (NodeExecutor exec : executors) {
            if (exec.getTrigger() == trigger) {
                if (checkConditions(exec, mgPlayer) && exec.canBeTriggered(mgPlayer)) {
                    toExecute.add(exec);
                }
            }
        }
        for (NodeExecutor exec : toExecute) {
            execute(exec, mgPlayer);
        }
    }

    public boolean checkConditions(NodeExecutor exec, MinigamePlayer player) {
        for (ConditionInterface con : exec.getConditions()) {
            boolean conditionCheck = con.checkNodeCondition(player, this);
            if (con.isInverted()) {
                conditionCheck = !conditionCheck;
            }
            if (!conditionCheck) {
                return false;
            }
        }
        return true;
    }

    public void execute(@NotNull NodeExecutor exec, @NotNull MinigamePlayer mgPlayer) {
        for (ActionInterface act : exec.getActions()) {
            if (!enabled && !act.getName().equalsIgnoreCase("SET_ENABLED")) continue;
            act.executeNodeAction(mgPlayer, this);
            if (!exec.isTriggerPerPlayer()) {
                exec.addPublicTrigger();
            } else {
                exec.addPlayerTrigger(mgPlayer);
            }
        }
    }

    @Override
    public ScriptReference get(String name) {
        if (name.equalsIgnoreCase("name")) {
            return ScriptValue.of(name);
        } else if (name.equalsIgnoreCase("pos")) {
            return ScriptWrapper.wrap(loc);
        } else if (name.equalsIgnoreCase("block")) {
            return ScriptWrapper.wrap(loc.getBlock());
        }

        return null;
    }

    @Override
    public Set<String> getKeys() {
        return Set.of("name", "pos", "block");
    }

    @Override
    public String getAsString() {
        return name;
    }

    public Minigame getMinigame() {
        return minigame;
    }
}
