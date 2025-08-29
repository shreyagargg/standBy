import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ClockUI extends JFrame {

    private JLabel timeLabel;
    private JLabel animationLabel;
    private JLabel locationLabel;
    private JLabel weatherLabel;

    public ClockUI() {
        // --- 1. Frame Setup ---
        setTitle("Digital Clock");
        setSize(1500, 1200); // Set a suitable size for the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window on the screen
        setResizable(false);

        // --- 2. Main Panel Setup ---
        JPanel mainPanel = new JPanel();
        // Use BoxLayout for vertical arrangement of components
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        // Set the dusty pink background color from the image
        mainPanel.setBackground(new Color(240, 220, 220));
        // Add some padding around the components
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        Font scriptFont = new Font("Script MT Bold", Font.BOLD, 28);
        Font timeFont = new Font("Script MT Bold", Font.BOLD, 120);
        Color textColor = Color.BLACK;

        // --- 4. Create and Style Components ---

        // Animation Label
        animationLabel = new JLabel("Animation 😊");
        animationLabel.setFont(scriptFont);
        animationLabel.setForeground(textColor);
        animationLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center horizontally

        // Time Label
        timeLabel = new JLabel();
        timeLabel.setFont(timeFont);
        timeLabel.setForeground(textColor);
        timeLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center horizontally

        // Location Label
        // Using the location from the context provided
        locationLabel = new JLabel("Delhi, India");
        locationLabel.setFont(scriptFont);
        locationLabel.setForeground(textColor);
        locationLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center horizontally

        // Weather Label (with placeholder data)
        weatherLabel = new JLabel("Weather: Haze, 31°C");
        weatherLabel.setFont(scriptFont);
        weatherLabel.setForeground(textColor);
        weatherLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center horizontally


        // --- 5. Add Components to Panel with Spacing ---
        mainPanel.add(animationLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Add vertical space
        mainPanel.add(timeLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add vertical space
        mainPanel.add(locationLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 5))); // Add vertical space
        mainPanel.add(weatherLabel);


        // --- 6. Add Panel to Frame and Start Clock ---
        add(mainPanel);
        startClock();
    }

    /**
     * Starts a timer to update the time label every second.
     */
    private void startClock() {
        // Define the time format: 12-hour format with AM/PM (e.g., 01:02 am)
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");

        // Use a Swing Timer to update the UI thread safely
        Timer timer = new Timer(1000, _ -> {
            String currentTime = LocalDateTime.now().format(timeFormatter).toLowerCase();
            timeLabel.setText(currentTime);
        });
        timer.start(); // Start the timer
    }

    /**
     * The main entry point for the application.
     */
    public static void main(String[] args) {
        // Ensure the UI is created on the Event Dispatch Thread (EDT) for thread safety
        SwingUtilities.invokeLater(() -> {
            new ClockUI().setVisible(true);
        });
    }
}