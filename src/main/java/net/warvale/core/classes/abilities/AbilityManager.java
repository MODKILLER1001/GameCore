 package net.warvale.core.classes.abilities;

        import net.warvale.core.Main;
        import net.warvale.core.classes.Class;
        import net.warvale.core.classes.ClassManager;
        import net.warvale.core.game.Game;
        import net.warvale.core.game.State;
        import org.bukkit.*;
        import org.bukkit.block.Block;
        import org.bukkit.entity.*;
        import org.bukkit.event.EventHandler;
        import org.bukkit.event.Listener;
        import org.bukkit.event.block.Action;
        import org.bukkit.event.entity.EntityDamageByEntityEvent;
        import org.bukkit.event.entity.EntityTargetEvent;
        import org.bukkit.event.entity.ProjectileHitEvent;
        import org.bukkit.event.player.PlayerInteractEvent;
        import org.bukkit.event.player.PlayerMoveEvent;
        import org.bukkit.inventory.ItemStack;
        import org.bukkit.material.MaterialData;
        import org.bukkit.plugin.Plugin;
        import org.bukkit.potion.PotionEffect;
        import org.bukkit.potion.PotionEffectType;
        import org.bukkit.util.Vector;

        import java.util.*;

        import static org.bukkit.event.block.Action.RIGHT_CLICK_AIR;
        import static org.bukkit.event.block.Action.RIGHT_CLICK_BLOCK;

/**
 * Created by Ron on 22/5/2017.
 */
public class AbilityManager implements Listener {

    private ArrayList<String> cooldown = new ArrayList<>();
    private ArrayList<Player> electroCooldown = new ArrayList<>();
    private ArrayList<Player> fireCooldown = new ArrayList<>();
    private ArrayList<Player> ArcherArrow = new ArrayList<>();
    private ArrayList<Player> freeze = new ArrayList<>();
    private ArrayList<Player> MusicianJukebox = new ArrayList<>();
    private ArrayList<Entity> blueTeamMobs = new ArrayList<Entity>();
    private ArrayList<Entity> redTeamMobs = new ArrayList<Entity>();




    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        if(!Game.getInstance().isState(State.INGAME)){
            return;
        }
        Player p = e.getPlayer();
        if (!(e.getAction() == RIGHT_CLICK_AIR || e.getAction() == RIGHT_CLICK_BLOCK)) return;
        if (cooldown.contains(p.getName())) return;

        Class classCheck = ClassManager.getClassForPlayer(p.getName());

        e.setCancelled(true);

        switch (classCheck.getName()){
            case "Soldier":
                if(!e.getItem().equals(new ItemStack(Material.FIREWORK_CHARGE))) return;
                this.Soldier(p);
                break;



            case "Archer":
                if(!e.getItem().equals(new ItemStack(Material.MAGMA_CREAM))) return;
                this.Archer(p);
                break;


            case "Assassin":
                if (!e.getItem().equals(new ItemStack(Material.SULPHUR))) return;
                this.Assassin(p);
                break;

            case "Miner":
                if (!e.getItem().equals(new ItemStack(Material.IRON_PICKAXE))) return;
                this.Miner(p);
                break;

            case "Spy":
                if (!e.getItem().equals(new ItemStack(Material.GLASS_BOTTLE))) return;
                this.Spy(p);
                break;

            case "Technician":
                if (!e.getItem().equals(new ItemStack(Material.REDSTONE_TORCH_ON))) return;
                this.Technician(p);
                break;

            case "Musician":
                if (!e.getItem().equals(new ItemStack(Material.RECORD_8))) return;
                this.Musician(p);
                break;

            case "Pyromaniac":
                if (!e.getItem().equals(new ItemStack(Material.FIREBALL))) return;
                this.Pyromaniac(p);
                break;

            case "Necromancer":
                if (!e.getItem().equals(new ItemStack(Material.FIREBALL))) return;
                this.Necromancer(p);
                break;

            case "Earthbender":
                if (!e.getItem().equals(new ItemStack(Material.FIREBALL))) return;
                this.Earthbender(p);
                break;

            case "Medic":
                if (!e.getItem().equals(new ItemStack(Material.FIREBALL))) return;
                this.Medic(p);
                break;
        }

        cooldown.add(p.getName());
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin) this, new Runnable() {
            public void run() {
                cooldown.remove(p.getName());
                p.sendMessage(ChatColor.GREEN + "You can use your ability now!");
            }
        }, 200);

    }

    public boolean isInRect (Player player, Location loc1, Location loc2) {
        double[] dim = new double[2];

        dim[0] = loc1.getX();
        dim[1] = loc2.getX();
        Arrays.sort(dim);
        if (player.getLocation().getX() > dim[1] || player.getLocation().getX() < dim[0]) return false;

        dim[0] = loc1.getZ();
        dim[1] = loc2.getZ();
        Arrays.sort(dim);
        if (player.getLocation().getZ() > dim[1] || player.getLocation().getZ() < dim[0]) return false;
        return true;
    }

    public Player closestPlayer(Set lol, Entity aentity) {
        if (!(aentity instanceof Player)) return null;
        Player aplayer = (Player) aentity;

        double closest = Double.MAX_VALUE;
        Player closestp = null;
        for(Object ii : lol){
            Player i = Bukkit.getServer().getPlayer(String.valueOf(ii));
            double dist = i.getLocation().distance(aplayer.getLocation());
            if (closest == Double.MAX_VALUE || dist < closest){
                closest = dist;
                closestp = i;
            }
        }
        return closestp;
    }

    @EventHandler
    public void onEntityDamage (EntityDamageByEntityEvent e) {

        if(!Game.getInstance().isState(State.INGAME)){
            return;
        }

        LivingEntity entity = (LivingEntity)e.getEntity();
        Player playerEntity = (Player)entity;
        if (entity.getLastDamageCause().getEntity() instanceof Player) {
            Player p = (Player)entity.getLastDamageCause().getEntity();
            if (electroCooldown.contains(p)){
                freeze.add(playerEntity);
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin) this, new Runnable() {
                    public void run() {
                        freeze.remove(entity);
                    }
                }, 100);
            }
            if (fireCooldown.contains(p)) entity.setFireTicks(100);
        }
    }

    @EventHandler
    public void onMove (PlayerMoveEvent e) {

        if(!Game.getInstance().isState(State.INGAME)){
            return;
        }

        if (!freeze.contains(e.getPlayer())) return;
        if (!electroCooldown.contains(e.getPlayer())) return;
        Location from = e.getFrom();
        Location to = e.getTo();
        double x = Math.floor(from.getX());
        double z = Math.floor(from.getZ());
        if (Math.floor(to.getX()) != x || Math.floor(to.getZ()) != z) {
            x += .5;
            z += .5;
            e.getPlayer().teleport(new Location(from.getWorld(),x,from.getY(),z,from.getYaw(),from.getPitch()));
        }
    }

    @EventHandler
    public void onEntityTarget(EntityTargetEvent event) {

        if(!Game.getInstance().isState(State.INGAME)){
            return;
        }

        Entity entity = event.getEntity();
        Entity target = event.getTarget();
    }

    private void Soldier (Player p) {
        Vector vector = p.getLocation().getDirection();
        vector.setY(0.4);
        p.setVelocity(vector);
        p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100, 1));
    }
    private void Archer (Player p) {
        Arrow arrow = p.launchProjectile(Arrow.class);
        arrow.setVelocity(arrow.getVelocity().multiply(10));
        ArcherArrow.add(p);

    }
    private void Assassin(Player p) {
        p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 100, 100));
    }

    private void Miner (Player p) {
        p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 100, 100));
    }

    private void Spy (Player p) {
        p.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 100, 100));
    }
    private void Earthbender(Player p) {

        List<Entity> nearByEntities = p.getNearbyEntities(5, 5, 5);
        List<Player> nearByPlayers = new ArrayList<Player>();
        for(Entity target : nearByEntities) {
            if (!(target instanceof Player)) return;
            Player playerTarget = (Player) target;
            nearByPlayers.add(playerTarget);
        }


        for (Player target : nearByPlayers) {
            target.playSound(target.getLocation(), Sound.BLOCK_ANVIL_PLACE, SoundCategory.HOSTILE, 100, 100);
            Block targetBlock = target.getLocation().getWorld().getBlockAt(target.getLocation().subtract(0, 1, 0));
            List<Block> changedBlocks = new ArrayList<Block>();

            changedBlocks.add(targetBlock);

            for (Block bt : changedBlocks) {
                bt.setType(Material.AIR);
                FallingBlock fblock = target.getLocation().getWorld().spawnFallingBlock(bt.getLocation(), new MaterialData(bt.getType()));
                fblock.setVelocity(new Vector(0, 3, 0));
                fblock.setHurtEntities(true);

            }



        }

    }
    private void Technician (Player p) {
        electroCooldown.add(p);
        p.sendMessage(ChatColor.GREEN + "You can now hit a player to paralyze them!");
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin) this, new Runnable() {
            public void run() {
                electroCooldown.remove(p);
            }
        }, 100);
    }

    private void Musician (Player p) {
        electroCooldown.add(p);
        p.sendMessage(ChatColor.GREEN + "A jukebox has been spawned beneath you!");

        final Block block = p.getLocation().subtract(0, 1, 0).getBlock();
        final Material type = block.getType();

        block.setType(Material.JUKEBOX); //set the block beneath player to jukebox

        ItemStack item = new ItemStack(Material.GREEN_RECORD);
        item.getItemMeta().getLore().add(ChatColor.translateAlternateColorCodes('&', "&7Use this in the Jukebox below you."));
        p.getInventory().addItem(item);
        MusicianJukebox.add(p);
        // (Give player a disc -- DONE-- ) and when the player inserts the disc into the jukebox, give healing in a radius of ten blocks

        /*getNearbyEntities(Location location,
        double x,
        double y,
        double z)No*/

        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin) this, new Runnable() {
            public void run() {
                block.setType(type);
            }
        }, 200);
    }

    private void Pyromaniac (Player p) {
        fireCooldown.add(p);
        p.sendMessage(ChatColor.GREEN + "You can now hit a player to light them on fire!");
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin) this, () -> fireCooldown.remove(p), 100);
     }

    private void Necromancer (Player p) {
        Set<String> blueteamplayers = Main.getTeams().getBlueTeam().getEntries();
        Set<String> redteamplayers = Main.getTeams().getRedTeam().getEntries();
        Location loc = p.getLocation();
        List<Entity> entities = new ArrayList<Entity>();
        Location locOne = loc.subtract(1, 0, 1);
        Location locTwo = loc.subtract(1, 0, 0);
        Location locThree = loc.subtract(0, 0, 1);
        Location locFour = loc.subtract(1, 0, 1);
        entities.add(loc.getWorld().spawnEntity(locOne, EntityType.ZOMBIE));
        entities.add(loc.getWorld().spawnEntity(locTwo, EntityType.ZOMBIE));
        entities.add(loc.getWorld().spawnEntity(locThree, EntityType.ZOMBIE));
        entities.add(loc.getWorld().spawnEntity(locFour, EntityType.ZOMBIE));
        if (blueteamplayers.contains(p.getName())) {
            for (Entity e : entities) {
                blueTeamMobs.add(e);
            }
        } else if (redteamplayers.contains(p.getName())) {
            for (Entity e : entities) {
                redTeamMobs.add(e);
            }
        }
    }



    @EventHandler
    public void onTarget(EntityTargetEvent event){
        if(!Game.getInstance().isState(State.INGAME)){
            return;
        }

        if(!(event.getTarget() instanceof Player)) return;

        Set<String> blueteamplayers = Main.getTeams().getBlueTeam().getEntries();
        Set<String> redteamplayers = Main.getTeams().getRedTeam().getEntries();

        if(blueTeamMobs.contains(event.getEntity()) && redteamplayers.contains(event.getTarget())){
            event.setTarget(closestPlayer(blueteamplayers, event.getEntity()));
        }

        if(redTeamMobs.contains(event.getEntity()) && blueteamplayers.contains(event.getTarget())){
            event.setTarget(closestPlayer(redteamplayers, event.getEntity()));
        }
    }

    private void Medic (Player p) {
        List<Entity> entities = p.getNearbyEntities(10, 10, 10);
        for(Entity e : entities){
            if (!(e instanceof Player)) return;
            Player nbp = (Player) e;
            if (Main.getTeams().getSpectatorTeam().getEntries().contains(nbp.getName())) return;
            Set<String> BlueTeam = Main.getTeams().getBlueTeam().getEntries();
            Set<String> RedTeam = Main.getTeams().getRedTeam().getEntries();
            Set<String> teammates = ((BlueTeam.contains(p.getName())) ? BlueTeam : RedTeam);
            if(!teammates.contains(nbp.getName())) return;
            nbp.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 100, 7));
        }
    }


    //         For Archer class.
    @EventHandler
    public void ArcherProjectileHit (ProjectileHitEvent e) {

        if(!Game.getInstance().isState(State.INGAME)){
            return;
        }

        if (!(e.getEntity() instanceof Arrow)) return;
        Arrow arrow = (Arrow)e.getEntity();
        if (!(arrow.getShooter() instanceof Player)) return;
        Player p = (Player)arrow.getShooter();
        if (!ArcherArrow.contains(p)) return;
        p.getWorld().createExplosion(arrow.getLocation(), 5.0F);
        ArcherArrow.remove(p);
    }

    @EventHandler
    public void MusicianInteract(PlayerInteractEvent e) {

        if(!Game.getInstance().isState(State.INGAME)){
            return;
        }

        Action action = e.getAction();
        Block blockFacing = e.getClickedBlock();
        Player p = e.getPlayer();

        if (!(action.equals(RIGHT_CLICK_BLOCK))) return;

        if (p.getEquipment().getItemInMainHand().getType() == Material.GREEN_RECORD && blockFacing.getType().equals(Material.JUKEBOX)) {
            Collection<Entity> entities = blockFacing.getWorld().getNearbyEntities(blockFacing.getLocation(), 10, 10, 10);
            for (Entity eee : entities) {
                if (!(eee instanceof Player)) return;
                Player eep = (Player) eee;
                eep.setHealth(eep.getHealth() + 3);
            }
        }

    }


}
