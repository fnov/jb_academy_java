package machine;

public class CMInstance {
    private State state;
    private int water;
    private int milk;
    private int coffee;
    private int cups;
    private int money;

    public CMInstance(int water, int milk, int coffee, int cups, int money) {
        this.water = water;
        this.milk = milk;
        this.coffee = coffee;
        this.cups = cups;
        this.money = money;

        setMainState();
    }

    public boolean isWorking() {
        return state != State.OFF;
    }

    public void handleInput(String input) {
        switch (state) {
            case MAIN:
                setState(input);
                break;
            case BUYING:
                handleBuying(input);
                setMainState();
                break;
            case FILLING_WATER:
                water += Integer.parseInt(input);
                System.out.print("Write how many ml of milk do you want to add:\n> ");
                state = State.FILLING_MILK;
                break;
            case FILLING_MILK:
                milk += Integer.parseInt(input);
                System.out.print("Write how many grams of coffee beans do you want to add:\n> ");
                state = State.FILLING_COFFEE;
                break;
            case FILLING_COFFEE:
                coffee += Integer.parseInt(input);
                System.out.print("Write how many disposable cups of coffee do you want to add:\n> ");
                state = State.FILLING_CUPS;
                break;
            case FILLING_CUPS:
                cups += Integer.parseInt(input);
                setMainState();
                break;
            default:
                break;
        }
    }

    public void setState(String command) {
        switch (command) {
            case "remaining":
                printState();
                setMainState();
                break;
            case "buy":
                System.out.print("What do you want to buy? " +
                        "1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:\n> ");
                state = State.BUYING;
                break;
            case "fill":
                System.out.print("Write how many ml of water do you want to add:\n> ");
                state = State.FILLING_WATER;
                break;
            case "take":
                giveMoney();
                setMainState();
                break;
            case "exit":
                state = State.OFF;
                break;
            default:
                System.out.println("Unexpected action.");
                setMainState();
        }
    }

    private void printState() {
        System.out.printf("The coffee machine has:\n" +
                "%d of water\n" +
                "%d of milk\n" +
                "%d of coffee beans\n" +
                "%d of disposable cups\n" +
                "%d of money\n", water, milk, coffee, cups, money);
    }

    private void setMainState() {
        state = State.MAIN;
        System.out.print("\nWrite action (buy, fill, take, remaining, exit):\n> ");
    }

    private void handleBuying(String input) {
        Drink drink;

        switch (input) {
            case "1":
                drink = Drink.ESPRESSO;
                break;
            case "2":
                drink = Drink.LATTE;
                break;
            case "3":
                drink = Drink.CAPPUCCINO;
                break;
            default:
                System.out.println("Returning to Main menu...");
                state = State.MAIN;
                return;
        }
        makeCoffee(drink);
        acceptPayment(drink.getCost());
    }

    private void makeCoffee(Drink drink) {
        if (water < drink.getWater()) {
            System.out.println("Sorry, not enough water!");
            return;
        }

        if (milk < drink.getMilk()) {
            System.out.println("Sorry, not enough milk!");
            return;
        }

        if (coffee < drink.getCoffee()) {
            System.out.println("Sorry, not enough coffee bean!");
            return;
        }

        if (cups < 1) {
            System.out.println("Sorry, not enough disposable cups!");
            return;
        }

        water -= drink.getWater();
        milk -= drink.getMilk();
        coffee -= drink.getCoffee();
        cups--;

        System.out.println("I have enough resources, making you a coffee!");
    }

    private void acceptPayment(int price) {
        money += price;
    }

    private void giveMoney() {
        System.out.printf("I gave you $%d\n", money);
        money = 0;
    }
}
