package com.ostapdev.todo.parser.command;

import java.util.List;

public class CommandFactory {
    public static List<Command> getCommands(){
        return List.of(new AddCommand("add"),new PrintCommand("print"),new DeleteCommand("delete"),new EditCommand("edit"),new PrintAllCommand("print all"),new QuitCommand("quit"),new SearchCommand("search"),new ToggleCommand("toggle"));
    }
}
