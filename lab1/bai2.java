
import java.util.Random;
public class bai2 {
    public static void main(String[] args) {
        // ve 1 hình tròn nội tiếp hìnhh vuông cạnh 2r
        // sinh ra 1 triệu điểm ngẫu nhiên và đếm số điểm nằm phía trong đường tròn từ đó có được diện tích tương đối của hình tròn
        // từ diện tích đó sử dụng công thức diện tích để tính ra pi

        int N = 1000000; // Số lượng điểm ngẫu nhiên
        int M = 0; // Số điểm nằm trong đường tròn đơn vị
        Random rand = new Random();// sinh ra 1 số double ngẫu nhiên
        for (int i = 0; i < N; i++) {
            double x = rand.nextDouble() * 2 - 1; // sinh ra x thuộc đoạn [0,1] sau đó nhân 2 vì hình tròn r bán kính [0;0] là từ -1 đến 1
            double y = rand.nextDouble() * 2 - 1; // y ∈ [-1,1]

            if (x * x + y * y <= 1) //đếm số điểm nằm trong hình tròn bằng pytago với 2 cạnh x y
                M++;
        }
        // ta có dien tich hình tròn là pi *1
        //diện tích hình vuông là 4
        //vậy tỉ lệ của hình tròn trên hình vuông lầ pi/4 = m/n
        double pi = (double)M / N * 4  ;
        System.out.println("Xấp xỉ giá trị của pi: " + pi);
    }
}
