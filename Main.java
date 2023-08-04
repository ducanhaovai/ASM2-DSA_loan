public class Main {
    private static final int MAX_MESSAGE_LENGTH = 250;

    public static void main(String[] args) {
        Queue messageQueue = new Queue(MAX_MESSAGE_LENGTH);
        Stack messageStack = new Stack(MAX_MESSAGE_LENGTH);

        boolean exit = false;
        java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));

        while (!exit) {
            System.out.println("\n----- Menu -----");
            System.out.println("1. Input message");
            System.out.println("2. Show all messages");
            System.out.println("3. Send Message");
            System.out.println("4. Received message / View message");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = Integer.parseInt(reader.readLine());
            } catch (NumberFormatException | java.io.IOException e) {
                System.out.println("Invalid input. Please enter a valid choice.");
                continue;
            }

            switch (choice) {
                case 1:
                    enqueueStringToQueue(messageQueue, reader);
                    break;
                case 2:
                    showAllMessagesInQueue(messageQueue);
                    break;
                case 3:
                    dequeueToStack(messageQueue, messageStack);
                    break;
                case 4:
                    System.out.println("Messages in the stack:");
                    printMessagesInStack(messageStack);
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        System.out.println("Exiting the program.");
    }

    private static void enqueueStringToQueue(Queue queue, java.io.BufferedReader reader) {
        System.out.print("Enter the string (less than 250 characters): ");
        try {
            String input = reader.readLine();
            if (input.length() <= MAX_MESSAGE_LENGTH) {
                queue.enqueue(input);
                System.out.println("String enqueued successfully!");
            } else {
                System.out.println("String length exceeds 250 characters. Please try again.");
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    private static void showAllMessagesInQueue(Queue queue) {
        if (queue.isEmpty()) {
            System.out.println("Queue is empty. No messages to show.");
            return;
        }

        System.out.println("Messages in the queue:");
        for (int i = 0; i < queue.size(); i++) {
            System.out.println(queue.get(i));
        }
    }

    private static void dequeueToStack(Queue queue, Stack stack) {
        while (!queue.isEmpty()) {
            String message = queue.dequeue();
            stack.push(message);
            System.out.println("Send Success");
        }
    }

    private static void printMessagesInStack(Stack stack) {
        while (!stack.isEmpty()) {
            String message = stack.pop();
            System.out.println("Received message: " + message);
        }
    }

    static class Queue {
        private String[] array;
        private int front;
        private int rear;
        private int capacity;
        private int size;

        public Queue(int capacity) {
            this.capacity = capacity;
            this.array = new String[capacity];
            this.front = 0;
            this.rear = -1;
            this.size = 0;
        }

        public void enqueue(String element) {
            if (size == capacity) {
                System.out.println("Queue is full. Cannot enqueue.");
                return;
            }
            rear = (rear + 1) % capacity;
            array[rear] = element;
            size++;
        }

        public String dequeue() {
            if (isEmpty()) {
                System.out.println("Queue is empty. Cannot dequeue.");
                return null;
            }
            String element = array[front];
            front = (front + 1) % capacity;
            size--;
            return element;
        }

        public String get(int index) {
            if (index < 0 || index >= size) {
                throw new IndexOutOfBoundsException();
            }
            return array[(front + index) % capacity];
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }
    }

    static class Stack {
                private String[] array;
                private int top;
                private int capacity;
                private int size;

                public Stack(int capacity) {
                    this.capacity = capacity;
                    this.array = new String[capacity];
                    this.top = -1;
                    this.size = 0;
                }

                public void push(String element) {
                    if (size == capacity) {
                        System.out.println("Stack is full. Cannot push.");
                        return;
                    }
                    top++;
                    array[top] = element;
                    size++;
                }

                public String pop() {
                    if (isEmpty()) {
                        System.out.println("Stack is empty. Cannot pop.");
                        return null;
                    }
                    String element = array[top];
                    top--;
            size--;
            return element;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }
    }
}
