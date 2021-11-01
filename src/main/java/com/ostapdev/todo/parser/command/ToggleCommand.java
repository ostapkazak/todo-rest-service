package com.ostapdev.todo.parser.command;


public class ToggleCommand extends Command{
    private static String command = "toggle";

    @Override
    public String getCommand() {
        return command;
    }

    @Override
    public void run(String inputLine) {
        try {
            getService().toggle(Integer.parseInt(inputLine.replace(command,"").trim()));
        }
        catch (NumberFormatException e){
            System.out.println("Введенный аргумент не является целым числом");
        }
    }
}
