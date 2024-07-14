### SuperHero Times Tables Task 4 User Manual

#### Introduction

SuperHero Times Tables Task 4 is an engaging Java Swing application designed to help children practice and master multiplication tables through an interactive and fun superhero-themed interface. This application allows users to select a multiplicand from a dropdown menu, generates random multiplication problems, and provides instant feedback on answers. It also includes a timer feature to track the duration of each round, enhancing the educational experience by adding an element of time management.

#### How to Run the Program

1. **Ensure Java Development Kit (JDK) is Installed**:

   - Your system needs to have the Java Development Kit installed to compile and execute Java applications.

2. **Open a Command Prompt or Terminal**:

   - Open the command line interface according to your operating system (e.g., Command Prompt on Windows, Terminal on macOS or Linux).

3. **Compile the Java File**:

   - Compile your Java file from the directory that contains the `ExtraCredit` folder. Navigate to this directory in your command line tool, then run the compilation command:

     ```
     bash
     javac ExtraCredit\SuperHeroTTask4.java
     ```

     This command will compile your Java file and generate the corresponding `.class` files inside the `ExtraCredit` folder.

4. **Run the Compiled Program**:

   - Execute the compiled class from the same directory. Because your class is part of a package, you need to include the full package path in the command. 

     ```
     bash
     java ExtraCredit.SuperHeroTTask4
     ```

#### Using the SuperHero Times Tables Task 4 Application

1. **Interface Components**:
   - **Number Selector**: A dropdown menu for selecting the multiplicand for the multiplication problems.
   - **Product Label**: Displays the current multiplication problem.
   - **Answer Field**: Where users type their answers.
   - **Control Button**: Starts the session and progresses through questions.
   - **Time Label**: Displays the elapsed time for the current round.
   - **Rating Label**: Shows the cumulative score after each session.
   - **Superhero Image Label**: Enhances the interface with a superhero image to make the learning process more engaging.
2. **How to Use the Application**:
   - **Select Multiplicand**: Choose a number from the dropdown menu to start with.
   - **Start the Quiz**: Click the "Start" button to begin. The timer starts counting immediately.
   - **Answer Questions**: After each question appears, type your answer in the answer field and click "Next" or press "Enter" to submit.
   - **Receive Feedback**: Instant feedback is provided after each submission. The application updates the elapsed time continuously.
   - **Complete the Session**: After five questions, the session ends. The application displays the total correct answers and the time taken for the session.
3. **Exiting the Application**:
   - Close the application window or terminate the program from the command line to exit.

#### Additional Notes

- The application is designed to provide a structured and enjoyable learning experience, encouraging young learners to improve their multiplication skills effectively.