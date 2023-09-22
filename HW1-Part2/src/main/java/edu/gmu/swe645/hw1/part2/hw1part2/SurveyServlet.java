package edu.gmu.swe645.hw1.part2.hw1part2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "SurveyServlet", urlPatterns = {"/SurveyServlet"})
public class SurveyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Retrieve form data
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        // Extract other form fields similarly

        // Handle Raffle numbers
        String raffleInput = request.getParameter("raffle");
        String[] raffleNumbers = raffleInput.split(",");
        boolean hasWon = checkForWinningNumber(raffleNumbers);

        // Process other form data and perform any necessary operations

        // Forward to a result JSP page with alert message
        String alertMessage = hasWon ? "Congratulations! You won!" : "Thank you for submitting the survey.";
        request.setAttribute("alertMessage", alertMessage);
        request.getRequestDispatcher("result.jsp").forward(request, response);
    }

    private boolean checkForWinningNumber(String[] raffleNumbers) {
        for (String numberStr : raffleNumbers) {
            try {
                int number = Integer.parseInt(numberStr.trim());
                if (number % 2 == 0) {
                    return true; // User has won
                }
            } catch (NumberFormatException e) {
                // Ignore invalid numbers in the raffle input
            }
        }
        return false; // User did not win
    }
}
