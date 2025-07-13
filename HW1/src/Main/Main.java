package Main;

import HWSystem.HWSystem;

public class Main {
    public static void main(String[] args) {
        HWSystem system = new HWSystem();

        system.loadConfig("config.txt");
        system.runCommands("commands.txt");
    }
}
