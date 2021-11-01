package com.ostapdev.todo.parser.command;

import java.util.List;

public class CommandFactory {
    public static List<BaseCommand> getCommands(){
        return List.of(new AddCommand(),new PrintCommand(),new DeleteCommand(),new EditCommand(),new QuitCommand(),
                new SearchCommand(),new ToggleCommand());
    }
}
