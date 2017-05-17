package warvale.core.plugin.map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Draem on 5/6/2017.
 */
public class GameMap {

    public static String filedir = "\\maps\\";
    public static String filename = "map.xml";
    public static String path = getPath();
    public static HashMap<String, GameMap> maps = new HashMap<>();

    private String name;
    private File mapFile;
    private File mapXML;

    public GameMap(String name) throws IOException {
        this.name = name;
        this.mapFile = new File(path + filedir + this.name + "\\");

        if (!this.mapFile.exists()) {
            if(this.mapFile.mkdirs()) {
                this.mapXML = new File(this.mapFile.getPath() + "\\" + filename);
                this.mapXML.createNewFile();

//            for (Player player : Bukkit.getOnlinePlayers()) {
//                if (player.isOp()) {
//                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4CTF &7Map &c\"" + this.name + "\" does not contain map.xml file." +
//                            "\n&7-> I have created one for you, please fill in the &arequired &7fields."));
//                }
//            }
            } else {
                throw new IOException("Failed to created directory for map.xml.");
            }
        } else {
            this.mapXML = new File(this.mapFile.getPath() + "\\" + filename);
        }

        maps.put(this.name, this);
    }

    public File getMapFile() {
        return mapFile;
    }

    public String getName() {
        return name;
    }

    public static String getPath() {
        URL url = GameMap.class.getProtectionDomain().getCodeSource().getLocation();
        String jarPath = null;
        try {
            jarPath = URLDecoder.decode(url.getFile(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String parentPath = new File(jarPath).getParentFile().getPath();
        return parentPath;
    }

    public List<String> getAuthors() throws ParserConfigurationException, IOException, SAXException {
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(this.mapXML);
        doc.getDocumentElement().normalize();

        return Arrays.asList(doc.getElementsByTagName("authors").item(0).getTextContent().replaceAll(",", "").split(" "));
    }

    public HashMap<String, Integer> getLobby() throws ParserConfigurationException, IOException, SAXException {
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(this.mapXML);
        doc.getDocumentElement().normalize();

        Element element = (Element) doc.getElementsByTagName("lobby").item(0);
        HashMap<String, Integer> lobby = new HashMap<>();

        lobby.put("plx", Integer.valueOf(element.getElementsByTagName("region").item(0).getTextContent().split(" ")[0]));
        lobby.put("ply", Integer.valueOf(element.getElementsByTagName("region").item(0).getTextContent().split(" ")[1]));
        lobby.put("plz", Integer.valueOf(element.getElementsByTagName("region").item(0).getTextContent().split(" ")[2]));
        lobby.put("p2x", Integer.valueOf(element.getElementsByTagName("region").item(0).getTextContent().split(" ")[3]));
        lobby.put("p2y", Integer.valueOf(element.getElementsByTagName("region").item(0).getTextContent().split(" ")[4]));
        lobby.put("p2z", Integer.valueOf(element.getElementsByTagName("region").item(0).getTextContent().split(" ")[5]));

        lobby.put("exx", Integer.valueOf(element.getElementsByTagName("exact").item(0).getTextContent().split(" ")[0]));
        lobby.put("exy", Integer.valueOf(element.getElementsByTagName("exact").item(0).getTextContent().split(" ")[1]));
        lobby.put("exz", Integer.valueOf(element.getElementsByTagName("exact").item(0).getTextContent().split(" ")[2]));

        lobby.put("yaw", Integer.valueOf( element.getElementsByTagName("yaw").item(0).getTextContent()));
        lobby.put("pitch", Integer.valueOf(element.getElementsByTagName("pitch").item(0).getTextContent()));

        return lobby;
    }

    protected Element getTeamInfo() throws ParserConfigurationException, IOException, SAXException {
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(this.mapXML);
        doc.getDocumentElement().normalize();

        return (Element) doc.getElementsByTagName("team").item(0);
    }

    public HashMap<String, Integer[]> getSpawns() throws IOException, SAXException, ParserConfigurationException {
        HashMap<String, Integer[]> spawns = new HashMap<>();
        Integer[] blue = new Integer[]{};
        Integer[] red = new Integer[]{};

        blue[0] = Integer.valueOf(((Element) getTeamInfo().getElementsByTagName("spawns")).getElementsByTagName("red").item(0).getTextContent().split(" ")[0]);
        blue[1] = Integer.valueOf(((Element) getTeamInfo().getElementsByTagName("spawns")).getElementsByTagName("red").item(0).getTextContent().split(" ")[1]);
        blue[2] = Integer.valueOf(((Element) getTeamInfo().getElementsByTagName("spawns")).getElementsByTagName("red").item(0).getTextContent().split(" ")[2]);
        blue[3] = Integer.valueOf(((Element) getTeamInfo().getElementsByTagName("spawns")).getElementsByTagName("red").item(0).getTextContent().split(" ")[3]);
        blue[4] = Integer.valueOf(((Element) getTeamInfo().getElementsByTagName("spawns")).getElementsByTagName("red").item(0).getTextContent().split(" ")[4]);
        blue[5] = Integer.valueOf(((Element) getTeamInfo().getElementsByTagName("spawns")).getElementsByTagName("red").item(0).getTextContent().split(" ")[5]);

        red[0] = Integer.valueOf(((Element) getTeamInfo().getElementsByTagName("spawns")).getElementsByTagName("blue").item(0).getTextContent().split(" ")[0]);
        red[1] = Integer.valueOf(((Element) getTeamInfo().getElementsByTagName("spawns")).getElementsByTagName("blue").item(0).getTextContent().split(" ")[1]);
        red[2] = Integer.valueOf(((Element) getTeamInfo().getElementsByTagName("spawns")).getElementsByTagName("blue").item(0).getTextContent().split(" ")[2]);
        red[3] = Integer.valueOf(((Element) getTeamInfo().getElementsByTagName("spawns")).getElementsByTagName("blue").item(0).getTextContent().split(" ")[3]);
        red[4] = Integer.valueOf(((Element) getTeamInfo().getElementsByTagName("spawns")).getElementsByTagName("blue").item(0).getTextContent().split(" ")[4]);
        red[5] = Integer.valueOf(((Element) getTeamInfo().getElementsByTagName("spawns")).getElementsByTagName("blue").item(0).getTextContent().split(" ")[5]);

        spawns.put("red", red);
        spawns.put("blue", blue);

        return spawns;
    }




}
