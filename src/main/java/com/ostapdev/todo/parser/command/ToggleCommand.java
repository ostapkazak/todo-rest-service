package com.ostapdev.todo.parser.command;


public class ToggleCommand extends Command{
    @Override
    public void run(String inputLine) {
        final String command = "toggle";

        if (isCommand(inputLine,command)){
            try {
                getService().toggle(Integer.parseInt(inputLine.replace(command,"").trim()));
            }
            catch (NumberFormatException e){
                System.out.println("Введенный аргумент не является целым числом");
            }
        }
    }
}
