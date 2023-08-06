/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Base.Util;
import java.text.Normalizer;
import java.util.regex.Pattern;
/**
 *
 * @author MSI
 */
public class SlugConverter {
    public static String convertToSlug(String input) {
        // Loại bỏ dấu thanh và dấu đặc biệt
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        String withoutDiacritics = pattern.matcher(normalized).replaceAll("");

        // Thay thế chữ "đ" thành "d"
        String replacedD = withoutDiacritics.replace("đ", "d").replace("Đ", "D");

        // Thay thế các ký tự không phải chữ cái và số bằng dấu gạch ngang
        String slug = replacedD.replaceAll("[^a-zA-Z0-9]+", "-").toLowerCase();

        // Loại bỏ dấu gạch ngang ở đầu và cuối
        slug = slug.replaceAll("^-+|-+$", "");

        return slug;
    }
}
