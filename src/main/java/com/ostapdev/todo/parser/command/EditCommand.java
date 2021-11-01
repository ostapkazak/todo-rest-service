package com.ostapdev.todo.parser.command;


public class EditCommand extends Command{
    @Override
    public void run(String inputLine) {
        final String command = "edit";

        if (isCommand(inputLine,command)){
            try {
                String[] commandArgs = inputLine.replace(command,"").trim().split(" ",2);
                getService().edit(Integer.parseInt(commandArgs[0]),commandArgs[1]);
            }
            catch (NumberFormatException e){
                System.out.println("Введенный аргумент не является целым числом");
            }
        }
    }
}
