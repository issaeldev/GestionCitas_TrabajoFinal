/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gestioncitas_trabajofinal.seguridad;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
/**
 *
 * @author Usuario
 */
public class CaptchaGenerator {
    private String captchaText;

    public BufferedImage generateCaptchaImage() {
        int width = 150, height = 50;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();

        Random rand = new Random();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);

        // Texto aleatorio
        captchaText = generateRandomText(5);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.setColor(new Color(rand.nextInt(128), rand.nextInt(128), rand.nextInt(128)));
        g.drawString(captchaText, 25, 35);

        // Líneas de ruido
        g.setStroke(new BasicStroke(2));
        for (int i = 0; i < 3; i++) {
            g.setColor(new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)));
            g.drawLine(rand.nextInt(width), rand.nextInt(height), rand.nextInt(width), rand.nextInt(height));
        }

        g.dispose();
        return image;
    }

    private String generateRandomText(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(rand.nextInt(chars.length())));
        }
        return sb.toString();
    }

    public String getCaptchaText() {
        return captchaText;
    }
}

/**
 *
 * @author Usuario
 */

