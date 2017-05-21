package net.warvale.core.map;

import net.warvale.core.Main;
import net.warvale.core.message.MessageManager;
import net.warvale.core.message.PrefixType;
import net.warvale.core.utils.files.FileUtils;
import org.bukkit.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Draem on 5/6/2017.
 */
public class GameMap {

    private static String path = getPath();
    private static HashMap<String, GameMap> maps = new HashMap<>();

    private String name;
    private File mapFile;
    private File mapXML;

    public GameMap(String name) throws IOException {
        this.name = name;
        String filedir = "maps";
        this.mapFile = new File(Main.getMapDir(), this.name);

        String filename = "map.xml";
        if (!this.mapFile.exists()) {
            if (this.mapFile.mkdirs()) {
                this.mapXML = new File(this.mapFile.getPath(), filename);
                this.mapXML.createNewFile();

                MessageManager.broadcast(PrefixType.MAIN,  ChatColor.GRAY +
                        "Map " + ChatColor.RED + this.name + ChatColor.GRAY + " does not contain a valid map.xml file.", "warvale.newmap");

                MessageManager.broadcast(PrefixType.MAIN, ChatColor.GRAY + "A new map.xml for the map " + ChatColor.RED + this.name +
                    ChatColor.GRAY + " has been created, please fill in the required fields");

                // for (Player player : Bukkit.getOnlinePlayers()) {
                // if (player.isOp()) {
                // player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                // "&4CTF &7Map &c\"" + this.name + "\" does not contain map.xml
                // file." +
                // "\n&7-> I have created one for you, please fill in the
                // &arequired &7fields."));
                // }
                // }
            } else {
                throw new IOException("Failed to created directory for map.xml.");
            }
        } else {
            this.mapXML = new File(this.mapFile, filename);
        }

        maps.put(this.name, this);
    }

    public File getMapFile() {
        return mapFile;
    }

    public String getName() {
        return name;
    }

    private static String getPath() {
        URL url = GameMap.class.getProtectionDomain().getCodeSource().getLocation();
        String jarPath = null;
        try {
            jarPath = URLDecoder.decode(url.getFile(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        assert jarPath != null;
        return new File(jarPath).getParentFile().getPath();
    }

    public List<String> getAuthors() throws ParserConfigurationException, IOException, SAXException {
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(this.mapXML);
        doc.getDocumentElement().normalize();

        return Arrays
                .asList(doc.getElementsByTagName("authors").item(0).getTextContent().replaceAll(",", "").split(" "));
    }

    public HashMap<String, Integer> getLobby() throws ParserConfigurationException, IOException, SAXException {
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(this.mapXML);
        doc.getDocumentElement().normalize();

        Element element = (Element) doc.getElementsByTagName("lobby").item(0);
        HashMap<String, Integer> lobby = new HashMap<>();

        lobby.put("plx",
                Integer.valueOf(element.getElementsByTagName("region").item(0).getTextContent().split(" ")[0]));
        lobby.put("ply",
                Integer.valueOf(element.getElementsByTagName("region").item(0).getTextContent().split(" ")[1]));
        lobby.put("plz",
                Integer.valueOf(element.getElementsByTagName("region").item(0).getTextContent().split(" ")[2]));
        lobby.put("p2x",
                Integer.valueOf(element.getElementsByTagName("region").item(0).getTextContent().split(" ")[3]));
        lobby.put("p2y",
                Integer.valueOf(element.getElementsByTagName("region").item(0).getTextContent().split(" ")[4]));
        lobby.put("p2z",
                Integer.valueOf(element.getElementsByTagName("region").item(0).getTextContent().split(" ")[5]));

        lobby.put("exx", Integer.valueOf(element.getElementsByTagName("exact").item(0).getTextContent().split(" ")[0]));
        lobby.put("exy", Integer.valueOf(element.getElementsByTagName("exact").item(0).getTextContent().split(" ")[1]));
        lobby.put("exz", Integer.valueOf(element.getElementsByTagName("exact").item(0).getTextContent().split(" ")[2]));

        lobby.put("yaw", Integer.valueOf(element.getElementsByTagName("yaw").item(0).getTextContent()));
        lobby.put("pitch", Integer.valueOf(element.getElementsByTagName("pitch").item(0).getTextContent()));

        return lobby;
    }

    private Element getTeamInfo() throws ParserConfigurationException, IOException, SAXException {
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(this.mapXML);
        doc.getDocumentElement().normalize();

        return (Element) doc.getElementsByTagName("team").item(0);
    }

    private Element getSpawnElement(String team) throws ParserConfigurationException, IOException, SAXException {
        return (Element) ((Element) getTeamInfo().getElementsByTagName("spawns").item(0)).getElementsByTagName(team);
    }

    public HashMap<String, Integer> getSpawnInfo(String team) throws IOException, SAXException, ParserConfigurationException {
        HashMap<String, Integer> spawn = new HashMap<>();
        spawn.put("exact", Integer.valueOf(getSpawnElement(team).getElementsByTagName("exact").item(0).getTextContent()));
        spawn.put("radius", Integer.valueOf(getSpawnElement(team).getElementsByTagName("radius").item(0).getTextContent()));
        return spawn;
    }

    private Element getCoreElement(String team) throws ParserConfigurationException, IOException, SAXException {
        return (Element) ((Element) getTeamInfo().getElementsByTagName("cores").item(0)).getElementsByTagName(team);
    }

    public HashMap<String, Integer> getCoreInfo(String team) throws IOException, SAXException, ParserConfigurationException {
        HashMap<String, Integer> core = new HashMap<>();
        core.put("exact", Integer.valueOf(getCoreElement(team).getElementsByTagName("exact").item(0).getTextContent()));
        core.put("radius", Integer.valueOf(getCoreElement(team).getElementsByTagName("radius").item(0).getTextContent()));
        return core;
    }

    public HashMap<String, Integer> getCenter() throws ParserConfigurationException, IOException, SAXException {
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(this.mapXML);
        doc.getDocumentElement().normalize();
        HashMap<String, Integer> center = new HashMap<>();
        center.put("exact", Integer.valueOf(((Element) doc.getElementsByTagName("center").item(0)).getElementsByTagName("exact").item(0).getTextContent()));
        center.put("radius", Integer.valueOf(((Element) doc.getElementsByTagName("center").item(0)).getElementsByTagName("radius").item(0).getTextContent()));
        return center;
    }

    public World load() throws IOException {
        File file = new File(Bukkit.getWorldContainer().getPath() + "/" + this.name + "/");
        if (!file.exists()) {
            file.mkdirs();
        }
        FileUtils.copyFolder(new File(getMapFile().getPath() + "/world/"), file);
        Bukkit.createWorld(WorldCreator.name(this.name));
        return Bukkit.getWorld(this.name);
    }

    public static HashMap<String, GameMap> getMaps() {
        return maps;
    }
}
