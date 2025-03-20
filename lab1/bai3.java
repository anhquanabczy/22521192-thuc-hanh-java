import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class bai3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap so luong may phat song: ");
        int n = scanner.nextInt();
        int[][] matrix = new int[n][2];
        System.out.println("Nhap toa do cac may phat song:");
        for (int i = 0; i < n; i++) {
            matrix[i][0] = scanner.nextInt();
            matrix[i][1] = scanner.nextInt();
        }

        // Tìm điểm có y nhỏ nhất (nếu bằng nhau thì lấy x lớn hơn)
        int start = 0;
        for (int i = 1; i < n; i++) {
            if (matrix[i][1] < matrix[start][1] || 
                (matrix[i][1] == matrix[start][1] && matrix[i][0] > matrix[start][0])) {
                start = i;
            }
        }
        System.out.println( matrix[start][0] + " " + matrix[start][1]);
        List<Integer> visited = new ArrayList<>(); // Danh sách các điểm đã đi qua
        visited.add(start);
        int current = start;
        int first = start; // Giữ lại điểm đầu tiên để kiểm tra khi quay lại
        int flag = 0;
        while (true) {
            double minAngle = 361; // Góc nhỏ nhất tìm thấy
            int minIndex = -1;
            for (int j = 0; j < n; j++) {
                if (visited.contains(j) || j == current) continue; // Bỏ qua điểm đã đi
                double deltaX = matrix[j][0] - matrix[current][0];
                double deltaY = matrix[j][1] - matrix[current][1];
                double angle = Math.toDegrees(Math.atan2(deltaY, deltaX)); // Tính góc dựa trên công thức hệ số góc
                if (angle > 0 && angle < minAngle) { // Chỉ xét góc bên phải
                    minAngle = angle;
                    minIndex = j;
                }
                else if (angle <= 0 && 360 + angle < minAngle) { // Nếu góc âm thì chuyển sang dương
                    minAngle = 360 + angle;
                    minIndex = j;
                }
            }
            // Kiểm tra góc đến điểm đầu tiên
            if (flag == 1)
            {
                double deltaXStart = matrix[first][0] - matrix[current][0];
                double deltaYStart = matrix[first][1] - matrix[current][1];
                double angleStart = Math.toDegrees(Math.atan2(deltaYStart, deltaXStart));
                if (angleStart >= 0 && angleStart < minAngle) {
                    minAngle = angleStart;
                    minIndex = first;
                }
            }
            if (minIndex == -1) // Không tìm thấy điểm nào
            {
                System.out.println("lỗi");
                break;
            }
            visited.add(minIndex);
            current = minIndex;
            flag = 1;// da co diem dau tien

            if (current == first) {
                System.out.println("Đã quay lại điểm đầu tiên.");
                break;
            }
            System.out.println( matrix[current][0] + " " + matrix[current][1]);
        }
        scanner.close();
    }
}
