import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import javax.swing.*;

public class Exercise33_01Server extends JFrame {
  private static final long serialVersionUID = 1L;

  private JTextArea jta = new JTextArea();

  public static void main(String[] args) {
    new Exercise33_01Server();
  }

  public Exercise33_01Server() {
    setLayout(new BorderLayout());
    add(new JScrollPane(jta), BorderLayout.CENTER);
    setTitle("Server");
    setSize(500, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setVisible(true);

    ServerSocket serverSocket = null;
    try {
      serverSocket = new ServerSocket(8000);
      jta.append("Server started at " + new Date() + '\n');

      Socket socket = serverSocket.accept();

      DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
      DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());

      while (true) {
        double interest = inputFromClient.readDouble();
        int year = inputFromClient.readInt();
        double loanAmount = inputFromClient.readDouble();

        Loan loan = new Loan(interest, year, loanAmount);
        double monthlyPayment = loan.getMonthlyPayment();
        double totalPayment = loan.getTotalPayment();

        outputToClient.writeDouble(monthlyPayment);
        outputToClient.writeDouble(totalPayment);

        jta.append("Annual Interest Rate " + interest + "\n");
        jta.append("Number Of Years " + year + "\n");
        jta.append("Loan Amount " + loanAmount + "\n");
        jta.append("monthlyPayment " + monthlyPayment + "\n");
        jta.append("totalPayment " + totalPayment + "\n");

      }
    } catch (IOException ex) {
      System.err.println(ex);
    }
  }
}