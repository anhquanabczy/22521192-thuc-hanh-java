import java.util.Random;
import java.util.Scanner;

public class bai1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập bán kính: ");
        int bankinh = scanner.nextInt();
        // Diện tích hình vuông chứa hình tròn
        double shv = Math.pow(bankinh * 2, 2); 
        int N = 1000000; // Số lượng điểm ngẫu nhiên
        int M = 0; // Số điểm nằm trong hình tròn
        Random rand = new Random();
        for (int i = 0; i < N; i++) {
            // Sinh tọa độ x, y trong phạm vi [-bankinh, bankinh]
            double x = rand.nextDouble() * 2 * bankinh - bankinh; 
            double y = rand.nextDouble() * 2 * bankinh - bankinh;
            // Kiểm tra xem điểm có nằm trong hình tròn hay không
            if (x * x + y * y <= bankinh * bankinh) { 
                M++;
            }
        }
        // Ước lượng diện tích hình tròn
        double stron = shv * ((double) M / N); 
        System.out.println("Diện tích hình tròn xấp xỉ: " + stron);

        scanner.close();
    }
}
