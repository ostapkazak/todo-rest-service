import service.TodoService;

import java.util.Scanner;

public class TodoApp {
    public static void main(String[] args) {
        TodoService service = new TodoService();
        Scanner scanner = new Scanner(System.in);
        System.out.println("---------TODO LIST---------\nСписок комманд - help\n");

        while (true){
            String inputLine = scanner.nextLine();

            if (inputLine.startsWith("add")){
                service.add(inputLine.replace("add ",""));
            }

            else if (inputLine.startsWith("print")){
                if (inputLine.equals("print")) service.print(false);
                else if (inputLine.equals("print all")) service.print(true);
                else System.out.println("Некорректный аргумент для команды");
            }

            else if (inputLine.startsWith("toggle")){
                try {
                    service.toggle(Integer.parseInt(inputLine.replace("toggle ","")));
                }
                catch (NumberFormatException e){
                    System.out.println("Введенный аргумент не является целым числом");
                }
            }

            else if (inputLine.equals("help")){
                service.help();
            }

            else if (inputLine.equals("quit")){
                break;
            }

            else {
                System.out.println("Некорректная команда");
            }
        }

        System.out.println("---------Работа программы завершена---------");
    }
}
