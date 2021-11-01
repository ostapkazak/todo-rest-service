package com.ostapdev.todo.parser.command;


public class DeleteCommand extends Command{
    private static String command  = "delete";

    @Override
    public String getCommand() {
        return command;
    }

    @Override
    protected void run(String inputLine) {
        try {
            getService().delete(Integer.parseInt(inputLine.replace(command,"").trim()));
        }
        catch (NumberFormatException e){
            System.out.println("Введенный аргумент не является целым числом");
        }
    }
}
