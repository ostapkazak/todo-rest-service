package com.ostapdev.todo.parser.command;


public class EditCommand extends BaseCommand {
    private static String command = "edit";
    @Override
    public String getCommand() {
        return command;
    }

    @Override
    protected void runImpl(String inputLine) {
        try {
            String[] commandArgs = inputLine.replace(command,"").trim().split(" ",2);
            getService().edit(Integer.parseInt(commandArgs[0]),commandArgs[1]);
        }
        catch (NumberFormatException e){
            System.out.println("Введенный аргумент не является целым числом");
        }
    }
}
